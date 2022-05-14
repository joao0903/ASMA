import jade.lang.acl.ACLMessage;

public class LowerDistanceClientEvaluator extends ClientEvaluator {

    /**
     * Constructs a car evaluator object responsible for assigning a value to
     * parking lot proposals. This evaluator chooses the lowest distance
     * available without considering the hourly cost of the parking lot.
     *
     * @param agent the evaluating car agent
     */
    public LowerDistanceClientEvaluator(clientAgent agent) {
        super(agent);
    }

    @Override
    protected int evaluateProposal(ACLMessage proposeMsg) {

        // Check distance to restaurant
        int dist = (int)proposal.getDistanceCost();

        // TODO Add a cap to the distance travelled

        return dist;
    }
}