package com.ba;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by ashishagarwal on 5/31/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomGenerator.class)
public class RandomGeneratorUnitTest {
	
	@Test
	public void testRandom() {
		// given
		PowerMockito.mockStatic(RandomGenerator.class);
		int expected = 1;
		when(RandomGenerator.getRandomNumber(Mockito.anyInt())).thenReturn(expected);
		
		// when
		int actual = RandomGenerator.getRandomNumber(5);
		
		// then
		Assert.assertEquals(expected, actual);
	}
}
