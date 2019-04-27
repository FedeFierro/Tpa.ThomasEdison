package entidades;

public class Tablero {

	private int _ancho;
	private int _largo;
	private Elemento[][] _elementos;
	
	public Tablero(int a , int l) {
		_ancho=a;
		_largo=l;
		_elementos = new Elemento[_ancho][_largo];
	}
	public int obtenerAncho() {
		return _ancho;
	}
	public int obtenerLargo() {
		return _largo;
	}
	public Elemento obtenerElemento(int x, int y) {
		return _elementos[x][y];
	}
	public void agregarElemento(Elemento e) {
		_elementos[e.posicionX()][e.posicionY()]=e;
	}
	public void eliminarElemento(Elemento e) {
		_elementos[e.posicionX()][e.posicionY()]=null;
	}
} 
