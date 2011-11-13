package com.training.tiv.param.tree;

public class ComplexLeaf extends Leaf{

   public ComplexLeaf(int number) {
     super(number);
   }
   
   protected String getEntityName() {
     return "complex leaf";
   }
}
