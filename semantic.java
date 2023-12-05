package Assignment3_CS411;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;



/*
class TreeNode {
    Map<String, String> data;
    List<TreeNode> children;

    public TreeNode(Map<String, String> data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    // You can add other methods here as needed
}
*/
public class semantic {
 private static Map<String, String> variableTypes = new LinkedHashMap<>();
 private static Map<String, String> paramTypes = new LinkedHashMap<>();

 private static Map<String, int[]> scopes = new HashMap<>();
 private static Map<String, List<String>> localVariables = new LinkedHashMap<>();
 private static Map<String, int[]> arrayVariables = new LinkedHashMap<>();

static int index = 0;
static String[][] trim2DArray;
static List<String> treeList = new ArrayList<>();
static List<Integer> relations = new ArrayList<>();





    static void semanticsearch() {
    	if (trim2DArray[index][0].equals("program")) {
index++;
declarList();
    	}




        
    }

static void declarList() {
while (index < trim2DArray.length) {
    declar();

    index++;

}

}

static void declar() {
	if (trim2DArray[index][0].equals("declarations") ) {

		vardecl();
		
	}
	if (trim2DArray[index][0].equals("func")) {
		funcdecl();

		
	}
	
}
 static void funcdecl() {

	 index++;
if (trim2DArray[index].length > 1) {
		   if (trim2DArray[index][0].equals("type")){
			   index++;
			   
    
	         if (trim2DArray[index].length > 1) {
		   if (trim2DArray[index][0].equals("new_id")){


			   if (variableTypes.containsKey(trim2DArray[index][1])) {
				   
				   error(1);}			   }
			   variableTypes.put(trim2DArray[index][1], trim2DArray[index-1][1]);

			   int[] scop = {0, trim2DArray.length};
			   scopes.put(trim2DArray[index][1], scop);


		}
		}
		   }
	 
	 params();
	 funcdeclarations();
	

	 

	 
	 
}

