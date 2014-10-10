package AeroProject;

import Utils.Vertice;

public class Cidade implements Vertice {
	
	private int id;
	private String nome;
	
	public Cidade(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Cidade() {
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Cidade) obj).getId() == this.id;
	}
	
	@Override
	public String toString() {
		return id + " - " + nome;
	}	

}
