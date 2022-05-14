import java.awt.*;

public class deliverAgentProposal {
    Point restaurantPosition;

    public deliverAgentProposal(){
        restaurantPosition = setPosition();
    }

    public Point setPosition(){
        int random_x = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        int random_y = (int)Math.floor(Math.random()*(20+1)); // Random from 0 to 20
        Logger.getInstance().logPrint("Points created " + random_x + ", " + random_y);

        return new Point(random_x, random_y);
    }
}
