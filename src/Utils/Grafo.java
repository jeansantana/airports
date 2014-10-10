package Utils;

import java.util.List;

public interface Grafo < V extends Vertice, A extends Aresta<V> > {
	
	void adicionarVertice(V vertice) throws ChaveDuplicadaException;
	void adicionarAresta(A aresta) throws ArestaDuplicadaException, VerticeNaoEncontradoException;
	
	int getNumeroVertices();
	int getNumeroArestas();
	void removerVertice(V v) throws VerticeNaoEncontradoException, ArestaNaoEncontradaException;
	void removerAresta(A a) throws ArestaNaoEncontradaException;
	
	//Retorna o vértice que tem essa identificão
	V getVertice (int id);	
	
	//Retorna a aresta que tem essa identificão
	A getAresta (int id);
		
	List<V> getVertices();
	List<A> getArestas();
	
    List<A> getArestasIncidentes(V v) throws VerticeNaoEncontradoException;
    List<V> getVerticesAdjacentes(V v) throws VerticeNaoEncontradoException;
	
	void dfs(V verticeInicial, AcaoVisita visitor);
	void bfs(V verticeInicial, AcaoVisita visitor);
	int kruskalAirs(int P);
}
