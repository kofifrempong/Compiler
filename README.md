

### to compile while in folder with these files
`javac -d . parse.java scanner.java semantic.java`

### to run, where input.c- is the c minus file
`java Assignment3_CS411.semantic input.c-`


### test cases which can be run after compiling:
## Valid programs
`java Assignment3_CS411.semantic fib.c-`
`java Assignment3_CS411.semantic count.c-`


## Invalid programs
# duplicate varibles
`java Assignment3_CS411.semantic fibduplvar.c-`

# missing function declarations
`java Assignment3_CS411.semantic sortNums.c-`
`java Assignment3_CS411.semantic gcd.c-`

# mismatched parameter types
`java Assignment3_CS411.semantic sortNumspar.c-`

# last function not main
`java Assignment3_CS411.semantic fibnotmain.c-`

# return on void function
`java Assignment3_CS411.semantic fibvoidret.c-`

# variable not declared
`java Assignment3_CS411.semantic fibundecl.c-`

# variable not in scope
`java Assignment3_CS411.semantic fibscope.c-`

# wrong type on function call
`java Assignment3_CS411.semantic gcdcalltype.c-`

# redeclaration in same scope 
`java Assignment3_CS411.semantic fibredecl.c-` 

# parameter redeclaration 
`java Assignment3_CS411.semantic fibparredecl.c-`

# empty return 
`java Assignment3_CS411.semantic fibnoret.c-`

# Incorrect function call
`java Assignment3_CS411.semantic fibnotarray.c-`
