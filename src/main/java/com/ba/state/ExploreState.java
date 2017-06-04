package com.ba.state;
import java.io.Serializable;

import com.ba.ApplicationContext;
import com.ba.RandomGenerator;

/**
 * Created by ashishagarwal on 5/28/17.
 */
public class ExploreState implements State, Serializable {
	private ApplicationContext context;
	
	public ExploreState(ApplicationContext context) {
		this.context = context;
	}
	
	public int explore() {
		System.out.println("You look around for something to kill.");

		int ran = RandomGenerator.getRandomNumber(5);
		if (ran == 0) {
			System.out.println("A monster approaches! Prepare for battle!");
			context.setState(context.getBattleState());
		} else if (ran == 1) {
			System.out.println("You find a golden jewel behind a tree!");
			return 2;
		}
		return 0;
	}

	public int battle(int level) {
		System.out.println("You find nothing to attack.");
		return 0;
	}
}
