import java.awt.Point;

import jade.lang.acl.ACLMessage;

public abstract class ClientEvaluator {

    clientAgent clientAgent;
    deliverAgentProposal proposal;

    /**
     * Constructs a car evaluator object responsible for assigning a value to
     * parking lot proposals.
     *
     * @param agent the evaluating car agent
     */
    public ClientEvaluator(clientAgent agent) {
        this.clientAgent = agent;
    }

    /**
     * Returns an integer value representing how good a parking lot agent
     * proposal. 0 is used as an automatic rejection.
     *
     * @param proposeMsg the parking lot agent proposal
     * @return the value associated with the proposal
     */
    protected abstract int evaluateProposal(ACLMessage proposeMsg);

    /**
     * Returns the rounded euclidean distance between 2 points.
     *
     * @param clientPosition the car agent coordinates
     * @param deliverPosition the parking lot agent coordinates
     * @return the rounded euclidean distance between the 2 agents
     */
    protected int distanceToDeliver(Point clientPosition, Point deliverPosition) {
        return (int) clientPosition.distance(deliverPosition);
    }

    /**
     * @param proposal the parking lot proposal to set
     */
    public void setProposal(deliverAgentProposal proposal) {
        this.proposal = proposal;
    }
}

