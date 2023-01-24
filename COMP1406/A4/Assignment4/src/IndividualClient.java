public class IndividualClient extends Client {

    public IndividualClient(String n) {
        super(n);
    }
    @Override
    public float makeClaim(int polNum) {
        Policy policy = getPolicy(polNum);
        if (policy == null || policy.isExpired()) {
            return 0;
        }
        if (policy.getClass().getName().equals(Policy.class.getName())) {
            cancelPolicy(polNum);
            return policy.getAmount();
        } else if (policy.getClass().getName().equals(DepreciatingPolicy.class.getName())) {
            ((DepreciatingPolicy) policy).depreciate();
            return policy.getAmount();
        } else if (policy.getClass().getName().equals(ExpiringPolicy.class.getName())) {
            return policy.getAmount();
        }
        return 0;
    }
}