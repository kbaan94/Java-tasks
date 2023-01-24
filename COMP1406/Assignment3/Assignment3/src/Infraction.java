import java.util.Date;
public class Infraction { //Infraction class
    //declare attributes
    float amount;
    String description;
    Date dateIssued;
    boolean outstanding;
    Driver driver;
    public Infraction() {
        this(0, null, null);
    }
    public Infraction(float amount, String description, Date dateIssued) {
        this.amount = amount;
        this.description = description;
        this.dateIssued = dateIssued;
        this.outstanding = true;
        this.driver = null;
    }
    @Override
    // return status of infraction fee
    public String toString() {
        String outstandingResult = "";
        if(outstanding) outstandingResult = "[OUTSTANDING]";
        else outstandingResult = "[PAID IN FULL]";
        return String.format("$%s Infraction on %s%s", String.format("%.2f", amount), String.format("%tc", dateIssued), outstandingResult);
    }
    // paying the infraction along with get and sets on the side
    public void pay() {
        if(outstanding) outstanding = false;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    public boolean isOutstanding() {
        return outstanding;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}