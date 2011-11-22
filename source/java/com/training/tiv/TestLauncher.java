package com.training.tiv;

import com.training.tiv.init.*;
import com.training.tiv.exc.*;
import com.training.tiv.collections.*;
import com.training.tiv.param.*;
import com.training.tiv.io.*;
import com.training.tiv.concurrent.*;

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
	}
}