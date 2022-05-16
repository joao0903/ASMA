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
}