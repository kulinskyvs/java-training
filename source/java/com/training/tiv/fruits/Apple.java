package com.training.tiv.fruits;

public class Apple extends Fruit{
 
   public Apple(String name) {
      super(name);
   }
   
   protected String getType() {
      return "{apple}";
   }
   
   public void someMethodFromApple() {
     System.out.println("   "+this+" from someMethodFromApple()");
   }
}
