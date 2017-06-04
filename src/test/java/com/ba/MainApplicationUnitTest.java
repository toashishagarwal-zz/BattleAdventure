package com.ba;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ba.menu.Menu;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MainApplication.class, ApplicationContext.class})
public class MainApplicationUnitTest {
	
	@Mock private ApplicationContext ac;
	@Mock private Scanner scanner;
	@Mock private Menu menu;
	@Mock private Banner banner;
	
	@Before
	public void setUp() throws Exception {
		String input = "A\nQ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		PowerMockito.whenNew(Scanner.class).withArguments(in).thenReturn(scanner);
		PowerMockito.whenNew(Menu.class).withNoArguments().thenReturn(menu);
		PowerMockito.whenNew(Banner.class).withNoArguments().thenReturn(banner);
		PowerMockito.mockStatic(ApplicationContext.class);
		PowerMockito.whenNew(ApplicationContext.class).withNoArguments().thenReturn(ac);
	}

	@Test
	public void shouldInit() {
		MainApplication.init();
		Mockito.verify(banner, Mockito.times(1)).render();
	}

	@Test
	public void shouldMenu() throws Exception {
		when(ac.getLevel()).thenReturn(10);
		when(ac.getScore()).thenReturn(Mockito.anyInt());
		MainApplication.main(new String[]{});
		
		Mockito.verify(menu, Mockito.times(1)).show(anyObject(), anyObject());
	}
	
	@After
	public void tearDown() {
		System.setOut(null);
	}
}
