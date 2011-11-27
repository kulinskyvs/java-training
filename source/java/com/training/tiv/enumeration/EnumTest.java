package com.training.tiv.enumeration;

enum EXT_COLOR {
 RED("red"), GREEN("green"), BLUE("blue");
 
 private String colorName;
 
 EXT_COLOR(String colorName) {
   this.colorName = colorName;
   System.out.println("From EXT_COLOR constructor: "+colorName);
 }
 
 public String getColorName() {
   return colorName;
 }
 
 /**
  * Notice, static methods are also available in enumerations!
  */
 public static void dump() {
   for (EXT_COLOR col : EXT_COLOR.values()) 
     System.out.println(col.dumpColor());
 }
 
 public String dumpColor() {
    return "[name:"+name()+", ordinal:"+ordinal()+", colorName:"+getColorName()+"]";
 }
}


class EnumContainer {
  public enum CLASS_COLOR {
    ORANGE, WHITE, YELLOW;
  }
  
  public static enum CLASS_STATIC_COLOR {
    ORANGE, WHITE, YELLOW;
  }
}

public class EnumTest {
  private enum INT_COLOR {
    BLACK, GRAY;
  }
  
  
  public static void main(String... args) {
    EXT_COLOR.dump();    //notice enumeration are initialized before the first usage!!!!

    EXT_COLOR redExtColor = EXT_COLOR.valueOf("RED");
    System.out.println(redExtColor+";"+redExtColor.dumpColor());

    try  {
       EXT_COLOR orangeExtColor = EXT_COLOR.valueOf("OR");
       System.out.println(orangeExtColor+";"+orangeExtColor.dumpColor());
    } catch(IllegalArgumentException ex) {
       System.out.println("Can't find OR color in EXT_COLOR!!!");
    }
    
    System.out.println("-------------------------------------------------");
    //note that the is no difference in access between static class enumerations and object enumerations
    System.out.println("--acessing class handled enumeration through Class--");
    EnumContainer.CLASS_COLOR orangeColor = EnumContainer.CLASS_COLOR.ORANGE;
    System.out.println("orangeColor : "+getEnumDetails(orangeColor));
    EnumContainer.CLASS_COLOR whiteColor = EnumContainer.CLASS_COLOR.WHITE;
    System.out.println("whiteColor : "+getEnumDetails(whiteColor));
    System.out.println("whiteColor.compareTo(orangeColow) : "+whiteColor.compareTo(orangeColor));
    
    EnumContainer.CLASS_STATIC_COLOR yellowStaticColor = EnumContainer.CLASS_STATIC_COLOR.YELLOW;
    System.out.println("yellowStaticColor : "+getEnumDetails(yellowStaticColor));
    //enumeration are parametrized > it's impossible to compare different enumeration between each others
    //System.out.println("whiteColor.compareTo(yellowStaticColor) : "+whiteColor.compareTo(yellowStaticColor));
    
    System.out.println("--acessing class handled enumeration through class instance--");
    System.out.println("  not allowed to access to enumeration though object intance (use Class instead)");
    EnumContainer enumContainerObj = new EnumContainer();
    //orangeColor = enumContainerObj.CLASS_COLOR.ORANGE;   //not allowed to access to enumration though object intance (use Class instead)
    //System.out.println("orangeColor : "+getEnumDetails(orangeColor));
    //not allowed to access to enumration though object intance (use Class instead)
    //CLASS_STATIC_COLOR orangeStaticColor = enumContainerObj.CLASS_STATIC_COLOR.ORANGE;
    //System.out.println("orangeColor : "+getEnumDetails(orangeStaticColor));


    System.out.println("-------------------------------------------------");
    
    //note - it's impossible to access directly the enumeration if it's placed in a class
    // the notation [CLASS].[ENUMERATION] should be use
    //CLASS_COLOR orColor = CLASS_COLOR.ORANGE;
    
    //if it's locally defined enumeration  - then the previous rule is not more relevant
    INT_COLOR blackColor = INT_COLOR.BLACK;
    //INT_COLOR grayColor = GRAY;  //enumeration name is required to get enumeration value
    System.out.println("blackColor : "+getEnumDetails(blackColor));
  }
  
  private static String getEnumDetails(Enum enumeration) {
    return  "[name:"+enumeration.toString()+", ordinal:"+enumeration.ordinal()+"]" ;
  }
}
