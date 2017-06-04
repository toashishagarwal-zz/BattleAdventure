package com.ba.state;

import java.io.Serializable;

import com.ba.ApplicationContext;
import com.ba.RandomGenerator;

/**
 * Created by ashishagarwal on 5/28/17.
 */
public class BattleState implements State, Serializable {
	private ApplicationContext context;
	private int rounds = 0;

	public BattleState(ApplicationContext context) {
		this.context = context;
	}
	
	public int explore() {
		System.out.println("You'd love to, but see, there's this big ugly monster in front of you!");
		return 0;
	}
	
	protected int getRounds() {
		return rounds;
	}

	protected void setRounds(final int r) {
		this.rounds = r;
	}
	
	public int battle(int level) {
		System.out.println("You try to slay the monster.. ");
		rounds++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		int maxRan = 10 - level;
		if (maxRan < 1) {
			maxRan = 1;
		}
		
		int ran = RandomGenerator.getRandomNumber(maxRan);
		if (ran == 0) {
			System.out.println("he's dead!");
			context.setState(context.getExploreState());
			int tempRounds = rounds;
			rounds = 0;
			return tempRounds;
		}
		else
			System.out.println("but fail.");
		
		if (rounds >= 9) {
			System.out.println("You panic and run away in fear.");
			context.setState(context.getExploreState());
			rounds = 0;
		}
		return 0;
	}
}