 private static void params() {
	 String currfunc = trim2DArray[index][1];
	 index++;
	 
	 if (trim2DArray[index][0].equals("parameters")) {
		 index++;
		 int j;
		  for (j = index+1; j < trim2DArray.length; j++ ) {
			
			  if (relations.get(j) == 1) {
				 
				  break;
				  
		  }
		  }
List<String> cfparams = new ArrayList<>();

while (trim2DArray[index][0].equals("var_param")) {
	
index++;

if (trim2DArray[index].length > 1) {

	   if (trim2DArray[index][0].equals("type")){

		   index++;

      if (trim2DArray[index].length > 1) {

	   if (trim2DArray[index][0].equals("new_id")){



 		   if (paramTypes.containsKey(trim2DArray[index][1])) {

			   error(14);}
 		  if (variableTypes.containsKey(trim2DArray[index][1])) {

			   error(1);}
 		  
 

 		  paramTypes.put(trim2DArray[index][1], trim2DArray[index-1][1]);
 		 int[] scop = {index, j};
 		 String s = trim2DArray[index][1];
 		 
 		  if (s.length() > 3) {
			  

			  
			  if (s.substring(s.length()-3, s.length()-1).equals("[]")) {
		   arrayVariables.put('[' + s.substring(1, s.length()-3) +']', scop);
		   cfparams.add(trim2DArray[index-1][1] + "[]");

			  }
			  else {
				   cfparams.add(trim2DArray[index-1][1]);

			  }
		  }
 		 else {
			   cfparams.add(trim2DArray[index-1][1]);

		  }
		    
		   scopes.put(trim2DArray[index][1], scop);


			


	}
	}
	   }
}
index++;

	
}	
paramTypes.clear();

localVariables.put(currfunc, cfparams);		
//System.out.print(cfparams.toString());

}
 }



static void vardecl() {

index++;
if (trim2DArray[index][0].equals("var_decl")) {
	index++;
}

if (trim2DArray[index].length > 1) {

	   if (trim2DArray[index][0].equals("type")){
		   if (!trim2DArray[index][1].equals("[int]")) {
			   error(13);
		   }
		  
		   index++;

      if (trim2DArray[index].length > 1) {

	   if (trim2DArray[index][0].equals("new_id")){


		   if (variableTypes.containsKey(trim2DArray[index][1])) {

			   error(1);}
		   variableTypes.put(trim2DArray[index][1], trim2DArray[index-1][1]);
		   int[] scop = {index, trim2DArray.length};
	 		 String s = trim2DArray[index][1];
	 		 
	 		  if (s.length() > 3) {
				  

				  
				  if (s.substring(s.length()-3, s.length()-1).equals("[]")) {
			   arrayVariables.put('[' + s.substring(1, s.length()-3) +']', scop);
				  }
			  }
			   
		   scopes.put(trim2DArray[index][1], scop);



			


	}
	}
	   }
}
}
 static void funcdeclarations() {

	if (trim2DArray[index][0].equals("block")) {
		index++;
	}
	index++;
	 int j;
	  for (j = index+1; j < trim2DArray.length; j++ ) {
		
		  if (relations.get(j) == 1) {
			 
			  break;
			  
	  }
	  }
	 
	 while (trim2DArray[index][0].equals("var_decl")) {
		 index++;
	 

	 if (trim2DArray[index].length > 1) {

	 	   if (trim2DArray[index][0].equals("type")){
	 		  if (!trim2DArray[index][1].equals("[int]")) {
				   error(13);
			   }

	 		   index++;

	       if (trim2DArray[index].length > 1) {

	 	   if (trim2DArray[index][0].equals("new_id")){

	 		   if (variableTypes.containsKey(trim2DArray[index][1])) {
	 			  // System.out.print(trim2DArray[index][1]);

	 			   error(1);}
	 	   
	 		   else if (scopes.containsKey(trim2DArray[index][1])) {
	 		 int[] currscope = scopes.get(trim2DArray[index][1]);
	 		  
	 		 if (currscope[0] <= index && currscope[currscope.length-1] >= index ) {
	 			 error(2);
	 		
	 		 }
	 	  }
	 		   
	 		 int[] scop = {index, j};
	 		 String s = trim2DArray[index][1];
	 		 
	 		  if (s.length() > 3) {
				  

				  
				  if (s.substring(s.length()-3, s.length()-1).equals("[]")) {
			   arrayVariables.put('[' + s.substring(1, s.length()-3) +']', scop);
				  }
			  }
			   
	 		   scopes.put(trim2DArray[index][1], scop);

	 		

	 			


	 	}
	 	}
	 	   }
	 }
	 
	 index++;
	 }
	 statementlist();
}



 private static void statementlist() {
	 
while (!trim2DArray[index][0].equals("func") && index < trim2DArray.length - 1) {

	statements();


}
index--;

}

static void statements() {

	 index++;
	 if (trim2DArray[index].length > 1) {
	
		}

	 if (trim2DArray[index][0].equals("if_else") || trim2DArray[index][0].equals("while")) {
		 int parentcount = relations.get(index);



		 ifwhilestmt();

		


		 if (trim2DArray[index][0].equals("if_else") || trim2DArray[index][0].equals("while") && (parentcount < relations.get(index))) {
			 index--;

				 statements();
		 }

	 } if (trim2DArray[index][0].equals("exprstmt")) {

		 expressstmt();
		 
		 
	 }
	 
	  if (trim2DArray[index][0].equals("return")) {
		 returnstmt();

		 
	 }
	 

}

