package com.ba.menu;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ba.ApplicationContext;
import com.ba.action.PlayerAction;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Menu.class)
public class MenuUnitTest {
	private Menu menu = new Menu();
	
	@Mock private PlayerAction player;
	@Mock private Scanner scanner;
	@Mock private ApplicationContext ac;
	
	@Before
	public void setup() throws Exception {
		PowerMockito.whenNew(PlayerAction.class).withNoArguments().thenReturn(player);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void shouldShowWithAttackMode() {
		// given
		String input = "A\nQ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		
		when(scanner.nextLine()).thenReturn("A");
		when(ac.getLevel()).thenReturn(10);
		
		// when
		menu.show(scanner, ac);
		
		// then
		Mockito.verify(player, times(1)).play('A', ac);
	}

	@Test
	public void shouldShowWithOnlyNewLineChar() {
		// given
		String input = "\nQ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		when(scanner.nextLine()).thenReturn("");
		when(ac.getLevel()).thenReturn(10);

		// when
		menu.show(scanner, ac);
		Mockito.verify(player, times(0)).play(Character.MIN_VALUE, ac);
	}

	@Test
	public void shouldShowWithQuitMode() {
		// given
		String input = "\nQ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		when(scanner.nextLine()).thenReturn("Q");
		when(ac.getLevel()).thenReturn(10);

		// when
		menu.show(scanner, ac);
		Mockito.verify(player, times(0)).play('Q', ac);
	}
	
}
