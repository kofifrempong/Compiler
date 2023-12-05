

package Assignment3_CS411;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Token {
    public String type;
    public String value;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }
}



    

public class parse extends scanner { 


			static int index;
		     static List<Token> tokens = new ArrayList<>();
		     static String indent;
             static int level = 0;
       

			 
	public static String program = "";
	
    public static void indent(int code){
        for (int i = 0; i < level; i++){
                    if (code == 1){
            program = program.concat("\t\t");
                    }
                    else if (code == 0) {
                                    program = program.concat("\t\t");

                    }
        }
    }
public static void parse() {
        program();
    }

    private static Token match(String expectedType) {
    
        if (index < tokens.size() && tokens.get(index).type.equals(expectedType)) {
            return tokens.get(index++);
        } else {
            error(11, index, expectedType);
            return tokens.get(index++);

        }
    
    	  
    }

    private static void program() {
    	  program =  program.concat("program");
        declarationList();
    }

    private static void declarationList() {

        while (index < tokens.size()) {
            if (tokens.get(index).value.equals("int") || tokens.get(index).value.equals("void")) {

                declaration();
            } else {

          
            		 // Handle syntax error or end of input
                     if (tokens.get(index).type.equals("ID")) {
            	error(8, index, null);
                     
                     }
                break;
            }
        }
    }

    private static void declaration() {

    	if (tokens.get(index).value.equals("int") || tokens.get(index).value.equals("void")) {

            match(tokens.get(index).type);
            if (tokens.get(index).type.equals("ID")) {
                match("ID");
                if (tokens.get(index).type.equals("O_PAREN")) {
                	index--;
                	program = program.concat("\n\tfunc @ line " + lines.get(index));
                    
                    funDeclaration();
                } else {
                index = index -2;
                                	  program =  program.concat("\n\tdeclarations");

                                      
                    varDeclaration();
                }
               
            } else {

error(0, index, null);
                
  
}
        } 
    }

    private static void varDeclaration() {
        typeSpecifier();

          

        program = program.concat("\n");
        indent(0);

        program = program.concat("\t\t\tvar_decl @ line " + lines.get(index));
        

            program = program.concat("\n");
        indent(0);
		  program = program.concat("\t\t\t\ttype [" + tokens.get(index-1).value + "] @ line " + lines.get(index));
          


		  match("ID");
	        if (!tokens.get(index).type.equals("O_BRACK")) {
	            program = program.concat("\n");
	            indent(0);
	        	  program = program.concat("\t\t\t\tnew_id [" + tokens.get(index-1).value + "] @ line " + lines.get(index));
	        }
	        
	        else if (tokens.get(index).type.equals("O_BRACK")) {
	            program = program.concat("\n");
	            indent(0);
	        	  program = program.concat("\t\t\t\tnew_id [" + tokens.get(index-1).value + "[]" +"] @ line " + lines.get(index));
	            match("O_BRACK");
            match("NUM");
            match("C_BRACK");

            
}
        match("SEM_COL");

    }

    private static void typeSpecifier() {
        if (tokens.get(index).value.equals("int") || tokens.get(index).value.equals("void")) {
            match(tokens.get(index).type);
        } else {
        	error(10, index, program);
            // Handle syntax error
        }
    }

    private static void funDeclaration() {
       
    			program = program.concat("\n\t\ttype [" + tokens.get(index-1).value + "] @ line " + lines.get(index));
                
    			program = program.concat("\n\t\tnew_id [" + tokens.get(index).value + "] @ line " + lines.get(index));
                
        
        match("ID");
        match("O_PAREN");
        params();
        match("C_PAREN");
program = program.concat("\n\t\tblock");
        compoundStmt();
    }

    private static void params() {
    	program = program.concat("\n\t\tparameters");
                

    	
        if (tokens.get(index).value.equals("int") || (tokens.get(index).value.equals("void") && !tokens.get(index+1).type.equals("C_PAREN"))) {

            paramList();
        } else if (tokens.get(index).type.equals("C_PAREN")) {
        
            // Empty parameters

        }
        else if (tokens.get(index).value.equals("void") && tokens.get(index+1).type.equals("C_PAREN")) {
        	match(tokens.get(index).type);
        }
        else {
        	error(11,index, null);
            // Handle syntax error
        }
    }

