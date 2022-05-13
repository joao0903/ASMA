import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;

public class main {

    private static ArrayList<AgentController> clientAgents = new ArrayList<AgentController>();
    private static ArrayList<AgentController> deliverAgents = new ArrayList<AgentController>();


    public static void main(String[] args) {
        System.out.println("IN");

        Runtime runtime = Runtime.instance();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.CONTAINER_NAME, "MainContainer");
        profile.setParameter(Profile.GUI, "true");

        ContainerController container = runtime.createMainContainer(profile);

        try {
            for(int i = 0; i < 10; i++) {
                clientAgents.add(container.createNewAgent("AgentNumber" + i, "clientAgent", null));
            }

            for(AgentController agent : clientAgents) {
                agent.start();
            }

        }catch (StaleProxyException e){
            e.printStackTrace();
        }

        System.out.println("OUT");
    }
}
