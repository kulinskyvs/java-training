package com.training.tiv.param;

import com.training.tiv.fruits.*;
import java.util.*;

public class ParametrizationTest {

  //this method is parametrized (please note that the class is not parametrized)
  private static <T> String getParamClassName(T obj) {
     return obj.getClass().getName();
  }
  
  private static <T> T testExtendsMeta(GenericContainer<? extends T> container, T obj) {
    //container.set(obj); // not possible since it's unknown excatly which type is used
    return container.get();
  }
  
  private static <T> T testSuperMeta(GenericContainer<? super T> container, T obj) {
    container.set(obj);
    return (T)container.get();
  }

  public static void main(String args[]) {
  
     ParametrizationTest prTest = new ParametrizationTest();
     print("1. testing erasure feature (the actuall class of a parameter is unknown)");
     print("  ArrayList<String>="+getParamClassName(new ArrayList<String>()));
     print("  ArrayList<Integer>="+getParamClassName(new ArrayList<Integer>()));
     print("  ArrayList<Double>="+getParamClassName(new ArrayList<Double>()));     
     
     GenericContainer<String> stringContainer = new SimpleContainer<String>();
     GenericContainer<? extends Fruit> fruitAppleContainer = new SimpleContainer<Apple>();
     GenericContainer<? extends Fruit> fruitOrangeContainer = new SimpleContainer<Orange>();
     print("  GenericContainer<String>="+getParamClassName(stringContainer));     
     print("  GenericContainer<? extends Fruit>="+getParamClassName(fruitAppleContainer));
     print("  GenericContainer<? extends Apple>="+getParamClassName(fruitOrangeContainer));
     
     print("2. test simple apple container");
     GenericContainer<Apple> appleContainer = new SimpleContainer<Apple>();
     appleContainer.set(new Apple("apple1"));
     Apple app1 = appleContainer.get();
     Fruit frApp1 = appleContainer.get(); //it possible since Apple extends Fruit
     print("  extracted object="+app1);
     appleContainer.set(new Macintosh("mac1"));  //it's possible since Macintosh extends apple
     Macintosh mac = (Macintosh)appleContainer.get(); //type cast is required!!
     print("  extracted object="+mac);
     
     print("3. test extends meta symbol");
     print("  3.1 "+testExtendsMeta(appleContainer, new Apple("app2")) );
     print("  3.2 "+testExtendsMeta(appleContainer, new Macintosh("mac2")) );  //possible since Macintosh extends Apple
     print("  3.3 "+testExtendsMeta(appleContainer, new Orange("orange2")) );
     
     print("4. test super meta symbol");
     print("  3.1 "+testSuperMeta(appleContainer, new Apple("app3")) );
     print("  3.2 "+testSuperMeta(appleContainer, new Macintosh("mac3")) );
     //print("  3.3 "+testSuperMeta(appleContainer, new Orange("orange1")) ); //not possible since Orange is not actuallt an apple    
     
  
  }


  private static void print(Object obj) {
    System.out.println((null != obj ) ? obj : "");
  }
}
