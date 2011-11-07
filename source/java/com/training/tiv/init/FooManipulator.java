package com.training.tiv.init;

public class FooManipulator {

 static {
   fooSt2 = new Foo("ST_2");
 }

 //static variables - initialization is performed only once
 private static Foo fooSt1 = new Foo("ST_1");
 private static Foo fooSt2;

 //instance variables - initialization is performed any time a class instance is created
 private Foo foo1 = new Foo("obj_1"); 
 private Foo foo2;
 private Foo foo3;

 {
   foo3 = new Foo("obj_3");
 } 

 
 public FooManipulator() {
   System.out.println("----from constructor----");
   foo2 = new Foo("obj_2");
   System.out.println("object construction finished!");
 }

 public static void main(String[] args) {
   System.out.println("from main...");
   new FooManipulator();
   System.out.println("-----------------------------");
   new FooManipulator();
   System.out.println("-----------------------------");

   //note that fooStM1 has package access > it's possible to use this variable here
   //Foo f1 = FooManipulator1.fooStM1;

   //check initialization order using inheritance engine
   System.out.println("\n\n-----------------------------");
   System.out.println("-- check inheritance ---");
   new FooManipulator1();
  }

}

/**
  * The class is placed in the same source module (FooManipulator.java) - 
  * but the class in compiled in a separate .class file in the package "com.training.tiv.init".
  */
class FooManipulator1 extends FooManipulator{
  
  static Foo fooStM1 = new Foo("MAN_ST_1");
  
  {
     obj5 = new Foo("MAN_OBJ_5");
  }
  
  private Foo obj4 = new Foo("MAN_OBJ_4");
  private Foo obj5;
  private Foo obj6;
  
  public FooManipulator1() {
     //note that default constuctor of base classes is executed automatically
     System.out.println("----from descendant constructor  ----");
     obj6 = new Foo("MAN_OBJ_6");
     System.out.println("descendant object construction finished!");
  }
}
