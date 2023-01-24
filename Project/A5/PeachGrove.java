import java.util.*;

public class PeachGrove extends Location{

    private int number;

    private Map<String, Integer> player = new HashMap<>();

    PeachGrove(Position p, String description, List<Player> people, List<Peach> peaches)

    {

        super(p, description, people, peaches);

        for( Player r:people)

        {

            if(player.containsKey(r.name))

            {

                number=player.get(r.name);

                for(int i=0;i<number;i++)

                {

                    Random rand = new Random();
                    int min=0,max=1;

                    int ran = rand.nextInt((max - min) + 1) + min;

                    if (ran == 1)

                        r.health-=5;

                }

                if(r.health<0)

                    r.health=0;

                else if(r.health > 0 && this.numberOfPeaches() > 0)

                {

                    System.out.println("how many peaches would you eat/take (total is "+this.numberOfPeaches()+") :");

                    Scanner sc=new Scanner(System.in);

                    int n=sc.nextInt();

                    for(int i=0;i<n;i++)

                    {

                        System.out.println("got Peach : "+this.getPeach());

                    }
                }

            }

        }

    }

// Method for PeacHunter class (Khaled)
    public void enter(PeachHunter p) {
        super.enter(p);
        //
        // if a PeachHunter enters any PeachGrove, remembers it
        //
        if (!p.peaches.contains(this))
            p.peachGroves.add(this);
        while (this.numberOfPeaches() > 0 && p.peaches.size() < 50) {
            p.peaches.add(this.getPeach());
        }
        //
        //   if PeachHunter has 50 or more peaches,
        //   return Home
        //
        if (this.numberOfPeaches() == 0)
            p.peachGroves.remove(this);
        if (p.peaches.size() >= 50) {
            p.setLocation(p.world.getHome());
        }
    }

}