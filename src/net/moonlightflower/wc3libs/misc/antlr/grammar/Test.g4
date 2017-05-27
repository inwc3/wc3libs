// Define a grammar called Hello
grammar Test;

@header {
	package net.moonlightflower.wc3libs.misc.antlr.out.grammar;
}

r  : 'start' WS ID_A WS ID_A ;         // match keyword hello followed by an identifier
ID_A : [a-z]+ ;             // match lower-case identifiers
ID_B : [a-z]+ ;             // match lower-case identifiers
WS : [ ]+ ; // skip spaces, tabs, newlines