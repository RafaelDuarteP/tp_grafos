package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Armazena um grafo e os vertices de origem e destino para encontrar caminhos
 * disjuntos
 * 
 * @author Rafael Duarte
 *
 */
public class CaminhosDisjuntos {

	private static final int NULL = -1;
	private static final int MAX_FLOW = 1;

	private int numVert;
	private Grafo grafo;
	private int s;
	private int t;

	public CaminhosDisjuntos(Grafo grafo) {
		this.grafo = grafo;
		this.s = grafo.getS();
		this.t = grafo.getT();
		this.numVert = grafo.getNumVertices();
	}

	/**
	 * Faz a busca em largura passando uma única vez pelos vértices e gera um vetor
	 * com os vértices pais
	 * 
	 * @param grafo o grafo sob qual irá fazer a busca
	 * @return um vetor com os pais de cada vertice (-1 se o pai for nulo)
	 */
	private int[] buscaLargura(int grafo[][]) {
		// inicia o vetor de pais com todos nulos
		int[] pais = new int[numVert];
		for (int i = 0; i < pais.length; i++) {
			pais[i] = NULL;
		}
		boolean[] visitado = new boolean[numVert];

		// cria a fila e adiciona o vértice s
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visitado[s] = true;

		while (!q.isEmpty()) {
			int u = q.remove();
			for (int v = 0; v < numVert; v++) {
				// verifica se tem aresta e se o vértice já foi visitado
				if (grafo[u][v] > 0 && visitado[v] == false) {
					q.add(v);
					pais[v] = u;
					visitado[v] = true;
				}
			}
		}

		return pais;
	}

	/**
	 * Gera uma uma rede fluxo com capacidade maxima = 1 e encontra os caminhos
	 * disjuntos em arestas armazenando a ordem dos vértices
	 * 
	 * @return um conjunto de listas dos vértices nos caminhos encontrados
	 */
	public Set<List<Integer>> encontrarCaminhos() {
		Set<List<Integer>> resp = new HashSet<>();
		int[][] matriz = grafo.getMatriz();
		int u, v;

		// Copia o grafo original para um novo grafo com a rede residual
		int[][] grafoResidual = new int[numVert][numVert];
		for (u = 0; u < numVert; u++)
			for (v = 0; v < numVert; v++)
				grafoResidual[u][v] = matriz[u][v];

		// Itera enquanto houver caminho de s para t
		for (int[] pais = buscaLargura(grafoResidual); pais[t] != NULL; pais = buscaLargura(grafoResidual)) {
			List<Integer> path = new ArrayList<>();
			// gera o caminho passando pelo vetor de pais a partir do vertice t
			for (int i = t; i != NULL; i = pais[i])
				path.add(0, i);

			// gera nova rede residual a partir dos vértices no caminho
			for (v = t; v != s; v = pais[v]) {
				u = pais[v];
				grafoResidual[u][v] -= MAX_FLOW;
				grafoResidual[v][u] += MAX_FLOW;
			}

			// adiciona o caminho à solução
			resp.add(path);
		}

		return resp;
	}
}
