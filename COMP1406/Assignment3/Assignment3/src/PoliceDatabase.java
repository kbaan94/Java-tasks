import java.util.*;
;
//PoliceDatabase class  in order step by steps    from question #2-4
public class PoliceDatabase{
    //create instance variables  and 3 arrays
    Vehicle[] vehicles;
    int numVehicles = 0;
    Driver[] drivers;
    int numDrivers;
    Infraction[] infractions;
    int numInfractions;
    //static constants for size limits
    private static int limitForDriver;
    static {
        limitForDriver = 2000;
    }
    private static int limitForVehicles;
    static {
        limitForVehicles = 1000;
    }
    private static int limitForInfractions;
    static {
        limitForInfractions = 800;
    }
    //empty constructor and initializing arrays constants
public PoliceDatabase() {
    drivers = new Driver[numDrivers];
    vehicles = new Vehicle[numVehicles];
    infractions = new Infraction[numInfractions];
}
    //checking registrations with owners, adding owners if not full, same with plates, and updating new ownership for
    //next 5 methods
public void registerDriver(Driver aDriver) {
    if (!(drivers.length != limitForDriver)) {
        return;
    }
    numDrivers ++ ;
    Driver[] newDriverArray;
    newDriverArray = new Driver[numDrivers];
    int i = 0;
    while (i < drivers.length) {
        newDriverArray[i] = drivers[i];
        i += 1;
    }
    newDriverArray[newDriverArray.length - 1] = aDriver;
    this.drivers = newDriverArray;
}
public void registerVehicle(Vehicle aVehicle, String license) {
    if (!(vehicles.length != limitForVehicles)) {
        return;
    }
    Driver owner = null;
    for (int i = drivers.length - 1; i >= 0; i--) {
        Driver driver = drivers[i];
        if (driver.getLicense().equals(license)) {
            owner = driver;
            break;
        }
    }
    numVehicles += 1;
    Vehicle[] newVehicleArray = new Vehicle[numVehicles];
    for (int i = 0; i < vehicles.length; i++) {
        newVehicleArray[i] = vehicles[i];
    }
    aVehicle.setOwner(owner);
    newVehicleArray[newVehicleArray.length - 1] = aVehicle;
    this.vehicles = newVehicleArray;
}
public void unregisterVehicle(String plate) {
    boolean isFound;
    isFound = false;
    for (int i = vehicles.length - 1; i >= 0; i--) {
        Vehicle vehicle;
        vehicle = vehicles[i];
        if (plate.equals(vehicle.getPlate())) {
            isFound = true;
            break;
        }
    }
    if (!isFound) {
        return;
    }
    Vehicle[] newVehicleArray;
    newVehicleArray = new Vehicle[vehicles.length - 1];
    int i = 0, k = 0;
    while (i < vehicles.length) {
        Vehicle vehicle = vehicles[i];
        if (!plate.equals(vehicle.getPlate())) {
            newVehicleArray[k++] = vehicles[i];
        }
        i ++ ;
    }
    this.vehicles = newVehicleArray;
    numVehicles -= 1;
}
public void reportStolen(String plate) {
    int i = vehicles.length - 1;
    while (i >= 0) {
        Vehicle vehicle = vehicles[i];
        if (plate.equals(vehicle.getPlate())) {
            vehicle.setReportedStolen(true);
            break;
        }
        i -- ;
    }
}
public void changeOwner(String plate, String license) {
    Driver owner = null;
    for (int i = drivers.length - 1; i >= 0; i--) {
        Driver driver = drivers[i];
        if (driver.getLicense().equals(license)) {
            owner = driver;
            break;
        }
    }
    for (int i = vehicles.length - 1; i >= 0; i--) {
        Vehicle vehicle = vehicles[i];
        if (vehicle.getPlate().equals(plate)) {
            vehicle.setOwner(owner);
        }
    }
}
    //Continuing to work on the PoliceDataBase class by adding infractions methods
public Infraction issueInfraction(String license, float amount, String description, Date date) {
    if (!(infractions.length != limitForInfractions)) {
        return null;                // return infraction  unless limit is reached
    }
    numInfractions += 1;
    Infraction[] newInfractionArray;
    newInfractionArray = new Infraction[numInfractions];
    for (int i = infractions.length - 1; i >= 0; i--) {
        newInfractionArray[i] = infractions[i];
    }
    Infraction infraction;
    infraction = new Infraction(amount, description, date);
    Driver d;
    d = null;
    for (int i = drivers.length - 1; i >= 0; i--) {
        Driver driver = drivers[i];
        if (license.equals(driver.getLicense())) {
            d = driver;
            break;
        }
    }
    infraction.setDriver(d);
    newInfractionArray[newInfractionArray.length - 1] = infraction;
    this.infractions = newInfractionArray;
    return infraction;
}
public boolean hasOutstandingInfractions(Driver d){
    for (int i = 0; i < infractions.length; i++) {
        Infraction infraction = infractions[i];
        Driver driver = infraction.getDriver();
        if (driver != null && driver.getLicense().equals(d.getLicense()) && infraction.isOutstanding()) {
            return true;
        }
    }
    return false;
}
public boolean shouldStopVehicle(String plate) {
    int i = 0;
    while (i < vehicles.length) {
        Vehicle vehicle;
        vehicle = vehicles[i];
        String p = vehicle.getPlate();
        Driver owner;
        owner = vehicle.getOwner();
        if (p.equals(plate) && (vehicle.isReportedStolen() || hasOutstandingInfractions(owner))) {
            return true;
        }
        i ++ ;
    }
    return false;
}
public void showInfractionsFor(String license) {
    Driver d = null;
    for (int i = drivers.length - 1; i >= 0; i--) {
        Driver driver;
        driver = drivers[i];
        if (license.equals(driver.getLicense())) {
            d = driver;
            break;
        }
    }
    if (d == null) {
        return;
    }
    int CountOutstandingInfractions = 0;
    for (int i = 0; i < infractions.length; i++) {
        Infraction infraction;
        Driver driver;
        infraction = infractions[i];
        driver = infraction.getDriver();
        if (!(driver == null) && driver.getLicense().equals(d.getLicense())) {
            System.out.println(infraction);
            if (infraction.isOutstanding()) {
                CountOutstandingInfractions++;
            }
        }
    }
    System.out.println("Total outstanding infractions = " + CountOutstandingInfractions);
}
public Driver[] cleanDrivers() {
        ArrayList<Driver> cleanDrivers = new ArrayList<>();
        for (int i = 0; i < drivers.length; i++) {
            Driver driver = drivers[i];
            boolean isInfracted = false;
            for (int j = infractions.length - 1; j >= 0; j--) {
                Infraction infraction = infractions[j];
                Driver infractedDriver = infraction.getDriver();
                if (infractedDriver != null && infractedDriver.getLicense().equals(driver.getLicense()))
                    isInfracted = true;
            }
            if (!isInfracted) cleanDrivers.add(driver);
        }
        Driver[] cleanDrivers1 = new Driver[cleanDrivers.size()];
        for (int i = cleanDrivers.size() - 1; i >= 0; i--) {
            cleanDrivers1[i] = cleanDrivers.get(i);
        }
      return cleanDrivers1;
  }
public void showInfractionReport(){
    LinkedHashMap<Driver, List<Float>> map;
    map = new LinkedHashMap<>();
    int i = 0;
    while (i < infractions.length){
        Driver driver = infractions[i].getDriver();
        if (!(driver != null)){
            i ++ ;
            continue;
        }
        if (map.containsKey(driver)){
            if (infractions[i].isOutstanding()){
                List<Float> list = map.get(driver);
                list.set(0, list.get(0) + 1);
                map.put(driver, list);
            } else{
                List<Float> list = map.get(driver);
                list.set(0, list.get(0) + 1);
                list.set(1, list.get(1) + infractions[i].getAmount());
                map.put(driver, list);
            }
        } else{
            List<Float> list;
            list = new ArrayList<>();
            list.add((float) 1);
            if (infractions[i].isOutstanding()){
                list.add((float) 0);
            } else
                list.add(infractions[i].getAmount());
            map.put(driver, list);
        }
        i ++ ;
    }
    for (Iterator<Map.Entry<Driver, List<Float>>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
        Map.Entry<Driver, List<Float>> entry = iterator.next();
        Driver key = entry.getKey();
        List<Float> value;
        value = entry.getValue();
        System.out.printf("%s: %s infractions, total paid = $%s%n", key.getName(), value.get(0), value.get(1));
    }
}
public static PoliceDatabase example() { // Register all drivers and their vehicles
    PoliceDatabase pdb = new PoliceDatabase();

    pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
            "1323 Kenaston St.", "Gloucester", "ON"));
    pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
            "32 Rideau Rd.", "Greely", "ON"));
    pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
            "1355 Louis Lane", "Gloucester", "ON"));
    pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
            "2348 Walkley Rd.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
            "2338 Carling Ave.", "Nepean", "ON"));
    pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
            "287 Bank St.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
            "200 St. Laurant Blvd.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
            "1654 Stonehenge Cres.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
            "98 Oak Blvd.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
            "3 Third St.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
            "434 Gatineau Way", "Hull", "QC"));
    pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
            "123 Thurston Drive", "Kanata", "ON"));
    pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
            "22 Colonel By Drive", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
            "17 Murray St.", "Nepean", "ON"));
    pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
            "3445 Bronson Ave.", "Ottawa", "ON"));
    pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
            "L0453-65433-87655");
    pdb.registerVehicle(new Vehicle("Pontiac", "Grand Prix", 2007, "dark green", "GO SENS"),
            "L0453-65433-87655");
    pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
            "L2333-45645-54354");
    pdb.registerVehicle(new Vehicle("Nissan", "Altima", 2017, "bergundy", "Y6P9O7"),
            "L1234-35489-99837");
    pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
            "L1948-87265-34782");
    pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
            "L0678-67825-83940");
    pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
            "L0122-43643-73286");
    pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
            "L6987-34532-43334");
    pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
            "L3345-32390-23789");
    pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
            "L3545-45396-88983");
    pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
            "L3545-45396-88983");
    pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
            "L5487-16576-38426");

    return pdb;
    }
}