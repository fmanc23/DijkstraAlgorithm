import view.Printer;
import java.util.Scanner;

/**
 * Classe realizzata per testare le prestazioni dell'algoritmo di Dijkstra.
 * Implementa anch'esso un main.
 */
public class Test {
    /**
     * Possono essere assegnati degli argomenti all'esecuzione del main:
     *      - maxNumberNodes: numero massimo di nodi con cui verranno effettuati i test. Verranno effettuati i test partendo
     *      da 10 aumentando di dieci volte tanto ogni volta, fino a che il numero di nodi è <= a maxNumberNodes.  Ad esempio se
     *      settato a 1000, verrà testato l'algoritmo con un numero di nodi pari a 10, 100 e 1000.
     *      - numberOfTries: numero di test che deve effettuare ogni volta con la stessa configurazione di parametri.
     *      Serve ad ottenere test multipli così da poter ottenere una media.
     *      - unitCost: se settato a true, il costo di ogni arco è pari ad 1, se settato a false, il costo sarà random
     *      - orientedGraph: true se gli archi devono essere orientati (unidirezionali),false se gli archi non
     * 	    devono essere orientati (bidirezionali)
     *      - maxCostForEdge: costo massimo di un arco. Se unitcost è false, verrà assegnato ad ogni arco un costo
     * 	    random tra 0 e maxCostForEdge
     *      - verbos: true se si desidera vedere tutti le informazioni dei percorsi trovati, false se si desidera
     * 	    analizzare soltanto le prestazioni dell'algoritmo.
     * 	    - maxDensity: rappresenta il valore massimo della densità ovvero il rapporto tra nodi e archi.
     *
     * Se sono presenti tutti e sei i parametri allora verranno assegnati, altrimenti verranno utilizzati i parametri
     * definiti nel codice.
     */
    
    private static int numberOfTries = 3;
    private static boolean unitCost = false;
    private static boolean orientedGraph = true;
    private static int maxCostForEdge = 10;
    private static boolean verbose = false;
    private static double maxDensity = 4;
    private static double densityStart = 1.0;
    private static int numberNodesStart = 10;
    private static int maxNumberNodes = numberNodesStart*3;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        getInput();
        maxNumberNodes = numberNodesStart*10*10*10;
        boolean firstRun=true;

        for(int numberNodes = numberNodesStart; numberNodes<=maxNumberNodes; numberNodes *= 10) {
            
            for (double density = densityStart; density <= maxDensity; density = density + 0.5){
                for (int tries = 0; tries < numberOfTries; tries++) {
                    if(verbose == true){
                        System.out.println("NODES:"+ numberNodes
                                + " DENSITY:"+ density
                                + " ORIENTED:"+ orientedGraph
                                + " UNITCOST:"+ unitCost
                                + " MAXCOST:"+ maxCostForEdge
                                + " VERBOSE:"+ verbose);
                    }else{
                        if(firstRun){
                            System.out.print("NODES:  DENSITY:    ORIENTED:    UnitCost:    MAXCOST:    VERBOSE:");
                            for(int k=0;k<numberOfTries-1;k++){
                                System.out.print("     Time"+(k+1)+"(ns):");
                            }
                            System.out.println("        Time"+numberOfTries+"(ns):");
                        }
                        if(tries==0) {
                            System.out.print(numberNodes + "      "
                                    + density + "         "
                                    + orientedGraph + "        "
                                    + unitCost + "        "
                                    + maxCostForEdge + "          "
                                    + verbose);
                        }
                    }
                    firstRun=false;
                    new Printer(numberNodes, unitCost, orientedGraph, maxCostForEdge, density, verbose);
                }
                System.out.println();
            }

        }
    }

    private static void getInput(){
        System.out.println("Vuoi usare i parametri di default?");
        Boolean defaultParameters = SCANNER.nextBoolean();
        if(!defaultParameters){
            //do{
                System.out.println("Inserisci il numero di nodi:");
                numberNodesStart = SCANNER.nextInt();
            //}while(numberNodesStart > maxNumberNodes);*/
            System.out.println("Vuoi che tutti gli archi hanno costo unitario? (true o false) ");
            unitCost = SCANNER.nextBoolean();
            System.out.println("Vuoi un grafo non orientato? (false: archi unilaterali. true: archi bilaterali) ");
            orientedGraph = SCANNER.nextBoolean();
            if(!unitCost){
                System.out.println("Inserisci il massimo costo che può avere un arco: ");
                maxCostForEdge = SCANNER.nextInt();
            }
            System.out.println("Inserisci la densità (non maggiore di 4.0):");
            densityStart = SCANNER.nextDouble();
            System.out.println("Vuoi vedere i collegamenti tra i nodi? (true o false) ");
            verbose = SCANNER.nextBoolean();
        }
    }
}
