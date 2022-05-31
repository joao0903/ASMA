import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

public class BalancedClientEvaluator extends ClientEvaluator {


    public BalancedClientEvaluator(ClientAgent agent) {
        super(agent);
    }

    @Override
    protected int evaluateProposalCost(ACLMessage proposeMsg) {

        int cost =(int) (proposal.getDistanceCost() * 0.5 + proposal.getTimeCost() * 0.5);
        return  cost;

    }

}