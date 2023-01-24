import java.util.ArrayList;
import java.util.List;
//Khaled's class
/*Rest of the methods for this class are in
PeachGrove class(bottom) and Home class.
*/
public class PeachHunter extends Player {
    protected List<Location> peachGroves;
    public PeachHunter(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb) {
        super(w, name, location, peaches, health, rgb);
        //
        //  Saved locations of PeachGroves
        //
        peachGroves = new ArrayList<Location>();
    }

    @Override
    public void play() {
        super.play();
        //
        // if health is less than 50%, put any excess peaches more than 25
        // in the current location.
        //
        if(this.getHealth() < 50) {
            while(this.peaches.size() > 25) {
                this.location.addPeach(this.getPeach());
            }
        }
    }
}