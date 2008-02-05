/**
 * Copyright 2007 Gerard Toonstra
 *
 * This file is part of the XSS Protect library
 *
 * XSS Protect is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * XSS Protect is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with XSS Protect; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package com.blogspot.radialmind.html;

import java.io.IOException;
import java.io.Writer;

/**
 * Holder class for text within a node. Text within a node is the text between
 * the <p> and </p> tags.
 * 
 * @author gt
 *
 */
public class TextNode extends Node implements IHTMLVisitor {
	
	private StringBuilder text = new StringBuilder();
	private String tagName = null;
	
	public TextNode( String tagName, String text ) {
		super();
		this.text.append( text );
	}
	
	// Add text to the node
	public void addText( String text ) {
		this.text.append( text );
	}
	
	// Return the text	
	public String getText() {
		return text.toString();
	}
	
	// Write this node to the stream
	public void writeAll( Writer writer, IHTMLFilter htmlFilter, boolean convertIntoValidXML, boolean filterText ) throws IOException {
		
		if ( filterText ) {
			return;
		}
		
		if ( htmlFilter != null ) {
			String newText = htmlFilter.modifyNodeText( tagName, getText() );
			writer.append( newText );
		} else {
			writer.append( getText() );
		}
	}
}
