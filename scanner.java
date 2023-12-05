package Assignment3_CS411;

    

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class scanner {

	public static String output = "";
	public static List<String> lexeme = new ArrayList<>();
	public static List<String> token = new ArrayList<>();
	public static List<Integer> lines = new ArrayList<>();


	
	static void delimiterprint(char ch, int line) {
		
		if (ch == '(') {
			lines.add(line);
			output = output.concat(line + ": O_PAREN\n");lexeme.add(Character.toString(ch)); token.add("O_PAREN");} 
			
		if (ch == ')') {		
			lines.add(line);
			output = output.concat(line + ": C_PAREN\n");lexeme.add(Character.toString(ch));
		    token.add("C_PAREN");}
			
		if (ch == ';') {
			lines.add(line);
			output = output.concat(line + ": SEM_COL\n");lexeme.add(Character.toString(ch));
	    token.add("SEM_COL");}
			
		if (ch == '{') {
			lines.add(line);
			output = output.concat(line + ": O_BRACE\n");lexeme.add(Character.toString(ch));
		    token.add("O_BRACE");}
			
		if (ch == '}') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
		    token.add("C_BRACE");
			output = output.concat(line + ": C_BRACE\n");}
			
		if (ch == '[')		{
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("O_BRACK");
			output = output.concat(line + ": O_BRACK\n");
		}
		if (ch == ']')		{
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("C_BRACK");
			output = output.concat(line + ": C_BRACK\n");}
			
		if (ch == ',')	{
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("COMMA");
			output = output.concat(line + ": COMMA\n");}
	}
	static void operatorprint(char ch, int line) {
		
		if (ch == '+') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
		    token.add("PLUS");
			output = output.concat(line + ": PLUS\n");}
			
		if (ch == '-') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("MINUS");
			output = output.concat(line + ": MINUS\n");}
			
		if (ch == '*') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("MULTI");
			output = output.concat(line + ": MULTI\n");}
			
		if (ch == '/') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("DIVIDE");
			output = output.concat(line + ": DIVIDE\n");}
			
		if (ch == '>') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("GREATER");
			output = output.concat(line + ": GREATER\n");}
			
		if (ch == '<') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("LESSER");
			output = output.concat(line + ": LESSER\n");}
			
		if (ch == '=') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("EQUALS");
			output = output.concat(line + ": EQUALS\n");}
			
		if (ch == ':') {
			lines.add(line);
			lexeme.add(Character.toString(ch));
			token.add("COLON");
			output = output.concat(line + ": COLON\n");}
	}
	/**
	 * @param ch
	 * @return 
	 * 
	 */
	public static boolean ifDelimiter(char ch) {
		if(ch == ' ' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ',' || ch == ';' || ch == '>' || ch == '<' || ch == '=' || ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == ';') {
			return true;
		}


		return false;
	}

	public static boolean ifIdentifier(String str) {
		for(int i = 0; i < 10; i++) {
			if(str.charAt(0) == i + '0' || ifDelimiter(str.charAt(0))) {
				return false;
			}
		}
		return true;
	}



	public static boolean ifOperator(char ch) {
		if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '>' || ch == '<' || ch == '=' || ch == ':') {
			return true;
		}


		return false;
	}

	
	
	
		public static boolean ifRealNumber(String str) {
			boolean mark = false;
			int len = str.length();
	
			if(len == 0) {
				return false;
			}
	
			for(int i = 0; i < len; i++) {
				if(str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' && str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5' && str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8' && str.charAt(i) != '9' && str.charAt(i) != '.' && str.charAt(i) != '-') {
					return false;
				}
	
				if(str.charAt(i) == '-' && i >= 1) {
					return false;
				}
	
	
				if(str.charAt(i) == '.') {
					mark = true;
				}
			}
	
	
	
			return mark;
		}



static boolean ifInteger(String str) {
    int len = str.length();
    if (len == 0)
        return false;
    for (int i = 0; i < len; i++) {
        if (!Pattern.matches("[0-9-]", String.valueOf(str.charAt(i)))) {
            return false;
        }
        if (str.charAt(i) == '-' && i >= 1) {
            return false;
        }
    }
    return true;
}

		public static boolean ifKeyword(String str) {
			if(str.equals("if") || str.equals("else") || str.equals("while") || str.equals("do") || str.equals("break") || str.equals("continue") || str.equals("int") || str.equals("double") || str.equals("float") || str.equals("return") || str.equals("char") || str.equals("case") || str.equals("char") || str.equals("sizeof") || str.equals("long") || str.equals("short") || str.equals("typedef") || str.equals("switch") || str.equals("unsigned") || str.equals("void") || str.equals("static") || str.equals("struct") || str.equals("goto")) {
				return true;
			}
	
	
			return false;
		}
	
		
/**
 * @param str
 */
