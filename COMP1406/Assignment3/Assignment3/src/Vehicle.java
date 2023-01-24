public class Vehicle { //Vehicle class
    //declare attributes
    String make;
    String model;
    int year;
    String color;
    String plate;
    Driver owner;
    boolean reportedStolen;
    // create constructor to initialize
    public Vehicle(String make, String model, int year, String color, String plate) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.plate = plate;
        this.owner = null;
        this.reportedStolen = false;
    }
    // create empty constructor that will call the appropriate values
    public Vehicle() {
        this("Honda", "Civic", 1998, "blue", "X5T6Y8");
    }
    @Override
    //return string
    public String toString() {
        return String.format("A %s %d %s %s with plate %s", color, year, make, model, plate);
    }
    //get n set (get/set)
    public Driver getOwner() {
        return owner;
    }
    public void setOwner(Driver owner) {
        this.owner = owner;
    }
    public boolean isReportedStolen() {
        return reportedStolen;
    }
    public String getPlate() {
        return plate;
    }
    public void setReportedStolen(boolean reportedStolen) {
        if (reportedStolen) this.reportedStolen = true;
        else this.reportedStolen = false;
    }
}