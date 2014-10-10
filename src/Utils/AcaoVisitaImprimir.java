package Utils;

public class AcaoVisitaImprimir implements AcaoVisita{

	@Override
	public void visita(Vertice v) {
		System.out.println(v);		
	}

}
