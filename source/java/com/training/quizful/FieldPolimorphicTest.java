package com.training.quizful;

class ClassA {
	int value = 3;
	
    public void addValue(int x) {
    	this.value += x;
    }
    
	public int getValue() {
		 return value;
	}
	
}

public class FieldPolimorphicTest extends ClassA {
	int value = 8;
	
	public void addValue(int x) {
    	this.value += x;
    }
    
    public static void main(String ...args) {
    	ClassA obj = new FieldPolimorphicTest();
    	obj.addValue(5);
    	System.out.println(obj.value+" "+obj.getValue());
    }
}