package Utils;

public class ArestaDuplicadaException extends Exception {
	
	private int idVi;
	private int idVf;
	
	public ArestaDuplicadaException(int idVi, int idVf) {
		this.idVi = idVi;
		this.idVf = idVf;
	}
	
	public int getIdVi() {
		return idVi;
	}
	
	public int getIdVf() {
		return idVf;
	}
	
	@Override
	public String toString() {
		return "Aresta Duplicada: (" + getIdVi() + ", " + getIdVf() + ")";
	}
	
}
