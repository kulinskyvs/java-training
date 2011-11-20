package com.training.tiv.io;

import java.io.*;
import java.util.*;

interface Dumpable {

  String dump();
  
  boolean dumpEquals(Object obj);
}

class SerializableObject implements Serializable, Dumpable {

   long longValue;
   String stringValue;
   
   SerializableChildObject childObject;

   //transient - the value is not stored with the object
   transient String transientStringValue;
   
   public SerializableObject() throws InterruptedException{
      longValue = System.currentTimeMillis();
      stringValue = new Date().toString(); 
      Thread.sleep(1000);
      childObject = new SerializableChildObject();
      transientStringValue = stringValue+"_nontransient";
   }
   
   public String toString() {
     return super.toString()+"||"+ SerializationTest.toString(childObject);
   } 
   
   public String dump() {
      return "[longValue:"+longValue+", "+
               "stringValue:"+ SerializationTest.toString(stringValue)+", "+
               "childObject:"+ (null != childObject ? childObject.dump() : "NULL")+", "+
               "transientStringValue:"+ SerializationTest.toString(transientStringValue)+" ]";
   }
  
  public boolean dumpEquals(Object obj) {
     SerializableObject obj1 = (SerializableObject)obj;
     return (longValue == obj1.longValue) &&
             (stringValue.equals(obj1.stringValue)) && 
             (childObject.dumpEquals(obj1.childObject));
   }      
}

/**
 * The class contains a reference to an non-serializable object > serialization is not possible for this class
 */
class SerializableObjectWithNonSerField extends SerializableObject {
   //non serializable object reference
   NonSerializableObject nonSerializableObject;
   
   public SerializableObjectWithNonSerField() throws InterruptedException{
      super();
      Thread.sleep(1000);
      nonSerializableObject = new NonSerializableObject();
  }

   public String dump() {
      return "[longValue:"+longValue+", "+
               "stringValue:"+ SerializationTest.toString(stringValue)+", "+
               "childObject:"+ (null != childObject ? childObject.dump() : "NULL")+", "+
               "transientStringValue:"+ SerializationTest.toString(transientStringValue)+", "+
               "nonSerializableObject:"+ (null != nonSerializableObject ? nonSerializableObject.dump() : "NULL")+" ]";
   }
   
  public boolean dumpEquals(Object obj) {
     SerializableObjectWithNonSerField obj1 = (SerializableObjectWithNonSerField)obj;
     return (longValue == obj1.longValue) &&
             stringValue.equals(obj1.stringValue) && 
             childObject.dumpEquals(obj1.childObject) &&
             nonSerializableObject.dumpEquals(obj1.nonSerializableObject);
   }      
   
}


class SerializableChildObject implements Serializable, Dumpable {
   long longValue = System.currentTimeMillis();
   
   public String dump() {
     return new Long(longValue).toString();
   }   
   
   public boolean dumpEquals(Object obj) {
     return longValue == ((SerializableChildObject)obj).longValue;
   }
}

class NonSerializableObject implements Dumpable{
   long longValue = System.currentTimeMillis();
   
   public String dump() {
     return new Long(longValue).toString();
   }
   
   public boolean dumpEquals(Object obj) {
     return longValue == ((NonSerializableObject)obj).longValue;
   }      
}


/**
 * The class implements Externalizable and at the same time Serializable. <br>
 * I wonder which exactly serialization logic will be used? <br>
 * See the results in main test method :)
 */
class ExternalizableObject extends SerializableObject implements Externalizable {
 
   public ExternalizableObject () throws InterruptedException{
      super();
      System.out.println(" !!! Hello from ExternalizableObject.constructor:"+stringValue);
   }
 
   public String dump() {
      return "[longValue:"+longValue+", "+
               "stringValue:"+ SerializationTest.toString(stringValue)+", "+
               "childObject:"+ (null != childObject ? childObject.dump() : "NULL")+", "+
               "transientStringValue:"+ SerializationTest.toString(transientStringValue)+" ]";
   }
  
  public boolean dumpEquals(Object obj)     {
    
     ExternalizableObject obj1 = (ExternalizableObject)obj;
     return (longValue == obj1.longValue) &&
             stringValue.equals(obj1.stringValue) && 
             childObject.dumpEquals(obj1.childObject) &&
             transientStringValue.equals(obj1.transientStringValue);
   } 
   
   public void readExternal(ObjectInput in) 
    throws IOException, ClassNotFoundException{   
      longValue = (Long)in.readObject();
      stringValue = (String)in.readObject();
      childObject = (SerializableChildObject)in.readObject();
      transientStringValue = (String)in.readObject();
   }
   
   public void writeExternal(ObjectOutput out) 
      throws IOException {
      out.writeObject(longValue);
      out.writeObject(stringValue);
      out.writeObject(childObject);
      out.writeObject(transientStringValue);
   }
}


public class SerializationTest {

  public static void main(String[] args) throws Exception {
    PrintWriter console = new PrintWriter(System.out, true);
    String formatPattern = "%1$35s : [%2$s] \n";
    
    print("1. test natural serialization through ObjectInput/ObjectOutput");
    SerializableObject obj = new SerializableObject();
    SerializableChildObject childObj = new SerializableChildObject();
	    
    //store object
    ByteArrayOutputStream outBuf1 = new ByteArrayOutputStream();
    ObjectOutputStream out1 = new ObjectOutputStream(outBuf1);
    out1.writeObject(obj);
    out1.writeObject(childObj);
    
    print("---------- -------------- --------------");
    //load object
    ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(outBuf1.toByteArray()));
    SerializableObject obj1 = (SerializableObject)in1.readObject();
    SerializableChildObject childObj1 = (SerializableChildObject)in1.readObject();
    
