package com.ba.state;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ba.ApplicationContext;
import com.ba.RandomGenerator;

/**
 * Created by ashishagarwal on 5/31/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BattleState.class, RandomGenerator.class})
public class BattleStateMockUnitTest {
	@Mock private RandomGenerator random;
	@Mock private ApplicationContext ac;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	private BattleState bs;
	
	@Before
	public void setup() {
		bs = new BattleState(ac);
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void shouldExplore() {
		Assert.assertEquals(0, bs.explore());
	}

	@Test
	public void shouldReturnRoundsWhenBattle() {
		// given
		int rounds = 5;
		bs.setRounds(rounds);
		int expected = rounds + 1;

		PowerMockito.mockStatic(RandomGenerator.class);
		when(RandomGenerator.getRandomNumber(5)).thenReturn(0);
		
		// when
		int actual = bs.battle(10);
		
		// then
		Assert.assertEquals(expected, actual);
		Mockito.verify(ac, times(1)).setState(Mockito.anyObject());
	}

	@Test
	public void shouldReturnZeroWhenBattleAndRoundsMoreThanEqual9() {
		// given
		int rounds = 9;
		bs.setRounds(rounds);
		int expected = 0;

		PowerMockito.mockStatic(RandomGenerator.class);
		when(RandomGenerator.getRandomNumber(3)).thenReturn(1);

		// when
		int actual = bs.battle(7);

		// then
		Assert.assertEquals(expected, actual);
		Mockito.verify(ac, times(1)).setState(Mockito.anyObject());
	}

	@Test
	public void shouldReturnZeroWhenBattle() {
		// given
		int rounds = 5;
		bs.setRounds(rounds);
		int expected = 0;

		PowerMockito.mockStatic(RandomGenerator.class);
		when(RandomGenerator.getRandomNumber(3)).thenReturn(1);

		// when
		int actual = bs.battle(7);

		// then
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void shouldThrowException() throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				bs.battle(7);
			}
		};
		t.start();
		Thread.sleep(1); // let the other thread start
		t.interrupt();
	}

	@Test
	public void shouldGetRounds() {
		BattleState bs = new BattleState(ac);
		Assert.assertEquals(0,bs.getRounds());
	}	
}
