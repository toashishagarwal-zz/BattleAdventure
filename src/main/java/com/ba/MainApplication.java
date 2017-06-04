package com.ba;

import java.util.Scanner;

import com.ba.menu.Menu;

public class MainApplication {
	private static ApplicationContext ac = new ApplicationContext();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Render the home screen
		init();

		// Show the menu
		Menu menu = new Menu();
		menu.show(scanner, ac);

		if (ac.getLevel() >= 10) {
			System.out.println();
			System.out.println("You WIN! Final score [" + ac.getScore() + "]");
		}
	}

	protected static void init() {
		Banner banner = new Banner();
		banner.render();
	}
}
