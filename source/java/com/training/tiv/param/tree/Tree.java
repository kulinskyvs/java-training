package com.training.tiv.param.tree;

import java.util.*;

/**
 * A tree is container for trunks
 **/
public class Tree extends LinkedHashSet<Trunk> {

   private Numberable numberableEntity;

   public Tree(int number) {

     numberableEntity = new Numberable(number) {
       protected String getEntityName() {
	 return "tree ";
       }     
     };
   }
   
   public final String toString() {
     StringBuilder res = new StringBuilder();
     res.append( numberableEntity.toString()).
         append("\n").
         append(" contains of the following trunks:").append("\n");
       for (Trunk trunk : this)  {
          res.append("  ").append(trunk).append("\n").
              append("     ").append("contains of the following branches:").append("\n");
              
          for (Branch branch : trunk) {
             res.append("      ").append(branch).append("\n").
                 append("         ").append("contains of the following leafs:").append("\n");
             
             for (Leaf leaf : branch) 
                res.append("         ").append(leaf).append("\n");             
                
          } //for branches
       } // fro trunks
     return res.toString();
   }
      
      
   public static void main(String args[]) {
     Tree tree = new Tree(1);
     Trunk trunk1 = new Trunk(1, 2,3);
     Trunk trunk1Copy = new Trunk(1, 10,100);

     tree.add(trunk1);
     tree.add(trunk1Copy); //note that this object will be skipped during adding to the tree     
     
     tree.add(new Trunk(2, 3,9));
     
     //add new leaf to the first branch of the first trunk
     trunk1.get(0).add(new ComplexLeaf(100));
     trunk1.get(0).add(new ComplexLeaf(101));
     
     System.out.println("-------------------------------------------------------") ;
     System.out.println(tree) ;
     System.out.println("-------------------------------------------------------") ;
     System.out.println(new Tree(5)) ;     
     System.out.println("-------------------------------------------------------") ;
   }
   
}
