package Utils;

public class ChaveDuplicadaException extends Exception {
	
	private int id;
	
	public ChaveDuplicadaException(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Chave Duplicada: " + getId();
	}
	
}
