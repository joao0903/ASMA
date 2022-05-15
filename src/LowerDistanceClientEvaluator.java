import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class LowerDistanceClientEvaluator extends ClientEvaluator {


    public LowerDistanceClientEvaluator(ClientAgent agent) {
        super(agent);
    }

    @Override
    protected int evaluateProposalCost(ACLMessage proposeMsg) {

        // Check distance to restaurant
         return  (int)proposal.getDistanceCost();




        // TODO Add a cap to the distance travelled

    }
    protected int evaluateProposalTime(ACLMessage proposeMsg) {

        // Check distance to restaurant

        return  (int)proposal.getTimeCost();




        // TODO Add a cap to the distance travelled


    }
    protected int evaluateProposalFuel(ACLMessage proposeMsg) {

        // Check distance to restaurant

        return  (int)proposal.getEnoughFuel();




        // TODO Add a cap to the distance travelled


    }
}