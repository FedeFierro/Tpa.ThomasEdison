package entidades;

public class PowerUp extends Elemento{

	public PowerUp(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
		setImageName(0);
	}

	@Override
	public int explotar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setImageName(Integer numero) {
		this.imgFinal = "bomba_powerup";
	}
	@Override
	public boolean esTransitable() {
		return true;
	}
	

}
