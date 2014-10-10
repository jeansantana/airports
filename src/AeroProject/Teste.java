package AeroProject;

import java.util.List;

import Utils.AcaoVisitaImprimir;
import Utils.ArestaDuplicadaException;
import Utils.ArestaNaoEncontradaException;
import Utils.ChaveDuplicadaException;
import Utils.Grafo;
import Utils.GrafoLista;
import Utils.Vertice;
import Utils.VerticeNaoEncontradoException;

public class Teste {
	
	public static void impress(List <Cidade> v) {
		System.out.println("Lista de vertices: ");
		for (Vertice vertice : v) {
			System.out.println(vertice);
		}
		System.out.println("end impress\n");
	}
	
	public static void impressA(List<Estrada> list) {
		System.out.println("Lista de Arestas: ");
		for (Estrada aresta : list) {
			System.out.println(aresta);
		}
		System.out.println("end impress\n");
	}
	
	public static void main(String[] args) throws ChaveDuplicadaException, ArestaDuplicadaException, VerticeNaoEncontradoException, ArestaNaoEncontradaException {
		Grafo <Cidade, Estrada> g = new GrafoLista<Cidade, Estrada>();
		
		Cidade a = new Cidade(1, "Natal");
		Cidade b = new Cidade(2, "Guamaré");
		Cidade c = new Cidade(3, "Extremoz");
		Cidade d = new Cidade(4, "Macau");
		
		try {
			g.adicionarVertice(a);
			g.adicionarVertice(b);
			g.adicionarVertice(c);
			g.adicionarVertice(d);
		} catch (ChaveDuplicadaException e) {
			System.out.println(e);
		}
		
		Estrada e1 = new Estrada(1, a, b, 20);
		Estrada e2 = new Estrada(2, a, c, 20);
		Estrada e3 = new Estrada(3, a, d, 20);
		Estrada e4 = new Estrada(4, c, d, 20);
		Estrada e5 = new Estrada(5, b, d, 20);
		Estrada e6 = new Estrada(6, c, b, 20);
		
		try {
			
			g.adicionarAresta(e1);
			g.adicionarAresta(e2);
			g.adicionarAresta(e3);
			g.adicionarAresta(e4);
			g.adicionarAresta(e5);
			g.adicionarAresta(e6);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		impress(g.getVertices());
		impressA(g.getArestas());
		
		System.out.println("Aresta incidentes a: "+ a);
		
		try {
			List<Estrada> as = g.getArestasIncidentes(a);
			impressA(as);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Vertices adjacentes a "+ a);
		impress(g.getVerticesAdjacentes(a));
		
		try {
			g.removerAresta(new Estrada(8, new Cidade(23, "Patos"), b, 20));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//g.removerAresta(e1);
		//g.removerVertice(a);
		impress(g.getVertices());
		impressA(g.getArestas());
		System.out.println("Vertices adjacentes a "+ a);
		impress(g.getVerticesAdjacentes(b));
		System.out.println("bfs: ");
		g.bfs(b, new AcaoVisitaImprimir());
		System.out.println("dfs: ");
		g.dfs(b, new AcaoVisitaImprimir());
		
	}
	
}
