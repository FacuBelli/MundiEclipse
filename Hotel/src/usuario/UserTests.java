package usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTests {
  private User user;
  private Huesped huesped;
  private Gerente gerente;
  private UserFactory userFactory;

  @BeforeEach
  public void setUp() {
    user = new User("12345678", "Juan", "Perez", "123456789", "juan.perez@example.com");
    huesped = new Huesped("12345678", "Ana", "Lopez", "123456789", "ana.lopez@example.com");
    gerente = new Gerente("12345678", "Luis", "Martinez", "123456789", "luis.martinez@example.com", "securePass123");
    userFactory = new UserFactory();
  }

  @Test
  // Prueba para verificar el set y get del DNI de User.
  public void testUserSetAndGetDni() {
    user.setDni("87654321");
    assertEquals("87654321", user.getDni());
  }

  @Test
  // Prueba para verificar el set y get del nombre de User.
  public void testUserSetAndGetNombre() {
    user.setNombre("Carlos");
    assertEquals("Carlos", user.getNombre());
  }

  @Test
  // Prueba para verificar el set y get del apellido de User.
  public void testUserSetAndGetApellido() {
    user.setApellido("Gomez");
    assertEquals("Gomez", user.getApellido());
  }

  @Test
  // Prueba para verificar el set y get del teléfono de User.
  public void testUserSetAndGetTelefono() {
    user.setTelefono("987654321");
    assertEquals("987654321", user.getTelefono());
  }

  @Test
  // Prueba para verificar el set y get del email de User.
  public void testUserSetAndGetEmail() {
    user.setEmail("carlos.gomez@example.com");
    assertEquals("carlos.gomez@example.com", user.getEmail());
  }

  @Test
  // Prueba para verificar el set y get del contacto SMS de Huesped.
  public void testHuespedSetAndGetContactoSMS() {
    huesped.setContactoSMS(true);
    assertTrue(huesped.getContactoSMS());
  }

  @Test
  // Prueba para verificar el set y get del contacto WhatsApp de Huesped.
  public void testHuespedSetAndGetContactoWhatsApp() {
    huesped.setContactoWhatsApp(true);
    assertTrue(huesped.getContactoWhatsApp());
  }

  @Test
  // Prueba para verificar el set y get del contacto Email de Huesped.
  public void testHuespedSetAndGetContactoEmail() {
    huesped.setContactoEmail(true);
    assertTrue(huesped.getContactoEmail());
  }

  // Prueba para verificar el set y get de la contraseña de Gerente.
  @Test
  public void testGerenteSetAndGetContraseña() {
    gerente.setContraseña("newSecurePass456");
    assertEquals("newSecurePass456", gerente.getContraseña());
  }

  // Prueba para verificar la creación de un Huesped usando UserFactory.
  @Test
  public void testUserFactoryCreateHuesped() {
    Huesped huesped = userFactory.createHuesped("12345678", "Ana", "Lopez", "123456789", "ana.lopez@example.com");
    assertEquals("12345678", huesped.getDni());
    assertEquals("Ana", huesped.getNombre());
    assertEquals("Lopez", huesped.getApellido());
    assertEquals("123456789", huesped.getTelefono());
    assertEquals("ana.lopez@example.com", huesped.getEmail());
  }

  @Test
  // Prueba para verificar la creación de un Gerente usando UserFactory.
  public void testUserFactoryCreateGerente() {
    Gerente gerente = userFactory.createGerente("87654321", "Luis", "Martinez", "987654321",
        "luis.martinez@example.com", "securePass123");
    assertEquals("87654321", gerente.getDni());
    assertEquals("Luis", gerente.getNombre());
    assertEquals("Martinez", gerente.getApellido());
    assertEquals("987654321", gerente.getTelefono());
    assertEquals("luis.martinez@example.com", gerente.getEmail());
    assertEquals("securePass123", gerente.getContraseña());
  }
}
