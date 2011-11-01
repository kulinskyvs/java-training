package com.training.tiv.init;

public class FooManipulator {

 static {
   fooSt2 = new Foo("st2");
 }

 //static variables - initialization is performed only a sinlge time
 private static Foo fooSt1 = new Foo("st1");
 private static Foo fooSt2;

 //instance variables - initialization is performed any time a class instance is created
 private Foo foo1 = new Foo("obj1"); 
 private Foo foo2;
 private Foo foo3;

 {
   foo3 = new Foo("obj3");
 } 

 
 public FooManipulator() {
   System.out.println("from constructor----");
   foo2 = new Foo("obj2");
   System.out.println("object construction finished!");
 }

 public static void main(String[] args) {
   System.out.println("from main...");
   new FooManipulator();
   System.out.println("-----------------------------");
   new FooManipulator();
   System.out.println("-----------------------------");

   //note that fooStM1 has package access > it's possible to use this variable here
   Foo f1 = FooManipulator1.fooStM1;
  }

}

/**
  * The class is placed in one source module (FooManipulator.java) - 
  * but the class in compiled in a separate .class file in the package "com.training.tiv.init".
  */
class FooManipulator1 {
  
  static Foo fooStM1 = new Foo("man1St1");

}
