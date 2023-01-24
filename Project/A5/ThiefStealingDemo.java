import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThiefStealingDemo {


    public static void main(String[] args){

        System.out.print("---------- DEMO FOR THIEF STEALING AND PEACH CONSUMING ----------\n");

        Position position = new Position(0,0);  // Assigning to same room
        World world = new World();
        Location location = new Location(position, "The Room", new ArrayList<Player>(), new ArrayList<Peach>());

        List<Peach> peachList = new ArrayList<>();
        List<Peach> peachList2 = new ArrayList<>();
        Peach examplePeach = new Peach(50);

        PeachThief thief = new PeachThief(world,"Thief", location, peachList, 50, RGB.RED  );
        world.addPlayer(thief);

        int random = new Random().nextInt(100);               // Takes random peach between 0-peach size

        for(int i = 0; i < random; i+=1) {
            peachList2.add(examplePeach);
        }

        PeachThief target = new PeachThief(world,"Target", location, peachList2, 5100, RGB.RED  );
        target.type = target.playerType[0];         // PlayerType 0/1 shows thief only targets Pitfinders and PeachHunters. Change to another to show the thief doesn't steal from them

        world.addPlayer(target);

        System.out.println("\n----- Before interacting -----");
        System.out.println("The Thief's peach list has a size of: " + thief.peaches.size());
        System.out.println("Player 2's Peach list has a size of: " + target.peaches.size());
        System.out.println("The Thief's health is: " + thief.getHealth() + "\n");

        int preliminaryHealth = thief.getHealth();
        thief.interact(target);
        System.out.println("Thief ate a peach. His health started at " + preliminaryHealth + " and is now " + thief.getHealth());                                                             // Testing code



        System.out.println("\n----- After interacting -----");
        System.out.println("The Thief's peach list has a size of: " + thief.peaches.size());
        System.out.println("Player 2's Peach list has a size of: " + target.peaches.size());
        System.out.println("The Thief's health is: " + thief.getHealth());


    }


}
