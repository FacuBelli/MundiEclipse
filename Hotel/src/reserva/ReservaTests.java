package reserva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import habitacion.Habitacion;
import habitacion.HabitacionBuilder;
import reserva.state.Cancelado;
import reserva.state.IReservaState;
import reserva.state.Pendiente;
import usuario.Huesped;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaTests {
  private Reserva reserva;
  private Huesped huesped;
  private Habitacion habitacion;
  private Date fechaInicio;
  private Date fechaFin;

  @BeforeEach
  public void setUp() {
    huesped = new Huesped("12345678", "Ana", "Lopez", "123456789", "ana.lopez@example.com");

    habitacion = new HabitacionBuilder()
        .identificador(101)
        .tipo(() -> "Suite")
        .capacidad(2)
        .tarifa(100.0)
        .balcon(true)
        .descripcion("Habitación con balcón y vista al mar")
        .build();

    fechaInicio = new Date();
    fechaFin = new Date(fechaInicio.getTime() + (1000 * 60 * 60 * 24 * 2)); // 2 days later

    reserva = new ReservaBuilder()
        .clear()
        .huesped(huesped)
        .habitacion(habitacion)
        .fechaInicio(fechaInicio)
        .fechaFin(fechaFin)
        .build();
  }

  @Test
  public void testSetAndGetHuesped() {
    // Prueba para verificar el set y get del huésped en la Reserva.
    reserva.setHuesped(huesped);
    assertEquals(huesped, reserva.getHuesped());
  }

  @Test
  public void testSetAndGetHabitacion() {
    // Prueba para verificar el set y get de la habitación en la Reserva.
    reserva.setHabitacion(habitacion);
    assertEquals(habitacion, reserva.getHabitacion());
  }

  @Test
  public void testSetAndGetFechaInicio() {
    // Prueba para verificar el set y get de la fecha de inicio en la Reserva.
    reserva.setFechaInicio(fechaInicio);
    assertEquals(fechaInicio, reserva.getFechaInicio());
  }

  @Test
  public void testSetAndGetFechaFin() {
    // Prueba para verificar el set y get de la fecha de fin en la Reserva.
    reserva.setFechaFin(fechaFin);
    assertEquals(fechaFin, reserva.getFechaFin());
  }

  @Test
  public void testSetAndGetEstado() {
    // Prueba para verificar el set y get del estado en la Reserva.
    IReservaState estado = new Pendiente(reserva);
    reserva.setEstado(estado);
    assertEquals(estado, reserva.getEstado());
  }

  @Test
  public void testCancelarReserva() {
    // Prueba para verificar la cancelación de la Reserva.
    reserva.cancelar();
    assertTrue(reserva.getEstado() instanceof Cancelado);
  }

  @Test
  public void testCalcularSubtotal() {
    // Prueba para verificar el cálculo del subtotal en el GestorFacturacion.
    GestorFacturacion gestorFacturacion = reserva.getGestorFacturacion();
    double subtotal = gestorFacturacion.calcularSubtotal();
    assertEquals(200.0, subtotal); // 100.0 tarifa por 2 días
  }

  @Test
  public void testGenerarFactura() {
    // Prueba para verificar la generación de la factura en el GestorFacturacion.
    GestorFacturacion gestorFacturacion = reserva.getGestorFacturacion();
    Factura factura = gestorFacturacion.generarFactura();
    assertNotNull(factura);
    assertEquals(gestorFacturacion, factura.getGestorFacturacion());
  }

  @Test
  public void testProcesarPago() {
    // Prueba para verificar la adición de un pago y el procesamiento de pagos.
    GestorFacturacion gestorFacturacion = reserva.getGestorFacturacion();
    List<Pago> pagos = new ArrayList<>();
    Pago pago = new Pago();
    pago.setMonto(200.0);
    pagos.add(pago);
    gestorFacturacion.setPagos(pagos);

    assertEquals(1, gestorFacturacion.getPagos().size());
    assertTrue(gestorFacturacion.getPagos().get(0).procesarPago());
  }
}
