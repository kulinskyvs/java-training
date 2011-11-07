package com.training.tiv.exc;

public class TryFinallyTest {

  public void someMethod(int i) {
    try {
      System.out.println(" ---- point="+i+" ----");
      
      if (i == 1) {
        System.out.println("before return");   
        return;
      }
      
      if (i == 2) {
        System.out.println("before throwing NullPointerException");   
        throw new NullPointerException("some exception");
      }
      
      if (i == 3) {
        System.out.println("before throwing IllegalStateException");   
        throw new IllegalStateException();
      }
      
    } catch(IllegalStateException ex) {
       System.out.println("from catch block: before throwing NullPointerException");   
       throw new NullPointerException();
    
    } finally {
      System.out.println("from 'finally' block");
    }
    
    System.out.println("Finished normally");
  }
  
  public static void main(String[] args) {
    TryFinallyTest test = new TryFinallyTest(); 
    for (int i = 0 ; i < 4 ; i++) {
       try {  test.someMethod(i);} catch (Exception ex) {}
    }
  }
}
