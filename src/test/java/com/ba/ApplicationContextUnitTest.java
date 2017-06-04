package com.ba;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ba.state.BattleState;
import com.ba.state.ExploreState;
import com.ba.state.State;

/**
 * Created by ashishagarwal on 6/2/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ApplicationContext.class)
public class ApplicationContextUnitTest {
	
	@Mock private ExploreState es;
	@Mock private BattleState bs;
	@Mock private State s;
	
	private ApplicationContext ac;
	
	@Before
	public void setup() throws Exception {
		ac = new ApplicationContext();
		PowerMockito.whenNew(ApplicationContext.class).withNoArguments().thenReturn(ac);
	}
	
	@Test
	public void shouldInstantiate() {
		when(s.explore()).thenReturn(0);
		ac = new ApplicationContext();

		Assert.assertNotNull(ac.getBattleState());
		Assert.assertNotNull(ac.getExploreState());
		Assert.assertEquals(1, ac.getLevel());
		Assert.assertEquals(10, ac.getNextLevel());
		Assert.assertEquals(0, ac.getScore());
		
		int actual = ac.explore();
		Assert.assertEquals(0, actual);

		actual = ac.battle();
		Assert.assertEquals(0, actual);
	}
	
	@Test
	public void testSet() {
		ac = new ApplicationContext();
		ac.setLevel(5);
		ac.setScore(6);
		ac.setNextLevel(7);
		ac.setState(new ExploreState(ac));

		Assert.assertEquals(5, ac.getLevel());
		Assert.assertEquals(6, ac.getScore());
		Assert.assertEquals(7, ac.getNextLevel());
		Assert.assertTrue(ac.getState() instanceof ExploreState);
	}
}
