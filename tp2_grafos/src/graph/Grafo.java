package graph;

/**
 * Armazena um grafo direcionado e não ponderado utilizando uma matriz de
 * adjacencia
 * 
 * @author Rafael Duarte
 *
 */
public class Grafo {

	private int[][] matriz;
	private int numVertices;
	private int s;
	private int t;

	/**
	 * Cria um grafo sem arestas
	 * 
	 * @param numVertices número total de vértices no grafo
	 * @param s vértice de origem
 	 * @param t vértice de destino
	 */
	public Grafo(int numVertices, int s, int t) {
		this.numVertices = numVertices;
		this.s = s;
		this.t = t;
		this.matriz = new int[numVertices][numVertices];

		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++)
				matriz[i][j] = 0;
	}

	/**
	 * Adiciona no grafo uma aresta na direção (v,w)
	 * 
	 * @param v vértice de origem
	 * @param w vértice de destino
	 * @return true se adicionou a aresta ou else caso aresta já existente
	 */
	public boolean addAresta(int v, int w) {
		boolean resp = matriz[v][w] == 0;
		if (resp)
			matriz[v][w] = 1;
		return resp;
	}

	public int[][] getMatriz() {
		return this.matriz;
	}

	public int getS() {
		return this.s;
	}

	public int getT() {
		return this.t;
	}

	public int getNumVertices() {
		return this.numVertices;
	}

}
