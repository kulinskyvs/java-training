package com.training.tiv.param.tree;

import java.util.*;

/**
 * A trunk is container for branches 
 **/
public class Trunk extends ArrayList<Branch> {

   private Numberable numberableEntity;

   public Trunk(int number, int initialNumberOfBranches, int initialNumberOfLeafs) {

     numberableEntity = new Numberable(number) {
       protected String getEntityName() {
	 return "trunk ";
       }     
     };
     
     for (int i = 0 ; i < initialNumberOfBranches ; i++) 
       add(new Branch(i+1, initialNumberOfLeafs));
   }
   
   public final String toString() {
     return numberableEntity.toString();
   }
   
   public boolean equals(Object obj) {
     if (!(obj instanceof Trunk)) return false;
     return ((Trunk)obj).numberableEntity.getNumber() == numberableEntity.getNumber();
   }
   
   public int hashCode() {
     return Integer.toString(numberableEntity.getNumber()).hashCode();
   }
}
