package com.ba.console;

/**
 * Created by ashishagarwal on 5/29/17.
 */
public class ConsoleWriterImpl implements ConsoleWriter {

	protected static final String ANSI_BLUE = "\u001B[34m";
	
	public void write(String s) {
		System.out.println(ANSI_BLUE + s);
	}
}
