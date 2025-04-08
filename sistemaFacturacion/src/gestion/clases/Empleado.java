package gestion.clases;

public class Empleado extends Usuario{
	private int id;
	private String username;
	private String rol;
	private String password;
	
	public Empleado (int id, String nombre, String apellido, String email, String username, String rol) {
		super(nombre, apellido, email);
		this.setId(id);
		this.setUsername(username);
		this.setRol(rol);
	}
	public Empleado (int id, String nombre, String apellido, String email, String username, String rol, String password) {
		super(nombre, apellido, email);
		this.setId(id);
		this.setUsername(username);
		this.setRol(rol);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}