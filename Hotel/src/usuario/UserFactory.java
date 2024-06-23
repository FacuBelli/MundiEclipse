package usuario;

public class UserFactory {
  public Huesped createHuesped(String dni, String nombre, String apellido, String telefono, String email) {
    return new Huesped(dni, nombre, apellido, telefono, email);
  }

  public Gerente createGerente(String dni, String nombre, String apellido, String telefono, String email,
      String contraseña) {
    return new Gerente(dni, nombre, apellido, telefono, email, contraseña);
  }
}
