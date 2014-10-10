package Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class GrafoLista < V extends Vertice, A extends Aresta <V> > implements Grafo<V, A> {

	private List<V> vertices;
	private List<List<A>> arestas;
	
	public GrafoLista() {
		vertices = new ArrayList<V>();
		arestas = new ArrayList<List<A>>();
	}
	
	@Override
	public void adicionarVertice(V vertice) throws ChaveDuplicadaException {
		
		boolean inserir = true;
		
		if (vertices.size() != 0) {
			if (vertices.indexOf(vertice) != -1) {
				inserir = false;
			}
		}
		
		if (inserir) {
			vertices.add(vertice);
			arestas.add(new ArrayList<A>());
		} else {
			throw (new ChaveDuplicadaException( ((Vertice) vertice).getId()) );
		}
		
	}

	@Override
	public void adicionarAresta(A aresta) throws ArestaDuplicadaException, VerticeNaoEncontradoException {
		
		V v1 = aresta.getV1();
		V v2 = aresta.getV2();
		
		int idxv1 = vertices.indexOf(v1);
		int idxv2 = vertices.indexOf(v2);
		
		if (!buscaAresta(aresta)) {
		
			if (idxv1 == -1 || idxv2 == -1) {//existe pelo menos um vertice que não está no grafo
				if (idxv1 == -1) {
					throw (new VerticeNaoEncontradoException(v1.getId()));
				} else if (idxv2 == -1) {
					throw (new VerticeNaoEncontradoException(v2.getId()));
				}
			} else if (idxv1 != -1 && idxv2 != -1) {
				arestas.get(idxv1).add(aresta);
				arestas.get(idxv2).add(aresta);
			}
		} else {
			throw (new ArestaDuplicadaException(v1.getId(), v2.getId()));
		}
		
	}
	
	private boolean buscaAresta(A a) {
		
		for (List<A> array : arestas) {
			for (A e : array) {
				if (e.getV1().equals(a.getV1()) && e.getV2().equals(a.getV2()) || e.getV2().equals(a.getV1()) && e.getV1().equals(a.getV2())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getNumeroVertices() {
		return vertices.size();
	}

	@Override
	public int getNumeroArestas() {
		List <A> lista = new ArrayList<A>();
		
		for (List <A> edges : arestas) {
			for (A a : edges) {
				if (lista.indexOf(a) == -1) {
					lista.add(a);
				}
			}
		}
		return lista.size();
	}

	@Override
	public void removerVertice(V v) throws VerticeNaoEncontradoException, ArestaNaoEncontradaException {
		int idx = vertices.indexOf(v);
		if (idx != -1) {
			//pegar as arestas incidentes a v e depois remover-lás
			List<A> arsInc = getArestasIncidentes(v);
			for (int i = 0; i < arsInc.size(); i++) {
				removerAresta(arsInc.get(i));
			}
			vertices.remove(idx);
			arestas.remove(idx);
		} else {
			throw (new VerticeNaoEncontradoException(v.getId()));
		}
	}

	@Override
	public void removerAresta(A a) throws ArestaNaoEncontradaException {
		
		boolean removeu = false;
		
		for (List<A> array : arestas) {
			
			int idx = array.indexOf(a);
			
			if (idx != -1) {
				array.remove(a);
				removeu = true;
			}
		}
		
		if (!removeu) {
			throw (new ArestaNaoEncontradaException(a));
		}
	}

	@Override
	public V getVertice(int id) {
		
		for (V v : vertices) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	@Override
	public A getAresta(int id) {
		for (List<A> array : arestas) {
			for (A a : array) {
				if (a.getId() == id) {
					return a;
				}
			}
		}
		return null;
	}

	@Override
	public List<V> getVertices() {
		return vertices;
	}

	@Override
	public List<A> getArestas() {
		List<A> as = new ArrayList<A>();
		for (List<A> edges : arestas) {
			for (A a : edges) {
				if (as.indexOf(a) == -1) {
					as.add(a);
				}
			}
		}
		return as;
	}

	@Override
	public List<A> getArestasIncidentes(Vertice v) throws VerticeNaoEncontradoException {
		
		int idxV = vertices.indexOf(v);
		if (idxV != -1) {
			List <A> arestasIncidentesV = arestas.get(idxV);
			return arestasIncidentesV;
		} else {
			throw (new VerticeNaoEncontradoException(v.getId()));
		}
	}

	@Override
	public List<V> getVerticesAdjacentes(Vertice v) throws VerticeNaoEncontradoException {
		
		int idx = vertices.indexOf(v);
		
		if (idx != -1) {
			
			List<A> ars = getArestasIncidentes(v);
			List<V> vers = new ArrayList<V>();
			
			for (A edge : ars) {
				if (edge.getV1().equals(v)) {
					vers.add(edge.getV2());
				} else {
					vers.add(edge.getV1());
				}
			}
			
			return vers;
			
		} else {
			throw (new VerticeNaoEncontradoException(v.getId()));
		}
	}

	@Override
	//Depth-First Search - Busca em profundidade
	public void dfs(V verticeInicial, AcaoVisita visitor) {
		//desmarca todos vertices
		int marked[] = new int[getNumeroVertices() + 1];
		
		for (int i = 0; i < marked.length; i++) {
			marked[i] = 0;
		}
		
		dfs_visit(verticeInicial, visitor, marked);
		
	}

	private void dfs_visit(V verticeInicial, AcaoVisita visitor, int[] marked) {
		
		marked[vertices.indexOf(verticeInicial)] = 1;
		
		for (A edge : arestas.get(vertices.indexOf(verticeInicial))) {
			V w = edge.getOposto(verticeInicial);
			if (marked[vertices.indexOf(w)] == 0) {
				visitor.visita(w);
				dfs_visit(w, visitor, marked);
			}
		}
	}

	@Override
	// Breadth-First Search - Busca em Largura
	public void bfs(V verticeInicial, AcaoVisita visitor) {
		// TODO Auto-generated method stub
		int marked[] = new int[getNumeroVertices()];
		
		for (int i = 0; i < marked.length; i++) {
			marked[i] = 0;
		}
		
		Queue<V> f = new LinkedList<V>();
		
		marked[vertices.indexOf(verticeInicial)] = 1;
		f.add(verticeInicial);
		
		while (!f.isEmpty()) {
			V w = f.peek();
			for (A aresta : arestas.get(vertices.indexOf(w))) {
				V op = aresta.getOposto(w);
				if (marked[vertices.indexOf(op)] == 0) {
					visitor.visita(op);
					marked[vertices.indexOf(op)] = 1;
					f.add(op);
				}
			}
			f.remove();
		}
	}
	
	private Aresta[] getArestasInVectorPreprocessor(int P) {
		Aresta[] ares;
		List <Aresta> ars = new ArrayList<Aresta>();  
		for (List<A> edges : arestas) {
			for (A a : edges) {
				if (ars.indexOf(a) == -1) {
					if(a.getCusto() <= P)
						ars.add(a);
				}
			}
		}
		
		int i = 0;
		ares = new Aresta[ars.size()];
		for (Aresta aresta : ars) {
			ares[i] = aresta;
			i++;
		}
			
		return ares;
		
	}
	
	public int kruskalAirs(int P) {
		
		Aresta[] ls = getArestasInVectorPreprocessor(P);
		Arrays.sort(ls);
	
		SetCollection set = new SetCollection(getNumeroVertices());
		int custo = 0;
		for (int i = 0; i < ls.length; i++) {
			int a = vertices.indexOf(ls[i].getV1());
			int b =  vertices.indexOf(ls[i].getV2());
			if (!set.formaCiclo(a, b)) {
				custo+= ls[i].getCusto();
				set.unir(a, b);
			}
		}
		return custo + set.getNumComponentesConexas() * P;
	}
	
}