 static void returnstmt() {
	 index++;
	 while (trim2DArray[index][0].equals("var") || trim2DArray[index][0].equals("int") ||(trim2DArray[index][0].equals("funcall")) && index < trim2DArray.length -4) {

		 int k = index;
	 if (trim2DArray[index][0].equals("funcall")) {

		 index++;
		 //if (!variableTypes.containsKey(trim2DArray[index][1])) {
			// error(3);
	//	 }
		 index=index+2;
		 int parentcall = relations.get(index-1);

	  	  for (k = index; k < trim2DArray.length - 1; k++ ) {
	  		  
	  		
	  		  if (relations.get(k) <= parentcall) {
	  			 
	  			  break;
	  			  
	  	  }
	  	  }
	  	  funcallcheck(k);
	  	  index = k;
	
		 
		 
	 }
	 
	 
	  while (trim2DArray[index][0].equals("var") || trim2DArray[index][0].equals("int")) {

			
	   	   if (trim2DArray[index][0].equals("var")) {

			   if (!variableTypes.containsKey(trim2DArray[index][1])) {
				   

				 		 
				 
			    if (!scopes.containsKey(trim2DArray[index][1])) {
				   error(3);
				   
			   }
			   else {
				   int[] currscope = scopes.get(trim2DArray[index][1]);
			 		  


			 		 if (!(currscope[0] <= index && currscope[currscope.length-1] >= index )) {
			 			 error(4);
			 		
			 		 }
			 	  }

			   }
	   	   }
	   	   index++;

	   	   if (!trim2DArray[index][0].equals("var") && !trim2DArray[index][0].equals("funcall") && !trim2DArray[index][0].equals("func") && !trim2DArray[index][0].equals("while") && !trim2DArray[index][0].equals("if_else") && !trim2DArray[index][0].equals("return")) {

	      
			   index++;
	   	   }
			
		   
		   
	  

	  }

	}
	  
 }

 static void expressstmt() {
	 index++;
 while ((trim2DArray[index][0].equals("var") || trim2DArray[index][0].equals("int") ||(trim2DArray[index][0].equals("funcall"))) && index < trim2DArray.length -3) {
	 int k = index;
 if (trim2DArray[index][0].equals("funcall")) {
	 index++;
	 index=index+2;
	 int parentcall = relations.get(index-1);

  	  for (k = index; k < trim2DArray.length - 1; k++ ) {
  		  
  		
  		  if (relations.get(k) <= parentcall) {
  			 
  			  break;
  			  
  	  }
  	  }
  	  funcallcheck(k);
  	  index = k;
	 

	 
	 
 }
 
 
  while ((trim2DArray[index][0].equals("var") || trim2DArray[index][0].equals("int")) && index < trim2DArray.length -1) {

		
   	   if (trim2DArray[index][0].equals("var")) {

		   if (!variableTypes.containsKey(trim2DArray[index][1])) {
			   if (index >= k) {
	   			 

			 		 
			 
		    if (!scopes.containsKey(trim2DArray[index][1])) {
		    	
			   error(3);
			   
		   }
		   else {
			   int[] currscope = scopes.get(trim2DArray[index][1]);
		 		  


		 		 if (!(currscope[0] <= index && currscope[currscope.length-1] >= index )) {
		 			 error(4);
		 		
		 		 }
		 	  }

		   }
   	   }
   	   }
   	
   	   index++;
   	
		if (!trim2DArray[index][0].equals("var") && !trim2DArray[index][0].equals("func") && !trim2DArray[index][0].equals("funcall") && !trim2DArray[index][0].equals("while") && !trim2DArray[index][0].equals("if_else") && !trim2DArray[index][0].equals("return")) {
	      
		   index++;
 	   }
		
	   
	   
  

  }

}
 index--;
  

}

