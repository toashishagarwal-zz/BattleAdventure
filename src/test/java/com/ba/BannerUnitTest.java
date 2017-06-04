package com.ba;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ba.console.ConsoleWriter;

/**
 * Created by ashishagarwal on 5/30/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BannerUnitTest {
	@InjectMocks
	private Banner banner = new Banner();
	
	@Mock
	private ConsoleWriter writer;
	
	@Test
	public void shouldRender() {
		BufferedReader reader = mock(BufferedReader.class);
		when(reader.lines()).thenReturn(Stream.empty());
		
		banner.render();
		
		// there are 28 lines in the banner.txt file
		Mockito.verify(writer, Mockito.times(28)).write(anyString());  
	}
}
