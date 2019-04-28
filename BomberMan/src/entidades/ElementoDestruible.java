package entidades;

public abstract class ElementoDestruible extends Elemento{
   private boolean vivo;
	
   
 
   public ElementoDestruible(){
	   vivo=true;
   }
   public void destruirse() {
	   vivo=false;
   }
   public boolean estaVivo() {
	   return vivo;
   }
}
