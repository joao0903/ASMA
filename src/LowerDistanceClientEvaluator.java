import jade.lang.acl.ACLMessage;

public class LowerDistanceClientEvaluator extends ClientEvaluator {

    public LowerDistanceClientEvaluator(ClientAgent agent) {
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