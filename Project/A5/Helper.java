import java.util.List;

public class Helper extends Player {

    public Helper(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb) {
        super(w, name, location, peaches, health, rgb);
        super.type = playerType[3];
        w.addPlayer(this);
    }

    public void interact(Player p){                 // Gives the hurt player their peaches
        while(this.peaches.size() > 0){
            p.peaches.add(this.getPeach());
        }
    }

}
