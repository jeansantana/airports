package teste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import AeroProject.Cidade;
import AeroProject.Estrada;
import Utils.ArestaDuplicadaException;
import Utils.ArestaNaoEncontradaException;
import Utils.ChaveDuplicadaException;
import Utils.GrafoLista;
import Utils.VerticeNaoEncontradoException;

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

	/**
	 *  testa se o objeto grafo é criado.
	 */
	@Test
	public void testGrafoLista() {
		assertNotNull(g);
	}

	/**
	 *  testa a adição de vértices
	 */
	@Test
	public void testAdicionarVertice() {

		boolean pass = true;

		for (int i = 0; i < cidades.size() && pass; i++) {

			if (!cidades.get(i).equals(g.getVertice(i + 1))) {
				pass = false;
			}
		}

		assertTrue(pass);
	}
	/**
	 * Teste de adição de de vértice com Chave duplicada 
	 * @throws ChaveDuplicadaException
	 */
	@Test(expected = ChaveDuplicadaException.class)  
	public void testAdicionarVerticeChaveDuplicada() throws ChaveDuplicadaException {
		g.adicionarVertice(new Cidade(1, "FakeCity"));
	}

	/**
	 *  Teste se as arestas em G, foram inseridas corretamente
	 */
	@Test
	public void testAdicionarAresta() {
		for (int i = 0; i < estradas.size(); i++) {
			assertTrue(estradas.get(i).equals(g.getAresta(i + 1)));
		}
	}
	/**
	 * Teste de adição de Aresta duplicada
	 * @throws ArestaDuplicadaException
	 * @throws VerticeNaoEncontradoException
	 */
	@Test(expected = ArestaDuplicadaException.class)
	public void testAdicionarArestaDuplicada() throws ArestaDuplicadaException, VerticeNaoEncontradoException {
		g.adicionarAresta(estradas.get(1));
	}
	
	/**
	 * Testa a tolerancia do software quando da insercao de uma aresta a(v, w) de um vertice nao existente (v ou w)
	 * @throws ArestaDuplicadaException
	 * @throws VerticeNaoEncontradoException
	 */
	@Test(expected = VerticeNaoEncontradoException.class)
	public void testAdicionarArestaVerticeNaoEncontrado() throws ArestaDuplicadaException, VerticeNaoEncontradoException {
		g.adicionarAresta(new Estrada(10, new Cidade(12, "Macau"), new Cidade(13, "Diogo Lopes"), 67));
	}

	/**
	 * Número de vértices, sem os vértices duplicados, da estrutura 
	 */
	@Test
	public void testGetNumeroArestas() {
		assertEquals(7, g.getNumeroArestas());
	}

	/**
	 * RemoverVertice, com critério de número de vértices, remove os vértices 
	 *um a um e testa se o número de arestas do grafo está dimuindo, juntamento com uma 
	 *lista que contém todos os vértices que foram armazenados na estrutura de grafo
	 */
	@Test
	public void testRemoverVerticeNumeroDeVertices() throws ArestaNaoEncontradaException,
			VerticeNaoEncontradoException {
		for (int i = 1; cidades.size() != 0; i++) {
			g.removerVertice(g.getVertice(i));
			cidades.remove(0);
			assertEquals(cidades.size(), g.getNumeroVertices());
		}
		
	}

	/**
	 * RemoverVertice: critério número de arestas, quando o vértice é removido,
	 * suas arestas incidentes também são removidas.
	 * 
	 * BUG: O vértice v é removido, mas só são removidas (n-1) arestas incidentes ao vértice v
	 * 
	 */
	@Test
	public void testRemoverVerticeNumeroDeArestasRemovidas() throws VerticeNaoEncontradoException, ArestaNaoEncontradaException {
		Cidade c = g.getVertice(2);
		
		List<Estrada> arestasIncidentes = g.getArestasIncidentes(c);
		g.removerVertice(c);
		assertEquals(estradas.size() - arestasIncidentes.size(), g.getNumeroArestas());
	}
	
	/**
	 * Remover vértice não existente
	 * @throws VerticeNaoEncontradoException
	 * @throws ArestaNaoEncontradaException
	 */
	
	@Test(expected = VerticeNaoEncontradoException.class)
	public void testRemoverVerticeNaoEncontrado() throws VerticeNaoEncontradoException, ArestaNaoEncontradaException {
		g.removerVertice(new Cidade(90, "Qualquer"));
	}
	
	/**
	 * Critério do Número de Arestas
	 * @throws ArestaNaoEncontradaException
	 */
	@Test
	public void testRemoverArestaNumeroArestas() throws ArestaNaoEncontradaException {
		while (estradas.size() != 0) {

			g.removerAresta(estradas.get(0));
			estradas.remove(0);

			assertEquals(estradas.size(), g.getNumeroArestas());
		}
	}
	
	/**
	 * Teste de remorção de uma aresta não econtrada no grafo
	 */
	@Test(expected = ArestaNaoEncontradaException.class)
	public void testRemoverArestaNaoEncontrada() throws ArestaNaoEncontradaException {
		g.removerAresta(new Estrada(13, new Cidade(19, "Currais Novos"), new Cidade(26, "Gauarabira"), 78));
	}

	/**
	 * Busca o vértice na lista de vértices V, do grafo G = (V, E) 
	 */
	@Test
	public void testGetVertice() {

		for (int i = 0; i < cidades.size(); i++) {
			assertTrue(cidades.get(i).equals(g.getVertice(i + 1)));
		}
	}

	/**
	 * Busca a aresta na lista de arestas E, do grafo G = (V, E) 
	 */
	@Test
	public void testGetAresta() {
		for (int i = 0; i < estradas.size(); i++) {
			assertTrue(estradas.get(i).equals(g.getAresta(i + 1)));
		}
	}
	
	/**
	 * Teste do tamanho do conjunto de vértices	
	 */
	@Test
	public void testGetVerticesTamanho() {
		List<Cidade> vertices = g.getVertices();
		assertEquals(cidades.size(), vertices.size());
	}
	
	/**
	 * Teste para saber se os vértices retornados pela função são os vértices inseridos no grafo	
	 */
	@Test
	public void testGetVerticesVerifcacao() {
		
		List<Cidade> vertices = g.getVertices();
		
		for (int i = 0; i < vertices.size(); i++) {
			assertTrue(vertices.get(i).equals(cidades.get(i)));
		}
	}
	
	/**
	 * Teste do tamanho do conjunto de arestas
	 */
	@Test
	public void testGetArestasTamanho() {
		assertEquals(estradas.size(), g.getArestas().size());
	}
	
	/**
	 * Teste do para saber se as arestas retornadas pela função são 
	 * as arestas que foram inseridas no grafo	
	 */
	@Test
	public void testGetArestasVerifcacao() {
		
		List<Estrada> arestas = g.getArestas();
		Collections.sort(arestas);
		Collections.sort(estradas);
		for (int i = 0; i < arestas.size(); i++) {
			assertTrue(arestas.get(i).equals(estradas.get(i)));
		}
	}

	/**
	 * Testa se o numero de arestas incidentes a v esta correto
	 * @throws VerticeNaoEncontradoException 
	 */
	@Test
	public void testGetArestasNumeroIncidentes() throws VerticeNaoEncontradoException {
		//teste cidade Natal
		assertEquals(3, g.getArestasIncidentes(g.getVertice(2)).size());
	}
	
	/**
	 * Seja G=(V, E) um grafo G. Esse metodo testa se as arestas 
	 * incidentes a um vertice v eh subconjunto de E (conjunto de arestas de G)
	 * @throws VerticeNaoEncontradoException 
	 */
	@Test
	public void testGetArestasIncidentesVerficacaoSubconjunto() throws VerticeNaoEncontradoException {
		for (int i = 0; i < cidades.size(); i++) {
			List<Estrada> ests = g.getArestasIncidentes(g.getVertice(i + 1));
			
			for (Estrada estrada : ests) {
				assertTrue(estradas.contains(estrada));
			}
		}		
	}

	/**
	 * Este teste visa testar a tolerancia do software, ao chamar o metodo getArestasIncidentes(Vertice v) 
	 * passando um vertice v nao existente em G.
	 * @throws VerticeNaoEncontradoException 
	 */
	@Test(expected = VerticeNaoEncontradoException.class)
	public void testGetArestasIncidentesVerticeNaoEncontrado() throws VerticeNaoEncontradoException {
		g.getArestasIncidentes(new Cidade(100, "Guabaratinga"));
	}

	/**
	 * Testa se o numero de vertices adjacentes eh igual ao numero de arestas incidentes, que 
	 * eh um criterio para que esta funcao esteja correta
	 * @throws VerticeNaoEncontradoException 
	 */
	
	@Test
	public void testGetVerticesAdjacentesNumero() throws VerticeNaoEncontradoException {
		
		for (Cidade cidade : cidades) {
			assertTrue( g.getArestasIncidentes(cidade).size() == g.getVerticesAdjacentes(cidade).size() );
		}
		
	}

	/**
	 * Este teste visa testar a tolerancia do software, ao chamar o metodo getVericesAdjacentes(Vertice v) 
	 * passando um vertice nao existente em G.
	 * @throws VerticeNaoEncontradoException 
	 */
	
	@Test(expected = VerticeNaoEncontradoException.class)
	public void testGetVerticesAdjacentesVerticeNaoEncontrado() throws VerticeNaoEncontradoException {
		
		g.getArestasIncidentes(new Cidade(100, "Guabaratinga"));
		
	}

	/**
	 * Testa se custo da obra é realmente o que é esperado
	 */
	@Test
	public void testKruskalAirs() {
		//parametro é o custo de construção de um aeroporto
		//testes exaustivos aqui é um dificuldade
		assertEquals(250, g.kruskalAirs(100));
	}

}