static void scan(String str) {
	String lineadd = null;
	int line = 1;
    int len = str.length();
    for (int p = 0; p < (len); p++) {
		   if (str.charAt(p) == '/') {
			   
	        	
	        	if (str.charAt(p + 1) == '*') {
	        		 int start = p+2;
						while (start < (len)) {
							if (str.charAt(start) == '\n') {
								lineadd = "\n";
								line++;
							}
						if (str.charAt(start) == '*') {
 
							if (str.charAt(start+1) == '/') {
								  str=  str.substring(0, p) + lineadd + str.substring(start+2);
					        		len = str.length();
break;
									
								}
							}
						start++;
						}
						
						
						
						
					}
	        		
	        	} 
		   }
    
    int i = 0, j = 0;
	

    while (i < (len - 1) && j <= i) {
int gauge = 0;
        if (str.charAt(i) == '\n') {
            line++;
			i++;
            j = i;
		
        }
     
    
    if (ifDelimiter(str.charAt(i)) == false)
            i++;
			
        if (ifDelimiter(str.charAt(i)) == true && j == i) {

            if (ifOperator(str.charAt(i)) == true)
			
                operatorprint(str.charAt(i), line);

            else
                delimiterprint(str.charAt(i), line);

            i++;
            j = i;
        } else if ((ifDelimiter(str.charAt(i)) == true && j != i) || (i == len && j != i)) {
            String sstr = str.substring(j, i); 
			if (sstr.charAt(0) == '\n' || sstr.charAt(0) == '\r' || sstr.charAt(0) == '\t' || sstr.charAt(0) == ' ') {
				line++;
				if (sstr.length() < 2) {
					gauge = sstr.length();
				}
				else {
					String ssstr = sstr.substring(1, sstr.length());
sstr = ssstr;
				}
				for (int k = 1; k < sstr.length(); k++) {
					if (!Character.isDigit(sstr.charAt(k)) || !Character.isLetter(sstr.charAt(k)) || !ifDelimiter(sstr.charAt(k))) {
						gauge++;
					
					
				}
			}
		}
            if (gauge != sstr.length()) {
                if (ifKeyword(sstr) == true) {
        			lines.add(line);
                	lexeme.add(sstr);
    		        token.add("Keyword");
                    output = output.concat(line + ": '" + sstr + "'\n");}
                
                if (ifInteger(sstr) == true) {
        			lines.add(line);
                	lexeme.add(sstr);
		        token.add("NUM");
                    output = output.concat(line + ": NUM'" + sstr + "'\n");
            }
                else if (ifRealNumber(sstr) == true) {
        			lines.add(line);
                	lexeme.add(sstr);
		        token.add("NUM");
                    output = output.concat(line + ": NUM '" + sstr + "'\n");}
                
                else if (ifIdentifier(sstr) == true && ifDelimiter(str.charAt(i - 1)) == false) {
                    if (ifKeyword(sstr) != true) {
            			lines.add(line);
                    	lexeme.add(sstr);
        		        token.add("ID");
                        output = output.concat(line + ": ID '" + sstr + "'\n");
                    
                }} else if (ifIdentifier(sstr) == false && ifDelimiter(str.charAt(i - 1)) == false) {
        			lines.add(line);
                	lexeme.add(sstr);
    		        token.add("NOTID");
                    output = output.concat(line + ": NOT ID '" + sstr + "'\n");
                }
            
            }
            j = i;
        }
    }/*
    setOutput(output);
    setToken(token);
    setLexeme(lexeme);
    setLines(lines);
    */return;
}

private  void setLines(ArrayList<Integer> lines2) {
	// TODO Auto-generated method stub
	this.lines = lines2;
	
}
private  void setLexeme(ArrayList<String> lexeme2) {
	// TODO Auto-generated method stub
	this.lexeme = lexeme2;
}
private  void setToken(ArrayList<String> token2) {
	// TODO Auto-generated method stub
	this.token = token2;
	
}
private void setOutput(String newput) {
	this.output = newput;
}


public static void main(String[] args) {
	BufferedReader inputf;
	
	if (args.length == 0) {
		System.out.println("Need an input file");
		System.exit(1);
	}
	try {
		inputf = new BufferedReader(new FileReader(args[0]));
		StringBuilder str = new StringBuilder();
		String line1 = inputf.readLine();
		//int[] l;
		while (line1 != null) {
			str.append(line1);
			str.append('\n');
			line1 = inputf.readLine();
			

		}
		
		scan(str.toString());
		token.add("");
		lexeme.add("");
		lines.add(lines.get(lines.size() - 1));
		

		inputf.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	catch (StringIndexOutOfBoundsException e) {
	}
/* 
	System.out.println("{line_no}: {TOKEN_NAME} '{LEXEME}'");
		 	System.out.println(output);
			System.out.println(token.toString());
			System.out.println(lexeme.toString());
			System.out.println(lines.toString());
			
*/



}

}