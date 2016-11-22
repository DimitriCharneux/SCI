Attention, sous linux, remplacer les \ du tuto par des / et les ; par des :

executer :
java -cp lib/jade.jar:classes jade.Boot -gui -agents ping1:examples.PingAgent.PongAgent

plusieurs agent :
java -cp lib/jade.jar:classes jade.Boot -gui -agents "pong1:examples.PingAgent.PongAgent;pong2:examples.PingAgent.PongAgent"


compiler : 
javac -classpath lib/jade.jar -d classes src/examples/PingAgent/*.java

