package com.training.quizful;

class A
{
    {
        System.out.print("5");
    }
    
    static {
        //System.out.print("3");
    }
    
    public A() {
        System.out.print("4");
    }
}
        
public class Test extends A {
    {
        System.out.print("2");
    }
    
    static {
        //System.out.print("6");
    }
    
    public Test() {
        System.out.print("1");
    }
    
    private static int getHalf(int i){
        return i/2;
    }
    
    public static void main(String[] args) {
        //new Test();
	//System.out.println("\n"+(6^3));
	//System.out.println(getHalf(0));
	
	String d = "beekeeper";
	d.substring(1,7);
	System.out.println(d);
    }
}
