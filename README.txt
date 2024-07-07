Per avviare il programma basta aprire un terminale nella cartella "out" e avviare il programma tramite il comando "java Main" o "java Test". Il codice sorgente si trova nella cartella "src".
#################################Main#################################
Il main si trova nella classe Main dentro la cartella "src".
Avviando il programma, verranno visualizzate delle domande che l'utente deve rispondere riguardo ai parametri di configurazione.
Domande:
 -"Vuoi usare i parametri di default?" true, se l'utente non vuole inserire i parametri. Il programma userà i parametri di default.
    false, se l'utente vuole inserire i parametri di default che desidera;
 -"Inserisci il numero di nodi:", l'utente dovrà digitare una cifra intera, corrispondente al numero di nodi che il programma terrà in considerazione;
 -"Vuoi che tutti gli archi hanno costo unitario?" true, se l'utente desidera un peso unitario per ogni arco creato dal programma,
    false, se l'utente desidera un peso casuale per ogni arco creato dal programma;
 -"Vuoi un grafo non orientato?", true, se l'utente desidera archi orientati,
    false, se l'utente desidera archi non orientati;
 -"Inserisci il massimo costo che può avere un arco:", l'utente dovrà digitare una cifra intera, corrispondente al peso massimo che può avere un arco;
 -"Inserisci la densità:", l'utente dovrà digitare una cifra intera, corrispondente al rapporto nodi/archi creati;

Parametri:
 - numberNodes: Il numero di nodi;
 - unitCost: se settato a true, il costo di ogni arco è pari ad 1, se settato a false, il costo sarà random;
 - orientedGraph: true se gli archi devono essere orientati (unidirezionali), false se gli archi non devono essere orientati (bidirezionali);
 - maxCostForEdge: costo massimo di un arco. Se unitcost è false, verrà assegnato ad ogni arco un costo random tra 0 e maxCostForEdge;
 - density: densità degli archi nel grafo. corrisponde al rapporto nodi/archi;
 - verbose: true se si desidera vedere tutti le informazioni dei percorsi trovati, false se si desidera analizzare soltanto le prestazioni dell'algoritmo.

++++++++++Parametri di Default++++++++++++++
V = 20;
unitCost = true;
orientedGraph = true;
maxCostForEdge = 10;
density= 2;
verbose = true;
+++++++++++++++++++++++++++++++++++++++++++++


#################################Testing#################################
È una classe realizzata per testare le prestazioni dell'algoritmo di Dijkstra. Implementa anch'esso un main:

Avviando il programma, verranno visualizzate delle domande che l'utente deve rispondere riguardo ai parametri di configurazione.
Domande:
 -"Vuoi usare i parametri di default?" true, se l'utente non vuole inserire i parametri. Il programma userà i parametri di default.
    false, se l'utente vuole inserire i parametri di default che desidera;
 -"Inserisci il numero di nodi:", l'utente dovrà digitare una cifra intera, corrispondente al numero di nodi che il programma terrà in considerazione;
 -"Vuoi che tutti gli archi hanno costo unitario?" true, se l'utente desidera un peso unitario per ogni arco creato dal programma,
    false, se l'utente desidera un peso casuale per ogni arco creato dal programma;
 -"Vuoi un grafo non orientato?", true, se l'utente desidera archi orientati,
    false, se l'utente desidera archi non orientati;
 -"Inserisci il massimo costo che può avere un arco:", l'utente dovrà digitare una cifra intera, corrispondente al peso massimo che può avere un arco;
 -"Inserisci la densità:", l'utente dovrà digitare una cifra intera, corrispondente al rapporto nodi/archi creati;
 -"Vuoi vedere i collegamenti tra i nodi?", true, se l'utente desidera vedere ogni collegamento che avviene durante l'esecuzione del programma,
    false, se l'utente non desidera vedere ogni collegamento che avviene durante l'esecuzione del programma;

Parametri:
- maxV: numero massimo di vertici con cui verranno effettuati i test. Verranno effettuati i test partendo da 10 aumentando di dieci volte tanto ogni volta fino a che il numero di vertici è <= a maxV.  Ad esempio se settato a 1000, verrà testato l'algoritmo con un numero di vertici pari a 10, 100 e 1000.
- numberOfTries: numero di test che deve effettuare ogni volta con la stessa configurazione di parametri. Serve ad ottenere test multipli così da poter ottenere una media.
- unitCostString: se settato a true, il costo di ogni arco è pari ad 1, se settato a false, il costo sarà random
- orientedGraphString: true se gli archi devono essere orientati (unidirezionali),false se gli archi non devono essere orientati (bidirezionali)
- maxCostForEdgeString: costo massimo di un arco. Se unitcost è false, verrà assegnato ad ogni arco un costo random tra 0 e maxCostForEdge
- verboseString: true se si desidera vedere tutti le informazioni dei percorsi trovati, false se si desidera analizzare soltanto le prestazioni dell'algoritmo.
- maxDensity: rappresenta il valore massimo della densità ovvero il rapporto tra nodi e archi.

Se sono presenti tutti e sei i parametri in String args[] allora verranno assegnati, altrimenti verranno utilizzati i parametri di default.

++++++++++Parametri di Default++++++++++++++
maxV = 1000;
numberOfTries = 3;
unitCostString = "false";
orientedGraphString = "true";
maxCostForEdgeString = "10";
verboseString = "false";
maxDensity = 4;
++++++++++++++++++++++++++++++++++++++++++++
