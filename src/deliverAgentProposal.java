import java.awt.*;
import java.io.Serializable;

public class deliverAgentProposal implements Serializable {
    Point restaurantPosition;
    double distanceCost;

    public deliverAgentProposal(Point deliverPosition, Point restaurantPosition, double distanceClientRestaurant){
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
