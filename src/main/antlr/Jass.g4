// Define a grammar called Jass
grammar Jass;

options {
	language = Java;
}

@header {
	package net.moonlightflower.wc3libs.antlr;
}

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
	('\r\n' | '\n' | '\r')+ ;
WS:
	(' ' | '\t')+ -> skip ;

ARRAY_DECL:
    'array';

CONST_DECL:
    'constant';
BOOL_OP_CONJUNCT:
    'and';
BOOL_OP_DISJUNCT:
    'or' ;
BOOL_OP_NEG:
    'not' ;

root:
	NEW_LINE*
	(typeDec NEW_LINE NEW_LINE*)*
	NEW_LINE*
	(nativeDec NEW_LINE NEW_LINE*)*
	globalsBlock NEW_LINE
	NEW_LINE*
	(nativeDec (NEW_LINE | EOF) NEW_LINE*)*
	NEW_LINE*
	(func NEW_LINE NEW_LINE*)*
	NEW_LINE* ;

globalsBlock:
	'globals'
	NEW_LINE+
	(globalDec NEW_LINE+)*
	NEW_LINE*
	'endglobals'
	;

varName:
	ID ;
funcName:
	ID ;

globalDec:
		type=typeName
		ARRAY_DECL
		name=varName
	|
		CONST_DECL?
		type=typeName
		name=varName 
		('=' val=expr)?
	;

surroundedExpr:
	'(' expr ')' ;
expr:
		expr (BOOL_OP_CONJUNCT | BOOL_OP_DISJUNCT) expr
	|
		'(' expr ')' (BOOL_OP_CONJUNCT | BOOL_OP_DISJUNCT) '(' expr ')'
	|
		expr (BOOL_OP_CONJUNCT | BOOL_OP_DISJUNCT) '(' expr ')'
	|
		'(' expr ')' (BOOL_OP_CONJUNCT | BOOL_OP_DISJUNCT) expr
	|
		BOOL_OP_NEG (expr | '(' expr ')')
	|
		literal
	|
		expr ('+' | '-' | '*' | '/') expr
	|
		('+' | '-') expr
	|
		expr ('<' | '<=' | '==' | '!=' | '>' | '>=') expr
	|
		funcExpr
	|
		varName
	|
		arrayRead
	|
		funcRef
	|
		'(' expr ')'
	;

//arithExpr:
	//expr ('+' | '-' | '*' | '/') expr | ('+' | '-') expr ;
//compExpr:
	//expr ('<' | '<=' | '==' | '!=' | '>' | '>=') expr ;
//boolExpr:
	//expr ('and' | 'or') expr | 'not' expr ;
funcExpr:
	funcName '(' arg_list ')' ;
arg_list:
	(expr (',' expr)*)? ;
arrayRead:
	varName '[' expr ']' ;
funcRef:
	'function' funcName ;

literal:
	BOOL_LITERAL | INT_LITERAL | REAL_LITERAL | STRING_LITERAL | funcRef ;

localVarDec:
	'local'
	(
			(
				typeName
				ARRAY_DECL
				name=varName
			)
		|
			(
				typeName
				name=varName
				('=' expr)?
			)
	)
	;
localVarDec_list:
	localVarDec (NEW_LINE+ localVarDec)* ;

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
	|
	    debug
	;
statement_list:
	statement (NEW_LINE+ statement)* ;

callFunc:
	'call'
	funcExpr
	;
setVar: 
	'set'
		name=varName
		('[' index=expr ']')?
		'='
		val=expr 
	;
condition:
	(surroundedExpr | expr)
	;
selection: 
	'if' condition 'then' NEW_LINE
		NEW_LINE*
		statement_list?
		NEW_LINE*
		('elseif' condition 'then' NEW_LINE
			NEW_LINE*
			statement_list?
			NEW_LINE*
		)*
		('else' NEW_LINE
			NEW_LINE*
			statement_list?
			NEW_LINE*
		)?
		NEW_LINE*
	'endif'
	;
loop:
	'loop' NEW_LINE
		NEW_LINE*
		(loopBody NEW_LINE)?
		NEW_LINE*
	'endloop'
	;
exitwhen:
	'exitwhen'
	condition
	;
loopBody:
	loopBodyLine (NEW_LINE+ loopBodyLine)* ;
loopBodyLine:
	statement_list
	;
rule_return:
	'return'
	(surroundedExpr | (name=expr)?)
	;
debug:
    'debug'
    (callFunc | setVar | selection | loop)
    ;

typeName:
	ID ;

funcDec:
	CONST_DECL?
	'function'
	name=ID
	'takes'
	params=funcParam_list
	'returns'
	returnType=funcReturnType
	;

func: 
	funcDec
	NEW_LINE+
	(body=funcBody NEW_LINE)?
	NEW_LINE*
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
		(params=funcParam (',' funcParam)*)
	;
funcParam: 
	typeName
	ID
	;
funcBody:
		(localVarDec_list)?
		(
			NEW_LINE+
			statement_list
		)?
	|
		(statement_list?)
	;

typeDec:
	'type'
	name=ID
	'extends'
	parent=ID
	;
nativeDec:
	CONST_DECL?
	'native'
	name=ID
	'takes'
	params=funcParam_list
	'returns'
	returnType=funcReturnType
	;