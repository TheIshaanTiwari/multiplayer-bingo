import java.util.ArrayList;

//These are the resources that are common to the moderator and all players. 
public class Resource {
    
    private Resource(){}
    private static Resource resource = new Resource();
    public static Resource getInstance(){ return resource; }
    
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private boolean flag = false;

    public synchronized void write(int i){
        numbers.add(i);
    }

    public synchronized void read(Player player){
        if(flag == true)
            return;
        player.check(numbers.get(numbers.size()-1));
        if(player.strike == 3)
            flag = true;
        return;
    }

    public synchronized boolean getFlag(){
        return flag;
    } 
}
