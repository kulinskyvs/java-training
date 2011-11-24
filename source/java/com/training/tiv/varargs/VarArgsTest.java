package com.training.tiv.varargs;

import com.training.tiv.fruits.*;

public class VarArgsTest {

  private static void method(int ...ints) {
    System.out.println("from method(int ...ints)");
  }
  
  private static void method(String stVar, int ...ints) {
    System.out.println("from method(String stVar, int ...ints)");
    int len = ints.length;
    for (int i : ints) 
       System.out.print(i+" ");
    System.out.println("\nlength = "+len+" ; 1st element:"+ints[0]) ;  
  }
  
  private static void method(String ...strings) {
    System.out.println("from method(String ...strings)");
  }
  
  
  //uncomment this method - it won't be able to execute any method with Fruit due to ambiguity
  /*private static void method(Fruit ...fruits) {
    System.out.println("from method(Fruit ...fruits)");
  }*/
  
  private static void method(Fruit fruit, Apple ...apples) {
    System.out.println("from method(Fruit fruit, Apple ...apples)");
  }
  
  //uncomment this method - it won't be able to execute any method with Apple due to ambiguity
  /*private static void method(Apple ...apples) {
    System.out.println("from method(Apple ...apples)");
  }*/
  
  private static void method(Macintosh mac, Apple ...apples) {
    System.out.println("from method(Macintosh mac, Apple ...apples)");
  }
  
  private static void method(Macintosh ...macs) {
    System.out.println("from method(Macintosh ...macs)");
  }
  
  public static void main(String[] args) {
    method(1);
    method(1,2,5);
    method("1",2,5);
    method("12","123");
    method("12","123","1234");
    
    //method();                                         //ambiguity - can't resolve the required method
    //method(new Apple("app1"), new Apple("app2"));     //ambiguity - can't resolve the required method
    method(new Apple("app1"), new Macintosh("mac1"));
    method(new Apple("app1"), new Macintosh("mac1"), new Apple("app2"));
    method(new Macintosh("mac1"), new Macintosh("mac2"), new Apple("app1"));
    
    //ambiguity - can't resolve the required method
    //method(new Macintosh("mac1"), new Macintosh("mac2"), new Macintosh("mac3"));
    
    method(new Macintosh("mac1"), new Macintosh("mac2"), new GoldenDel("gold1"));
  }
}
