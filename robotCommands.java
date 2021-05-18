//Jaden Heinle

// Import needed packages
import java.io.File;
import java.io.IOException;
import java.math.*;
import java.util.*;

public class Hw04 
{

	public static void main(String[] args) 
	{
		boolean goingOn = true;
		while(goingOn)
		{
			System.out.println("Welcome to the Robot Simulator \nEnter File for the Board");
			String[] boardArray = getBoard();
			System.out.println("Enter file for the Robot Commands");
			CommandQueue cQueue;
			cQueue = new CommandQueue();
			cQueue = getCommands(cQueue);
			displayBoard(boardArray);
			System.out.println("\nSimulation begin");
			move(boardArray,cQueue, 0);
			System.out.println("Simulation end");
			goingOn = continueCheck();
		}
	}
	
	public static String[] getBoard() 
	{
		//Opens file and saves it to a String array size 10 with each line
		Scanner key = new Scanner(System.in);
		String textFile = "./";
		textFile += key.nextLine();
		String[] Lines = grabFile(textFile);
		//Here we set up the big array to hold all the values of the board individually
		//Definitely a better way to store this, memory wise, but this'll work!
		String[] finishedLines = new String[100];
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(i==0)
					finishedLines[j] = Lines[i].substring(j, j+1);
				else
					finishedLines[j+(i*10)] = Lines[i].substring(j, j+1);
			}
		}
		finishedLines[0] = "O";
		return finishedLines;
	}
	
	//Displays the current board
	public static void displayBoard(String[] a) 
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++) 
			{
				if(i==0)
					System.out.print(a[j]);
				else
					System.out.print(a[j+(i*10)]);
			}
		System.out.println();
		}
	}
	
	//Getting and queuing the commands
	public static CommandQueue getCommands(CommandQueue cQueue) 
	{
		Scanner key = new Scanner(System.in);
		String textFile = "./";
		textFile += key.nextLine();
		String[] Lines = grabFile(textFile);
		for(int i=0;i<Lines.length;i++) {
			cQueue.enqueue(Lines[i].substring(5, 6));
		}
		
		return cQueue;
	}
	
	//Recursion Loop
	public static void move(String[] a, CommandQueue cQueue, int cRun) 
	{
		//Checks to see if the command is available
		String command = cQueue.dequeue();
		if(command==null)
			return;
		System.out.println("Command " + cRun);
		//Figures out where the robot is on the map
		int oPlace = 0;
		for(int i=0;i<a.length;i++) 
		{
			if(a[i].equalsIgnoreCase("O"))
					oPlace = i;
		}
		//Checks to see if the robot can move in that direction and either moves them or not
		if(command.equalsIgnoreCase("u")) {
			if(a[oPlace-10].equalsIgnoreCase("X") || oPlace-10 < 0) 
			{
				System.out.println("CRASH!");
				return;
			}
			a[oPlace-10] = "O";
			a[oPlace] = "_";
		} else if(command.equalsIgnoreCase("d")) {
			if(a[oPlace+10].equalsIgnoreCase("X") || oPlace+10 > 100) 
			{
				System.out.println("CRASH!");
				return;
			}
			a[oPlace+10] = "O";
			a[oPlace] = "_";
		} else if(command.equalsIgnoreCase("l")){
			if(a[oPlace+1].equalsIgnoreCase("X") || oPlace - 1 >= Math.ceil(oPlace*10)*10) 
			{
				System.out.println("CRASH!");
				return;
			}
			a[oPlace-1] = "O";
			a[oPlace] = "_";
		} else {
			if(a[oPlace+1].equalsIgnoreCase("X") || oPlace+1 < Math.floorMod(oPlace, 10))
			{
				System.out.println("CRASH!");
				return;
			}
			a[oPlace+1] = "O";
			a[oPlace] = "_";
		}
		//Displays changes and calls itself again
		displayBoard(a);
		move(a,cQueue,cRun+1);
	}
	//Used to open the file for commands
	public static String[] grabFile(String fileName)
	{
		try
		{
			Scanner fileScanner = new Scanner(new File(fileName));
			int wordCount = 0;
			while(fileScanner.hasNextLine())
			{
				fileScanner.nextLine();
				wordCount++;
			}
			String[] lineArray = new String[wordCount];
			fileScanner = new Scanner(new File(fileName));
			for(int i=0;i<lineArray.length;i++)
			{
				if(!fileScanner.hasNextLine())
					break;
				lineArray[i] = fileScanner.nextLine();
			}
			return lineArray;
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	//checks if the user wants to go again
	public static boolean continueCheck() 
	{
		Scanner key = new Scanner(System.in);
		System.out.println("Quit? Enter \"true\" to quit or hit enter to run another simulation");
		String ans = key.nextLine();
		if(ans.equalsIgnoreCase("true"))
			return true;
		return false;
	}
}