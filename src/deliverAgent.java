import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.swing.*;
import java.awt.*;


public class deliverAgent extends Agent{

    private Point position;


    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Receive the clients message
                ACLMessage msg = receive();
                if (msg!= null){
                    JOptionPane.showMessageDialog(null, "Message received :" + msg.getContent());
                }else block();
            }
        });
    }

}
