public class DepreciatingPolicy extends Policy {

    private float rate;
    public DepreciatingPolicy(float a, float r) {
        super(a);
        this.rate = r;
    }
    public float getRate() {
        return rate;
    }
    @Override
    public String toString() {
        return "Depreciating Policy: 000" + getPolicyNumber() + " amount: $" + getAmount() + " rate: " + getRate() * 100f
                + "%";
    }
    public boolean isExpired() {
        if (getAmount() == 0) {
            return true;
        } else {
            return false;
        }
    }
    public void depreciate() {
        float amountTakeaway;
        amountTakeaway = amount * rate;
        amount -= amountTakeaway;
    }

    public float handleClaim() {
        float amtBeforeDep = amount; // temporary store the amount as we need to return the amount of policy, before it was deprecated..
        depreciate();
        return amtBeforeDep;
    }

}