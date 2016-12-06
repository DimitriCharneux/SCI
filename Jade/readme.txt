Attention, sous linux, remplacer les \ du tuto par des / et les ; par des :

executer :
java -cp lib/jade.jar:classes jade.Boot -gui -agents ping1:examples.PingAgent.PongAgent

plusieurs agent :
java -cp lib/jade.jar:classes jade.Boot -gui -agents "pong1:examples.PingAgent.PongAgent;pong2:examples.PingAgent.PongAgent"


compiler : 
javac -classpath lib/jade.jar -d classes src/examples/PingAgent/*.java


A faire
Un reseau d'accointance
Un reseau d'accointance en Jade
des agents qui interagissent entre eux (fait : EchangeTout.java)
envoi message "inventaire" pour connaitre ce qu'a une personne
envoi message "jeTeVendsBanane" pour proposer de vendre une banane contre une tomate
si il a une tomate, il accepte, sinon il refuse
si il accepte, je lui envoie sa banane si je l'ai, sinon je m'arrete

pareil pour "jeTeVendsTomate"

on peut améliorer en fesant qu'il vende le maximum de banane qu'il peuvent.




des agents qui interagissent entre eux dans un reseaux d'accointance


