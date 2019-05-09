package entidades;


public class Bomba extends Elemento{
	private Jugador jugador;
	protected int tiempoexplosion;
	private int rango;
	/**
	 * Constructor Solo para Test
	 */
	public Bomba(int x, int y, Tablero tablero, Jugador jugador) {
		super(x,y,tablero);
		tiempoexplosion=3000;
		this.jugador = jugador;
		rango=3;
		
	}
	public Bomba (Tablero tablero, Jugador jugador) {
		super(jugador.pos,tablero);
		tiempoexplosion=3000;
		this.jugador = jugador;
		rango=3;
	}
	
	public void explotar() {
		if(vivo) {
			vivo =false;
			boolean aIzquierda , aDerecha, aArriba, aAbajo;
			 	aIzquierda = aDerecha =aArriba =aAbajo =true;
			Coordenada posExpIzq = new Coordenada(this.pos);
			Coordenada posExpDer = new Coordenada(this.pos);
			Coordenada posExpArr = new Coordenada(this.pos);
			Coordenada posExpAba = new Coordenada(this.pos);
			
			Elemento e = tablero.obtenerJugador(this.pos);
				if (e!=null) e.explotar();
			
			for(int i=1; i<=rango; i++) {
				/*HACIA ATRAS*/
				posExpIzq.actualizarPosicion(-1, 0);
				aIzquierda=aIzquierda && tablero.puedeExplotar(posExpIzq);
				if(aIzquierda) {
					e = tablero.obtenerElemento(posExpIzq);
					e.explotar();
				 aIzquierda = aIzquierda && e.puedeSeguirExplotando();
						
				}
				
				/*HACIA ADELANTE*/
				posExpDer.actualizarPosicion(1, 0);
				aDerecha= aDerecha && tablero.puedeExplotar(posExpDer);
				if(aDerecha) {
					e = tablero.obtenerElemento(posExpDer);
					e.explotar();
					aDerecha =  aDerecha && e.puedeSeguirExplotando();
				}
				/*HACIA ARRIBA*/
				posExpDer.actualizarPosicion(0, -1);
				aArriba=aArriba && tablero.puedeExplotar(posExpArr);
				if(aArriba) {
					e = tablero.obtenerElemento(posExpArr);
					e.explotar();
					aArriba = aArriba &&  e.puedeSeguirExplotando();
				}
				/*HACIA ABAJO*/
				posExpAba.actualizarPosicion(0, 1);
				aAbajo = aAbajo && tablero.puedeExplotar(posExpAba);
				if(aAbajo) {
					e = tablero.obtenerElemento(posExpAba);
					e.explotar();
					aAbajo = aAbajo && e.puedeSeguirExplotando();
				}
			}
			this.jugador.explotoBomba();
			tablero.eliminarElemento(this);
		}
	}//fin explotarBomba
}
	