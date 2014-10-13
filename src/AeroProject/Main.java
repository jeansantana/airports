package AeroProject;

import Utils.Grafo;
import Utils.GrafoLista;

public class Main {
	
	public static void main(String[] args) {
		Grafo <Cidade, Estrada> g = new GrafoLista<Cidade, Estrada>();
		
		/*outro exemplo Cidade c1 = new Cidade(1, "Extremoz");
		Cidade c2 = new Cidade(2, "Natal");
		Cidade c3 = new Cidade(3, "Guamaré");
		Cidade c4 = new Cidade(4, "Parnamirim");
		Cidade c5 = new Cidade(5, "Caucaia");
		
		Estrada e1 = new Estrada(1, c1, c2, 20);
		Estrada e2 = new Estrada(2, c4, c5, 40);
		Estrada e3 = new Estrada(3, c3, c2, 30);		
		
		try {
			g.adicionarVertice(c1);
			g.adicionarVertice(c2);
			g.adicionarVertice(c3);
			g.adicionarVertice(c4);
			g.adicionarVertice(c5);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			g.adicionarAresta(e1);
			g.adicionarAresta(e2);
			g.adicionarAresta(e3);
		} catch (Exception e) {
			System.out.println(e);
		}*/
		Cidade c1 = new Cidade(1, "Extremoz");
		Cidade c2 = new Cidade(2, "Natal");
		Cidade c3 = new Cidade(3, "Guamaré");
		Cidade c4 = new Cidade(4, "Parnamirim");
		Cidade c5 = new Cidade(5, "Caucaia");
		
		Estrada e1 = new Estrada(1, c1, c2, 200);
		Estrada e2 = new Estrada(2, c1, c3, 15);
		Estrada e3 = new Estrada(3, c3, c2, 300);
		Estrada e4 = new Estrada(4, c1, c4, 40);
		Estrada e5 = new Estrada(5, c2, c4, 500);
		Estrada e6 = new Estrada(6, c3, c5, 10);
		Estrada e7 = new Estrada(7, c4, c5, 25);
		
		try {
			g.adicionarVertice(c1);
			g.adicionarVertice(c2);
			g.adicionarVertice(c3);
			g.adicionarVertice(c4);
			g.adicionarVertice(c5);
			g.adicionarVertice(c3);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			g.adicionarAresta(e1);
			g.adicionarAresta(e2);
			g.adicionarAresta(e3);
			g.adicionarAresta(e4);
			g.adicionarAresta(e5);
			g.adicionarAresta(e6);
			g.adicionarAresta(e7);
			g.adicionarAresta(e3);
			g.adicionarAresta(new Estrada(8, new Cidade(6, "Bogotá"), c1, 45));
		} catch (Exception e) {
			System.err.println(e);
		}
		
		int P = 100;
		System.out.println("Custo mínimo da obra é: R$ " + g.kruskalAirs(P));
		
	}
}
