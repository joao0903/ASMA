import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.awt.*;


public class DeliverAgent extends Agent{

    private Point position;

    public void setup() {

        this.initRandomArgs();

        this.logDeliverAgent();

        this.contractNetRespond();
    }

    private void initRandomArgs() {

        // Assign values
        position = this.setPosition();

    }

    public Point setPosition(){
        int random_x = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        int random_y = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20

        return new Point(random_x, random_y);
    }

    private void logDeliverAgent() {

        Logger logger = Logger.getInstance();

        logger.logPrint("Created Deliverer Agent at Point " + position.getX() + "," + position.getY());

    }

    private void contractNetRespond() {

        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP));

        addBehaviour(new DeliverBehaviour(this, template));
    }

    public Point getPosition(){
        return this.position;
    }
}
