package com.ba.menu;

import java.util.Scanner;

import com.ba.ApplicationContext;
import com.ba.action.PlayerAction;

/**
 * Created by ashishagarwal on 5/31/17.
 */
public class Menu {

	public void show(Scanner scanner, ApplicationContext ac) {
		String command;
		char key;
		PlayerAction player = new PlayerAction();
		
		do {
			System.out.println();
			System.out.println("[C]reate Character, [L]ook Around, [A]ttack, [Q]uit, [S]ave, [O]pen");
			System.out.println("Score [" + ac.getScore() + "] Level [" + ac.getLevel() + "] Action [L,A,Q]: ");
			command = scanner.nextLine();
			if(command.length() == 0) {
				key = '.';
				continue;
			}
			key = command.charAt(0);
			if(key == 'Q')
				break;
			System.out.println();
			player.play(key, ac);
		} while (key != 'Q' && ac.getLevel() < 10);
	}
}
