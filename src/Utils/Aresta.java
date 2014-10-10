package Utils;

public interface Aresta<V> {
	
	int getId();
	V getV1();
	V getV2();
	int getCusto();
	V getOposto(V v);
}

