parser grammar htmlParser;

options {
    tokenVocab=htmlLexer;
    output=AST;
}

tokens {
    ELEMENT;
    ATTRIBUTE;
    SETTING;
}

scope ElementScope {
  String currentElementName;
}

@header { 
	package com.blogspot.radialmind.html;
	
	import java.io.IOException;
} 

document : element ;

element
scope ElementScope;
    : ( startTag^
            (element
            | PCDATA
            )*
            endTag!
        | emptyElement
      )
    ;

startTag
    : TAG_START_OPEN GENERIC_ID attribute* setting* TAG_CLOSE
            {$ElementScope::currentElementName = $GENERIC_ID.text; }
        -> ^(ELEMENT GENERIC_ID attribute* setting*)
    ;
	
attribute : GENERIC_ID ATTR_VALUE -> ^(ATTRIBUTE GENERIC_ID ATTR_VALUE) ;
	
setting : GENERIC_ID -> ^(SETTING GENERIC_ID) ;
	
endTag!
    : { $ElementScope::currentElementName.equals(input.LT(2).getText()) }?
      TAG_END_OPEN GENERIC_ID TAG_CLOSE
    ;
	catch [FailedPredicateException fpe] {
	    String hdr = getErrorHeader(fpe);
	    String msg = "end tag (" + input.LT(2).getText() +
	                 ") does not match start tag (" +
	                 $ElementScope::currentElementName +
	                 ") currently open, closing it anyway";
	    emitErrorMessage(hdr+" "+msg);
	    // consumeUntil(input, TAG_CLOSE);
	    // input.consume();
	}

emptyElement : TAG_START_OPEN GENERIC_ID attribute* setting* TAG_EMPTY_CLOSE
        -> ^(ELEMENT GENERIC_ID attribute* setting*)
    ;
