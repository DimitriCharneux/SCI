/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package tp;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.util.Logger;

/**
 * This agent implements a simple Ping Agent that registers itself with the DF and 
 * then waits for ACLMessages.
 * If  a REQUEST message is received containing the string "ping" within the content 
 * then it replies with an INFORM message whose content will be the string "pong". 
 * 
 * @author Tiziana Trucco - CSELT S.p.A.
 * @version  $Date: 2010-04-08 13:08:55 +0200 (gio, 08 apr 2010) $ $Revision: 6297 $  
 */
public class EchangeTout extends Agent {

	private Logger myLogger = Logger.getMyLogger(getClass().getName());

	private class WaitPingAndReplyBehaviour extends CyclicBehaviour {
		private int nbBanane = (int)(Math.random() * 10), nbTomate = (int)(Math.random() * 10);
		public WaitPingAndReplyBehaviour(Agent a) {
			super(a);
		}

		public void action() {
			ACLMessage  msg = myAgent.receive();
			if(msg != null){
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.REQUEST);

				if(msg.getPerformative()== ACLMessage.REQUEST){
					String content = msg.getContent();
					if((content != null) && (content.indexOf("inventaire") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a " + nbBanane + " bananes et " + nbTomate + " tomates.");
						reply.setContent("Agent "+getLocalName()+" a " + nbBanane + " bananes et " + nbTomate + " tomates.");
					} 
					
					/**** Ventes de bananes ****/
					else if ((content != null) && (content.indexOf("jeTeVendsBanane") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une offre pour une banane de "+msg.getSender().getLocalName());
						if(nbTomate > 0){
							myLogger.log(Logger.INFO, "il a " + nbTomate + " tomates, il va donc acheter une banane.");
							reply.setContent("jacheteTesBanane");
						} else {
							myLogger.log(Logger.INFO, "il n'a plus de tomate, il refuse l'offre");
							reply.setContent("nonmerci");
						}
					}else if ((content != null) && (content.indexOf("jacheteTesBanane") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une reponse positif de "+msg.getSender().getLocalName());
						if(nbBanane > 0){
							myLogger.log(Logger.INFO, "il " + nbBanane + " bananes, il va donc acheter une tomate.");
							nbBanane--;
							nbTomate++;
							reply.setContent("confirmeVenteBanane");
						} else {
							myLogger.log(Logger.INFO, "il n'a plus de banane, il peut pas les vendre !");
							return;
						}
					}else if ((content != null) && (content.indexOf("confirmeVenteBanane") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une banane de "+msg.getSender().getLocalName() + ", il envoie une tomate");
						nbBanane++;
						nbTomate--;
						return;
						
					} 
					
					
					/**** Ventes de tomates ****/
					else if ((content != null) && (content.indexOf("jeTeVendsTomate") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une offre pour une tomate de "+msg.getSender().getLocalName());
						if(nbBanane > 0){
							myLogger.log(Logger.INFO, "il a " + nbBanane + " bananes, il va donc acheter une tomate.");
							reply.setContent("jacheteTesTomate");
						} else {
							myLogger.log(Logger.INFO, "il n'a plus de banane, il refuse l'offre");
							reply.setContent("nonmerci");
						}
					}else if ((content != null) && (content.indexOf("jacheteTesTomate") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une reponse positif de "+msg.getSender().getLocalName());
						if(nbTomate > 0){
							myLogger.log(Logger.INFO, "il " + nbTomate + " tomates, il va donc acheter une banane.");
							nbBanane++;
							nbTomate--;
							reply.setContent("confirmeVenteTomate");
						} else {
							myLogger.log(Logger.INFO, "il n'a plus de tomate, il peut pas les vendre !");
							return;
						}
					}else if ((content != null) && (content.indexOf("confirmeVenteTomate") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" a recu une tomate de "+msg.getSender().getLocalName() + ", il envoie une tomate");
						nbBanane--;
						nbTomate++;
						return;
						
					} 
					
					
					
					else if ((content != null) && (content.indexOf("nonmerci") != -1)){
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" s'est vu refuser l'offre par "+msg.getSender().getLocalName());
						return;
					} else{
						myLogger.log(Logger.INFO, "Agent "+getLocalName()+" - pas compris la requete ["+content+"] received from "+msg.getSender().getLocalName());
						return;
					}

				}
				else {
					myLogger.log(Logger.INFO, "Agent "+getLocalName()+" - pas recu de request ["+ACLMessage.getPerformative(msg.getPerformative())+"] received from "+msg.getSender().getLocalName());
					return;   
				}
				send(reply);
			}
			else {
				block();
			}
		}
	} // END of inner class WaitPingAndReplyBehaviour


	protected void setup() {
		// Registration with the DF 
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();   
		sd.setType("PongAgent"); 
		sd.setName(getName());
		sd.setOwnership("TILAB");
		dfd.setName(getAID());
		dfd.addServices(sd);
		try {
			DFService.register(this,dfd);
			WaitPingAndReplyBehaviour PingBehaviour = new  WaitPingAndReplyBehaviour(this);
			addBehaviour(PingBehaviour);
		} catch (FIPAException e) {
			myLogger.log(Logger.SEVERE, "Agent "+getLocalName()+" - Cannot register with DF", e);
			doDelete();
		}
	}
}
