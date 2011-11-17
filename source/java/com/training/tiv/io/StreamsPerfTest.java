package com.training.tiv.io;

import java.io.*;
import java.util.*;

public class StreamsPerfTest  {
  
  private final static String FILE_NAME = "/com/training/tiv/io/somefile.txt";
  private final static String TEMP_FILE_NAME = System.getProperty("java.io.tmpdir")+"/tempfile.txt";
  private final static String FILE_CONTENT;
  private final static List<String> FILE_CONTENT_LIST;
  
  static {
    BufferedReader reader = null;
    StringBuilder content = new StringBuilder();
    try {
       reader = new BufferedReader(new InputStreamReader(getFileInputStream()));
       String s = null;
       while( (s = reader.readLine()) != null)
          content.append(s).append("\n");
          
    } catch (Exception ex) {
      print("Unable to load file content:"+ex);
    } finally {
      if (null != reader) {
        try {  reader.close(); } catch(Exception e){}
      }
      
      FILE_CONTENT = content.toString();
    }
    
    FILE_CONTENT_LIST = new ArrayList<String>(Arrays.asList(FILE_CONTENT.split("\n")));
  }
  
  private static InputStream getFileInputStream() {
    return StreamsPerfTest.class.getResourceAsStream(FILE_NAME);
  }
  
  
  private static interface StreamTester {
  
     String getName();
    
     void doTest() throws IOException;
  }
  
  
  private static final StreamTester[] TESTERS = new StreamTester[] {
    new StreamTester() {
       public String getName() {
          return "buffered input stream";
       }
    
       public void doTest() throws IOException {
           InputStream in = new BufferedInputStream(getFileInputStream());
           read(in);
           in.close();
       }
    },  
    
    new StreamTester() {
       public String getName() {
          return "NONbuffered input stream";
       }
    
      public void doTest() throws IOException {
           InputStream in = getFileInputStream();
           read(in);
           in.close();
      }
    },  

    new StreamTester() {
       public String getName() {
          return "buffered output stream";
       }
    
      public void doTest() throws IOException {
         PrintStream out = new PrintStream(
              new BufferedOutputStream(
                 new FileOutputStream(TEMP_FILE_NAME)));
         write(out);
         out.close();
      }
    },    
    
    new StreamTester() {
       public String getName() {
          return "NONbuffered output stream";
       }
    
      public void doTest() throws IOException {
         PrintStream out = new PrintStream(
                 new FileOutputStream(TEMP_FILE_NAME));
         write(out);
         out.close();      
      }
    },
    
    new StreamTester() {
       public String getName() {
          return "buffered reader";
       }
    
      public void doTest() throws IOException {
         Reader in = new BufferedReader(
             new InputStreamReader(getFileInputStream()));
         read(in);
         in.close();    
      }
    },  
    
    new StreamTester() {
       public String getName() {
          return "NONbuffered reader";
       }
    
      public void doTest() throws IOException {
         Reader in = new InputStreamReader(getFileInputStream());
         read(in);
         in.close();      
      }
    },  
    
    new StreamTester() {
       public String getName() {
          return "buffered writer";
       }
    
      public void doTest() throws IOException {
         PrintWriter out = new PrintWriter(
           new BufferedWriter(
              new OutputStreamWriter(
                new FileOutputStream(TEMP_FILE_NAME))) );
         write(out);
         out.close();       
      }
    },    

    
    new StreamTester() {
       public String getName() {
          return "NONbuffered writer";
       }
    
      public void doTest() throws IOException {
         PrintWriter out = new PrintWriter(
              new OutputStreamWriter(
                new FileOutputStream(TEMP_FILE_NAME)) );
         write(out);
         out.close();       
      }
    }
  
  };
  
  private double test(StreamTester tester, int numberOfTests) throws IOException{
    double duration = 0d;
    for (int i = 0 ; i < numberOfTests ; i++) {
       long start = System.nanoTime();
       tester.doTest();
       duration += (double)(System.nanoTime() - start);
    }
    
    return duration/1.0e9;
  }
  
  private static void read(InputStream in) throws IOException {
    int read;
    int var;
    while( (read = in.read()) != -1)
       var = read;
  }
  
  private static void read(Reader in) throws IOException {
    int read;
    int var;
    while( (read = in.read()) != -1)
       var = read;
  }
  
  private static void write(PrintStream out) throws IOException {
    for (String line : FILE_CONTENT_LIST)
       out.println(line);
  }
  
  private static void write(PrintWriter out) throws IOException {
    for (String line : FILE_CONTENT_LIST)
       out.println(line);
  }
  
  
  
  public static void main(String[] args) throws IOException {
    StreamsPerfTest perfTest = new StreamsPerfTest();
    PrintWriter console = new PrintWriter(System.out, true);
  
    print("temp file:"+TEMP_FILE_NAME);
    print("initial file contains "+FILE_CONTENT.length()+" characters");
    print("initial file contains "+FILE_CONTENT_LIST.size()+" rows");
    print("-------------------------------------------");
  
    String formatPattern = "%1$30s : %2$-10.4f s\n";
    print("1. test 10 attempts:");
    for (StreamTester tester : TESTERS) 
       console.printf(formatPattern,tester.getName(),perfTest.test(tester, 10));
    print("-------------------------------------------");
    
    print("2. test 100 attempts:");
    for (StreamTester tester : TESTERS) 
       console.printf(formatPattern,tester.getName(),perfTest.test(tester, 100));
    print("-------------------------------------------");
    
    print("3. test 200 attempts:");
    for (StreamTester tester : TESTERS) 
       console.printf(formatPattern,tester.getName(),perfTest.test(tester, 200));
    print("-------------------------------------------");
  }
  
  private static void print(String message) {
    System.out.println(message);
  }
  
 
}

