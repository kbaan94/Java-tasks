import java.util.List;
import java.math.*;
import java.util.Random;

public class PeachThief extends Player {

    public PeachThief(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb) {
        super(w, name, location, peaches, health, rgb);
        super.type = playerType[4];
    }

    public void eatPeach(Peach otherPeach){             // Eating a peach action
        if(otherPeach.bad == true){
            this.setHealth(getHealth() - otherPeach.getRipeness());
        }
        else {
            this.setHealth(getHealth() + otherPeach.getRipeness());   // Health = current objects health + last peach object's health. Remove the peach from the other persons list
        }
    }

    public void eatPeach(){
        Peach peach = getPeach();

        if(peach.bad == true){
            setHealth(getHealth() - peach.getRipeness());
        }
        else{
            this.setHealth(getHealth() + peach.getRipeness());
        }
    }

    @Override
    public void interact(Player p) {

        // If the other player is not a thief
        if(p.getType() == playerType[0] || p.getType() == playerType[1]){      // Compare if player is a hunter or a pitfinder
            int peachesStolen = 0;

            if(p.getLocation() == this.location){       // If players are in the same location.

                if(p.peaches.size() > 0){               // If other player has one or more peaches

                    double probabilityToSteal = 1;         // variable to determine if stealing was a success. Default 1 so while loop iterates at least once.
                    int randomPeach;

                    while(probabilityToSteal > 0 && p.peaches.size() > 0){                          // While stealing
                        probabilityToSteal = Math.random();                                         // Random number between 0-1
                        randomPeach = new Random().nextInt(p.peaches.size());                       // Takes random peach between 0-peach size

                        if(probabilityToSteal <= 0.75){                                             // 75% chance to steal was a success
                            if(peachesStolen == 0){                                                 // If this is the first peach stolen

                                eatPeach(p.getPeach(randomPeach));

                                peachesStolen += 1;
                            }
                            else{
                                peaches.add(p.getPeach(randomPeach));                               // If success, add to peach list
                                peachesStolen+=1;
                            }

                        }
                        else{
                            probabilityToSteal = 0;                                                 // Unsuccessful
                        }

                    }
                    System.out.println("The Thief stole " + peachesStolen + " peaches from " + p.getName());                                                                // Testing code
                }
                else{
                    return;         // If player has no peaches
                }
            }


        }


    }

    public void play(){                 // Call helper if necessary
        super.play();

        if(getLocation().peachesAtLocation.size() > 0){          // Take peach from here if there is one to take
            this.peaches.add(getLocation().peachesAtLocation.remove(0));
        }

        location.addStockpile(this);

        if(getHealth() < 25 && this.peaches.size() > 0){           // Eat a peach
            eatPeach();
        }

        if(getLocation().peopleAtLocation.size() > 1){
            for(int i = 0; i<getLocation().peopleAtLocation.size(); i++){
                if(!this.equals(getLocation().peopleAtLocation.get(i))){
                    this.interact(getLocation().peopleAtLocation.get(i));       // Steal from the people here
                }
            }
        }

        if(this.peaches.size() > 0){                                                                        // If there are enough peaches to go deposit
            if(this.location.getPosition() != getWorld().home.getPosition()) {
                this.moveHome();
            }
            if(this.location.getPosition() == getWorld().getHome().getPosition()) {
                System.out.println("Stockpile peaches: " + getLocation().getStockpile(this));
                getLocation().addStockpile(this);
                System.out.println("Stockpile: " + this.getName() + " in " + getLocation());
                System.out.println("\n" + this.getName() + "'s stockpile now contains " + getLocation().getTotalStockpile(this) + " peaches");
            }
        }
        else{                                                               // Go look for more peaches
            /*int theDirection = new Random().nextInt(3);          // Move in a random direction

            // Checking for out of bounds
            if(theDirection == 0 && this.getLocation().getPosition().getY() == getWorld().locations.length){
                move(1);;
            }
            else if(theDirection == 1 && this.getLocation().getPosition().getY() == 0){
                move(0);
            }
            else if(theDirection == 2 && this.getLocation().getPosition().getX() == 0){
                move(3);
            }
            else if(theDirection == 3 && this.getLocation().getPosition().getX() == getWorld().locations.length){
                move(2);
            }
            else{
                if(move(theDirection) == false){

                }
                //move(theDirection);
            }*/

        }


    }
}