 static void ifwhilestmt() {
	       int parentcount = relations.get(index);

	       index++;
	 

	       while (trim2DArray[index][0].equals("var") || trim2DArray[index][0].equals("int")) {
	
	    	   if (trim2DArray[index][0].equals("var")) {
			   if (!variableTypes.containsKey(trim2DArray[index][1])) {
	 			   
	 			
	 			 		 
	 			 
			    if (!scopes.containsKey(trim2DArray[index][1])) {
 				   error(3);
 				   
 			   }
			   else {
				   int[] currscope = scopes.get(trim2DArray[index][1]);
			 		  


			 		 if (!(currscope[0] <= index && currscope[currscope.length-1] >= index )) {
			 			 error(4);
			 		
			 		 }
			 	  }

			   }
	    	   }
	       
			   index = index + 2;
		   
			
		   
		   
	   

 
 
 }
		

	   
	       index--;
	      

	   	 int j;
	   	  for (j = index+1; j < trim2DArray.length; j++ ) {
	   		
	   		  if (relations.get(j) == parentcount) {
	   			 
	   			  break;
	   			  
	   	  }
	   	  }
	   	  
	   	  if (trim2DArray[index][0].equals("declarations")) {
	   		  index++;  

	   	 
	   	 while (trim2DArray[index][0].equals("var_decl")) {
	   		 index++;

	   	 

	   	 if (trim2DArray[index].length > 1) {

	   	 	   if (trim2DArray[index][0].equals("type")){
	   	 		if (!trim2DArray[index][1].equals("[int]")) {
	 			   error(13);
	 		   }

	   	 		   index++;

	   	       if (trim2DArray[index].length > 1) {

	   	 	   if (trim2DArray[index][0].equals("new_id")){

	   	 		   if (variableTypes.containsKey(trim2DArray[index][1])) {

	   	 			   error(1);}
	   	 	   
	   	 	  if (scopes.containsKey(trim2DArray[index][1])) {
	   	 		 int[] currscope = scopes.get(trim2DArray[index][1]);
	   	 		  


	   	 		 if (currscope[0] <= index && currscope[currscope.length-1] >= index ) {
	   	 			 error(2);
	   	 		
	   	 		 }
	   	 	  }
	   	 	 int[] scop = {index, j};
	 		 String s = trim2DArray[index][1];
	 		 
	 		  if (s.length() > 3) {
				  

				  
				  if (s.substring(s.length()-3, s.length()-1).equals("[]")) {
			   arrayVariables.put('[' + s.substring(1, s.length()-3) +']', scop);
				  }
			  }
			   
	   	 		   scopes.put(trim2DArray[index][1], scop);

	   	 		

	   	 			


	   	 	}
	   	 	}
	   	 	   }
	   	 }
	   	 
	   	 index++;
	   	 }
	   	 index++;
 }
	   	  else {
	   		  index--;
	   	  }
 }
 
 private static void funcallcheck(int k) {


		 
				int n = relations.get(k);

				 while ((relations.get(index) > n) && index < trim2DArray.length - 1) {

					 if (trim2DArray[index][0].equals("var")) {

						   if (!variableTypes.containsKey(trim2DArray[index][1])) {
					   			 

							 		 
						    

						    	
							   
						    	if (arrayVariables.containsKey(trim2DArray[index][1])) {


						    	
						    	
						    		
						    		int[] currscope = arrayVariables.get(trim2DArray[index][1]);
			                        

							 		  


							 		 if (!(currscope[0] <= index && currscope[currscope.length-1] >= index )) {

							 			 error(4);
							 		
							 		 }
						    		
						    		
						    	}
						   
						    	else if (!scopes.containsKey(trim2DArray[index][1])) {
						   error(3);
						   }
						   else {
							   int[] currscope = scopes.get(trim2DArray[index][1]);
						 		  


						 		 if (!(currscope[0] <= index && currscope[currscope.length-1] >= index )) {
						 			 error(4);
						 		
						 		 }
						 	  }

						   }
				   	   }
					 index++;
				   	   }
				   	
				 }
				
			
				 
 
 
 private static void functionchack() {
	 int count = 1;
	 for (Map.Entry<String, String> entry : variableTypes.entrySet()) {
		if (count == variableTypes.size()) {
			 if (!entry.getKey().equals("[main]")) {
error(5);			 
}
		}
		count++;
		}
	
	
			 
			
		 
	 for (index = 0; index < trim2DArray.length; index++) {
		 if (trim2DArray[index][0].equals("funcall")) {

				 index++;
				 if (!variableTypes.containsKey(trim2DArray[index][1])) {
					 error(7);
				 }
			 
		 }
	 }
	 
	 
		 
	 
	 for (index = 0; index < trim2DArray.length; index++) {
		 if (trim2DArray[index][0].equals("func")) {
			 index++;
			 String type = trim2DArray[index][1];
			
			 while (!trim2DArray[index][0].equals("func") && index < trim2DArray.length - 1) {
				 index++;
				 
		 }
			 index--;
			 if (type.equals("[void]")) {
				 while (!trim2DArray[index][0].equals("func")) {

					 index--;
					 if (trim2DArray[index][0].equals("return")) {
						 error(9);
					 }
				 }
				 
			 }
             if (type.equals("[int]")) {
            	 while (!trim2DArray[index][0].equals("func")) {
					 index--;
					 if (trim2DArray[index][0].equals("exprstmt")) {
	                     error(10);
	                     break;
	                     }
					 if (trim2DArray[index][0].equals("return") && index < trim2DArray.length - 2) {
						 break;
                     
                     }
					
            	 }
				 
			 }
			 
	 }
	 
	 }
	 
	 
	 
	 for (index = 0; index < trim2DArray.length - 1; index++) {
	
		 if (trim2DArray[index][0].equals("return")) {
			 index++;
			 if (!trim2DArray[index][0].equals("var") && !trim2DArray[index][0].equals("funcall") && !trim2DArray[index][0].equals("int")) {
				 error(11);}
			 }
	 }
	 
 }
 
