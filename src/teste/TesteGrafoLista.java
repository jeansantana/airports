package teste;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import AeroProject.Cidade;
import AeroProject.Estrada;
import Utils.GrafoLista;

public class TesteGrafoLista {

	private GrafoLista<Cidade, Estrada> g;
	private List<Cidade> cidades;
	private List<Estrada> estradas;
	
	@Before
	public void setUp() throws Exception {
		
		g = new GrafoLista<Cidade, Estrada>();
		cidades = new ArrayList<Cidade>();
		estradas = new ArrayList<Estrada>();
		
		Cidade c1 = new Cidade(1, "Extremoz");
		cidades.add(c1);
		Cidade c2 = new Cidade(2, "Natal");
		cidades.add(c2);
		Cidade c3 = new Cidade(3, "Guamaré");
		cidades.add(c3);
		Cidade c4 = new Cidade(4, "Parnamirim");
		cidades.add(c4);
		Cidade c5 = new Cidade(5, "Caucaia");
		cidades.add(c5);
		
		
		Estrada e1 = new Estrada(1, c1, c2, 200);
		estradas.add(e1);
		Estrada e2 = new Estrada(2, c1, c3, 15);
		estradas.add(e2);
		Estrada e3 = new Estrada(3, c3, c2, 300);
		estradas.add(e3);
		Estrada e4 = new Estrada(4, c1, c4, 40);
		estradas.add(e4);
		Estrada e5 = new Estrada(5, c2, c4, 500);
		estradas.add(e5);
		Estrada e6 = new Estrada(6, c3, c5, 10);
		estradas.add(e6);
		Estrada e7 = new Estrada(7, c4, c5, 25);
		estradas.add(e7);
		
		try {
			g.adicionarVertice(c1);
			g.adicionarVertice(c2);
			g.adicionarVertice(c3);
			g.adicionarVertice(c4);
			g.adicionarVertice(c5);
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
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	//testa se o objeto grafo é criado.
	@Test
	public void testGrafoLista() {
		assertNotNull(g);
	}

	//testa a adição de vértices
	@Test
	public void testAdicionarVertice() {
		
		boolean pass = true;
		
		for (int i = 0; i < cidades.size() && pass; i++) {
			
			if ( !cidades.get(i).equals(g.getVertice(i + 1)) ) {
				pass = false;
			}			
		}
		
		assertTrue(pass);
	}

	//Teste de adição de Aresta
	@Test
	public void testAdicionarAresta() {
		for (int i = 0; i < estradas.size(); i++) {
			assertTrue(!estradas.get(i).equals(g.getAresta(i + 1)));
		}
	}

	@Test
	public void testGetNumeroVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumeroArestas() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverVertice() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoverAresta() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertice() {
		
		for (int i = 0; i < cidades.size(); i++) {
			assertTrue( cidades.get(i).equals(g.getVertice(i + 1)) );			
		}
	}

	@Test
	public void testGetAresta() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetArestas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetArestasIncidentes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVerticesAdjacentes() {
		fail("Not yet implemented");
	}

	@Test
	public void testDfs() {
		fail("Not yet implemented");
	}

	@Test
	public void testBfs() {
		fail("Not yet implemented");
	}

	@Test
	public void testKruskalAirs() {
		fail("Not yet implemented");
	}

}
