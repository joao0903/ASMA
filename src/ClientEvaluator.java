import java.awt.Point;

import jade.lang.acl.ACLMessage;

public abstract class ClientEvaluator {

    ClientAgent clientAgent;
    DeliverAgentProposal proposal;


    public ClientEvaluator(ClientAgent agent) {
        this.clientAgent = agent;
    }


    protected abstract int evaluateProposalCost(ACLMessage proposeMsg);


    protected int distanceToDeliver(Point clientPosition, Point deliverPosition) {
        return (int) clientPosition.distance(deliverPosition);
    }


    public void setProposal(DeliverAgentProposal proposal) {
        this.proposal = proposal;
    }

    protected abstract int evaluateProposalTime(ACLMessage proposeMsg);
    protected abstract int evaluateProposalFuel(ACLMessage proposeMsg);

}

