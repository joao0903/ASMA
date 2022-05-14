
import java.awt.Point;
import java.io.Serializable;


public class clientAgentProposal implements Serializable{

    Point clientPosition;
    Point restaurantPosition;
    double distanceClientRestaurant;


    public clientAgentProposal(Point clientPosition, Point restaurantPosition){
        this.clientPosition = clientPosition;
        this.restaurantPosition = restaurantPosition;
        this.distanceClientRestaurant = clientPosition.distance(restaurantPosition);
    }

    public Point getClientPosition() {
        return clientPosition;
    }

    public Point getRestaurantPosition() {
        return restaurantPosition;
    }

    public double getDistance_client_restaurant() {
        return distanceClientRestaurant;
    }
}
