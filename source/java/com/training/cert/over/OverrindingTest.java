package cert.over;

public class OverrindingTest {

  //uncomment this method in order to break the compilation
  //public void test() {}
  
  public static void test() {}
  
}

class SubClass extends OverrindingTest {

  public static void test(){}
  
}
