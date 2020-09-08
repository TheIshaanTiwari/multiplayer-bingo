# multiplayer-bingo
An N player game of Bingo in Java.

IMPORTANT: You will find the main method inside the Game.java file.
	
Design Patterns: Observer Design Pattern (Observed class: Moderator, Observer class: Player); Singletons (Moderator, Resources, FileOP and SysOP); Strategy Design Pattern (Interface: Output, Classes: FileOP and SysOP)

Multithreading:	Multiple players run their threads at the same time. The moderator also has its own dedicated thread. They access shared resources in a synchronised manner.

Input: The run-time inputs needed are: n (the number of players); User's preference for o/p (1 or 2) 

Output: There are 2 options for o/p in this program. The user must pick one as stated above. (1 = Printing to the terminal and 2 = Writing to "Output.txt" file)
