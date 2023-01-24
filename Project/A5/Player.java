import java.util.List;

public class Player{
    protected World        world;    // world that player lives in
    protected String       name;     // name of player
    protected Location     location; // where in the world the player is
    protected List<Peach>  peaches;  // peaches
    protected int          health;   // health of player
    protected RGB          colour;   // colour of player (if graphics is used)

    // PlayerTypes for thief to recognize
    public final String[] playerType = {"PeachHunter", "PitFinder", "SmartyPants", "Helper", "PeachThief"};
    protected String type;

    /** Creates a player in the game
     *
     * @param world is the world that the player lives in
     * @param name is the name of the player
     * @param location is where in the world the player is
     * @param peaches is a list of peaches the player starts with
     * @param health is the health of the player (which may or may not be relevant in your game)
     * @param RGB is the colour of the player
     */
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb){
        this.world = w;
        this.name = name;
        this.location = location;
        location.getPlayers().add(this);
        this.peaches = peaches;
        this.health = health;
        this.colour = rgb;
    }

    /** Getter for a player's peach list size */
    public int getInventorySize(){ return this.peaches.size(); }

    /** Getter for a player's type */
    public String getType(){ return this.type;}

    /** Getter for a player's world */
    public World        getWorld(){ return world; }

    /** Getter for a player's name */
    public String       getName(){ return name; }

    /** Getter for a player's location */
    public Location     getLocation(){ return location; }

    /** Getter for a player's peach */
    public Peach  getPeach(){ return peaches == null ? null : peaches.remove(0); }

    /** Getter for peaches in thief. Steals a random peach */
    public Peach getPeach(int i){return peaches == null ? null : peaches.remove(i);}

    /** Getter for a player's health */
    public int          getHealth(){ return health; }


    /** This is the logic of the player.
     * It defines what they should do when given a chance to do somerthing
     */
    public void play(){
        if( health < 10 ){
            getHelp();
            return;
        }
/*
   if(getLocation().peopleAtLocation.size() > 1) {               // If there are more than one players in the location, let them interact
       Player other;
       for(int i = 0; i < getLocation().peopleAtLocation.size(); i+=1){
           if(getLocation().peopleAtLocation[i].equals(this) == false){
               other = getLocation().peopleAtLocation[i];
           }
       }


       this.interact(other);
   }*/
    }


    /** Moves a player from one location to a new location
     *
     * @param newLocation is the new location that the player will be moved to
     * @return true if the move was successful and false otherwise (e.g. when trying to move from one
     *         location to another that are not connected)
     */
    public boolean move(int direction){
        // move from current location to new location (if possible)
        world.move(this, direction);
        return false;
    }

    /** sets a player's current location
     *
     * @param location is the Location to set for the player
     */
    public void setLocation(Location location){
        this.location = location;
    }

    /** Setter for a player's health
     *
     * @param h is the new health of the player
     */
    public void setHealth(int h){
        this.health = h;
    }


    /** Allows for interaction with this player and another player
     * (presumably called from within the play method)
     *
     * @param p is a player that is interacting with this player
     */
    public void interact(Player p){
        // allows for some interaction with a player
    }

    /** ask for help when they need it */
    public void getHelp(){
        System.out.print("--- " + world.getHome());
        world.getHome().callForHelp(this, location);
    }

    @Override
    public String toString(){
        return name;
    }

    /** Two players are the same if they have the same name, location and health. */
    @Override
    public boolean equals(Object o){
        if( o instanceof Player){
            return this.name.equals( ((Player)o).name )
                    && this.location.equals( ((Player)o).location )
                    && this.health == ((Player)o).health;

        }else{
            return false;
        }
    }

    public void moveHome(){
        while(getLocation() != getWorld().getHome()){    // When not in the same room

            if(getLocation().getPosition().getX() > getWorld().getHome().getPosition().getX()){ // Move Up to x = 0
                move(0);
            }

            if(getLocation().getPosition().getY() > getWorld().getHome().getPosition().getY()){
                move(2);
            }

            if(getLocation() == getWorld().locations[0][0]){
                System.out.println("\n" + this.getName() + " is back at: " + this.getLocation().position);
                return;
            }
        }
    }


}