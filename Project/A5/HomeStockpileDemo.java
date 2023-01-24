import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeStockpileDemo {

    public static void main(String[] args){

        System.out.println("---------- DEMO FOR HOME LOCATION COLLECTING PEACHES AND MAINTAINING A UNIQUE LIST FOR EACH PLAYER ----------");
        System.out.println("---------- AND FOR COLLECTING PEACHES  ----------\n");


        World world = new World();
        Home location = new Home(world.getHome().getPosition(), "Home Base", world.getPlayers(), new ArrayList<Peach>());
        world.locations[0][0] = location;

        List<Peach> peachList = new ArrayList<>();
        List<Peach> peachList2 = new ArrayList<>();

        PeachThief thief = new PeachThief(world,"Thief", world.locations[0][0], peachList, 50, RGB.RED  );
        PeachThief target = new PeachThief(world,"Target", world.locations[0][0], peachList2, 55, RGB.RED  );

// ----------- Filling lists with random peaches --------- \\
        int random = new Random().nextInt(100);               // Takes random peach between 0-peach size

        for(int i = 0; i < random; i+=1) {
            peachList2.add(new Peach(new Random().nextInt(20)));
        }
        int random2 = new Random().nextInt(35);               // Takes random peach between 0-peach size

        for(int i = 0; i < random2; i+=1) {
            peachList.add(new Peach(new Random().nextInt(20)));
        }

// Initialize home's stockpile
        target.getLocation().initializeStockpile(target);

// ------------  Target Adding to Stockpile ---------- \\
        System.out.println("     ----- Before Stockpiling -----");

        System.out.println("Thief has " + thief.getInventorySize() + " peaches in their inventory.");

        //while(world.getHome().)
        System.out.println(thief.getName() + "'s stockpile has " + thief.getLocation().getStockpile(thief));
        thief.play();

        System.out.println("\n     ----- After Stockpiling -----");
        System.out.println("Thief has " + thief.getInventorySize() + " peaches in their inventory.");
        System.out.println(thief.getName() + "'s stockpile has " + thief.getLocation().getStockpile(thief));

// ------------  Target Adding to Stockpile ---------- \\
        System.out.println("\n");
        System.out.println("     ----- Before Stockpiling -----");
        System.out.println(target.getName() + " has " + target.getInventorySize() + " peaches in their inventory.");

        //while(world.getHome().)
        System.out.println(target.getName() + "'s stockpile has " + target.getLocation().getStockpile(target));
        target.play();

        System.out.println("\n     ----- After Stockpiling -----");
        System.out.println("Target has " + target.getInventorySize() + " peaches in their inventory.");
        System.out.println(target.getName() + "'s stockpile has " + target.getLocation().getStockpile(target));

// ---------- Thief picks up some more peaches ------------\\
        for(int m = 0; m < new Random().nextInt(40); m++){
            thief.getLocation().addPeach(new Peach(new Random().nextInt(20)));
        }
        for(int d = 0; d < thief.getLocation().peachesAtLocation.size(); d++) {
            thief.play();
        }
        System.out.println("\nThief picked up "+ thief.getLocation().peachesAtLocation.size() + " peaches, and deposited them");

// ---------- Sum of Home's Stockpile ---------- \\
        System.out.println("\nThe home has a stockpile of " + thief.getLocation().getTotalStockpile(thief) );




    }


}