    console.printf(formatPattern, "obj", obj);
    console.printf(formatPattern, "obj.dump()", obj.dump());
    console.printf(formatPattern, "obj1", obj1);
    console.printf(formatPattern, "obj1.dump()", obj1.dump());
    console.printf(formatPattern, "obj.equals(obj1)", obj.equals(obj1));
    console.printf(formatPattern, "obj.dumpEquals(obj1)", obj.dumpEquals(obj1));    
    print("---------- -------------- --------------");
    console.printf(formatPattern, "childObj", childObj);
    console.printf(formatPattern, "childObj.dump()", childObj.dump());
    console.printf(formatPattern, "childObj1", childObj1);
    console.printf(formatPattern, "childObj1.dump()", childObj1.dump());
    console.printf(formatPattern, "childObj.equals(childObj1)", childObj.equals(childObj1));
    console.printf(formatPattern, "childObj.dumpEquals(childObj1)", childObj.dumpEquals(childObj1));    
    print("--------------------------------------------------------------");
    
    print("\n2. trying to serialize a serializable object with NON-serializable field reference");
    SerializableObject obj2 = new SerializableObjectWithNonSerField();
    console.printf(formatPattern, "obj2", obj);
    console.printf(formatPattern, "obj2.dump()", obj2.dump());
    try {
	out1.writeObject(obj2);    
    } catch (Throwable ex) {
      System.out.println("\n Ooops... An error occured:"+ex);
    }
    print("--------------------------------------------------------------");
    
    
    print("\n3. test natural externalization through ObjectInput/ObjectOutput");
    outBuf1 = new ByteArrayOutputStream();
    out1 = new ObjectOutputStream(outBuf1);    
    ExternalizableObject ext1 = new ExternalizableObject();
    out1.writeObject(ext1);
    console.printf(formatPattern, "ext1", ext1);
    console.printf(formatPattern, "ext1.dump()", ext1.dump());

    
    in1 = new ObjectInputStream(new ByteArrayInputStream(outBuf1.toByteArray()));
    ExternalizableObject ext2 = (ExternalizableObject)in1.readObject();

    console.printf(formatPattern, "ext2", ext2);
    console.printf(formatPattern, "ext2.dump()", ext2.dump());
    console.printf(formatPattern, "ext1.equals(ext2)", ext1.equals(ext2));
    console.printf(formatPattern, "ext1.dumpEquals(ext2)", ext1.dumpEquals(ext2));
    print("--------------------------------------------------------------");
    
    
    print("\n4. test object reference rstoring from different serialization resources");
    List<SerializableObject> objects = new ArrayList<SerializableObject>(Arrays.asList(
       new SerializableObject(), new SerializableObject(),
       new SerializableObject(), new SerializableObject() )) {
          
          public String toString() {
            StringBuilder res = new StringBuilder();
            for (SerializableObject obj : this)
              res.append(obj.toString()).append("\n");
            return res.toString();
          }
          
          public boolean equals(List<SerializableObject> list) {
            if (null == list || list.size() != this.size()) return false;
            boolean equals = true;
            for (int  i = 0 ; i < size() ; i++)
               equals = equals && (get(i).equals(list.get(i)));
            return equals;
          }
       };

    //serialize
    outBuf1 = new ByteArrayOutputStream();
    ByteArrayOutputStream outBuf2 = new ByteArrayOutputStream();
    
    out1 = new ObjectOutputStream(outBuf1);    
    ObjectOutputStream out2 = new ObjectOutputStream(outBuf2);
    
    out1.writeObject(objects);
    out1.writeObject(objects);
    out2.writeObject(objects);
    
    //deserialize
    in1 = new ObjectInputStream(new ByteArrayInputStream(outBuf1.toByteArray()));
    ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(outBuf2.toByteArray()));
    List objects1 = (List)in1.readObject(),
         objects2 = (List)in1.readObject(),
         objects3 = (List)in2.readObject();
   
    //compare
    console.printf(formatPattern, "objects", objects); 
    console.printf(formatPattern, "objects1", objects1); 
    console.printf(formatPattern, "objects2", objects2); 
    console.printf(formatPattern, "objects3", objects3);
 
    console.printf(formatPattern, "objects.equals(objects1)", objects.equals(objects1));
    console.printf(formatPattern, "objects.equals(objects2)", objects.equals(objects2));        
    console.printf(formatPattern, "objects.equals(objects3)", objects.equals(objects3));                

    // the list are equals = it means that the lists contain the same object instances !!!
    console.printf(formatPattern, "objects1.equals(objects2)", objects1.equals(objects2));        
    
    console.printf(formatPattern, "objects1.equals(objects3)", objects1.equals(objects3));             
    console.printf(formatPattern, "objects2.equals(objects3)", objects2.equals(objects3));
    
  }
  
  
  public static void print(String message) {
    System.out.println(message);
  }
  
  public static String toString(Object obj) {
    return (null != obj) ? obj.toString() : "NULL";
  }
}  

