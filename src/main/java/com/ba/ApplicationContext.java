package com.ba;

import java.io.Serializable;

import com.ba.state.BattleState;
import com.ba.state.ExploreState;
import com.ba.state.State;

/**
 * Created by ashishagarwal on 5/28/17.
 */
public class ApplicationContext implements Serializable {

	private State exploreState;
	private State battleState;
	private State state;
	private int level = 1;
	
	private int score=0;
	private int nextLevel = 10;

	public ApplicationContext() {
		exploreState = new ExploreState(this);
		battleState = new BattleState(this);
		state = exploreState;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;      
	}

	public int explore() {
		return state.explore();
	}

	public int battle() {
		return state.battle(level);
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return this.state;
	}
	
	public State getExploreState() {
		return exploreState;
	}
	
	public State getBattleState() {
		return battleState;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}
}

