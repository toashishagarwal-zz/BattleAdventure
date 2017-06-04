package com.ba.action;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import com.ba.console.ConsoleWriterImpl;
import com.ba.serialization.ObjectDeserializer;
import com.ba.serialization.ObjectSerializer;

/**
 * Created by ashishagarwal on 6/2/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayerAction.class)
public class PlayerActionUnitTest {

	@Mock private ApplicationContext ac;
	@Mock private Scanner scanner;
	@Mock private ConsoleWriterImpl writer;
	@Mock private System system;
	
	@Mock private ObjectSerializer serializer;
	@Mock private ObjectDeserializer deSerializer;
	
	private PlayerAction player;
	
	@Before
	public void setup() throws Exception {
		PowerMockito.whenNew(ConsoleWriterImpl.class).withNoArguments().thenReturn(writer);
		player = new PlayerAction();
	}
	
	@Test
	public void shouldPlayForLookAround() {
		int points = 5;
		int score = 10;
		when(ac.explore()).thenReturn(points);
		when(ac.getScore()).thenReturn(score);
		
		player.play('L', ac);

		Mockito.verify(ac).setScore(score + points);
	}

	@Test
	public void shouldPlayForAttack() {
		int score = 10;
		int rounds = 3;
		when(ac.battle()).thenReturn(rounds);
		when(ac.getScore()).thenReturn(score);

		int expectedPoints = 10 - rounds;
		player.play('A', ac);

		Mockito.verify(ac).setScore(score + expectedPoints);
	}

	@Test
	public void shouldPlayForAttackForRoundsMoreThan10() {
		int score = 10;
		int rounds = 11;
		when(ac.battle()).thenReturn(rounds);
		when(ac.getScore()).thenReturn(score);

		int expectedPoints = 0;
		player.play('A', ac);

		Mockito.verify(ac).setScore(score + expectedPoints);
	}

	@Test
	public void shouldCreateCharacter() throws Exception {
		// given
		String input = "anyString";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		PowerMockito.whenNew(Scanner.class).withArguments(in).thenReturn(scanner);
		
		String expectedName = "Balia";
		when(scanner.nextLine()).thenReturn(expectedName);
		
		String expectedOutput = "Current warrior set to " + expectedName + "!!";
		
		// when
		player.play('C', ac);
		
		// then
		Mockito.verify(writer).write(expectedOutput);
	}

	@Test
	public void shouldHandleAnyOtherCase() throws Exception {
		// when
		player.play('X', ac);

		// then
		Mockito.verify(writer).write("Invalid key .. try again !! ");
	}
	
	@Test
	public void shouldHandleQuit() throws Exception {
		PowerMockito.mockStatic(System.class);

		// when
		player.play('Q', ac);
		PowerMockito.verifyStatic();
		System.exit(1);
	}

	@Test
	public void shouldSave() throws Exception {
		PowerMockito.whenNew(ObjectSerializer.class).withNoArguments().thenReturn(serializer);

		// when
		player.play('S', ac);
		
		Mockito.verify(serializer,times(1)).serialize(ac);
		Mockito.verify(writer,times(1)).write("Your adventure is saved successfully !");
	}

	@Test
	public void shouldOpen() throws Exception {
		PowerMockito.whenNew(ObjectDeserializer.class).withNoArguments().thenReturn(deSerializer);
		
		when(deSerializer.deserialize()).thenReturn(new ApplicationContext());
		
		// when
		player.play('O', ac);

		Mockito.verify(deSerializer,times(1)).deserialize();
		Mockito.verify(writer,times(1)).write("Loaded your adventure ! ");
	}
}
