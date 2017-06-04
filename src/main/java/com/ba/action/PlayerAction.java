package com.ba.action;

import static java.lang.System.exit;

import java.util.Scanner;

import com.ba.ApplicationContext;
import com.ba.console.ConsoleWriterImpl;
import com.ba.serialization.ObjectDeserializer;
import com.ba.serialization.ObjectSerializer;

public class PlayerAction {
	private ConsoleWriterImpl writer= new ConsoleWriterImpl();
	
	public void play(final char key, ApplicationContext ac) {
		if (key == 'L') {																								// Explore section
			int points = ac.explore();
			if (points > 0) {
				System.out.println("You gain " + points + " heroic points!");
				ac.setScore(ac.getScore() + points);
			}
		} else if (key == 'A') {																				// Attack section
			int rounds = ac.battle();
			if (rounds > 0) {
				int points = 10 - rounds;
				if (points < 0) {
					points = 0;
				}
				ac.setScore(ac.getScore() + points);
				System.out.println("You gain " + points + " heroic points!");
			}
		} else if (key == 'Q') {																				// Quit section
			exit(1);
		} else if (key == 'C') {																				// New Character section
			writer.write("Enter the name of character : ");
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			writer.write("Current warrior set to " + name + "!!");
		} else if (key == 'S') {																				// Save section
			ObjectSerializer serializer = new ObjectSerializer();
			serializer.serialize(ac);
			writer.write("Your adventure is saved successfully !");
		} else if (key == 'O') {	
			ObjectDeserializer deserializer = new ObjectDeserializer(); 	// Open saved game
			ApplicationContext loadedAC = (ApplicationContext)deserializer.deserialize();
			adapt(ac, loadedAC);
			System.out.println("level="+ac.getLevel() + "\t score=" + ac.getScore());
			writer.write("Loaded your adventure ! ");
		}else {																													// Anything else
			writer.write("Invalid key .. try again !! ");
			return;
		}
		if (ac.getScore() >= ac.getNextLevel()) {
			ac.setLevel(ac.getLevel() + 1);
			ac.setNextLevel(ac.getLevel() * 10);
			System.out.println("Your wonderous experience has gained you a level! Level " + ac.getLevel());
		}
	}

	public void adapt(ApplicationContext ac, ApplicationContext loadedAC) {
		ac.setLevel(loadedAC.getLevel());
		ac.setState(loadedAC.getState());
		ac.setScore(loadedAC.getScore());
		ac.setNextLevel(loadedAC.getNextLevel());
	}
}
