package usuario;

public class Gerente extends User {
	  private String contraseña;

	  public Gerente(String dni, String nombre, String apellido, String telefono, String email, String contraseña) {
	    super(dni, nombre, apellido, telefono, email);
	    this.contraseña = contraseña;
	  }

	  public String getContraseña() {
	    return contraseña;
	  }

	  public void setContraseña(String contraseña) {
	    this.contraseña = contraseña;
	  }
	}
