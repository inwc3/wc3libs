// Define a grammar called FDF
grammar FDF;

options {
	language = Java;
}

@header {
	package net.moonlightflower.wc3libs.antlr;
}

CURLY_L : '{' ;
CURLY_R : '}' ;
COMMA : ',' ;
WS : (' ' | '\t' | '\r' | '\n') ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;

ID : ID_START (ID_TAIL)* ;
fragment ID_START : [A-Za-z] ;
fragment ID_TAIL : [A-Z] | [a-z] | [0-9] | '_' ;

STRING_LITERAL : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"';

root : (WS | stringList)* EOF ;

stringList : 'StringList' (WS)* CURLY_L (WS)* stringListContent? (WS)* CURLY_R ;

stringListContent : (stringAssign (WS)*) (COMMA (WS)* stringAssign (WS)*)* COMMA? ;

stringAssign : id=ID (WS)+ val=STRING_LITERAL ;






//STRING_ASSIGN : STRING_LITERAL (WS)+ STRING_LITERAL ;
//STRING_LITERAL : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\''))* '"';
