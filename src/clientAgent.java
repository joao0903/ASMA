import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;


public class clientAgent extends Agent {

    private String localName;

    private Point position;

    private ClientEvaluator eval;



    @Override
    protected void setup() {
        this.position = this.setPosition();

        this.logClientAgent();

        eval = selectClientEvaluator();

        localName = this.getAID().getLocalName();
        try {
            this.contractNetInitiate();
        } catch(StaleProxyException e) {
            e.printStackTrace();
            System.err.println("Exception initiating contract net!");
            System.exit(1);
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.err.println("Thread interrupted!");
            System.exit(1);
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("I/O Exception in car agent!");
            System.exit(1);
        }
    }

    private ClientEvaluator selectClientEvaluator() {

        return new LowerDistanceClientEvaluator(this);

    }

    public Point setPosition(){
        int random_x = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        int random_y = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        Logger.getInstance().logPrint("Points created " + random_x + ", " + random_y);

        return new Point(random_x, random_y);
    }

    private void logClientAgent(){
        Logger.getInstance().logPrint("Created Client Agent at Point (" + this.position.getX() + "," + this.position.getY() + ")");
    }

    private void contractNetInitiate() throws StaleProxyException, InterruptedException, IOException {

        // Wait for turn
        checkQueue();

        // Call for proposals
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);

        // Add all deliverers as receivers
        ArrayList<AgentController> deliverAgents = main.getDeliverAgents();
        for(AgentController agent : deliverAgents) {
            msg.addReceiver(new AID(agent.getName(), AID.ISGUID));
        }

        // Message protocol and content
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));

        // Set proposal parameters
        msg.setContentObject(new clientAgentProposal(this.position, this.position));

        addBehaviour(new clientBehaviour(this, msg));
    }


    private void checkQueue() throws InterruptedException {

        LinkedBlockingQueue<String> queue = main.getWaitingClients();

        // Block in queue if current head isn't the same ID as this agent
        if(!queue.peek().equals(localName)) {

            Logger.getInstance().logPrint("waiting in queue");
            synchronized(queue) {
                while(!queue.peek().equals(localName)) {
                    queue.wait();
                }
            }
        }

        Logger.getInstance().logPrint("my turn");
    }

    public ClientEvaluator getEval() {
        return eval;
    }

    public Point getPosition(){
        return this.position;
    }


}
