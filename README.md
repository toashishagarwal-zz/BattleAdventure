
# Introduction
Battle Adventure is a role playing, terminal-based game in which the player needs to gain points by killing monsters. 

# Technical Aspects
- Language			  - Core Java (version 8)
- Build   		    - Maven (version 3.1.1)
- Mocking 		    - PowerMock (version 1.6.6)
- Code Coverage 	- Cobertura plugin for Maven (version 2.7)
 
# Instructions
1 - To build the project use - 
		```
    mvn clean install
    ```
    
2 - To see the code coverage use -
    ```
		mvn cobertura:cobertura ```
    
	The coverage report gets created in the location - BattleAdventure/target/site/cobertura/index.html

3 - To extend the code -
    a) To add a new state define its class and embed that in the ApplicationContext.java
    b) To map additional key operations add the option in PlayerAction.java and Menu.java

# Additional Notes
1 - This application has been developed using the STATE design pattern where the 2 states are - ExploreState 
and BattleState

2 - The application makes use of pseudo random functions to simulate the gaming behaviors 
e.g. The killing or saving of the monster is based on a pseudo random value

3 - The selection of keys - A [for Attack] and L [for Explore (or Look Around)] is intentional. When the game is 
played with both hands on a standard QWERTY-keyboard these keys are on extreme left and right side of middle row.
This makes playing very convenient with the left hand focusing on A and right focusing on L

4 - How did I choose BLUE and WHITE combo? 
Based on the site - http://blog.usabilla.com/how-to-design-for-color-blindness/ 
This site (and many other resources) lists the different combo of colors which should be avoided
to make the UI friendly for color blind people. BLUE appears the same for different types of color blind people

5 - Some of the best practices followed in the code are - 
   * Code to interfaces and not classes
   * Prefer composition over inheritance
   * The code follows SOLID principles of design
   * The unit test methods' names are very detailed to convey their intention
	
6 - This code contains examples of mocking the following in Java for JUnit tests -
   * System.in 
   * System.out 
   * System.exit
   * Simulate Thread interrupt 
   * Stubbing of private methods using spy
   * static methods
    
7 - Though Java 8 jdk has been used I could not use many Java 8 features like Lambdas, Streams, etc because 
Cobertura, the code coverage tool does not support java 8 features thereby affecting the coverage reports adversely.  


