package reserva;

import usuario.Huesped;
import java.util.Date;

import habitacion.Habitacion;

public class ReservaBuilder {
  private Reserva reserva;

  public ReservaBuilder clear() {
    this.reserva = new Reserva();
    return this;
  }

  public ReservaBuilder huesped(Huesped huesped) {
    reserva.setHuesped(huesped);
    return this;
  }

  public ReservaBuilder habitacion(Habitacion habitacion) {
    reserva.setHabitacion(habitacion);
    return this;
  }

  public ReservaBuilder fechaInicio(Date fechaInicio) {
    reserva.setFechaInicio(fechaInicio);
    return this;
  }

  public ReservaBuilder fechaFin(Date fechaFin) {
    reserva.setFechaFin(fechaFin);
    return this;
  }

  public Reserva build() {
    return reserva;
  }
}