package com.training.tiv.param;

import com.training.tiv.fruits.*;
import java.util.*;

public class ParametrizationTest {

  //this method is parametrized (please note that the class is not parametrized)
  private static <T> String getParamClassName(T obj) {
     return obj.getClass().getName();
  }
  
  private static <T> T testWildcardWithExtends(GenericContainer<? extends T> container, T obj) {
    // not possible since it's unknown exactly which type is used
    //container.set(obj); 

    return container.get();
  }
  
  
  private static <T extends Apple> T testExtends(GenericContainer<T> container, T obj) {
    //possible since it's known that at least Apple type is used here
    container.set(obj);
    
    T apple =  container.get();

    //it's possible to invoke a method from Apple class since the T class parameter is limited by Apple
    // any subclass of an Apple can be here > it's safe to invoke someMethodFromApple()
    apple.someMethodFromApple();  

    return apple;
  }
  
  @SuppressWarnings("unchecked") 
  private static <T> T testWildcardWithSuper(GenericContainer<? super T> container, T obj) {
    container.set(obj);
    
    //annotation @SuppressWarnings("unchecked") should be used is order not to display warnings during compilation
    return (T)container.get();
  }
  
  public static void main(String args[]) {
     GenericContainer<Fruit>      fruitContainer  = new SimpleContainer<Fruit>("[fruit container]");
     GenericContainer<Apple>      appleContainer  = new SimpleContainer<Apple>("[apple container]");
     GenericContainer<Macintosh>  macContainer    = new SimpleContainer<Macintosh>("[macintosh container]");
     GenericContainer<Orange>     orangeContainer = new SimpleContainer<Orange>("[orange container]");
     
     Fruit fruit = null;
     Apple apple = null;
     Macintosh mac = null;
     Orange orange = null;
  
     ParametrizationTest prTest = new ParametrizationTest();
     
     print("---------------------------------------------------------");
     print("1. testing erasure feature (the actuall class of a parameter is unknown)");
     print("  ArrayList<String>="+getParamClassName(new ArrayList<String>()));
     print("  ArrayList<Integer>="+getParamClassName(new ArrayList<Integer>()));
     print("  ArrayList<Double>="+getParamClassName(new ArrayList<Double>()));     
     
     GenericContainer<String> stringContainer = new SimpleContainer<String>("[string container]");
     GenericContainer<? extends Fruit> fruitAppleContainer = new SimpleContainer<Apple>("[apple container]");
     GenericContainer<? extends Fruit> fruitOrangeContainer = new SimpleContainer<Orange>("[orange container]");
     print("  GenericContainer<String>="+getParamClassName(stringContainer));     
     print("  GenericContainer<? extends Fruit>="+getParamClassName(fruitAppleContainer));
     print("  GenericContainer<? extends Apple>="+getParamClassName(fruitOrangeContainer));
     
     print("\n---------------------------------------------------------");
     print("2. test adding different object to different parametrized containers");
     /// 2.1 adding objects to fruti container - it's possible to add all the
     print("\n  2.1 adding objects to fruit container");
     fruitContainer.set(new Apple("app21"));
     fruit = fruitContainer.get();                  //type casting is not required since it's known that only Fruits can be stored in this container
     apple = (Apple)fruitContainer.get();           //type casting is required here !!!
     try {
       //it's possible to cast to Orange but ClassCastException will be thrown at runtime
       orange = (Orange)fruitContainer.get(); 
     }catch ( Exception ex) {
        System.out.println("   trying to extract orange:" + ex.getMessage());
     } 
     fruitContainer.set(new Macintosh("mac21"));
     fruit = fruitContainer.get();                  //type casting is not required since it's known that only Fruits can be stored in this container
     apple = (Apple)fruitContainer.get();           //type casting is required here !!!
     mac   = (Macintosh)fruitContainer.get();       //type casting is required here !!!
     fruitContainer.set(new Orange("orange21"));
     
     /// 2.2 adding objects to apple
     print("\n  2.2 adding objects to apple container");
     appleContainer.set(new Apple("apple22"));
     appleContainer.set(new Macintosh("mac22"));
     //appleContainer.set(new Orange("orange22"));          //not possible since Orange is not a subclass of Apple     

     /// 2.3 adding objects to mac container
     print("\n  2.3 adding objects to mac container");
     //macContainer.set(new Apple("apple23"));              //not possible since Apple is not a subclass of Macintosh     
     macContainer.set(new Macintosh("mac23"));
     //macContainer.set(new Orange("orange23"));            //not possible since Orange is not a subclass of Macintosh     
     mac = macContainer.get();

     /// 2.4 adding objects to orange container
     print("\n  2.4 adding objects to orange container");
     //orangeContainer.set(new Apple("apple24"));           //not possible since Apple is not a subclass of Orange     
     //orangeContainer.set(new Macintosh("mac24"));         //not possible since Macintosh is not a subclass of Orange  
     orangeContainer.set(new Orange("orange24"));         
     orange = orangeContainer.get();
     
     print("\n---------------------------------------------------------");
     print("3. testWildcardWithExtends");
     print("  3.1 "+testWildcardWithExtends(appleContainer, new Apple("app3")) );
     print("  3.2 "+testWildcardWithExtends(appleContainer, new Macintosh("mac3")) );  //possible since Macintosh extends Apple
     print("  3.3 "+testWildcardWithExtends(appleContainer, new Orange("orange3")) );  // no sence in this statement

     print("\n---------------------------------------------------------");
     print("4. testExtends");
     testExtends(appleContainer, new Apple("app41"));
     testExtends(appleContainer, new Macintosh("mac41"));        //possible since Macintosh extends Apple
     //testExtends(appleContainer, new Orange("orange41"));      //not possible since Orange is not actually an Apple
     //testExtends(macContainer, new Apple("app42"));            //not possible since only macintosh can be added to macContainer
     //testExtends(orangeContainer,  new Macintosh("mac42"));    //not possible since orangeContainer is not actually a GenericContainer<T extends Apple>
     
     print("\n---------------------------------------------------------");
     print("5. testWildcardWithSuper");
     testWildcardWithSuper(fruitContainer, new Apple("app51"));
     testWildcardWithSuper(fruitContainer, new Macintosh("mac51"));
     testWildcardWithSuper(fruitContainer, new Orange("orange51"));
     testWildcardWithSuper(appleContainer, new Apple("app52"));
     testWildcardWithSuper(appleContainer, new Macintosh("mac52"));
     //testWildcardWithSuper(appleContainer, new Orange("orange52"));   //not possible since Orange is not actually an apple
     //testWildcardWithSuper(macContainer, new Apple("app53"));         //not possible since only Macintosh ana its subclasses can be used
     testWildcardWithSuper(macContainer, new Macintosh("mac53"));
     //testWildcardWithSuper(macContainer, new Orange("orange53"));     //not possible since Orange is not a superclass of Macintosh
     //testWildcardWithSuper(orangeContainer, new Apple("app54"));      //not possible since Apple is not superclass of Orange
     //testWildcardWithSuper(orangeContainer, new Macintosh("mac54"));  //not possible since Macintosh is not a superclass of Orange
     testWildcardWithSuper(orangeContainer, new Orange("orange54"));   
     
     
     print("\n---------------------------------------------------------");
     GenericContainer<? super Macintosh> macSuperContainer = new SimpleContainer<Macintosh>("[macintosh container]");
     macSuperContainer.set(new Macintosh("mac71"));
     macSuperContainer = new SimpleContainer<Apple>("[apple container]");     //it's possible since <? super Macintosh> is used
     try {
        //type cast is required, but runtime exception ClassCastException will be thrown !!! 
        macSuperContainer.set((Macintosh)new Apple("app72"));                    
     } catch (Exception ex) {
        System.out.println("   trying to extract macintosh:" + ex.getMessage());     
     }

     GenericContainer<? super Apple> appleSuperContainer = new SimpleContainer<Apple>("[apple container]");
     appleSuperContainer.set(new Macintosh("mac73"));                           // possible since Macintosh is not a subclass of Apple 
     //appleSuperContainer = new SimpleContainer<Macintosh>("[mac container]");   //not possible since Macintosh is not a superclass of Apple
     
     GenericContainer<? extends Fruit> appleExtendsContainer = new SimpleContainer<Macintosh>("[macintosh container]");
     //not possible since exact type is unknown (could be either Apple or Orange - these types are incompatible)
     //appleExtendsContainer.set(new Macintosh("mac52"));                       
  }


  private static void print(Object obj) {
    System.out.println((null != obj ) ? obj : "");
  }
}
