package cliente;

import serializable.ElementoInfo;
import serializable.JugadorInfo;
import serializable.TableroInfo;

public class Main {
	public static void main(String[] args) {
		TableroInfo ti = new TableroInfo(0);
		System.out.println("iniciando cliente....");
		 Client c = new Client("192.168.100.100", 11000, ti);
		 
		 int cont =0;
		 while(true) {
			 cont++;
			
			 System.out.println(ti.imagen);
			 
			 for(JugadorInfo ji :ti.jugadoresInfo) {
				 System.out.print("jug INfo:");
				 System.out.print(ji.imagen + " - ");
				 System.out.println(ji.nombre);
			 }
			 for(ElementoInfo ei :ti.elementos) {
				 System.out.println("elemnto INfo:");
				 System.out.print(ei.imagen +" - ");
				 System.out.println(ei.sonido);
			 }
		 }
		
	}

}
