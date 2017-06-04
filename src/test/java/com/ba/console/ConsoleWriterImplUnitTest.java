package com.ba.console;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleWriterImplUnitTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ConsoleWriter writer;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		writer = new ConsoleWriterImpl();
	}
	
	@Test
	public void shouldWrite() throws Exception {
		writer.write("ABC");
		assertEquals(ConsoleWriterImpl.ANSI_BLUE + "ABC" + "\n", outContent.toString());
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}
}
