package com.ba.state;

import static org.mockito.Mockito.when;

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
@PrepareForTest({ExploreState.class, RandomGenerator.class})
public class ExploreStateMockUnitTest {
	
	@Mock private RandomGenerator random;
	@Mock private ApplicationContext ac;
	
	private ExploreState es;
	
	@Before
	public void setup() {
		es = new ExploreState(ac);
	}
	
	@Test
	public void shouldReturn0WhenExplore() {
		// given
		PowerMockito.mockStatic(RandomGenerator.class);
		when(RandomGenerator.getRandomNumber(5)).thenReturn(0);
		
		// when
		int actual = es.explore();
		
		// then
		Assert.assertEquals(0, actual);
		Mockito.verify(ac, Mockito.times(1)).setState(Mockito.anyObject());
	}

	@Test
	public void shouldReturn2WhenExplore() {
		// given
		PowerMockito.mockStatic(RandomGenerator.class);
		when(RandomGenerator.getRandomNumber(5)).thenReturn(1);
		ExploreState es = new ExploreState(ac);

		// when
		int actual = es.explore();

		// then
		Assert.assertEquals(2, actual);
	}

	@Test
	public void shouldBattle() throws Exception {
		Assert.assertEquals(0, es.battle(3));
	}
	
}
