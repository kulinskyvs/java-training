package com.training.tiv.param.tree;

public class Leaf extends Numberable{

   public Leaf(int number) {
      super(number);
   }
   
   protected String getEntityName() {
     return "simple leaf";
   }
   
}
