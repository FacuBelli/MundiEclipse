package facade;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import habitacion.Habitacion;
import habitacion.tipo.IHabitacionTipo;
import habitacion.tipo.Simple;
import habitacion.tipo.Suite;
import reserva.Factura;
import reserva.Reserva;
import reserva.state.Cancelado;
import usuario.Huesped;

public class FacadeHoteleriaTest {
  private FacadeHoteleria facadeHoteleria;

  @BeforeEach
  public void setUp() {
    facadeHoteleria = new FacadeHoteleria();
  }

  @Test
  public void testRegistrarHuesped() {
    // Prueba la función de registrar un huésped
    Huesped huesped = facadeHoteleria.registrarHuesped("12345678", "John", "Doe", "555-1234", "john@example.com");
    assertNotNull(huesped);
    assertEquals("John", huesped.getNombre());
    assertEquals("Doe", huesped.getApellido());
  }

  @Test
  public void testObtenerHuesped() {
    // Prueba la función de obtener un huésped por su ID
    facadeHoteleria.registrarHuesped("87654321", "Jane", "Doe", "555-5678", "jane@example.com");
    Huesped huesped = facadeHoteleria.obtenerHuesped("87654321");
    assertNotNull(huesped);
    assertEquals("Jane", huesped.getNombre());
  }

  @Test
  public void testCargarHabitacion() {
    // Prueba la función de cargar una habitación
    IHabitacionTipo tipo = new Suite();
    Habitacion habitacion = facadeHoteleria.cargarHabitacion(101, tipo, 2, 150.0, true, "Habitación con balcón");
    assertNotNull(habitacion);
    assertEquals(101, habitacion.getIdentificador());
  }

  @Test
  public void testObtenerHabitacion() {
    // Prueba la función de obtener una habitación por su identificador
    IHabitacionTipo tipo = new Suite();
    facadeHoteleria.cargarHabitacion(202, tipo, 3, 200.0, false, "Habitación sin balcón");
    Habitacion habitacion = facadeHoteleria.obtenerHabitacion(202);
    assertNotNull(habitacion);
    assertEquals(202, habitacion.getIdentificador());
  }

