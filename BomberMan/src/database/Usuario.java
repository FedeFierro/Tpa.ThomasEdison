package database;

public class Usuario {
	private String Usuario;
	private String Nombre;
	private String Apellido;
	private String Email;
	private String Password;
	
	public Usuario(String u,String n,String a,String e,String p) {
		this.Usuario = u;
		this.Nombre= n;
		this.Apellido = a;
		this.Email = e;
		this.Password = p;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	
}
