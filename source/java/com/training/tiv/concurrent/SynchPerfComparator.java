package com.training.tiv.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

abstract class SomeTask implements Runnable {

  private static final int ATTEMPTS_NUMBER = 1000;
  
  protected abstract void doTask();
  protected abstract String getName();
  
  public void run () {
    long start = System.nanoTime();
    for (int i = 0 ; i < ATTEMPTS_NUMBER ; i++)
       doTask();
    long delta = System.nanoTime() - start;
    System.out.println("thread ["+getName()+"] finished in "+
       TimeUnit.NANOSECONDS.convert(delta, TimeUnit.SECONDS)) ;
  }
  
  protected void sleep() {
/*    try {
      TimeUnit.MILLISECONDS.sleep(10);
    } catch (InterruptedException ex) {
    } */
    Thread.yield();
  }
}


class SynchMethodTask extends SomeTask {

  protected String getName() {
   return  "method synchronization";
  }

  protected synchronized void doTask() {
    sleep();
  }
}

class SynchCriticalSectionTask extends SomeTask {

  protected String getName() {
   return  "synch critical section";
  }

  protected void doTask() {
    synchronized(this) {
      sleep();
    }
  }
}


class LockCriticalSectionTask extends SomeTask {
  private Lock lock = new ReentrantLock();

  protected String getName() {
   return  "lock critical section";
  }

  protected void doTask() {
    lock.lock();
    try {
      sleep();
    }finally { 
      lock.unlock();
    }
  }
}

public class SynchPerfComparator {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    executor.execute(new SynchMethodTask());
    executor.execute(new SynchCriticalSectionTask());
    executor.execute(new LockCriticalSectionTask());
    executor.shutdown();
  }
}
