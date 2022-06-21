package teste;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import graph.CaminhosDisjuntos;
import graph.Grafo;

public class Teste {

	public static void main(String[] args) {
		CaminhosDisjuntos cDisjuntos = null;
		Grafo g = null;
		for (int i = 1; i <= 3; i++) {
			try (Scanner sc = new Scanner(new File("g" + i +".txt"))) {
				int numVer = sc.nextInt();
				int s = sc.nextInt();
				int t = sc.nextInt();

				g = new Grafo(numVer, s, t);

				while (sc.hasNext()) {
					int v = sc.nextInt();
					int w = sc.nextInt();

					g.addAresta(v, w);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			cDisjuntos = new CaminhosDisjuntos(g);
			Set<List<Integer>> caminhos = cDisjuntos.encontrarCaminhos();
			System.out.println("Numero maximo de caminhos = " + caminhos.size());
			System.out.println("Possiveis caminhos");
			for (List<Integer> list : caminhos) {
				System.out.print("\t");
				for (Integer integer : list) {
					System.out.print(integer + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

	}

}
