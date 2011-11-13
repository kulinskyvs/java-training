package com.training.tiv.param.tree;

public class Numberable {

   private int number;
   
   public Numberable(int number) {
      this.number = number;
   }
   
   public int getNumber() {
     return number;
   }
   
   public final String toString() {
     return getEntityName()+" number #"+number;
   }
   
   protected String getEntityName() {
     return "";
   }
   
}
