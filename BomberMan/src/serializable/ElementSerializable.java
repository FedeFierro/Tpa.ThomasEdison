package serializable;

public class ElementSerializable {
	public String img;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public ElementSerializable(int x, int y, String imagen,int width, int height) {
		this.x=x;
		this.y=y;
		this.img= imagen;
		this.width = width;
		this.height = height;
	}
}
