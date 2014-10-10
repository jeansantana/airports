package Utils;

public class VerticeNaoEncontradoException  extends Exception{
	
	private int idV;
	
	public VerticeNaoEncontradoException(int idV) {
		this.idV = idV;
	}
	
	public int getIdV() {
		return idV;
	}
	
	@Override
	public String toString() {
		return "Chave não encotrada: "+ getIdV();
	}
}
