import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class main {

    private static ArrayList<AgentController> clientAgents = new ArrayList<AgentController>();
    private static ArrayList<AgentController> deliverAgents = new ArrayList<AgentController>();

    // Queue of cars awaiting negotiation
    private static LinkedBlockingQueue<String> waitingClients = new LinkedBlockingQueue<String>();


    public static void main(String[] args) {
        System.out.println("IN");

        Logger.getInstance().initLog(Logger.LogMethod.BOTH);

        Runtime runtime = Runtime.instance();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.CONTAINER_NAME, "MainContainer");
        profile.setParameter(Profile.GUI, "true");

        ContainerController container = runtime.createMainContainer(profile);

        try {
            for(int i = 0; i < 3; i++) {
                clientAgents.add(container.createNewAgent("ClientAgent" + i, "clientAgent", null));
                //Logger.getInstance().logPrint("Created Client Agent " + i);
                waitingClients.add("ClientAgent" + i);

                deliverAgents.add(container.createNewAgent("DeliverAgent" + i, "deliverAgent", null));
                //Logger.getInstance().logPrint("Created Deliver Agent " + i);

            }

            for(AgentController agent : clientAgents) {
                agent.start();
            }

            for(AgentController agent : deliverAgents) {
                agent.start();
            }

        }catch (StaleProxyException e){
            e.printStackTrace();
        }

        System.out.println("OUT");
    }

    public static LinkedBlockingQueue<String> getWaitingClients() {
        return waitingClients;
    }

    public static ArrayList<AgentController> getDeliverAgents() {
        return deliverAgents;
    }
}

