package entidades;

public class Fondo extends Elemento {

	public Fondo(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
	}
	public Fondo(int x,int y, Tablero tablero) {
		super(x, y, tablero);
	}
	
	@Override
	public void explotar() {
		return;
		
	}
	@Override
	public boolean esTransitable() {
		return true;
	}
	
	

}
