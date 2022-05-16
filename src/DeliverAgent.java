import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.awt.*;


public class DeliverAgent extends Agent{

    private Point position;

    private float fuel;

    private float costPerDistance;

    private boolean available;

    private float speed;

    public void setup() {

        this.initRandomArgs();

        this.logDeliverAgent();

        this.contractNetRespond();
    }

    private void initRandomArgs() {

        // Assign values
        position = this.setPosition();
        fuel = 20;
        costPerDistance=1;
        available = true;
        speed= 2;

    }

    public Point setPosition(){
        int random_x = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        int random_y = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20

        return new Point(random_x, random_y);
    }

    private void logDeliverAgent() {

        Logger logger = Logger.getInstance();

        logger.logPrint("Created Deliverer Agent at Point (" + position.getX() + "," + position.getY() + ")");

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

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public float getCostPerDistance() {
        return costPerDistance;
    }

    public void setCostPerDistance(float costPerDistance) {
        this.costPerDistance = costPerDistance;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
