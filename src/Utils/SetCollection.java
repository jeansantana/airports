package Utils;

/**
 * estrutura pra union-find disjoints sets
 * 
 * */

public class SetCollection {
	
	private int Pai[];
	
	//construtor é a operação de gerar
	public SetCollection(int n) {
		
		Pai = new int[n]; 
		
		for (int i = 0; i < Pai.length; i++) {
			Pai[i] = i;
		}
	}
	
	//retorna o conjunto a que x pertence
	public int busca(int x) {
		if (Pai[x] == x) {
			return x;
		} else {
			return Pai[x] = busca(Pai[x]);//path compression - compressão de caminho.
		}
	}
	//une dois conjuntos
	public void unir(int a, int b) {
		Pai[busca(a)] = busca(b);
	}
	//verifica se i e j pertencem ao mesmo conjunto, e em particular para o algoritmo de Kruskal identifica um ciclo.
	public boolean formaCiclo(int i, int j) {
		return (Pai[i] == Pai[j]);
	}
	//retorna o numero de componentes conexas
	public int getNumComponentesConexas() {
		int ncc = 0;
		for (int i = 0; i < Pai.length; i++) {

			if (Pai[i] == i) {
				ncc+= 1;
			}
		}
		
		return ncc;
	}
	
	public int[] getPai() {
		return Pai;
	}
	
}
