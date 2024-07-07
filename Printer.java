package view;

import algorithm.DijkstraAlgorithm;
import algorithm.Graph;
import algorithm.Node;
public class Printer {
    /**
     *  Possono essere assegnati degli argomenti all'esecuzione del main:
     *   	- numberNodes: Il numero di nodi
     *  	- unitCost: se settato a true, il costo di ogni arco è pari ad 1, se settato a false, il costo sarà random
     *  	- orientedGraph: true se gli archi devono essere orientati (unidirezionali),false se gli archi non
     *  	devono essere orientati (bidirezionali)
     *   	- maxCostForEdge: costo massimo di un arco. Se unitcost è false, verrà assegnato ad ogni arco un costo
     *   	random tra 0 e maxCostForEdge
     *  	- density: densità degli archi nel grafo. corrisponde al rapporto nodi/archi.
     *  	- verbose: true se si desidera vedere tutti le informazioni dei percorsi trovati, false se si desidera
     *  	analizzare soltanto le prestazioni dell'algoritmo.
     *
     *  Se sono presenti tutti e sei i parametri allora verranno assegnati, altrimenti verranno utilizzati i parametri
     *  definiti nel codice.
     *
     *  Dopodiché viene creato e generato un oggetto Graph in maniera casuale e un oggetto DijkstraAlgorithm.
     *
     *  Prima dell'esecuzione dell'algoritmo, viene avviato un timer.
     *  Poi viene fatto eseguire l'algoritmo di ricerca del percorso minimo dato un nodo di partenza (nodo 0).
     *  Una volta completato, il timer verrà fermato.
     *
     *  Infine verranno stampati i risultati.
     */
    


    public Printer(int numberNodes, boolean unitCost, boolean orientedGraph, int maxCostForEdge, double density, boolean verbose) {

        long totalTime =0;
        long averageTime;
        long startTime =0;
        long stopTime =0;

        Node source;
        Graph graph = new Graph(numberNodes,density, unitCost, maxCostForEdge, orientedGraph, verbose);
        graph.createGraph();
        source = graph.getAdjNode(0);


        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        startTime = System.nanoTime(); //avvio il timer
        dijkstraAlgorithm.ShortestP(source); //avvio l'algoritmo di Dijkstra
        stopTime = System.nanoTime(); //fermo il timer
        totalTime = totalTime + (stopTime - startTime);

        if(verbose) { //stampo tutte le informazioni se verbose è settato a true
            for (int j = 0; j < numberNodes; j++) {
                if (graph.getAdjNode(j).getDistance() != Double.MAX_VALUE) {
                    System.out.println("Shortest Path " + 0 + " to " + j + ": {Cost: " + graph.getAdjNode(j).getDistance() + "}" +
                        dijkstraAlgorithm.getShortestP(graph.getAdjNode(j)));
                }else{
                    System.out.println("Shortest Path " + 0 + " to " + j + ": Not Found");
                }
            }
        }
        averageTime = totalTime / numberNodes;

        if(verbose) { //stampo tutte le informzaioni se verbose è settato a true
            System.out.println("Total time Used for calculate " + numberNodes + " path: " + totalTime + " nanosec");
            System.out.println("Average time Used for find a path: " + averageTime + " nanosec");
            System.out.println();
        }else{
            System.out.print("     \t"+totalTime);
        }
    }

    
}