  @Test
  public void testCrearReserva() {
    // Prueba la función de crear una reserva
    Huesped huesped = facadeHoteleria.registrarHuesped("123123123", "Alice", "Smith", "555-7890", "alice@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion = facadeHoteleria.cargarHabitacion(303, tipo, 2, 180.0, true, "Habitación con vista al mar");
    Date fechaInicio = new Date();
    Date fechaFin = new Date(fechaInicio.getTime() + 2 * 24 * 60 * 60 * 1000); // 2 días después
    Reserva reserva = facadeHoteleria.crearReserva(huesped, habitacion, fechaInicio, fechaFin, 360.0);
    assertNotNull(reserva);
    assertEquals(huesped, reserva.getHuesped());
    assertEquals(habitacion, reserva.getHabitacion());
  }

  @Test
  public void testCancelarReserva() {
    // Prueba la función de cancelar una reserva
    Huesped huesped = facadeHoteleria.registrarHuesped("456456456", "Bob", "Johnson", "555-4567", "bob@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion = facadeHoteleria.cargarHabitacion(404, tipo, 1, 100.0, false, "Habitación económica");
    Date fechaInicio = new Date();
    Date fechaFin = new Date(fechaInicio.getTime() + 1 * 24 * 60 * 60 * 1000); // 1 día después
    Reserva reserva = facadeHoteleria.crearReserva(huesped, habitacion, fechaInicio, fechaFin, 100.0);
    facadeHoteleria.cancelarReserva(reserva);
    assertTrue(reserva.getEstado().getClass().equals(Cancelado.class));
  }

  @Test
  public void testObtenerFactura() {
    // Prueba la función de obtener una factura para una reserva
    Huesped huesped = facadeHoteleria.registrarHuesped("789789789", "Charlie", "Brown", "555-6789",
        "charlie@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion = facadeHoteleria.cargarHabitacion(505, tipo, 4, 250.0, true, "Habitación familiar");
    Date fechaInicio = new Date();
    Date fechaFin = new Date(fechaInicio.getTime() + 3 * 24 * 60 * 60 * 1000); // 3 días después
    Reserva reserva = facadeHoteleria.crearReserva(huesped, habitacion, fechaInicio, fechaFin, 750.0);
    Factura factura = facadeHoteleria.obtenerFactura(reserva);
    assertNotNull(factura);
    assertEquals(750.0, factura.getGestorFacturacion().getSubtotal(), 0.01);
  }

  @Test
  public void testRegistrarYObtenerMultiplesHuespedes() {
    // Prueba la función de registrar y obtener múltiples huéspedes
    facadeHoteleria.registrarHuesped("12345678", "John", "Doe", "555-1234", "john@example.com");
    facadeHoteleria.registrarHuesped("87654321", "Jane", "Doe", "555-5678", "jane@example.com");
    List<Huesped> huespedes = facadeHoteleria.obtenerHuespedes();
    assertEquals(2, huespedes.size());
  }

  @Test
  public void testCargarYObtenerMultiplesHabitaciones() {
    // Prueba la función de cargar y obtener múltiples habitaciones
    IHabitacionTipo tipoSimple = new Simple();
    IHabitacionTipo tipoSuite = new Suite();
    facadeHoteleria.cargarHabitacion(101, tipoSimple, 2, 150.0, true, "Habitación con balcón");
    facadeHoteleria.cargarHabitacion(102, tipoSuite, 4, 300.0, true, "Suite de lujo");
    List<Habitacion> habitaciones = facadeHoteleria.obtenerHabitacion(tipoSimple, 2);
    assertEquals(1, habitaciones.size());
    habitaciones = facadeHoteleria.obtenerHabitacion(tipoSuite, 4);
    assertEquals(1, habitaciones.size());
  }

  @Test
  public void testCrearYObtenerReservas() {
    // Prueba la función de crear y obtener reservas
    Huesped huesped1 = facadeHoteleria.registrarHuesped("Alice", "Smith", "123123123", "555-7890", "alice@example.com");
    Huesped huesped2 = facadeHoteleria.registrarHuesped("Bob", "Johnson", "456456456", "555-4567", "bob@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion1 = facadeHoteleria.cargarHabitacion(201, tipo, 2, 180.0, true, "Habitación con vista al mar");
    Habitacion habitacion2 = facadeHoteleria.cargarHabitacion(202, tipo, 3, 220.0, false, "Habitación familiar");
    Date fechaInicio = new Date();
    Date fechaFin = new Date(fechaInicio.getTime() + 2 * 24 * 60 * 60 * 1000); // 2 días después
    Reserva reserva1 = facadeHoteleria.crearReserva(huesped1, habitacion1, fechaInicio, fechaFin, 360.0);
    Reserva reserva2 = facadeHoteleria.crearReserva(huesped2, habitacion2, fechaInicio, fechaFin, 440.0);
    assertNotNull(facadeHoteleria.obtenerReserva(habitacion1, huesped1));
    assertEquals(facadeHoteleria.obtenerReserva(habitacion1, huesped1), reserva1);
    assertNotNull(facadeHoteleria.obtenerReserva(habitacion2, huesped2));
    assertEquals(facadeHoteleria.obtenerReserva(habitacion2, huesped2), reserva2);
  }

  @Test
  public void testCancelarYVerificarReserva() {
    // Prueba la función de cancelar una reserva y luego verificar su estado
    Huesped huesped = facadeHoteleria.registrarHuesped("789789789", "Charlie", "Brown", "555-6789",
        "charlie@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion = facadeHoteleria.cargarHabitacion(301, tipo, 1, 100.0, false, "Habitación económica");
    Date fechaInicio = new Date();
    Date fechaFin = new Date(fechaInicio.getTime() + 1 * 24 * 60 * 60 * 1000); // 1 día después
    Reserva reserva = facadeHoteleria.crearReserva(huesped, habitacion, fechaInicio, fechaFin, 100.0);
    facadeHoteleria.cancelarReserva(reserva);
    assertTrue(reserva.getEstado().getClass().equals(Cancelado.class));
    Reserva retrievedReserva = facadeHoteleria.obtenerReserva(habitacion, huesped);
    assertTrue(retrievedReserva.getEstado().getClass().equals(Cancelado.class));
  }

  @Test
  public void testGenerarFacturaConMultiplesReservas() {
    // Prueba la generación de facturas con múltiples reservas
    Huesped huesped = facadeHoteleria.registrarHuesped("321321321", "David", "Miller", "555-1111", "david@example.com");
    IHabitacionTipo tipo = new Simple();
    Habitacion habitacion1 = facadeHoteleria.cargarHabitacion(401, tipo, 2, 150.0, true, "Habitación con balcón");
    Habitacion habitacion2 = facadeHoteleria.cargarHabitacion(402, tipo, 2, 180.0, false,
        "Habitación con vista al mar");
    Date fechaInicio = new Date();
    Date fechaFin1 = new Date(fechaInicio.getTime() + 1 * 24 * 60 * 60 * 1000); // 1 día después
    Date fechaFin2 = new Date(fechaInicio.getTime() + 2 * 24 * 60 * 60 * 1000); // 2 días después
    Reserva reserva1 = facadeHoteleria.crearReserva(huesped, habitacion1, fechaInicio, fechaFin1, 150.0);
    Reserva reserva2 = facadeHoteleria.crearReserva(huesped, habitacion2, fechaInicio, fechaFin2, 180.0);
    Factura factura1 = facadeHoteleria.obtenerFactura(reserva1);
    Factura factura2 = facadeHoteleria.obtenerFactura(reserva2);
    assertEquals(150.0, factura1.getGestorFacturacion().getSubtotal(), 0.01);
    assertEquals(360.0, factura2.getGestorFacturacion().getSubtotal(), 0.01);
  }
}
