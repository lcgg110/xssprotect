/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.html;

public interface IHTMLFilter {
	/**
	 * This method can filter tags from a particular document. Notice that this method
	 * will still call the filter method for its child elements. It is possible that this
	 * causes invalid HTML to be generated
	 * 
	 * @param tagName	The name of the tag currently processed
	 * @return	"true" to filter the tag (not write to output), "false" to add it to the output
	 */
	public abstract boolean filterTag( String tagName );
	
	/**
	 * This method determines if the attribute will be filtered from the tag.
	 * 
	 * @param tagName	The name of the tag where the attribute is located
	 * @param attrName	The name of the attribute that can be filtered.
	 * @param attrValue	The value of the attribute that can be filtered.
	 * @return			"true" to filter out, "false" to leave it in.
	 */
	public abstract boolean filterAttribute( String tagName, String attrName, String attrValue );
	
	/**
	 * This method can modify (specifically filter) the attribute value. Use it to
	 * clean up XSS attacks and so forth.
	 * 
	 * @param tagName	The name of the tag where the attribute is located
	 * @param attrName	The name of the attribute that can be filtered. 
	 * @param value		The value of the attribute
	 * @return			The modified attribute value
	 */
	public abstract String  modifyAttributeValue( String tagName, String attrName, String value );
	
	/**
	 * This method can modify the text body of a tag. Use it to clean up the text or
	 * process the text body differently. Notice that this method does not contain
	 * other child element texts.
	 * 
	 * @param tagName	The name of the tag in which the text is located
	 * @param text		The text of the body.
	 * @return			The modified text.
	 */
	public abstract String	modifyNodeText( String tagName, String text );
}