    private static void paramList() {

        param();
        while (tokens.get(index).type.equals("COMMA")) {
            match("COMMA");
            param();
        }
    }

    private static void param() {

        typeSpecifier();

        match("ID");
        program = program.concat("\n\t\t\tvar_param @ line " + lines.get(index));
                


		  program = program.concat("\n\t\t\t\ttype [" + tokens.get(index-2).value + "] @ line " + lines.get(index-2));
                  
	        if (!tokens.get(index).type.equals("O_BRACK")) {
	           
	        	  program = program.concat("\n\t\t\t\tnew_id [" + tokens.get(index-1).value + "] @ line " + lines.get(index));
	        }
	        
	        else if (tokens.get(index).type.equals("O_BRACK")) {
	            
	        	  program = program.concat("\n\t\t\t\tnew_id [" + tokens.get(index-1).value + "[]" +"] @ line " + lines.get(index));
	            match("O_BRACK");
          match("C_BRACK");

          
}
    }

    private static void compoundStmt() {
    	
        match("O_BRACE");
        localDeclarations();
        statementList();
if (index >= tokens.size()-2) {
	
    		return;
    	}
        match("C_BRACE");
        
    }

    private static void localDeclarations() {
           program =  program.concat("\n");
        indent(0);
    	  program =  program.concat("\t\tdeclarations");
                            

        
        while (tokens.get(index).value.equals("int") || tokens.get(index).value.equals("void")) {
            varDeclaration();
        }
    }

    private static void statementList() {
                   program =  program.concat("\n");
         indent(0);
    	program = program.concat("\t\t\tstatements");
                          


        while (index < tokens.size() && !tokens.get(index).value.equals("}")) {
            statement();
        }
    }

    private static void statement() {
    	
        if (tokens.get(index).value.equals("if")) {
             program =  program.concat("\n");
             indent(1);
            program = program.concat("\t\t\t\tif_else @ line " + lines.get(index));
                          

            match(tokens.get(index).type);
            match("O_PAREN");
            expression(1);
            match("C_PAREN");
            level++;

            statement();
            level--;
            if (tokens.get(index).value.equals("else")) {
                match(tokens.get(index).type);

                statement();
            }
        	

            
            
        } else if (tokens.get(index).value.equals("while")) {
             program =  program.concat("\n");
             indent(1);
        	program = program.concat("\t\t\t\twhile @ line " + lines.get(index));
                                      

            match(tokens.get(index).type);
            match("O_PAREN");
            expression(1);
            match("C_PAREN");
            level++;
            statement();
            level--;
        } else if (tokens.get(index).value.equals("return")) {
            match(tokens.get(index).type);
            program = program.concat("\n\t\t\t\treturn @ line " + lines.get(index));
                                      

            if (!tokens.get(index).value.equals(";")) {
                expression(0);
            }

            match("SEM_COL");
        } else if (tokens.get(index).value.equals("{")) {
        	compoundStmt();        	
        }
        
        else {
            expressionStmt();
        }
    }

    private static void expressionStmt() {
        if (tokens.get(index).value.equals(";")) {
            match("SEM_COL");
        } else {
                       program =  program.concat("\n");
                 indent(1);
            program = program.concat("\t\t\t\texprstmt @ line " + lines.get(index));
                                                  


            expression(0);
            
            match("SEM_COL");
        }
    }

    private static void expression(int code) {
    	if (code == 0) {
    	var();
    	}
    	if (code == 1) {
    		
    		index++;
    	}
    	if (code == 2) {
    		simpleExpression();
    	}
    	
    if (tokens.get(index).type == "SEM_COL") {
    return;
    }
    	

            if (tokens.get(index).value.equals("=") && (reloplite() == false)) {
                           program =  program.concat("\n");
                             indent(1);

                program = program.concat("\t\t\t\t\t= @ line " + lines.get(index));
                                                                  


                match("EQUALS");
                if (tokens.get(index).type.equals("NUM") || !tokens.get(index+1).type.equals("SEM_COL") ){
               
                	 expression(2);
                }
                else {
                expression(0);
                }
            } 
    	
    	else {
    		index--;

       


         
            simpleExpression();
        
    	
            }
    	
    }

