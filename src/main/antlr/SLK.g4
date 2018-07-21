// Define a grammar called SLK
grammar SLK;

options {
	language = Java;
}

@header {
	package net.moonlightflower.wc3libs.antlr;
}

BOOL_LITERAL:
	'true' | 'false' ;
DEC_INT_LITERAL:
	[1-9] [0-9]* | [0-9] ;
/*ID:
	ID_START (ID_TAIL)* ;
fragment ID_START:
	[A-Za-z] ;
fragment ID_TAIL:
	[A-Z] | [a-z] | [0-9] | '_' ;*/
STRING_LITERAL:
	'"' ( EscapeSequence | ~('"'|'\r'|'\n') | NEW_LINE )* '"';
ID:
//    [A-Za-z]+;
//UNQUOTED_STRING_LITERAL:
	( EscapeSequence | ~('"'|'\r'|'\n'|';'|[0-9]) )+;

fragment EscapeSequence: '\\' [abfnrtvz"'\\];

SEP:
    ';';
NEW_LINE:
	('\r\n' | '\n' | '\r')+ ;
WS:
	(' ' | '\t')+ -> skip ;

root:
    records+=record (NEW_LINE+ records+=record)*
    NEW_LINE*
    ;

record:
    type=recordType (SEP recordPart)*;

recordType:
    ID;

recordPart:
    attr=ID recordVal?;

recordVal:
    (complex=complexVal | str=STRING_LITERAL);

complexVal:
    val=(DEC_INT_LITERAL | ID)+;