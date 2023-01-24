import java.util.*;

public class Home extends Location{

    HashMap<String, ArrayList<Peach> > stockpile = new HashMap<>();                   // Holding the peach stockpiles for each player
    Position home = new Position(0,0);

// Constructor
    public Home(Position p, String description, List<Player> people, List<Peach> peaches){
        super(new Position(0,0),description,people,peaches);
    }

// Initialize the Home's stockpiles of peaches
    public void initializeStockpile(Player p){
        for (int i = 0; i < p.getWorld().getPlayers().size(); i++) {                                   // HashMap to hold a list of peaches for every player
            stockpile.put(getPlayers().get(i).getName(), new ArrayList<Peach>());                      // Key = players name
        }
    }

// Adding to and getting the stockpile lists
    public void addStockpile(Player p){
        if(p.getLocation() == this) {
            if (peopleAtLocation.contains(p)) {                            // If at home
                while (p.peaches.size() > 0) {                             // and they have some peaches
                    stockpile.get(p.getName()).add(p.getPeach());          // Add the peaches to their stockpile
                }
            }
        }
    }

    public int getStockpile(Player p){                  // Getter for players stockpile
        if(stockpile.get(p.getName()) == null){
            return 0;
        }
        else {
            return stockpile.get(p.getName()).size();
        }
    }

    public int getTotalStockpile(Player p){             // Getter for sum of all players stockpiles
        int total = 0;
        for(int i = 0; i < p.getWorld().players.size(); i++){
            total += getStockpile(p.getWorld().players.get(i));
        }
        return total;
    }

// Constructor for Helper player
    public void callForHelp(Player p, Location location){
        ArrayList<Peach> helperPeaches = new ArrayList<>();                         // Giving the helper random peaches
        for(int i = 0; i<3; i+=1){
            helperPeaches.add(new Peach(new Random().nextInt(100)));
        }

        Helper helper = new Helper(p.getWorld(), p.playerType[3], p.world.getHome(), helperPeaches, 100, RGB.CYAN);         // Helper Constructor

        while(helper.getLocation() != p.getLocation()){                                                                            // Move towards the distraught player

            if(helper.getLocation().getPosition().getY() < location.getPosition().getY()){
                helper.move(3);
            }

            if(helper.getLocation().getPosition().getX() < location.getPosition().getX()){
                helper.move(1);
            }
        }

        helper.interact(p);                     // Give peaches & go back home
        System.out.println("\nPeaches were given to " + p.getName());
        helper.moveHome();
    }

// Khaled's method for PeachHunter class
    private Map<String, Integer> collectedPeaches = new HashMap<>();

    public void enter(PeachHunter p)
    {
        super.enter(p);
        //
        // if a PeachHunter enters Home, it leaves all peaches and returns to a PeachGrove
        //
        while(this.numberOfPeaches() > 0)
        {
            p.peaches.add(this.getPeach());
        }
        //
        //  returns to a peachGrove
        //
        if(! p.peachGroves.isEmpty())
        {
            p.setLocation(p.peachGroves.get(0));
        }
    }
}
