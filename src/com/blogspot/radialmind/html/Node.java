/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.html;

public abstract class Node implements IHTMLVisitor {
	
	private Node prevNode;
	
	public final Node getPrevNode() {
		return prevNode;
	}
	
	public final void setPrevNode( Node prevNode ) {
		this.prevNode = prevNode;
	}
}
