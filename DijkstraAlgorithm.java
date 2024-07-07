package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * La classe DijkstraAlgorithm si occupa del calcolo del percorso minimo dato un nodo sorgente in ingresso attraverso
 * l'algoritmo di Dijkstra.
 * Sono presenti 2 metodi:
 * ShortestP
 * getShortestP
 */
public class DijkstraAlgorithm{
    /**
	 * Il metodo ShortestP inizialmente inizializza la distanza con la sorgente pari a 0, e crea un oggetto PriorityQueue
	 * (tramite la libreria java.utils). La coda di priorità viene memorizzata con un array. È una soluzione efficiente
	 * in questo caso perché si andranno a scansionare tutti i nodi del grafo in quanto l'obiettivo è risolvere il
	 * problema Single-Source Shortest Path (SSSP).
	 *
	 * Quindi viene iterato un ciclo while finché la coda di priorità è vuota. Sarà vuota quando finirà di visitare
	 * tutti gli archi.
	 * Quando trova un percorso più breve da un nodo alla sorgente, viene aggiornato il valore della distanza.
	 * Al termine verranno restituiti tutti i percorsi minimi.
	 * @param sourceNode nodo di partenza
	 */

     public void ShortestP(Node sourceNode){
		sourceNode.setDistance(0);
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceNode);
		sourceNode.setVisited(true);
       
		while(!priorityQueue.isEmpty()){
			Node NodeToCheck = priorityQueue.poll();
			for(Edge edge : NodeToCheck.getList()){
				Node targetNode = edge.getEndNode();

				if(!targetNode.getVisited()) {
					double newDistance = NodeToCheck.getDistance() + edge.getWeight();
					if( newDistance < targetNode.getDistance() ){
						priorityQueue.remove(targetNode);
						targetNode.setDistance(newDistance);
						targetNode.setPredecessorNode(NodeToCheck);
						priorityQueue.add(targetNode);
					}
				}
			}
			NodeToCheck.setVisited(true);
		}
	}

    /**
	 * Il metodo getShortestP serve ad ottenere il percorso minimo dato un nodo target, precedentemente calcolato da
	 * ShortestP.
	 * Viene restituita la lista di nodi LinkedList<Node> che contiene tutti i nodi necessari per arrivare al nodo
	 * target dal nodo source, con il minor costo.
	 * @param targetNode nodo target (finale del percorso)
	 * @return Lista dei nodi necessari per passare da source a targetNode
	 */
	public String getShortestP(Node targetNode){
		List<Node> path = new ArrayList<>();
		String string = " (";
		for(Node node = targetNode; node!=null; node=node.getPredecessorNode()){
			path.add(node);
		}
		Collections.reverse(path);
		for(int i=0; i < path.size(); i++){
			if(i!=0){
				string += ", ";
			}
			string = string + path.get(i).getName();
			
		}
		string += ");";
		return string;
	}
}