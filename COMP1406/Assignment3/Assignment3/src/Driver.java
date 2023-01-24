public class Driver { // Driver class
    //declare attributes
    String license;
    String name;
    String street;
    String city;
    String province;
    //create constructor to initialize
    public Driver(String license, String name, String street, String city, String province) {
        this.license = license;
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
    }
    //zero-parameter constructor
    public Driver() {
        this("#L0678-67825-83940", "John Doe", "12 Elm St.", "Ottawa", "ON");
    }
    @Override
    public String toString() {
        return "#" + license + " " + name + " living at " + street + ", " + city + ", " + province;
    }

    //get License and name if needed
    public String getLicense() {
        return license;
    }
    public String getName() {
        return name;
    }
}