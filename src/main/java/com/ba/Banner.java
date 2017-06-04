package com.ba;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import com.ba.console.ConsoleWriter;
import com.ba.console.ConsoleWriterImpl;

/**
 * Created by ashishagarwal on 5/29/17.
 */
public class Banner {
	
	private ConsoleWriter writer;

	public Banner() {
		writer = new ConsoleWriterImpl();
	}

	public void render() {
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				Banner.class.getClassLoader().getResourceAsStream("banner.txt")));
		
		reader.lines().forEach(new Consumer<String>() {
			public void accept(String line) {
				writer.write(line);
			}
		});
	}
}
