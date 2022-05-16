import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class LowerTimeClientEvaluator extends ClientEvaluator {


    public LowerTimeClientEvaluator(ClientAgent agent) {
        super(agent);
    }

    @Override
    protected int evaluateProposalCost(ACLMessage proposeMsg) {

        int time = (int)proposal.getTimeCost();

        return time;


    }
}
