package com.training.tiv.param.tree;

import java.util.*;

/**
 * A branch is container for leafs 
 **/
public class Branch extends ArrayList<Leaf> {

   private Numberable numberableEntity;

   public Branch(int number, int initialNumberOfLeafs) {

     numberableEntity = new Numberable(number) {
       protected String getEntityName() {
	 return "branch ";
       }     
     };
     
     for (int i = 0 ; i < initialNumberOfLeafs ; i++)
        add(new Leaf(i+1));
   }
   
   public final String toString() {
     return numberableEntity.toString();
   }
      
}
