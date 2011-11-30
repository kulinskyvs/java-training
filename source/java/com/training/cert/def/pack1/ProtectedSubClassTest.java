package com.training.cert.def.pack1;

import com.training.cert.def.ProtectedSuperClassTest;

public class ProtectedSubClassTest extends ProtectedSuperClassTest {

    public ProtectedSubClassTest() {
      // note: protected value is available here since the sub class INHERITS its from super class
      System.out.println("from sub:"+getValue());
    }
    
    public static void main(String... args) {
      ProtectedSubClassTest subClassObj = new ProtectedSubClassTest();
      System.out.println("from main:"+subClassObj.getValue());
    
      //uncomment me to get a compiler error since subclass can't
      //treat protected member through a super class reference
      //Only "INHERITANCE access method" is available
      //ProtectedSuperClassTest subClassObj = new ProtectedSubClassTest();
      //System.out.println(" from main:"+subClassObj.getValue());
      
      
      ProtectedSubClassTest subClassObj2 = new ProtectedSubClassTest();
      ProtectedSubClassTest1 test1 = new ProtectedSubClassTest1();
      System.out.println("from main:"+test1.getValue(subClassObj2));
    }
}

class ProtectedSubClassTest1 {

  public int getValue(ProtectedSubClassTest subClassObj) {
    return 5;
    
    //uncomment me to get an exception 
    //!!the cause is that protected menber that is inherited by a subclass has no more "package" visiblity!!!
    //the member if hiddenfor all the classes except otrher subclasses
    //return subClassObj.getValue();
  }
}
