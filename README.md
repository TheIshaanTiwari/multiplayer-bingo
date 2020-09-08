# multiplayer-bingo
An N player game of Bingo in Java.
IMPORTANT: You will find the main method inside the Game.java file.
	
Design Patterns:
	1. Observer Design Pattern
		Observed class: Moderator
		Observer class: Player
	2. Singletons
		I. Moderator
		II. Resources
		III. FileOP and SysOP
	3. Strategy Design Pattern
		Interface: Output
		Classes:
			A. FileOP
			B. SysOP

Multithreading: 
	Multiple players run their threads at the same time.
	The moderator also has its own dedicated thread.
	They access shared resources in a synchronised manner.

Input/Output 
	A. Input:
		The run-time inputs needed are:	 			
			I. n, the number of players.
			II. User's preference for o/p.
	B. Output:
		There are 2 options for o/p in this program. The user must pick one as stated above.
			I. Printing to the terminal using System.out.print()
			II. Writing to "Output.txt" file using FileWriter
