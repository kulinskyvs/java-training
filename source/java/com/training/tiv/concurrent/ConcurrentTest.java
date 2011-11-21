package com.training.tiv.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.text.*;

public class ConcurrentTest  {

  private static final TaskPriorityChangingPolicy FIXED_MIN_PRIORITY_POLICY =
     new SimpleTaskPriorityChangingPolicy(Thread.MIN_PRIORITY);
  private static final TaskPriorityChangingPolicy FIXED_NORM_PRIORITY_POLICY =
     new SimpleTaskPriorityChangingPolicy(Thread.NORM_PRIORITY);
  private static final TaskPriorityChangingPolicy FIXED_MAX_PRIORITY_POLICY =
     new SimpleTaskPriorityChangingPolicy(Thread.MAX_PRIORITY);  
 
  private static final DateFormat DATE_FORMAT = 
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS");

  private static void launch(ExecutorService executor, int numberOfTasks) {
     for (int i = 0 ; i < numberOfTasks ; i++)
       executor.execute(new RunnableTask(10));
    executor.shutdown();
  }

  public static void main(String[] args) throws Throwable {
    PrintWriter console = new PrintWriter(System.out);

    print("\n----------------------------------------------------");
    print("1. launch using Thread objects");
    for (int i = 0 ; i < 3 ; i++)
       new Thread(new RunnableTask(5)).start();
    sleep(2);
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("2. launch 3 tasks using executors: Executors.newCachedThreadPool()");
    launch(Executors.newCachedThreadPool(), 5);
    sleep(3);
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("3. launch 5 tasks using executors: Executors.newFixedThreadPool(3)");
    launch(Executors.newFixedThreadPool(3), 5);
    sleep(3);
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("4. launch 3 tasks using executors: Executors.newSingleThreadExecutor()");
    launch(Executors.newSingleThreadExecutor(), 3);
    sleep(4);
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("5. test callable interface");
    ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
    Future runnableFuture = cacheThreadPool.submit(new RunnableTask(10));
    cacheThreadPool.shutdown();
    
    runnableFuture.get();
    print("\nThe message is printed only when the callable thread is finished "+
         " (but there is not explicit Thread.yeild() or Thread.sleep())");
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("6. launch 4 tasks with different priorities: MIN_PRIORITY, NORM_PRIORITY, MAX_PRIORITY, NORM_PRIORITY");
    cacheThreadPool = Executors.newCachedThreadPool();
    cacheThreadPool.execute(new RunnableTask(10, FIXED_MIN_PRIORITY_POLICY) );
    cacheThreadPool.execute(new RunnableTask(10, FIXED_NORM_PRIORITY_POLICY));
    cacheThreadPool.execute(new RunnableTask(10, FIXED_MAX_PRIORITY_POLICY));
    cacheThreadPool.execute(new RunnableTask(10, FIXED_NORM_PRIORITY_POLICY));
    cacheThreadPool.shutdown();
    sleep(4);

    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("7. launch a task with changing priority (MAX, MIN, NORM)");
    cacheThreadPool = Executors.newCachedThreadPool();
    runnableFuture = cacheThreadPool.submit(
       new RunnableTask(10, new SimpleTaskPriorityChangingPolicy(Thread.MAX_PRIORITY, 
                                                                  Thread.MIN_PRIORITY,
                                                                  Thread.NORM_PRIORITY)) );
    cacheThreadPool.shutdown();
    runnableFuture.get();
    
    
    print("\n----------------------------------------------------");
    RunnableTask.reset();
    print("8. launch schedulled task (print beep every 100 millis), terminate after 3 seconds execution");
    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    final ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleAtFixedRate(
        new Runnable() {
           public void run() {
              System.out.print(DATE_FORMAT.format(new Date())+":beep; ");
           }
        },
        0, 100, TimeUnit.MILLISECONDS);
    //wait for 3 seconds
    sleep(3);
    //scheduledFuture.cancel(true);
    scheduledExecutor.shutdown();    
    print("\n----------------------------------------------------");

  }
  
  private static void print(String message) {
    System.out.println(message);
  }
  
  private static void sleep(int secs) throws InterruptedException {
    TimeUnit.SECONDS.sleep(secs);
  }
}
