import java.awt.*;
import java.io.Serializable;

public class DeliverAgentProposal implements Serializable {
    Point restaurantPosition;
    double distanceCost;
    double time;
    double enoughFuel;

    public DeliverAgentProposal(DeliverAgent deliver, Point restaurantPosition, double distanceClientRestaurant){
        this.restaurantPosition = restaurantPosition;
        this.distanceCost = (deliver.getPosition().distance(restaurantPosition) + distanceClientRestaurant)*deliver.getCostPerDistance();
        this.time = (deliver.getPosition().distance(restaurantPosition) + distanceClientRestaurant)/deliver.getSpeed();
        this.enoughFuel = deliver.getFuel()- deliver.getPosition().distance(restaurantPosition) + distanceClientRestaurant ;
    }


    public Point getRestaurantPosition() {
        return restaurantPosition;
    }

    public double getDistanceCost() {
        return distanceCost;
    }
    public double getTimeCost() {
        return time;
    }
    public double getEnoughFuel() {return enoughFuel;}
}
