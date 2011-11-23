package com.training.tiv.boxing;

public class AutoBoxingTest {
   
   private int dumpsQuantity = 0;

   private byte byteValue;
   private short shortValue;
   private int intValue;
   private long longValue;
   private double doubleValue;
   private float floatValue;
     
   //autoboxing during initialization in action
   private Byte byteObj = 1;
   private Short shortObj = 2;
   private Integer intObj = 3;
   private Long longObj = 4L;
   private Float floatObj = 1.5f;
   private Double doubleObj = 1.1d;

  public static void main(String[] args) {
     AutoBoxingTest boxer = new AutoBoxingTest();
     boxer.dump();
     
     //deboxing in action
     boxer.byteValue = boxer.byteObj;
     boxer.shortValue = boxer.shortObj;
     boxer.intValue = boxer.intObj;
     boxer.longValue = boxer.longObj;
     boxer.floatValue = boxer.floatObj;
     boxer.doubleValue = boxer.doubleObj;
     boxer.dump();
     
     //boxer.byteValue = boxer.longObj;                 // !!incompatible types: byte and long
     //boxer.byteValue = (byte)boxer.longObj;           // !!even manual casting can't help here
     boxer.byteValue   = boxer.longObj.byteValue();     // !!only this approach is legal - note no autoboxing is enabled
     //boxer.shortValue = boxer.intObj;                 // !!incompatible types: short and int
     boxer.intValue = boxer.shortObj;                   // upper casting is applied automatically since int type is longer than short
     boxer.longValue = boxer.byteObj;                   // upper casting is applied automatically since int type is longer than short
     boxer.floatValue = boxer.doubleObj.floatValue();
     boxer.doubleValue = boxer.floatObj;
     boxer.dump();
     
     //boxer.byteValue = boxer.floatObj;                 // !!incompatible types: byte and float
     //boxer.byteValue = (byte)boxer.floatObj;           // !!even manual casting can't help here
     boxer.byteValue   = boxer.floatObj.byteValue();     // !!only this approach is legal - note no autoboxing is enabled
     
     boxer.intValue = boxer.doubleObj.intValue();        //only manual unpacking is authorized here
     boxer.longValue = boxer.floatObj.longValue();       //only manual unpacking is authorized here
     boxer.floatValue = boxer.intObj;                    //legal casting
     boxer.doubleValue = boxer.longObj;                  //legal casting  
     boxer.dump();
  }
  
  private void dump() {
    dumpsQuantity++;
    print(dumpsQuantity+". primitive values");  
    print("byte value:"+byteValue);
    print("short value:"+shortValue);
    print("int value:"+intValue);
    print("long value:"+longValue);
    print("float value:"+floatValue);
    print("double value:"+doubleValue);
    print("");
    print(dumpsQuantity+". object value");  
    print("byte object:"+toString(byteObj));
    print("short object:"+toString(shortObj));
    print("int object:"+toString(intObj));
    print("long object:"+toString(longObj));
    print("float object:"+toString(floatObj));
    print("double object:"+toString(doubleObj));
    print("----------------------------------------");      
  }
  
  private static void print(String message) {
    System.out.println(message);
  }
  
  private static String toString(Object obj) {
    return obj.getClass().getName()+"@"+
            obj.hashCode()+":<"+obj.toString()+">";
  }
  
}
