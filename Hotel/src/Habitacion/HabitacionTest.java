package habitacion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import habitacion.state.IHabitacionState;
import habitacion.state.Disponible;
import habitacion.state.Ocupado;
import habitacion.tipo.IHabitacionTipo;
import habitacion.tipo.Simple;
import habitacion.tipo.Suite;

public class HabitacionTest {
  @Test
  public void testCrearHabitacion() {
    // Se asegura de que una nueva habitación se inicialice correctamente.
    Habitacion habitacion = new Habitacion();
    assertNotNull(habitacion);
    assertEquals(0, habitacion.getIdentificador());
    assertNull(habitacion.getTipo());
    assertEquals(0, habitacion.getCapacidad());
    assertEquals(0.0, habitacion.getTarifa());
    assertFalse(habitacion.isBalcon());
    assertFalse(habitacion.isTv());
    assertFalse(habitacion.isToallas());
    assertFalse(habitacion.isDespertador());
    assertNull(habitacion.getDescripcion());
  }

  @Test
  public void testCambiarEstadoHabitacion() {
    // Se asegura de que se pueda cambiar el estado de una habitación.
    Habitacion habitacion = new Habitacion();
    IHabitacionState estadoInicial = habitacion.getEstado();

    habitacion.setEstado(new Ocupado(habitacion));
    assertNotEquals(estadoInicial, habitacion.getEstado());
  }

  @Test
  public void testHabitacionBuilder() {
    // Se prueba la construcción de una habitación utilizando el HabitacionBuilder.
    IHabitacionTipo tipo = new Simple();

    Habitacion habitacion = new HabitacionBuilder()
        .identificador(102)
        .tipo(tipo)
        .capacidad(1)
        .tarifa(100.0)
        .balcon(false)
        .tv(true)
        .toallas(true)
        .despertador(true)
        .descripcion("Habitación estándar con TV y servicios adicionales")
        .build();

    assertEquals(102, habitacion.getIdentificador());
    assertEquals(tipo, habitacion.getTipo());
    assertEquals(1, habitacion.getCapacidad());
    assertEquals(100.0, habitacion.getTarifa());
    assertFalse(habitacion.isBalcon());
    assertTrue(habitacion.isTv());
    assertTrue(habitacion.isToallas());
    assertTrue(habitacion.isDespertador());
    assertEquals("Habitación estándar con TV y servicios adicionales", habitacion.getDescripcion());
    assertEquals(new Disponible(habitacion).getClass(), habitacion.getEstado().getClass());
  }

  @Test
  public void testHabitacionBuilderConEstadoPorDefecto() {
    // Se prueba la construcción de una habitación con estado por defecto si no se
    // especifica.
    IHabitacionTipo tipo = new Suite();

    Habitacion habitacion = new HabitacionBuilder()
        .identificador(103)
        .tipo(tipo)
        .capacidad(2)
        .tarifa(150.0)
        .balcon(true)
        .descripcion("Habitación doble con balcón")
        .build();

    assertEquals(103, habitacion.getIdentificador());
    assertEquals(tipo, habitacion.getTipo());
    assertEquals(2, habitacion.getCapacidad());
    assertEquals(150.0, habitacion.getTarifa());
    assertTrue(habitacion.isBalcon());
    assertFalse(habitacion.isTv());
    assertFalse(habitacion.isToallas());
    assertFalse(habitacion.isDespertador());
    assertEquals("Habitación doble con balcón", habitacion.getDescripcion());
    assertNotNull(habitacion.getEstado());
  }
}
