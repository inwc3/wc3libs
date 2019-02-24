// Define a grammar called Jass
grammar LightJass;

options {
	language = Java;
}

@header {
	package net.moonlightflower.wc3libs.antlr;
}

BOOL_LITERAL:
	'true' | 'false' ;
int_literal:
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
	(('0' [xX]) | '$') [0-9a-fA-F]+ ;
ID_INT_LITERAL:
	'\'' ([\u0000-\u0026\u0028-\u00FF]+) '\'' ;
REAL_LITERAL:
	([0-9]+ '.' [0-9]*) | ('.' [0-9]+) ;
STRING_LITERAL:
	'"' ( EscapeSequence | ~('\\'|'"'|'\r'|'\n') | NEW_LINES )* '"';
NULL_LITERAL:
    'null';

COMMENT_SINGLE:
    '//' (~('\n'|'\r'))*;

COMMENT_BLOCK:
    '/*' (~('*') | ('*' + ~('/')))* '*/';

fragment EscapeSequence: '\\' [abfnrtvz"'\\];

CURLY_L:
	'{' ;
CURLY_R:
	'}' ;
COMMA:
	',' ;
NEW_LINES:
	('\r\n' | '\n' | '\r')+ ;
PARENS_L:
    '(';
PARENS_R:
    ')';
BRACKET_L:
    '[';
BRACKET_R:
    ']';

LOCAL:
    'local';
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

GLOBALS_START:
    'globals';
GLOBALS_END:
    'endglobals';
DEBUG:
    'debug';
FUNCTION:
    'function';
ENDFUNCTION:
    'endfunction';
TAKES:
    'takes';
RETURNS:
    'returns';
RETURN:
    'return';
NOTHING:
    'nothing';
ASSIGN_OP:
    '=';

TYPE_DECL:
    'type';
TYPE_EXTENDS:
    'extends';
NATIVE:
    'native';

CALL:
    'call';

SET:
    'set';

IF:
    'if';
THEN:
    'then';
ELSE:
    'else';
ELSEIF:
    'elseif';
ENDIF:
    'endif';
LOOP:
    'loop';
ENDLOOP:
    'endloop';
EXITWHEN:
    'exitwhen';

WS:
	(' ' | '\t')+ -> skip ;

ID:
	ID_START (ID_TAIL)* ;
fragment ID_START:
	[A-Za-z] ;
fragment ID_TAIL:
	[A-Z] | [a-z] | [0-9] | '_' ;

ADD:
    '+';
SUB:
    '-';
MULT:
    '*';
DIV:
    '/';
MOD:
    '%';

LESS:
    '<';
LESS_EQUAL:
    '<=';
EQUAL:
    '==';
UNEQUAL:
    '!=';
GREATER:
    '>';
GREATER_EQUAL:
    '>=';

root:
	NEW_LINES? (top_decl (NEW_LINES top_decl)*)? NEW_LINES?;

top_decl:
    type_decl
    |
    native_decl
    |
    globals_block
    |
    func_impl
    ;

globals_block:
	GLOBALS_START
	NEW_LINES
	(global_decl NEW_LINES)*
	NEW_LINES?
	GLOBALS_END
	;

var_name:
	ID;
func_name:
	ID;
type_name:
	ID;

var_ref:
    ID;
func_ref:
    ID;
type_ref:
    ID;

global_decl:
		type_ref
		ARRAY_DECL
		var_name
	|
		CONST_DECL?
		type_ref
		var_name
		(ASSIGN_OP val=expr)?
	;

expr:
    expr_prim
    |
    PARENS_L expr PARENS_R
    |
    BOOL_OP_NEG expr
    |
    ADD expr
    |
    SUB expr
    |
    expr (MULT | DIV | MOD) expr
    |
    expr (ADD | SUB) expr
    |
    expr (LESS | LESS_EQUAL | GREATER | GREATER_EQUAL) expr
    |
    expr (EQUAL | UNEQUAL) expr
    |
    expr BOOL_OP_CONJUNCT expr
    |
    expr BOOL_OP_DISJUNCT expr
    ;

expr_prim:
    array_read
    |
    var_ref
    |
    func_call
    |
    literal
    ;

literal:
    NULL_LITERAL
    |
    FUNCTION func_ref
    |
    BOOL_LITERAL
    |
    OCT_INT_LITERAL
    |
    DEC_INT_LITERAL
    |
    HEX_INT_LITERAL
    |
    ID_INT_LITERAL
    |
    REAL_LITERAL
    |
    STRING_LITERAL
    ;

func_call:
	func_ref PARENS_L arg_list PARENS_R;
arg_list:
	(expr (COMMA expr)*)? ;
array_read:
	var_ref BRACKET_L expr BRACKET_R;

local_var_decl:
	LOCAL
	(
			(
				type_ref
				ARRAY_DECL
				var_name
			)
		|
			(
				type_ref
				var_name
				(ASSIGN_OP expr)?
			)
	)
	;
local_var_decl_list:
	local_var_decl (NEW_LINES local_var_decl)* ;

statement:
		call
	|
		set_var
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
	statement (NEW_LINES statement)* ;

call:
	CALL
	func_call
	;
set_var:
	SET
		var_ref
		(BRACKET_L index=expr BRACKET_R)?
		ASSIGN_OP
		val=expr
	;
condition:
	expr
	;
selection_elseif_branch:
    ELSEIF condition THEN
    NEW_LINES
    statement_list?
    NEW_LINES?
    ;
selection_else_branch:
    ELSE
    NEW_LINES
    statement_list?
    NEW_LINES?
    ;
selection:
	IF condition THEN
		NEW_LINES
		thenStatements=statement_list?
		NEW_LINES?
    elseif_branches+=selection_elseif_branch*
    else_branch=selection_else_branch?
	ENDIF
	;
loop:
	LOOP
		NEW_LINES
		(loop_body NEW_LINES)?
		NEW_LINES?
	ENDLOOP
	;
exitwhen:
	EXITWHEN
	condition
	;
loop_body:
	loop_body_line (NEW_LINES loop_body_line)*;
loop_body_line:
	statement_list
	;
rule_return:
	RETURN
	expr?
	;
debug:
    DEBUG
    (call | set_var | selection | loop)
    ;

func_decl:
	CONST_DECL?
	FUNCTION
	func_name
	TAKES
	params=func_param_list
	RETURNS
	returnType=func_return_type
	;

func_impl:
	func_decl
	NEW_LINES
	(body=func_body NEW_LINES)?
	ENDFUNCTION
	;

func_return_type:
		NOTHING
	|
		type_ref
	;
func_param_list:
		NOTHING
	|
		(params=func_param (COMMA func_param)*)
	;
func_param:
	type_ref
	var_name
	;
func_body:
        local_var_decl_list
    |
        statement_list
    |
		local_var_decl_list NEW_LINES statement_list
	;

type_decl:
	TYPE_DECL
	type_name
	TYPE_EXTENDS
	type_ref
	;
native_decl:
	CONST_DECL?
	NATIVE
	func_name
	TAKES
	params=func_param_list
	RETURNS
	returnType=func_return_type
	;