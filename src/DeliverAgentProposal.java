import java.awt.*;
import java.io.Serializable;

public class DeliverAgentProposal implements Serializable {
    Point restaurantPosition;
    double distanceCost;

    public DeliverAgentProposal(Point deliverPosition, Point restaurantPosition, double distanceClientRestaurant){
        this.restaurantPosition = restaurantPosition;
        this.distanceCost = deliverPosition.distance(restaurantPosition) + distanceClientRestaurant;
    }


    public Point getRestaurantPosition() {
        return restaurantPosition;
    }

    public double getDistanceCost() {
        return distanceCost;
    }
}
