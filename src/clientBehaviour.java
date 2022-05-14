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
}