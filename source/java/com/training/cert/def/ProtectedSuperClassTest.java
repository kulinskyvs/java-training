package com.training.cert.def;

public class ProtectedSuperClassTest {

    private int value = 10;
    
    public ProtectedSuperClassTest() {
      System.out.println("from super:"+getValue());
    }
    
    protected int getValue() {
       return value;
    }
    
}
