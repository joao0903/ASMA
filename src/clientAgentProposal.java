
import java.awt.Point;
import java.io.Serializable;


public class clientAgentProposal implements Serializable{

    Point clientPosition;
    Point restaurantPosition;


    public clientAgentProposal(Point clientPosition, Point restaurantPosition){
        this.clientPosition = clientPosition;
        this.restaurantPosition = restaurantPosition;
    }

    public Point getClientPosition() {
        return clientPosition;
    }

    public Point getRestaurantPosition() {
        return restaurantPosition;
    }



}
