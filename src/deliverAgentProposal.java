import java.awt.*;
import java.io.Serializable;

public class deliverAgentProposal implements Serializable {
    Point restaurantPosition;
    double cost;

    public deliverAgentProposal(Point deliverPosition){
        restaurantPosition = this.setPosition();
        cost = Math.abs(restaurantPosition.distance(deliverPosition));
    }

    public Point setPosition(){
        int random_x = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        int random_y = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20

        return new Point(random_x, random_y);
    }
}
