import java.util.Date;

public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;
    private static int NEXT_CLIENT_ID = 1;
    private String name;
    private int id;
    protected Policy[] policies;
    protected int numPolicies;
    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }
    public String getName() { return name; }
    public int getId() { return id; }
    public Policy[] getPolicies() { return policies; }
    public int getNumPolicies() { return numPolicies; }
    @Override
    public String toString() { return String.format(this.getClass().getName() + ": %06d amount: %s", id, name); }
    public float totalCoverage() {
        float totalCoverage = 0;
        int i;
        for (i = 0; i < policies.length; i++) {
            Policy policy = policies[i];
            if (policy == null) // as policy can be null,
                break;
            totalCoverage += policy.getAmount();
        }
        return totalCoverage;
    }
    public Policy addPolicy(Policy p) {
        boolean isAdded = false;
        int i = 0;
        while (i < policies.length) {
            if (policies[i] == null) {
                policies[i] = p;
                numPolicies++;
                isAdded = true;
                break;
            }
            i++;
        }
        if (isAdded) return p;
        else { return null; }
    }
    public Policy openPolicyFor(float amt) {
        Policy policy = new Policy(amt);
        return addPolicy(policy);
    }
    public Policy openPolicyFor(float amt, float rate) {
        Policy policy = new DepreciatingPolicy(amt, rate);
        return addPolicy(policy);
    }
    public Policy openPolicyFor(float amt, Date expire) {
        Policy policy = new ExpiringPolicy(amt, expire);
        return addPolicy(policy);
    }

    public Policy getPolicy(int polNum) {
        int i = 0;
        while (i < policies.length) {
            if (policies[i] != null && policies[i].getPolicyNumber() == polNum) {
                return policies[i];
            }
            i++;
        }
        return null;
    }

    public boolean cancelPolicy(int polNum) {
        Policy policy = getPolicy(polNum);
        if (policy == null) {
            return false;
        }
        Policy lastPolicy = getLastPolicy();
        int indexOfLastPolicy = indexOfLastPolicy();
        int i = 0;
        while (i < policies.length) {
            if (policies[i] == policy) {
                policies[i] = lastPolicy;
                policies[indexOfLastPolicy] = null;
                numPolicies--;
                return true;
            }
            i++;
        }
        return true;
    }
    private Policy getLastPolicy() {
        for (int i = 0; i < policies.length; i++) {
            if (policies[i] == null) {
                return policies[i - 1];
            }
        }
        return policies[policies.length - 1];
    }
    private int indexOfLastPolicy() {
        for (int i = 0; i < policies.length; i++) {
            if (policies[i] == null) {
                return (i - 1);
            }
        }
        return policies.length - 1;
    }
    public abstract float makeClaim(int polNum);
}