 private static void funcalltypecheck() {
	 int x;
	 int y;
	 for (index = 0; index < trim2DArray.length; index++) {
		 if (trim2DArray[index][0].equals("funcall")) {
			 y = relations.get(index);
			 index++;

			 if (variableTypes.containsKey(trim2DArray[index][1])) {
				 if (variableTypes.get(trim2DArray[index][1]).equals("[void]")) {
					 if (!trim2DArray[index-2][0].equals("exprstmt")) {
						 error(12);
					 }
					 x = index + 2;
					 while (x < trim2DArray.length - 1) {
						 if (relations.get(x).equals(y)) {
							 error(12);
							 break;
						 }
						 
						 if (relations.get(x) < y) {
							 break;
						 }
						 x++;
					 }
					 
					 
					 
					 
				 }
				/* if (variableTypes.get(trim2DArray[index][1]).equals("[int]")) {
					 if (!trim2DArray[index-2][0].equals("exprstmt")) {
						 
					 }
				 }
				 */
				 

			 }
		 
	 }
		 
	 }
	 
	 String s; 
	 
	 for (index = 0; index < trim2DArray.length; index++) {
		 if (trim2DArray[index][0].equals("funcall")) {
			 List<String> callparams = new ArrayList<>();
			 

			 y = relations.get(index);
			 index++;
			 String typefunc = trim2DArray[index][1];
			 index=index+2;
			 while(relations.get(index) > y && index < trim2DArray.length) {
					 
				 
					 if ((trim2DArray[index][0].equals("+") || trim2DArray[index][0].equals("-") || trim2DArray[index][0].equals("*") || trim2DArray[index][0].equals("/")) &&  relations.get(index) > y) {
				 
						 callparams.add(trim2DArray[index][0]);
						 }
					 
				 
			
				 if (trim2DArray[index][0].equals("int")) {
					 callparams.add("[int]");
					 
				 }
                  if (trim2DArray[index][0].equals("var")) {
                		 s = trim2DArray[index][1];
                	 
                	 if (arrayVariables.containsKey(s)) {
                		  int[] currscope = arrayVariables.get(s);
                		  
	                        




					 		 if (currscope[0] <= index && (currscope[currscope.length-1] >= index )) {

					 			  callparams.add("[int][]");
					 		
					 		 }
				    		
                		  else {
                    		  callparams.add("[int]");
                    	  }
                		  
                	  }
                	  else {
                		  callparams.add("[int]");
                	  }
			 }
                  index++;
                  if (index == trim2DArray.length) {
                	  break;
                  }
			 
		 }
			 List<String> cpnew = new ArrayList<>();
			 for (int r = 0; r < callparams.size(); r++) {
				

				 if (callparams.get(r).equals("[int]") ||callparams.get(r).equals("[int][]")  ) {
					 cpnew.add(callparams.get(r));
				 }


				 else if ((callparams.get(r).equals("-") || callparams.get(r).equals("+") || callparams.get(r).equals("*") || callparams.get(r).equals("/"))) {
					 r++;
			
				
				 }
				 
				 
				 
				 
				
			 }
			 
			 if (!cpnew.equals(localVariables.get(typefunc))){
				 error(15);
				 
			 }
			 /* 
			 System.out.print(typefunc);
			 System.out.print("\ncall params" + callparams.toString() + " ");
			 System.out.print("\ncp new " + cpnew.toString() + " ");
*/
		 
	 }
	 
	 

	 }
	 
	}


		
	


