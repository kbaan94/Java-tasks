import java.util.ArrayList;

public class PeachPitDemo{
    public static void main(String[] args) {
        World w = new World();
        Player p = new Player(w, "cat", w.home, new ArrayList<Peach>(), 50, RGB.YELLOW);
        Player q = new Player(w, "dog", w.home, new ArrayList<Peach>(), 100, RGB.BLUE);
        w.addPlayer(p).addPlayer(q);


        //
        // Define home location.
        //
        Location home = new Home(w.home.getPosition(), "home", w.getHome().getPlayers(), w.getHome().peachesAtLocation);
        Location peachPit = new PeachPit(new Position(0, 1), "peachPit", new ArrayList<Player>(), new ArrayList<Peach>());
        // p.world.locations[0][1]=peachPit;
        //Location peachPit = new PeachPit(w, "peachPit", new Position(1,1), new ArrayList<Peach>());

        System.out.println("Home : " + w.getHome());
        System.out.println("------------------------------");
        System.out.println("Players at Home : " + w.getHome().getPlayers());
        System.out.println("------------------------------");


        /*System.out.println("Location of all players in world");
        for(Player pp: w.getPlayers()){
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
        }
        */

        //
        // add peaches to peachPit
        //

        System.out.println("Adding Peaches....");
        peachPit.addPeach(new Peach(60));
        peachPit.addPeach(new Peach(50));
        peachPit.addPeach(new Peach(40));
        System.out.println("Moving players in world");
        System.out.println("------------------------------");
/*
        System.out.println(p.getLocation().getPosition());
        System.out.println(q.getLocation().getPosition());

        p.move(3);
        q.move(3);
        System.out.println(p.getLocation());
        System.out.println(p.getLocation().peachesAtLocation.size());
        System.out.println(p.getLocation().getPosition());
*/
        // cat enters peachpit
        home.exit(p);
        peachPit.enter(p);
        // dog enters peachpit
        home.exit(q);
        peachPit.enter(q);

        p.move(Directions.DOWN);

        System.out.println("The health before entering pit: " + p.getHealth());

        while (p.getLocation().getPosition() != peachPit.getPosition()) {
            if (p.getLocation().getPosition().getY() < peachPit.getPosition().getY()) {
                p.move(3);
            }
            if (p.getLocation().getPosition().getX() < peachPit.getPosition().getX()) {
                p.move(1);
            }
            if (p.getLocation().getPosition() == peachPit.getPosition()) {
                return;
            }
        }

        System.out.println("The health after leaving pit: " + p.getHealth());


        System.out.println("  Players at Home : " + w.getHome().getPlayers());
        home.exit(q);
        peachPit.enter(q);
        System.out.println("  Players at Home : " + w.getHome().getPlayers());

        home.exit(p);
        peachPit.enter(p);
        System.out.println("Location of all players in world");

        for (Player pp : w.getPlayers()) {
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
        }
    }
}