package entidades;

public class Pared extends Elemento {

	public Pared(int x, int y, Tablero tablero) {
		super(x, y, tablero);
	}

	public Pared(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
	}

	@Override
	public void explotar() {
		super.vivo = false;
		tablero.eliminarElemento(this);
	}
	@Override
	public boolean puedeSeguirExplotando() {
		return false;
	}
	
}
