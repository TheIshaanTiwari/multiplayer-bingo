import java.util.ArrayList;

public class Player implements Runnable {

    public final String name;
    // Only the player must have the ability to edit their card at any point of the game.
    private ArrayList<Integer> playerCard = new ArrayList<Integer>();
    public int strike;
    
    Output output;
    public void setOutput(Output o){ this.output = o; }
    Resource resource = Resource.getInstance();
    Moderator moderator;

    Player(Moderator moderator, String name) {
        this.name = name;
        this.moderator = moderator;
        this.moderator.addPlayer(this);
        this.playerCard = moderator.cardGenerator();
        output.println(name + " added successfully. Their card is: " + playerCard);
    }

    void check(int val){
        for(int i=0; i<10; i++){
            if(playerCard.get(i) == val){
                // Setting a card to -1 is equivalent to striking a number out.
                playerCard.set(i, -1);
                strike++;
                return;
            }
        }
    }

    @Override
    public void run() {
        try{Thread.sleep(1 * 1000);} catch(InterruptedException e){ System.out.println("Unexpected Interrupt"); }
        resource.read(this); 
        // output.println(name + " has: " + strike + " matches.");     //Thread Test
    }
}