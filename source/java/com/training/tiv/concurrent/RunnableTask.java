package com.training.tiv.concurrent;

import java.util.concurrent.*;

public class RunnableTask implements Runnable {

   private static final int DEFAULT_COUNTER = 10;
   private static int taskNumber = 1;
   
   //task counter
   private int initialCounter;
   private int counter;
   
   //task number
   private int id = taskNumber++;

   //thread priority
   private int priority;
   private TaskPriorityChangingPolicy priorityPolicy;
   
   
   public RunnableTask(int counter) {
      this(counter, Thread.NORM_PRIORITY);
   }
   
   
   public RunnableTask(int counter, int priority) {
      this.counter = counter;
      this.initialCounter = this.counter;
      this.priority = priority;
   }
   
   public RunnableTask(int counter, TaskPriorityChangingPolicy priorityPolicy) {
      this(counter, priorityPolicy.getStartPriority());
      this.priorityPolicy = priorityPolicy;
   }
   
   public String toString() {
     return "#"+id+"(counter:"+counter+", priority:"+getThreadPriority()+")";
   }
   
   public void setPriorityPolicy(TaskPriorityChangingPolicy policy) {
     this.priorityPolicy = policy;
   }
   
   public void run() {
     changePriority();
   
     try {
        while (counter-- > 0) {
          System.out.print(" "+this+";");
          TimeUnit.MILLISECONDS.sleep(100);

          //actually the same
          //Thread.yield(); 
          //Thread.sleep(100);
          
          //change the priority
          changePriority();
        }
     } catch (InterruptedException ex) {
        System.out.print(" #"+id+"(interrupted!!!, priority:"+getThreadPriority()+");");
     }
     System.out.print(" #"+id+"(finished!, priority:"+getThreadPriority()+");");
   }
   
   private void changePriority() {
     int targetPriority = priority;
     
     if (null != priorityPolicy) {
       //calculate the priority using the specified policy
       int oneThird = (int)initialCounter / 3;
       int secondThird = (int)(initialCounter / 3)*2;
       
       if (counter <= oneThird) {
         targetPriority =  priorityPolicy.getStartPriority();
       } else if (oneThird < counter && counter <= secondThird) {
         targetPriority = priorityPolicy.getMediumPriority();
       } else {
         targetPriority = priorityPolicy.getEndPriority();
       }
       
     }

     Thread.currentThread().setPriority(targetPriority);     
   }
   
   public static void reset() {
     taskNumber = 1;
   }
   
   private static int getThreadPriority() {
     return Thread.currentThread().getPriority();
   }
}
