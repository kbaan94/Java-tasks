import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExpiringPolicy extends Policy {
    private Date expiryDate;
    public ExpiringPolicy(float a, Date d) {
        super(a);
        expiryDate = d;
    }
    public ExpiringPolicy(float a) {
        super(a);
        GregorianCalendar aCalendar;
        aCalendar = new GregorianCalendar();
        aCalendar.add(Calendar.YEAR, 1);
        expiryDate = aCalendar.getTime();
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public String toString() {
        SimpleDateFormat dateFormatter;
        dateFormatter = new SimpleDateFormat("MMMM d, yyyy '('h:mma')'");
        return "Expiring Policy: 000" + getPolicyNumber() + " amount: $" + amount + " expires: "
                + dateFormatter.format(getExpiryDate());
    }
    public boolean isExpired() {
        Date x = new Date();
        if (x.after(this.expiryDate)) {
            return true;
        } else if (x.before(this.expiryDate)) {
            return false;
        } else {
            return false;
        }
    }
    public float handleClaim() {
        if (isExpired()) {
            return 0;
        }
        return amount;
    }
}