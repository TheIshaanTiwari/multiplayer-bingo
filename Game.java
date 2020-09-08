import java.io.*;

public class Game {
    public static void main(String... args) throws InterruptedException {

        // Getting user input for the number of players (n).
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        System.out.println("\nWelcome to the CS F213 Game! We need you to set a few things up first.\nPLEASE ENTER THE NUMBER OF PLAYERS.");
        try{ n = Integer.parseInt(br.readLine()); 
        if(n<2) throw new userDefinedException();}
        // If we encounter an exception we will use a default value of n (2).
        catch(IOException e){ e.printStackTrace(); System.out.println("We encountered an I/O exception.\nUsing n = 2."); n = 2;} 
        catch(NumberFormatException e2){ System.out.println("The input must be an integer.\nUsing n = 2."); n = 2;}
        catch(userDefinedException e){ System.out.println("Sorry. You need at least 2 players to play this game.\nUsing n = 2."); n=2; }
        
        // Setting the game up.
        Moderator moderator = Moderator.getInstance();
        System.out.println("\nGame setup complete! Starting a " + n + " player game.\n");
        Resource resource = Resource.getInstance();
        for (int i = 0; i < n; i++) {
            new Player(moderator, "Player "+(i+1));
        }

        // Calling the moderator to generate a single number (10 times).
        for (int i = 0; i < 10; i++) {
            Thread T = new Thread(moderator);
            T.start();
            // The main thread must wait for the previous iteration before going on to the next one.
            T.join();
            if(resource.getFlag()==true){
                // We have found a winner with this number.
                moderator.winner(true);
                return;
            }
        }
        
        // No player won the game with those 10 numbers.
        moderator.winner(false);
    }
}

// User defined exception that exists only for this game.
class userDefinedException extends Exception{
    userDefinedException(){
        System.out.println("Sorry, you need atleast 2 players to play this game.");
    }
}
