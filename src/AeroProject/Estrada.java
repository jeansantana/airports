package AeroProject;

import Utils.Aresta;

public class Estrada implements Aresta<Cidade>, Comparable<Estrada> {

	private Cidade v1;
	private Cidade v2;
	private int custo;
	private int id;

	public Estrada(int id, Cidade v1, Cidade v2, int custo) {
		this.id = id;
		this.v1 = v1;
		this.v2 = v2;
		this.custo = custo;
	}

	public Estrada() {

	}

	@Override
	public Cidade getV1() {
		return v1;
	}

	@Override
	public Cidade getV2() {
		return v2;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public int getCusto() {
		return custo;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Estrada) obj).getId() == this.getId();
	}

	@Override
	public String toString() {
		return v1.getNome() + ", " + v2.getNome() + " - R$" + custo;
	}

	@Override
	public Cidade getOposto(Cidade c) {
		if (v1.equals(c)) {
			return v2;
		} else {
			return v1;
		}
	}

	@Override
	public int compareTo(Estrada o) {
		return this.custo - o.custo;
	}

}
