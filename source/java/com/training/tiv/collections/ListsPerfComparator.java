package com.training.tiv.collections;

import java.util.*;

/**
 * The class compares characteristics of different lists' implementations.
 */
public class ListsPerfComparator {

  private static final int ARRAY_SIZE = 30000;

  public static void main(String[] args) {
    //show all enviroment variables
//    for (Map.Entry<String, String> env : System.getenv().entrySet())
//       System.out.println(env.getKey()+"="+env.getValue());
//         System.out.println(env);

    new ListsPerfComparator().launchTest();
  }
  
  private void launchTest() {
  
    List<Integer> valueList = new ArrayList<Integer>(ARRAY_SIZE);
    for (int i = 0 ; i < ARRAY_SIZE; i++) valueList.add(i);
    Collections.shuffle(valueList);     //shuffle initialial collection
    int[] indexes = toArray(valueList);
    

    List<Integer> initialValues = Arrays.asList(new Integer[ARRAY_SIZE]);
    List<Integer> arrayList = new ArrayList<Integer>(initialValues);
    List<Integer> linkedList = new LinkedList<Integer>(initialValues);

    long arrayListTestElapsedTime = 0;
    long linkedListTestElapsedTime = 0;

    print("------------- sequential access --------------");
    arrayListTestElapsedTime = checkSequentialAccess(arrayList);
    linkedListTestElapsedTime = checkSequentialAccess(linkedList);
    print("for ArrayList:"+arrayListTestElapsedTime);
    print("for LinkedList:"+linkedListTestElapsedTime);

    print("------------- random access --------------");
    arrayListTestElapsedTime = checkRandomAccess(arrayList, indexes);
    linkedListTestElapsedTime = checkRandomAccess(linkedList, indexes);
    print("for ArrayList:"+arrayListTestElapsedTime);
    print("for LinkedList:"+linkedListTestElapsedTime);
    
    print("------------- adding to the begining --------------");
    arrayListTestElapsedTime = addToBeginningOfList(new ArrayList<Integer>(initialValues));
    linkedListTestElapsedTime = addToBeginningOfList(new LinkedList<Integer>(initialValues));
    print("for ArrayList:"+arrayListTestElapsedTime);
    print("for LinkedList:"+linkedListTestElapsedTime);
    
    print("------------- adding to the end --------------");
    arrayListTestElapsedTime = addToEndOfList(new ArrayList<Integer>(initialValues));
    linkedListTestElapsedTime = addToEndOfList(new LinkedList<Integer>(initialValues));
    print("for ArrayList:"+arrayListTestElapsedTime);
    print("for LinkedList:"+linkedListTestElapsedTime);
    
    print("------------- adding random order --------------");
    arrayListTestElapsedTime = addToListInAccessOrder(new ArrayList<Integer>(initialValues), indexes);
    linkedListTestElapsedTime = addToListInAccessOrder(new LinkedList<Integer>(initialValues), indexes);
    print("for ArrayList:"+arrayListTestElapsedTime);
    print("for LinkedList:"+linkedListTestElapsedTime);                                    

  }


  private long checkRandomAccess(List<Integer> list, int[] indexes) {
     long now = System.currentTimeMillis();
     for (int i : indexes) list.get(i);
     return System.currentTimeMillis() - now;
  }
  
  private long checkSequentialAccess(List<Integer> list) {
     long now = System.currentTimeMillis();
     for (Iterator it = list.iterator() ; it.hasNext();) it.next();
     return System.currentTimeMillis() - now;
  }
  
  private long addToBeginningOfList(List<Integer> list) {
     long now = System.currentTimeMillis();
     for (int i = 0 ; i < ARRAY_SIZE ; i++) list.add(0, i);
     return System.currentTimeMillis() - now;
  }
  
  private long addToEndOfList(List<Integer> list) {
     long now = System.currentTimeMillis();
     for (int i = 0 ; i < ARRAY_SIZE ; i++) list.add(i);
     return System.currentTimeMillis() - now;
  }
  
  private long addToListInAccessOrder(List<Integer> list, int[] orders) {
     long now = System.currentTimeMillis();
     for (int i : orders) list.add(i, 0);
     return System.currentTimeMillis() - now;
  }  
  
 private void print(Object obj) {
   System.out.println(obj);
 }
 
 private int[] toArray(Collection<Integer> collection) {
    int[] resultArray = new int[collection.size()];
    int index = 0;
    for (Integer value : collection) {
       resultArray[index] =  value;
       index++;
    }
    return resultArray;
 }

}

