tree grammar htmlTreeParser;

options {
    tokenVocab=htmlLexer;
    ASTLabelType = Tree;
}

@header {
	package com.blogspot.radialmind.html;
	
	import com.blogspot.radialmind.html.HTMLParser;
	import java.io.IOException;
}

document : element ;

element
    : ^( ELEMENT name=GENERIC_ID 
            { HTMLParser.openTag( $name.text ); }
            ( 
                ^(ATTRIBUTE attrName=GENERIC_ID value=ATTR_VALUE) 
                { HTMLParser.addAttribute( $attrName.text, $value.text ); }
            )*
            ( 
             	^(SETTING attrName=GENERIC_ID) 
                { HTMLParser.addAttribute( $attrName.text, "" ); }
            )*
            { HTMLParser.finishAttributes(); }
            (element
            | text=PCDATA
                { HTMLParser.addText( $text.text ); }
            )*
            { HTMLParser.closeTag( $name.text ); }
        )
    ;	catch [IOException ioe] {
		ioe.printStackTrace();
	}
