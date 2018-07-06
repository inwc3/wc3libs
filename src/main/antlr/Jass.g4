// Define a grammar called Jass
grammar Jass;

options {
	language = Java;
}

/*@header {
	package net.moonlightflower.wc3libs.antlr;
}*/

BOOL_LITERAL:
	'true' | 'false' ;
INT_LITERAL:
		ID_INT_LITERAL
	|
		DEC_INT_LITERAL
	|
		OCT_INT_LITERAL
	|
		HEX_INT_LITERAL
	;
DEC_INT_LITERAL:
	[1-9] [0-9]* | [0-9] ;
OCT_INT_LITERAL:
	'0' [0-7]* ;
HEX_INT_LITERAL:
	('0' [xX] | '$') [0-9a-fA-F]+ ;
ID_INT_LITERAL:
	'\'' ([\u0000-\u0026\u0028-\u00FF]+) '\'' ;
REAL_LITERAL:
	[0-9]* '.' [0-9]+ | [0-9]+ '.' ;
STRING_LITERAL:
	'"' ( EscapeSequence | ~('\\'|'"'|'\r'|'\n') | NEW_LINE )* '"';

COMMENT_SINGLE:
    '//' (~('\n'|'\r'))*;

COMMENT_BLOCK:
    '/*' (~('*') | ('*' + ~('/')))* '*/';

