lexer grammar htmlLexer;

@header {
	package com.blogspot.radialmind.html;
}

@members {
    boolean tagMode = false;
}

TAG_START_OPEN : { !tagMode }?=> '<' { tagMode = true; } ;
TAG_END_OPEN : { !tagMode }?=> '</' { tagMode = true; } ;
TAG_CLOSE : { tagMode }?=> '>' { tagMode = false; } ;
TAG_EMPTY_CLOSE : { tagMode }?=> '/>' { tagMode = false; } ;

// FIXME: Double and single quotes should be stripped from values!
// ! operator does not work for that any more. 
// How to get this done?
//   '"' (~'"')* '"'
// | '\'' (~'\'')* '\''
//
ATTR_VALUE : { tagMode }?=>
		( WS* '=' WS* )
		( 
		'"' (~'"')* '"'
		| '\'' (~'\'')* '\''
		| '`' (~'`')* '`'
		| ~QUOTECHAR (~WSCHAR)*
		)
	;

PCDATA : { !tagMode }?=> (options {greedy=true;} : ~'<')+ ; 

GENERIC_ID 
    : { tagMode }?=>
      ( LETTER | '_' | ':') ( options {greedy=true;} : NAMECHAR )*
    ;

fragment NAMECHAR
    : LETTER | DIGIT | '.' | '-' | '_' | ':'
    ;

fragment SPECIALCHAR
	: ' ' | '\t' | '\u000C' | '>' | '\'' | '"'
	;

fragment WSCHAR
	: '\u0000'..'\u0020' | '/>' | '>' | '<'
	;

fragment QUOTECHAR
	: '\'' | '"' | '`'
	;

fragment DIGIT
	:	'0'..'9'
	;

fragment LETTER
	: 'a'..'z' 
	| 'A'..'Z'
	;

WS  :  { tagMode }?=>
       (' '|'\r'|'\t'|'\u000C'|'\n') {channel=99;} 
    ;
