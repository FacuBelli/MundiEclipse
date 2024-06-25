package facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import habitacion.Habitacion;
import habitacion.HabitacionBuilder;
import habitacion.tipo.IHabitacionTipo;
import reserva.Factura;
import reserva.Pago;
import reserva.Reserva;
import reserva.ReservaBuilder;
import usuario.Huesped;
import usuario.UserFactory;

public class FacadeHoteleria {
  private UserFactory userFactory;
  private HabitacionBuilder habitacionBuilder;
  private ReservaBuilder reservaBuilder;
  private List<Huesped> huespedes;
  private List<Habitacion> habitaciones;
  private List<Reserva> reservas;

  public FacadeHoteleria() {
    this.userFactory = new UserFactory();
    this.habitacionBuilder = new HabitacionBuilder();
    this.reservaBuilder = new ReservaBuilder();
    this.huespedes = new ArrayList<>();
    this.habitaciones = new ArrayList<>();
    this.reservas = new ArrayList<>();
  }

  public Huesped registrarHuesped(String dni, String nombre, String apellido, String telefono, String email) {
    Huesped huesped = userFactory.createHuesped(dni, nombre, apellido, telefono, email);
    huespedes.add(huesped);
    return huesped;
  }

  public List<Huesped> obtenerHuespedes() {
    return huespedes;
  }

  public Huesped obtenerHuesped(String dni) {
    Huesped result = null;
    for (Huesped huesped : huespedes) {
      if (huesped.getDni() == dni) {
        result = huesped;
        break;
      }
    }

    return result;
  }

  public Habitacion cargarHabitacion(int identificador, IHabitacionTipo tipo, int capacidad, double tarifa,
      boolean balcon,
      String descripcion) {
    Habitacion habitacion = habitacionBuilder.clear()
        .identificador(identificador)
        .tipo(tipo)
        .capacidad(capacidad)
        .tarifa(tarifa)
        .balcon(balcon)
        .descripcion(descripcion)
        .build();
    habitaciones.add(habitacion);
    return habitacion;
  }

  public List<Habitacion> obtenerHabitaciones() {
    return habitaciones;
  }

  public Habitacion obtenerHabitacion(int identificador) {
    Habitacion result = null;
    for (Habitacion habitacion : habitaciones) {
      if (habitacion.getIdentificador() == identificador) {
        result = habitacion;
        break;
      }
    }

    return result;
  }

  public List<Habitacion> obtenerHabitacion(IHabitacionTipo tipo, int capacidad) {
    List<Habitacion> result = new ArrayList<>();
    for (Habitacion habitacion : habitaciones) {
      if (habitacion.getTipo().equals(tipo) && habitacion.getCapacidad() == capacidad) {
        result.add(habitacion);
      }
    }
    return result;
  }

  public Reserva crearReserva(Huesped huesped, Habitacion habitacion, Date fechaInicio, Date fechaFin) {
    Reserva reserva = reservaBuilder.clear()
        .huesped(huesped)
        .habitacion(habitacion)
        .fechaInicio(fechaInicio)
        .fechaFin(fechaFin)
        .build();
    reserva.getGestorFacturacion().calcularSubtotal();
    reservas.add(reserva);
    return reserva;
  }

  public List<Reserva> obtenerReservas() {
    return reservas;
  }

  public Reserva obtenerReserva(Habitacion habitacion, Huesped huesped) {
    Reserva result = null;
    for (Reserva reserva : reservas) {
      if (reserva.getHabitacion().equals(habitacion) && reserva.getHuesped().equals(huesped)) {
        result = reserva;
        break;
      }
    }

    return result;
  }

  public void realizarPago(Reserva reserva, Pago pago) {
    reserva.getGestorFacturacion().getPagos().add(pago);

    boolean pagoProcesado = pago.procesarPago();

    if (pagoProcesado) {
      Factura factura = reserva.getGestorFacturacion().generarFactura();
      factura.imprimir();
    }
  }

  public void cancelarReserva(Reserva reserva) {
    reserva.cancelar();
  }

  public Factura obtenerFactura(Reserva reserva) {
    return reserva.getGestorFacturacion().generarFactura();
  }
}