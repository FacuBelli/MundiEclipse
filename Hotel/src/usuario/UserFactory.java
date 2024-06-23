package usuario;

public class UserFactory {
	  public Huesped createHuesped(String nombre, String apellido, String dni, String telefono, String email) {
	    return new Huesped(nombre, apellido, dni, telefono, email);
	  }

	  public Gerente createGerente(String dni, String nombre, String apellido, String telefono, String email, String contraseña) {
	    return new Gerente(dni, nombre, apellido, telefono, email, contraseña);
	  }
	}