    private static void var() {

        match("ID");
if (tokens.get(index-1).type == "ID" && tokens.get(index).type != "O_PAREN" &&  tokens.get(index-1).type != "O_BRACK" && tokens.get(index).type != "C_BRACK") {
    
	
	program = program.concat("\n");
    indent(1);
    if (!tokens.get(index).type.equals("O_BRACK")) {
       program = program.concat("\t\t\t\t\tvar [" + tokens.get(index-1).value + "] @ line " + lines.get(index-1));
    }


    else if (tokens.get(index).type.equals("O_BRACK")) {
    	 program = program.concat("\t\t\t\t\tvar [" + tokens.get(index-1).value + "[]" + "] @ line " + lines.get(index-1));
            match("O_BRACK");
            while(!tokens.get(index).type.equals("C_BRACK")) {
            	index++;
            }
            match("C_BRACK");
        }
        
    }
}

    private static void simpleExpression() {
        additiveExpression();

        if (relops() == true) {

            additiveExpression();
        }
    }

    private static void additiveExpression() {
        term();
        while (tokens.get(index).value.equals("+") || tokens.get(index).value.equals("-")) {
                       program =  program.concat("\n");
             indent(1);
            program = program.concat("\t\t\t\t\t" + tokens.get(index).value + " @ line " + lines.get(index-1));
                                                              
            match(tokens.get(index).type);
            term();
        }
    }

    private static void term() {
        factor();
        while (tokens.get(index).value.equals("*") || tokens.get(index).value.equals("/")) {
             program =  program.concat("\n");
             indent(1);
            program = program.concat("\t\t\t\t\t" + tokens.get(index).value + " @ line " + lines.get(index-1));
                                                                          
            match(tokens.get(index).type);
            factor();
        }
    }

    private static void factor() {
        if (tokens.get(index).value.equals("(")) {
            match("O_PAREN");
            expression(0);
            match("C_PAREN");
        } else if (tokens.get(index).type.equals("ID")) {
            match("ID");
            if (tokens.get(index).type.equals("O_PAREN")) {
                 program =  program.concat("\n");
             indent(1);
            	program = program.concat("\t\t\t\t\tfuncall @ line " + lines.get(index-1));
                  
                                                                           
                 program =  program.concat("\n");
             indent(1);
                program = program.concat("\t\t\t\t\t\tvar [" + tokens.get(index-1).value + "] @ line " + lines.get(index-1));
                
                
                                                                             
                call();
            } else if (tokens.get(index).type.equals("O_BRACK")) {
                 program =  program.concat("\n");
             indent(1);
            	program = program.concat("\t\t\t\t\tvar [" + tokens.get(index-1).value + "[]" + "] @ line " + lines.get(index-1));
                
                
                match("O_BRACK");
                while(!tokens.get(index).type.equals("C_BRACK")) {
                	index++;
                }
                match("C_BRACK");
            }
            else {
            	--index;
            	var();

            }
 
        }
        else if (tokens.get(index).type.equals("NUM")) {
            match("NUM");
             program =  program.concat("\n");
             indent(1);
        	program = program.concat("\t\t\t\t\tint [" + tokens.get(index-1).value + "] @ line " + lines.get(index-1));



        } 
       
        else {
        	error(11,index, null);
        }

    }

    private static void call() {
        match("O_PAREN");
        
        args();
        match("C_PAREN");
    }

    private static void args() {
         program =  program.concat("\n");
             indent(1);
    	program = program.concat("\t\t\t\t\targuments");
        
        if (!tokens.get(index).type.equals("C_PAREN")) {
            level++;
            argList();
            level--;
        }
    }

    private static void argList() {
        expression(1);
        while (tokens.get(index).type.equals("COMMA")) {
            match("COMMA");
            expression(1);
        }
    }
    
    
    
