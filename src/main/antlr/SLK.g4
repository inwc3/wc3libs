// Define a grammar called SLK
grammar SLK;

options {
	language = Java;
}

@header {
	package net.moonlightflower.wc3libs.antlr;
}

RECORD_PART:
	(STRING_UNQUOTED STRING_QUOTED?) | STRING_QUOTED;

fragment STRING_UNQUOTED:
    ( EscapeSequence | ~('"'|'\r'|'\n'|';') )+;

fragment STRING_QUOTED:
	'"' ( EscapeSequence | ~('"'|'\r'|'\n') | NEW_LINE )* '"';

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
    type=RECORD_PART (SEP recordPart?)*;

recordPart:
    RECORD_PART;