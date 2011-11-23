package com.training.tiv.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.text.*;

interface EvenNumbersGenerator {

  int getNumber();
  
  void next();
}

/**
 * Non safe implementation of number Generator
 */
class NonSafeEvenNumberGenerator implements EvenNumbersGenerator {
  
  private int number;

  public int getNumber() {
    return number;
  }
  
  public void next() {
     number++;
     Thread.yield();
     number++;
  }

}

/**
 * The class seems to be safe, but that is not so. <br>
 * The reason is that number is not refresshed on multiprocessor computers.
 */
class PseudoSafeEvenNumberGenerator implements EvenNumbersGenerator {
  private int number;

  public int getNumber() {
    return number;
  }
  
  public synchronized void next() {
     number++;
     Thread.yield();     
     number++;
  }
}

/**
 * Absolutely safe number generator
 */
class SafeEvenNumberGenerator implements EvenNumbersGenerator {
  private int number;

  public synchronized int getNumber() {
    return number;
  }
  
  public synchronized void next() {
     number++;
     Thread.yield();     
     number++;
  }
}

public class SynchronizationTest implements Runnable{

  private static final int THREADS_SIZE = 10;
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSS");
  
  private static int numberOfErrors = 0;
  
  private static synchronized void notEvenNumberObtained(int number) {
    if (numberOfErrors > 0) return;
    numberOfErrors++;
    System.out.println("\n"+Thread.currentThread().getName()+": not an even number is obtained:"+number);
    System.exit(0); //terminate the program
  }
  
  
  private EvenNumbersGenerator numberGenerator;
  
  public SynchronizationTest(EvenNumbersGenerator numberGenerator) {
    this.numberGenerator = numberGenerator;
  }
  
  
  public void run() {
    while(!Thread.interrupted()) {
    
      numberGenerator.next();
      int number = numberGenerator.getNumber();
      //System.out.println(Thread.currentThread().getName()+":"+number);
      if (number % 2 != 0)  notEvenNumberObtained(number);
      
      Thread.yield();
    } //while loop
  }


  private static EvenNumbersGenerator createGenerator(int generatorId) {
    switch (generatorId) {
      case 1: return new NonSafeEvenNumberGenerator();
      case 2: return new PseudoSafeEvenNumberGenerator();
      case 3: return new SafeEvenNumberGenerator();
      default:
         throw new IllegalArgumentException("Unknown generator id:"+generatorId);
    }
  }


  public static void main(String[] args) throws IOException{
    System.out.println(" Select which generator should be tested:");
    System.out.println("   1. Non thread safe even numbers generator (no synchronization at all);");
    System.out.println("   2. Pseudo thread safe even numbers generator (next() method in synchronized, althouh volatile is NOT used);");
    System.out.println("   3. Thread safe even numbers generator (next() method in synchronized, althouh volatile is used);");
    System.out.print(">");
    
   
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int selectedVariant = 0; 
    try {
       selectedVariant = Integer.parseInt(in.readLine());
    } catch (NumberFormatException ex) {
      System.out.println("Please type one of the mentioned values (1-3)");
      return;
    }
    
    final EvenNumbersGenerator numberGenerator = createGenerator(selectedVariant);
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0 ; i < THREADS_SIZE ; i++) 
      executor.execute(new SynchronizationTest(numberGenerator));
    executor.shutdown();
    
    System.out.println("Print Ctrl+C to terminate the program.");

    //schedule task to print status each 10 seconds
    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    scheduledExecutor.scheduleAtFixedRate(
        new Runnable() {
           public void run() {
              System.out.println(DATE_FORMAT.format(new Date())+":"+numberGenerator.getNumber()+"; ");
           }
        },
        0, 10, TimeUnit.SECONDS);
  }
  
}
