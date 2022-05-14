import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.ContractNetResponder;

import java.awt.*;
import java.io.IOException;

public class deliverBehaviour extends ContractNetResponder {

    deliverAgent agent;

    public deliverBehaviour(Agent a, MessageTemplate mt) {
        super(a, mt);
        this.agent = (deliverAgent) a;
    }

    private ACLMessage buildProposal(ACLMessage cfp) {

        ACLMessage propose = cfp.createReply();
        propose.setPerformative(ACLMessage.PROPOSE);

        // Build proposal object
        Point deliverPosition = agent.getPosition();

        deliverAgentProposal proposalTerms = new deliverAgentProposal(deliverPosition);

        try {
            propose.setContentObject(proposalTerms);
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("I/O Exception in parking lot agent!");
            System.exit(1);
        }

        return propose;
    }

    private clientAgentProposal getClientAgentProposal(ACLMessage msg) {

        clientAgentProposal clientAgentProposal = null;
        try {
            clientAgentProposal = (clientAgentProposal) msg.getContentObject();
        } catch(UnreadableException e) {
            e.printStackTrace();
            System.err.println("Error occured during the decoding of the content of the ACLMessage!");
            System.exit(1);
        }

        return clientAgentProposal;
    }

    @Override
    protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {

        // Get the client agent proposal sent with the cfp
        clientAgentProposal clientProposal = getClientAgentProposal(cfp);

        Logger.getInstance().logPrint("Agent " + cfp.getSender().getLocalName() + " exists (?)");

        return buildProposal(cfp);

    }

    @Override
    protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {

        String proposer = accept.getSender().getLocalName();

        Logger.getInstance().logPrint(accept.getSender().getLocalName() + " accepted proposal ");

        // TODO check for deliver vacancies

        ACLMessage inform = accept.createReply();
        inform.setPerformative(ACLMessage.INFORM);
        return inform;
    }

    @Override
    protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
        Logger.getInstance().logPrint(reject.getSender().getLocalName() + " rejected proposal");
    }
}
