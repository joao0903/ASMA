import java.awt.*;
import java.io.Serializable;

public class DeliverAgentProposal implements Serializable {
    Point restaurantPosition;
    double distanceCost;

    public DeliverAgentProposal(DeliverAgent deliver, Point restaurantPosition, double distanceClientRestaurant){
        this.restaurantPosition = restaurantPosition;
        this.distanceCost = (deliver.getPosition().distance(restaurantPosition) + distanceClientRestaurant)*deliver.getCostPerDistance();
    }


    public Point getRestaurantPosition() {
        return restaurantPosition;
    }

    public double getDistanceCost() {
        return distanceCost;
    }
}
