import jade.lang.acl.ACLMessage;

public class LowerDistanceClientEvaluator extends ClientEvaluator {

    public LowerDistanceClientEvaluator(ClientAgent agent) {
        super(agent);
    }

    @Override
    protected int evaluateProposal(ACLMessage proposeMsg) {

        // Check distance to restaurant
        int dist = (int)proposal.getDistanceCost();

        if(dist > clientAgent.getMaxDistance()) {
            Logger.getInstance().logPrint("Rejecting proposal of " + proposeMsg.getSender().getLocalName() + " because of distance");
            return 0;
        }



        // TODO Add a cap to the distance travelled

        return dist;
    }
}