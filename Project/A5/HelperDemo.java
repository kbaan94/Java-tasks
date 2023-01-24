import java.util.ArrayList;
import java.util.List;

public class HelperDemo{

    public static void main(String[] args) {

// Make a small world with a home, and then have a player in distress. Have the helper go deliver them peaches, then go back to the home. Then have the recipient go home and stockpile their peaches.

        World world = new World();
        Home location = new Home(world.getHome().getPosition(), "Home Base", world.getPlayers(), new ArrayList<Peach>());
        world.home = location;

        ArrayList<Peach> peachList = new ArrayList<>();

        PeachThief thief = new PeachThief(world, "Friend In Need", world.locations[2][2], peachList, 20, RGB.RED);

// ----- Hurt the player with a bad peach to trigger ----- \\
        thief.peaches.add(new Peach(15, true));         // Adding a peach giving negative health
        System.out.println("Player's health is: "+ thief.getHealth() + ", so Player will consume a peach.");
        thief.play();
        System.out.println("Player consumed a peach, and their health is now: " + thief.getHealth() + "\n");

// ----- Calling the helper because the player is hurt ----- \\
        thief.play();
        System.out.println("\n\nPlayer consumed a peach, and their health is now: " + thief.getHealth());
    }
}
