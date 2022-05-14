import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetInitiator;

public class clientBehaviour extends ContractNetInitiator {

    clientAgent agent;

    public clientBehaviour(Agent a, ACLMessage cfp) {
        super(a, cfp);
        this.agent = (clientAgent) a;
    }

    private void removeFromQueue() {

        LinkedBlockingQueue<String> queue = main.getWaitingClients();

        // Remove head from queue since it was the agent that just negotiated
        try {
            queue.take();
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.err.println("Thread interrupted!");
            System.exit(1);
        }

        // Check if all negotiations have ended
        if(queue.size() == 0) {
            Logger.getInstance().logPrint("Waiting clients queue emptied, no more orders to be performed!");
        }

        // Notify all threads waiting for queue
        synchronized(queue) {
            queue.notifyAll();
        }
    }

    private deliverAgentProposal getDeliverAgentProposal(ACLMessage msg) {

        deliverAgentProposal deliveragentProposal = null;
        try {
            deliveragentProposal = (deliverAgentProposal) msg.getContentObject();
        } catch(UnreadableException e) {
            e.printStackTrace();
            System.err.println("Error occured during the decoding of the content of the ACLMessage!");
            System.exit(1);
        }

        return deliveragentProposal;
    }

    @Override
    protected void handlePropose(ACLMessage propose, Vector v) {

        deliverAgentProposal deliverAgentProposal = getDeliverAgentProposal(propose);

        Logger.getInstance().logPrint("Deliver agent proposal has been received by " + agent.getName()); // TODO Add more info
    }

    @Override
    protected void handleRefuse(ACLMessage refuse) {

        Logger.getInstance().logPrint(refuse.getSender().getLocalName() + " refused to propose");
        removeFromQueue();
    }

    @Override
    protected void handleFailure(ACLMessage failure) {

        if(failure.getSender().equals(myAgent.getAMS())) Logger.getInstance().logPrint("Responder does not exist!");
        else Logger.getInstance().logPrint(failure.getSender().getLocalName() + " failed!");
    }

    @Override
    protected void handleAllResponses(Vector responses, Vector acceptances) {

        int bestProposal = Integer.MAX_VALUE;
        int bestProposerI = 0;
        AID bestProposer = null;
        ACLMessage accept = null;

        // Evaluate proposals
        for(int i = 0; i < responses.size(); i++) {

            ACLMessage msg = (ACLMessage) responses.get(i);
            if(msg.getPerformative() == ACLMessage.PROPOSE) {

                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                acceptances.addElement(reply);

                // Evaluate proposal using the agent's evaluator (personality)
                ClientEvaluator eval = agent.getEval();
                eval.setProposal(getDeliverAgentProposal(msg)); // TODO Do a deliver agent proposal
                int proposal = eval.evaluateProposal(msg);

                // Lower values are better since cost and distance are both best when minimised
                if((proposal != 0) && (proposal < bestProposal)) {
                    Logger.getInstance().logPrint("New best proposal by " + msg.getSender().getLocalName() + " evaluated at " + proposal);
                    bestProposal = proposal;
                    bestProposer = msg.getSender();
                    bestProposerI = i;
                    accept = reply;
                } else Logger.getInstance().logPrint("Worse proposal by " + msg.getSender().getLocalName() + " evaluated at " + proposal);
            }
        }

        // Accept the proposal of the best proposer
        if(accept != null) {
            Logger.getInstance().logPrint("Accepting proposal of " + bestProposer.getLocalName());
            accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            accept.setContent("Accepted proposal of " + bestProposer.getLocalName());
            // All proposals rejected, exit queue
        } else {
            Logger.getInstance().logPrint("Rejected all proposals");
            removeFromQueue();
        }
    }

    @Override
    protected void handleInform(ACLMessage inform) {

        Logger.getInstance().logPrint("negotiation with " + inform.getSender().getLocalName() + " ended");
        removeFromQueue();
    }
}