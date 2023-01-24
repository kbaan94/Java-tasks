import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Khaled's class
public class PeachPit extends Location {
    protected  List<Player> visitedPlayers;
    public PeachPit(Position p, String description, List<Player> people, List<Peach> peaches) {
        super(p, description, people, peaches);
        visitedPlayers = new ArrayList<>();

    }

    @Override
    public void enter(Player p) {
        //
        // assume that this is the first time
        // the player enters PeachPit.
        //
        boolean firstTime = true;
        super.enter(p);
        //
        //   reduce the health
        //
        System.out.println("Health of player " + p.getName() + " before entering PeachPit : " + p.getHealth());
        p.setHealth(p.getHealth()/2);
        System.out.println("Health of player " + p.getName() + " after entering PeachPit : " + p.getHealth());
        //
        //  look for the player in the list of players visited PeachPit before
        //
        if(! visitedPlayers.isEmpty()) {
            if(visitedPlayers.contains(p))
                firstTime = false;
            else
                visitedPlayers.add(p);
        }
        else {
            visitedPlayers.add(p);
        }

        if(firstTime) {
            System.out.println("This is the first time player " + p.getName() + " enters PeachPit ");
            //
            // the player may take peaches
            //
            if(this.numberOfPeaches() > 0) {
                System.out.println("Do you want to take the peaches ? " );
                Scanner sc=new Scanner(System.in);
                String ans = sc.next();
                if(ans.compareToIgnoreCase("y") == 0)
                {
                    //
                    //
                    // remove peaches from PeachPit and take give them to the player
                    Peach pch;
                    while(this.numberOfPeaches() != 0)
                        p.peaches.add(this.peachesAtLocation.remove(0));
                }
            }
        }
        else {
            //
            //  move the player to the Home
            //
            this.exit(p);
            p.getWorld().getHome().enter(p);
            //p.setLocation(p.getWorld().getHome());
        }
    }
}
