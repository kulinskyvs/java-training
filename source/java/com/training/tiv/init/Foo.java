package com.training.tiv.init;

public class Foo {

  private String message;
  
  public Foo() {
	this("null");
   }

  public Foo(String msg) {
    message = msg;
    System.out.println("contructing foo:"+message);
   }

  public void foo() {
    System.out.println("foo("+message+")");
  }

}
