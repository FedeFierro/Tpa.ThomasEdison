package database;

public class Usuario {
	private String email;
	private String password;
	private String Usuario;
	private String Nombre;
	private String Apellido;
	
	public Usuario() {}
	
	public Usuario(String e, String p, String u, String n, String a) {
		this.email = e;
		this.password = p;
		this.Usuario = u;
		this.Nombre = n;
		this.Apellido = a;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
		
}
