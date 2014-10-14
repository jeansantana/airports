package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Utils.SetCollection;

public class SetCollectionTest {

	public SetCollection setCollection;
	public int n = 50;

	@Before
	public void setUp() throws Exception {
		setCollection = new SetCollection(n);
	}

	/*************************************
	 * Teste do Construtor SetCollection
	 * ***********************************/
	/* Testando se o vetor criado pelo construtor tem o tamanho n */
	@Test
	public void testSetCollection() {
		assertEquals(setCollection.getPai().length, n);
	}

	/* Testando se o vetor foi inicializado corretamente */
	@Test
	public void test2SetCollection() {

		for (int i = 0; i < setCollection.getPai().length; i++) {
			assertEquals(setCollection.getPai()[i], i);
		}
	}

	/*********************************************
	 * Teste do metodo Busca
	 * *******************************************/

	/* Testando se cada elemento do setCollection estão conjuntos disjuntos */
	@Test
	public void testBusca() {
		setCollection = new SetCollection(n);

		for (int i = 0; i < setCollection.getPai().length; i++) {
			assertEquals(setCollection.busca(i), i);
		}
	}

	/*
	 * Alterando o conjunto de alguns elementos e testando se realmente foi
	 * alterado
	 */
	@Test
	public void test2Busca() {

		for (int j = 0; j < n; j++) {

			setCollection = new SetCollection(n);

			for (int i = 0; i < setCollection.getPai().length; i++) {
				setCollection.unir(i, 0);
			}

			for (int i = 0; i < setCollection.getPai().length; i++) {
				assertEquals(setCollection.busca(i), 0);
			}

		}

	}

	/**************************************
	 * Teste do método unir
	 * ************************************/

	/*
	 * Teste de união entre os pares de elementos
	 */
	@Test
	public void testUnir() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				setCollection = new SetCollection(n);

				setCollection.unir(i, j);

				assertEquals(setCollection.busca(i), setCollection.busca(j));
			}
		}
	}

	/*
	 * Teste para entrada inválida
	 */
	@Test
	public void test2Unir() {

		try {
			setCollection = new SetCollection(n);
			setCollection.unir(-1, n);
			assertNotEquals(setCollection.busca(-1), setCollection.busca(n));
		} catch (Exception e) {
			assertTrue(true);
		}

	}

	/***********************************
	 * Teste do método FormaCiclo
	 * *********************************/

	/*
	 * Se dois elementos tem um mesmo pai, significa que uma aresta entre eles,
	 * fará dessa união um ciclo. Primeiro teste garante que nenhum ciclo é
	 * criado inicialmente;
	 */
	@Test
	public void testFormaCiclo() {
		setCollection = new SetCollection(n);
		for (int i = 0; i < setCollection.getPai().length - 1; i++) {
			for (int j = i + 1; j < setCollection.getPai().length; j++) {
				assertFalse(setCollection.formaCiclo(i, j));
			}
		}
	}

	/*
	 * Unindo elementos e testando se forma ciclo
	 */

	@Test
	public void test2FormaCiclo() {
		setCollection = new SetCollection(n);

		setCollection.unir(0, 1);
		setCollection.unir(0, 2);

		assertTrue(setCollection.formaCiclo(1, 2));
		assertFalse(setCollection.formaCiclo(1, 5));
	}

	/***********************************************
	 * Teste do método GetNumComponennntesConexas
	 * *********************************************/

	/*
	 * Teste default, onde o número de elementos deve ser o mesmo que o número
	 * de componentes
	 */
	@Test
	public void testGetNumComponentesConexas() {
		setCollection = new SetCollection(n);

		assertEquals(setCollection.getNumComponentesConexas(), n);

	}
	
	/*
	 * Teste com uniões
	 * */
	@Test
	public void test2GetNumComponentesConexas() {
		setCollection = new SetCollection(n);

		for (int i = 1; i < n; i++) {
			setCollection.unir(0, i);
			assertEquals(setCollection.getNumComponentesConexas(), n-i);
		}
		
	}

	/********************
	 * Teste método getPai
	 * ******************/
	/* Teste trivial */
	@Test
	public void testGetPai() {
		setCollection = new SetCollection(n);

		assertEquals(setCollection.getPai().length, n);

	}

}
