package reserva;

import usuario.Huesped;
import java.util.Date;

import habitacion.Habitacion;
import reserva.state.IReservaState;
import reserva.state.Pendiente;

public class ReservaBuilder {
  private Reserva reserva;

  public ReservaBuilder clear() {
    this.reserva = new Reserva();
    reserva.setEstado(new Pendiente(reserva));
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

  public ReservaBuilder estado(IReservaState estado) {
    reserva.setEstado(estado);
    return this;
  }

  public Reserva build() {
    return reserva;
  }
}