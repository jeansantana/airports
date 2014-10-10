package Utils;

public class ArestaNaoEncontradaException extends Exception {
	
	private Aresta a;
	
	public ArestaNaoEncontradaException(Aresta a) {
		this.a = a;
	}
	
	private Aresta getAresta() {
		return a;
	}
	
	@Override
	public String toString() {
		return "Aresta não encontrada: (" + a.getV1() + ", " + a.getV2() + ")";
	}
}
