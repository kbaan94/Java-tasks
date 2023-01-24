import java.util.ArrayList;
import java.util.List;

public class ThiefEatPeachDemo{

    public static void main(String[] args){

        Position position = new Position(0,0);

        World world = new World();
        Location location = new Location(position, "The Room", new ArrayList<Player>(), new ArrayList<Peach>());

        List<Peach> peachList = new ArrayList<>();
        Peach examplePeach = new Peach(50);

        PeachThief thief = new PeachThief(world,"Thief", location, peachList, 50, RGB.RED  );

        world.addPlayer(thief);

        System.out.println("Health " + thief.getHealth());

        thief.eatPeach(examplePeach);
        System.out.println("Health " + thief.getHealth());


    }

}
