import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;

public class main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");

        ContainerController container = runtime.createAgentContainer(profile);
        Agent deliverer= new delivererAgent();
    }
}