    static int indentcount(String node){
        int total = 0;
        for (int i = 0; i < node.length(); i++) {
          if (Character.isWhitespace(node.charAt(i))) {
            total++;
          } else {
            break;
          }
        }
        return total;


    }
    static int findparent(String node, List<String> treeList, int i){

        if (i == 0) {
            return 0;


        }
        else {
            int c = i;
        while (c>= 1){
            int before = indentcount(treeList.get(c));
            int after = indentcount(node);
            if (before < after){
                break;

            }
            c--;

        }
        return c;
    }


    }

    static void error(int code) {

        if (code == 1) {
            System.out.println("Semantic error at " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " duplicate variable");
        }
        if (code == 2) {
            System.out.println("Semantic error at " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " variable already declared in scope");

        }
        if (code == 3) {
            System.out.println("Semantic error at " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " not global or local variable");

        }
        if (code == 4) {
            System.out.println("Semantic error at " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " variable not declared in scope");

        }
        if (code == 5) {
            System.out.println("Semantic error at last global declaration, not main function");

        }
        if (code == 6) {
            System.out.println("Function around index " + index + " is a int function not returning int");

        }
        if (code == 7) {
            System.out.println("Function call " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " is unknown");

        }
        if (code == 8) {
            System.out.println("Function call " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " has unknown variable");

        }
        if (code == 9) {
            System.out.println("Return statement " + trim2DArray[index][0] + " on line " + trim2DArray[index][3] + " on void function");

        }
        if (code == 10) {
            System.out.println("Last statement " + trim2DArray[index][0] + " on line " + trim2DArray[index][3] + " on int function not return");

        }
        if (code == 11) {
            System.out.println("Empty return on line " + trim2DArray[index-1][3]);

        }
        if (code == 12) {
            System.out.println("Wrong type for function call " + trim2DArray[index][1] + " on line " + trim2DArray[index][4]);

        }
        if (code == 13) {
            System.out.println("Variable of type " + trim2DArray[index][1] + " not of type [int] on line " + trim2DArray[index][4]);

        } 
        if (code == 14) {
            System.out.println("Semantic error at " + trim2DArray[index][1] + " on line " + trim2DArray[index][4] + " duplicate parameters");
        }
        if (code == 15) {
            System.out.println("Mismatched parameters at " + trim2DArray[index-1][0] + " on index " + index);
        }



    }

    public static void main(String[] args) {
        
    parse parser = new parse();
    parser.main(args);
    String tree = parser.program;
 String[] linesArray = tree.split("\n");

   trim2DArray = new String[linesArray.length][10];
   treeList = Arrays.asList(linesArray);

  for (int i = 0; i < linesArray.length; i++) {
    
	  String n = Integer.toString(indentcount(treeList.get(i)));

    trim2DArray[i] = linesArray[i].trim().split("\\s+");


            // System.out.println(Arrays.toString(trim2DArray[i]));

 }
    
    for (int i = 0; i < treeList.size(); i++){
        String node = treeList.get(i);
        int icount = indentcount(node);
        relations.add(icount);
        

    }
          
             semanticsearch();
             functionchack();
             funcalltypecheck();
			 System.out.println("\nGlobal variable types " + variableTypes + "\n");
System.out.println("Most recent global and local variable scopes by index");
for (Entry<String, int[]> entry : scopes.entrySet())  
    System.out.println("Key = " + entry.getKey() + 
                     ", Value = " + Arrays.toString(entry.getValue())); 
System.out.println("\n");
/* 
System.out.println("arrayvar");
for (Entry<String, int[]> entry : arrayVariables.entrySet())  
    System.out.println("Key = " + entry.getKey() + 
                     ", Value = " + Arrays.toString(entry.getValue())); 

System.out.println("\n" + paramTypes + "\n");
 */
System.out.println("Parameters for functions");
for (Entry<String, List<String>> entry : localVariables.entrySet())  
    System.out.println("Key = " + entry.getKey() + 
                     ", Value = " + entry.getValue().toString()); 



    }

	
	
}
