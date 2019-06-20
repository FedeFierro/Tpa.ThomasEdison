package serializable;

public class ElementoInfo {
	public String imagen;
	public int x;
	public int y;
	public int width;
	public int height;
	public String sonido;
	
	public ElementoInfo(int x, int y, String imagen,int width, int height, String sonido) {
		this.x=x;
		this.y=y;
		this.imagen= imagen;
		this.width = width;
		this.height = height;
		this.sonido = sonido;
		
	}
}
