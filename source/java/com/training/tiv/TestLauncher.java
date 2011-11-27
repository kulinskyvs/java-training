package com.training.tiv;

import com.training.tiv.init.*;
import com.training.tiv.exc.*;
import com.training.tiv.collections.*;
import com.training.tiv.param.*;
import com.training.tiv.io.*;
import com.training.tiv.concurrent.*;
import com.training.tiv.boxing.*;
import com.training.tiv.varargs.*;
import com.training.tiv.annotation.*;
import com.training.tiv.enumeration.*;

public class TestLauncher {
	public static void main(String[] args) throws Throwable{
		
  	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> initialization tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  FooManipulator.main(args);	

	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> exception handling tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  TryFinallyTest.main(args);	

	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> collections tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  ListsPerfComparator.main(args);	

	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> parametrization tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  ParametrizationTest.main(args);	

	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> io tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  StreamsPerfTest.main(args);	
	  System.out.println("\n\n");
	  SerializationTest.main(args);	

	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> concurrent tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  ConcurrentTest.main(args);	
	  SynchPerfComparator.main(args);
	  
	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> autoboxing tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  AutoBoxingTest.main(args);
	  
	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> varargs tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  VarArgsTest.main(args);
	  
	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> annotation tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  AnnoTest.main(args);
	  
 	  System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>> enumeration tests <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  EnumTest.main(args);								
	  
	}
}