fragment EscapeSequence: '\\' [abfnrtvz"'\\];

ID:
	ID_START (ID_TAIL)* ;
fragment ID_START:
	[A-Za-z] ;
fragment ID_TAIL:
	[A-Z] | [a-z] | [0-9] | '_' ;

CURLY_L:
	'{' ;
CURLY_R:
	'}' ;
COMMA:
	',' ;
NEW_LINE:
	('\r\n' | '\n')+ ;
WS:
	(' ' | '\t')+ ;

root:
	(WS | NEW_LINE)*
	(typeDec WS? NEW_LINE (WS | NEW_LINE)*)*
	(WS | NEW_LINE)*
	(nativeDec WS? NEW_LINE (WS | NEW_LINE)*)*
	WS? globalsBlock WS? NEW_LINE
	(WS | NEW_LINE)*
	(nativeDec WS? (NEW_LINE | EOF) (WS | NEW_LINE)*)*
	(WS | NEW_LINE)*
	(func WS? NEW_LINE (WS | NEW_LINE)*)*
	(NEW_LINE | WS)* ;

globalsBlock:
	'globals' WS? NEW_LINE
	(WS | NEW_LINE)*
	(globalDec WS? NEW_LINE (WS | NEW_LINE)*)*
	(WS | NEW_LINE)*
	'endglobals'
	;

varName:
	ID ;
funcName:
	ID ;

globalDec:
		type=typeName
		WS
		'array'
		WS
		name=varName
	|
		('constant' WS)?
		type=typeName 
		WS
		name=varName 
		(WS? '=' WS? val=expr)?
	;

surroundedExpr:
	'(' WS? expr WS? ')' ;
expr:
		expr WS ('and' | 'or') WS expr
	|
		'(' WS? expr WS? ')' WS? ('and' | 'or') WS? '(' WS? expr WS? ')'
	|
		expr WS ('and' | 'or') WS? '(' WS? expr WS? ')'
	|
		'(' WS? expr WS? ')' WS? ('and' | 'or') WS expr
	|
		'not' (WS expr | '(' WS? expr WS? ')')
	|
		literal
	|
		expr WS? ('+' | '-' | '*' | '/') WS? expr
	|
		('+' | '-') WS? expr
	|
		expr WS? ('<' | '<=' | '==' | '!=' | '>' | '>=') WS? expr
	|
		funcExpr
	|
		varName
	|
		arrayRead
	|
		funcRef
	|
		'(' WS? expr WS? ')'
	;

//arithExpr:
	//expr WS? ('+' | '-' | '*' | '/') WS? expr | ('+' | '-') WS? expr ;
//compExpr:
	//expr WS? ('<' | '<=' | '==' | '!=' | '>' | '>=') WS? expr ;
//boolExpr:
	//expr WS ('and' | 'or') WS expr | 'not' WS expr ;
funcExpr:
	funcName WS? '(' WS? arg_list WS? ')' ;
arg_list:
	(expr (WS? ',' WS? expr)*)? ;
arrayRead:
	varName '[' expr ']' ;
funcRef:
	'function' WS funcName ;

literal:
	BOOL_LITERAL | INT_LITERAL | REAL_LITERAL | STRING_LITERAL | funcRef ;

localVarDec:
	'local'
	(
			(
				WS 
				typeName 
				WS 
				'array'
				WS
				name=varName
			)
		|
			(
				WS 
				typeName
				WS 
				name=varName
				(WS? '=' WS? expr)?
			)
	)
	;
localVarDec_list:
	localVarDec (WS? NEW_LINE (NEW_LINE | WS)* localVarDec)* ;

statement2:
		callFunc 
	|
		selection
	;
statement:
		callFunc 
	|
		setVar
	|
		selection
	|
		loop
	|
		exitwhen
	|
		rule_return
	;
statement_list:
	statement (WS? NEW_LINE (NEW_LINE | WS)* statement)* ;

callFunc:
	'call'
	WS
	funcExpr
	;
setVar: 
	'set' 
		WS
		name=varName
		(WS? '[' index=expr ']')?
		WS?
		'='
		WS?
		val=expr 
	;
condition:
	(surroundedExpr | expr)
	;
selection2: 
	'if' WS? condition WS? 'then' WS? NEW_LINE
		(WS | NEW_LINE)*
	'endif'
	;
selection: 
	'if' WS? condition WS? 'then' WS? NEW_LINE
		(WS | NEW_LINE)*
		statement_list?
		(WS | NEW_LINE)*
		('elseif' (surroundedExpr | (WS expr)) WS? 'then' WS? NEW_LINE
			(WS | NEW_LINE)*
			statement_list?
			(WS | NEW_LINE)*
		)*
		('else' WS? NEW_LINE
			(WS | NEW_LINE)*
			statement_list?
			(WS | NEW_LINE)*
		)?
		(WS | NEW_LINE)*
	'endif'
	;
loop:
	'loop' WS? NEW_LINE
		(WS | NEW_LINE)*
		(loopBody WS? NEW_LINE)?
		(WS | NEW_LINE)*
	'endloop'
	;
exitwhen:
	'exitwhen'
		((WS expr)
	|
		'(' WS? expr WS? ')')
	;
loopBody:
	loopBodyLine (NEW_LINE (NEW_LINE | WS)* loopBodyLine)* ;
loopBodyLine:
	statement_list
	;
rule_return:
	'return'
	(surroundedExpr | (WS name=expr)?)
	;

typeName:
	ID ;

funcDec:
	('constant')? 
	'function' 
	WS 
	name=ID 
	WS 
	'takes' 
	WS 
	params=funcParam_list 
	WS 
	'returns' 
	WS 
	returnType=funcReturnType
	;

func: 
	funcDec
	WS? NEW_LINE (NEW_LINE | WS)* 
	(body=funcBody WS? NEW_LINE)?
	(NEW_LINE | WS)*
	'endfunction'
	;

funcReturnType:
		'nothing' 
	|
		typeName
	;
funcParam_list: 
		'nothing'
	|
		(params=funcParam (WS? ',' WS? funcParam)*)
	;
funcParam: 
	typeName
	WS
	ID
	;
funcBody:
		(localVarDec_list)?
		(
			WS?
			NEW_LINE
			(WS | NEW_LINE)*
			statement_list
		)?
	|
		(statement_list?)
	;

typeDec:
	'type'
	WS
	name=ID
	WS
	'extends'
	WS
	parent=ID
	;
nativeDec:
	('constant' WS)?
	'native'
	WS
	name=ID
	WS
	'takes'
	WS
	params=funcParam_list
	WS
	'returns'
	WS
	returnType=funcReturnType
	;