import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Moderator implements Runnable {

    // Keeping the range of values for the game adjustable
    int upperBound = 51;
    int lowerBound = 0;
    // lowerBound + rand.nextInt(upperBound - lowerBound)

    Resource resource = Resource.getInstance();
    Random rand = new Random();
    private ArrayList<Player> players = new ArrayList<>();
    public Output output;

    // Making Moderator a Singleton class.
    private Moderator() {
        int op = 2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nPLEASE SELECT YOUR PREFERENCE.\nPRESS 1: PRINT PROCEEDINGS TO SCREEN.\nPRESS 2: WRITE PROCEEDINGS TO FILE.");
        try{ op = Integer.parseInt(br.readLine()); }catch(Exception e){ e.printStackTrace(); }
        try{ br.close(); }catch(Exception e){ e.printStackTrace(); }
        this.setOutput(op);
    }
    private static Moderator moderator = new Moderator();
    public static Moderator getInstance() {
        return moderator;
    }

    // Setting up where the moderator is supposed to record the proceedings of the game.
    // Using strategy design pattern. Reffer to Output.java to know more.
    private void setOutput(int op){
        if(op == 1) output = SysOP.getInstance();
        else if (op == 2) output = FileOP.getInstance();
        else {
            System.out.println("Invalid Input. Writing output to file.");
            output = FileOP.getInstance();
        }
    }

    // Adding the players to the game. Making Moderator the observed class.
    public void addPlayer(Player p) {
        players.add(p);
        p.setOutput(this.output);
    }

    // Generating random number and notifying all observers accordingly.
    @Override
    public void run() {
        int val = this.lowerBound + rand.nextInt(this.upperBound - this.lowerBound);
        // int val = 0;    //Test
        output.println("\nThe moderator generated: " + val);
        resource.write(val);

        Thread[] threads = new Thread[players.size()];
        for (int i=0; i<players.size(); i++) {
            threads[i] = new Thread(players.get(i));
            threads[i].start();
            if(resource.getFlag()==true)
                return;
        }
        // Ensuring moderator waits for a certain minimum time before generating the next number.
        // Change this number to 60 * 1000 in case a 1 minute wait is wanted.
        try { Thread.sleep(3 * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
        // Ensuring moderator waits for all the players to read the number before generating the next number.
        try {
            for(int i=0; i<players.size(); i++)
                threads[i].join();
        } catch(InterruptedException e){ System.out.println("Unecpected interrupt. Moderator might not have waited for all players."); }
    }

    // Moderator generating the cards for players.
    // we may also optionally store this for verification. I am not doing so.
    public ArrayList<Integer> cardGenerator(){
        ArrayList<Integer> card = new ArrayList<>();
        for(int i=0; i<10; i++)
            card.add(this.lowerBound + rand.nextInt(this.upperBound - this.lowerBound));
            // card.add(0);    //Test
        return card;
    }

    // Displaying the results.
    void winner(boolean winner){
        output.print("\n");
        if(winner == true){
            String s = "";
            output.println("We have a winner!");
            for(Player player : players){
                output.println(player.name + " had " + player.strike + " matches.");
                if(player.strike == 3){
                    s = player.name;
                }
            }
            output.println("Winner: " + s + "\n");
        }
        else{
            output.println("Nobody won that game.\n");
        }
        output.close();
    }
    
}