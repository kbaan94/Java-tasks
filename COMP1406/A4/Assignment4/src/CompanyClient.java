public class CompanyClient extends Client {

    public CompanyClient(String n) {
        super(n);
    }
    @Override
    public float makeClaim(int polNum) {
        Policy policy = getPolicy(polNum);
        if (policy == null) {
            return 0;
        }
        return policy.handleClaim();
    }
}