    static void error(int e, int line0, String expectedType){
        int index0 = line0;
        line0 = lines.get(line0);
		if (e == 0 )
			program = program.concat("no function or variable type declared at " + line0);
		
		if (e == 1) {
			program = program.concat("improper identifier at " + line0);

		}
		if (e == 2) {
			program = program.concat("improper parameter form " + line0);

		}
		if (e == 3 ) {
			program = program.concat("no brace for block" + line0);
		}
		if (e == 4) {
			program = program.concat("else without if or something else " + line0);

		}
		if (e == 5) {
			program = program.concat("invalid keyword  " + line0);
      program = "\ninvalid keyword " + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;


		}
		if (e == 6) {
			program = program.concat("wrong declaration style  " + line0);
           program = "\nwrong declaration style " + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;
            

		}
		
		if (e == 7) {
			program = program.concat("wrong if style  " + line0);
           program = "\nwrong if style " + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;

		}
		if (e == 8) {
			program = program.concat("Declaration list wrong at token " + tokens.get(index0).value +  " @ line " + line0);
           program = "\nDeclaration list wrong at token " + tokens.get(index0).value +  " @ line "  + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;


		}
		if (e == 9) {
			program = program.concat("no close " + line0);
            program = "\nno close " + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;

		}
		if (e == 10) {
			program = program.concat("wrong type " + line0);
             program = "\nwrong type " + line0 + "\n\n\nIncomplete AST: \n\n\n" + program;
		}
		if (e == 11) {
			program = program.concat("Syntax error at token " + tokens.get(index0).value + " @ line " + line0 + " should be token " + expectedType);
            program = "\nSyntax error at token " + tokens.get(index0).value + " @ line " + line0/* + " should be token " + expectedType*/ + "\n\n\nIncomplete AST: \n\n\n" + program;

            
		
	}
     throw new ArithmeticException();
    
		
		
    
    }
   
    private static boolean reloplite() {
    	String relop = tokens.get(index).value + tokens.get(index+1).value;

    	if (relop.equals("<=") || relop.equals(">=") || relop.equals("==")||relop.equals("!=")) {
    		return true;
    	}
    	else if (tokens.get(index).value == ">" || tokens.get(index).value == "<") {
    		
    			return true;
    	}
    
    			return false;
    }
    private static boolean relops() {
    	String relop = tokens.get(index).value + tokens.get(index+1).value;

    	if (relop.equals("<=") || relop.equals(">=") || relop.equals("==")||relop.equals("!=")) {
                        program = program.concat("\n");
          indent(0);
    		program = program.concat("\t\t\t\t\t" + relop + " @ line " + lines.get(index));
            
    		match(tokens.get(index).type);
    		match(tokens.get(index).type);
          
            
         

    		return true;
    		}
    		else if (tokens.get(index).type == "LESSER" || tokens.get(index).value == "GREATER") {
    		

    			match(tokens.get(index).type);
                       program = program.concat("\n");
          indent(0);
                program = program.concat("\t\t\t\t\t" + tokens.get(index-1).value + " @ line " + lines.get(index));
                            
    			return true;
    	}
    
    			return false;
    		
    	}
    public static void main(String[] args) {
        
    	BufferedReader inputf;
        ArrayList<String> linesArrayList;
    	
    	if (args.length == 0) {
    		System.out.println("Need an input file");
    		System.exit(1);
    	}
    	scanner scanit = new scanner();
	    scanit.main(args);
		lines = scanit.lines;
		token = scanit.token;
		lexeme = scanit.lexeme;
		output = scanit.output;

		 for (int i = 0; i < token.size(); i++) {
             Token tokene = new Token(token.get(i), lexeme.get(i));
             tokens.add(tokene);
         }
		 try {
		parse();
        		System.out.println("\nComplete AST: \n\n" + program);
               


		 }
        catch (IndexOutOfBoundsException e) { System.out.println("\nC minus program doesn't end block with proper delimiters");}
         catch (ArithmeticException err) {
            

           System.out.println(program);

         }
      
		 
    			

    }

}