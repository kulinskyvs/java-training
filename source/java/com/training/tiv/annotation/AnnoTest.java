package com.training.tiv.annotation;

import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * The annotation is available only in source code <br>.
 * This annotation is discarded by the compiler.
 */
@Retention(RetentionPolicy.SOURCE)
@interface SourceAnnotation {
  String stringValue();
  int intValue();
}

/**
 * The annotation is included into compiled byte code but it not available during runtime;
 */
@Retention(RetentionPolicy.CLASS)
@interface ClassAnnotation {
  String stringValue();
  int intValue();
}

/**
 * The annotation is available during runtime. <br>
 * Indicates nothing -created just for test purposes. <br>
 * Attributes: <br>
 * <ul>
 * <li> stringValue - some string value
 * <li> intValue - some intereger value
 * </ul>
 */
@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAnnotation {
  String stringValue() default "some string value";
  int intValue();
}

/**
 * The annotation is available during runtime. <br>
 * The annotation is an example of "marker" annotation - it means it doesn't have any attributes;
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MarkerRuntimeAnnotation {

}

/**
 * The annotation is available during runtime. <br>
 * Single member annotation, it means that a form @SingleMemberRuntimAnnotation(11) is available.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface SingleMemberRuntimeAnnotation {
  int value() default 111;
}

/**
 * The annotation is available during runtime. <br>
 * Single member annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface SingleMemberRuntimeAnnotation1 {
  int value();
  int intValue() default 1;
}


@SourceAnnotation ( stringValue="sourceClassScope",  intValue = 111)
@ClassAnnotation  ( stringValue="classClassScope",   intValue = 222)
@RuntimeAnnotation( stringValue="runtimeClassScope", intValue = 333)
//@RuntimeAnnotation( intValue = 444)    //it's not possible to use the same annotations several times
@MarkerRuntimeAnnotation
@SingleMemberRuntimeAnnotation(555)
//@SingleMemberRuntimeAnnotation         //it's not possible to use the same annotations several times
//@SingleMemberRuntimeAnnotation1(666)   //it's not possible to use the same annotations several times
@SingleMemberRuntimeAnnotation1(value=777, intValue=888)
public class AnnoTest {
  
  
  @SourceAnnotation ( stringValue="sourceMethodScope",  intValue = 1111)
  @ClassAnnotation  ( stringValue="classMethodScope",   intValue = 2222)
  @RuntimeAnnotation( stringValue="runtimeMethodScope", intValue = 3333)
  //@RuntimeAnnotation( intValue = 444)    //it's not possible to use the same annotations several times
  @MarkerRuntimeAnnotation
  //@SingleMemberRuntimeAnnotation(555)    //it's not possible to use the same annotations several times
  @SingleMemberRuntimeAnnotation           //note that no attriutes is define here since value has default value :)
  @SingleMemberRuntimeAnnotation1(6666)   
  //@SingleMemberRuntimeAnnotation1(value=777, intValue=888)  //it's not possible to use the same annotations several times
  public int methodWithAnnotations(int value) {
    return 0;
  }	
  
  
  public int methodWithoutAnnotations() {
    return 0;
  }
  
  
  @SourceAnnotation ( stringValue="sourceFieldScope",  intValue = 11111)
  @ClassAnnotation  ( stringValue="classFieldScope",   intValue = 22222)
  @RuntimeAnnotation( stringValue="runtimeFieldScope", intValue = 33333)
  @MarkerRuntimeAnnotation
  @SingleMemberRuntimeAnnotation(5555)    
  @SingleMemberRuntimeAnnotation1(66666)   
  public String someField;

  @RuntimeAnnotation( stringValue="runtimeConstructorScope", intValue = 333333)
  @SingleMemberRuntimeAnnotation(555555)
  @SingleMemberRuntimeAnnotation1(value=555, intValue=123)        
  public AnnoTest() {
  }
  
  public AnnoTest(int i) {
  }
  
  
  public static void main(String ...args) throws Exception{
    Class<AnnoTest> clazz = AnnoTest.class;
    System.out.println("class:"+clazz.getName());
    showAnnotations(clazz,0);
    System.out.println("---------------------------------------------------");
    
    //1. dump constructors' annotations
    for (Constructor cons : clazz.getConstructors()) {
     System.out.printf("   constructor:%s: %n",cons); 
     showAnnotations(cons,3);
    }
    System.out.println("---------------------------------------------------");
    
    //2. dump fields' annotations
    for (Field field : clazz.getDeclaredFields()) {
     System.out.printf("   field:%s: %n",field); 
     showAnnotations(field,3);
    }
    System.out.println("---------------------------------------------------");
    
    //3. dump methods' annotations
    for (Method method : clazz.getDeclaredMethods()) {
     System.out.printf("   method:%s: %n",method); 
     showAnnotations(method,3);
    }
    System.out.println("---------------------------------------------------");
    
    
    //4. check annotation
    Constructor<AnnoTest> defaultConstructor = clazz.getConstructor();
    System.out.print("const:"+defaultConstructor);
    if (checkAnnotation(defaultConstructor))
       dumpRuntimeAnnotation(defaultConstructor.getAnnotation(SingleMemberRuntimeAnnotation1.class));
    
    Constructor<AnnoTest> paramConstructor = clazz.getConstructor(int.class);
    System.out.print("const:"+paramConstructor);
    if (checkAnnotation(paramConstructor))
       dumpRuntimeAnnotation(paramConstructor.getAnnotation(SingleMemberRuntimeAnnotation1.class));
    
  }
  
  
  private static void showAnnotations(AnnotatedElement annElement, int indentation) {
    StringBuilder ind = new StringBuilder("");
    for (int i = 0 ; i < indentation ; i++) ind.append(" ");
    
    System.out.printf("%s   %s: %n",ind.toString(),"annotations"); 
    for (Annotation ann : annElement.getAnnotations())
      System.out.printf("%s     %s%n",ind,ann);  
  }
  
  private static boolean checkAnnotation(AnnotatedElement annElement) {
    //check whether an annotation with the given class exists
    boolean isFound = annElement.isAnnotationPresent(SingleMemberRuntimeAnnotation1.class);
    System.out.println(" SingleMemberRuntimeAnnotation1 annotation exists:"+isFound);
    return isFound;
  }
  
  private static void dumpRuntimeAnnotation(SingleMemberRuntimeAnnotation1 ann) {
    System.out.println(" annotation dump [type:"+ann.annotationType().getName()+" ; "+
       "value:"+ann.value()+" ; intValue:"+ann.intValue()+"]");
  }
  
}
