package reserva;

import java.util.Date;

import Habitacion.Habitacion;
import reserva.state.IReservaState;
import usuario.Huesped;

public class Reserva {
  private Huesped huesped;
  private Habitacion habitacion;
  private Date fechaInicio;
  private Date fechaFin;
  private IReservaState estado;
  private GestorFacturacion gestorFacturacion;

  public Reserva() {
    this.gestorFacturacion = new GestorFacturacion(this);
  }

  public Reserva(Huesped huesped, Habitacion habitacion, Date fechaInicio, Date fechaFin, IReservaState estado, double costoTotal) {
    this.huesped = huesped;
    this.habitacion = habitacion;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.estado = estado;
    this.gestorFacturacion = new GestorFacturacion(this);
  }






  public Huesped getHuesped() {
    return huesped;
  }

  public void setHuesped(Huesped huesped) {
    this.huesped = huesped;
  }

  public Habitacion getHabitacion() {
    return habitacion;
  }

  public void setHabitacion(Habitacion habitacion) {
    this.habitacion = habitacion;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(Date fechaFin) {
    this.fechaFin = fechaFin;
  }

  public IReservaState getEstado() {
    return estado;
  }

  public void setEstado(IReservaState estado) {
    this.estado = estado;
  }
  
  public GestorFacturacion getGestorFacturacion() {
    return gestorFacturacion;
  }

public void setId(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setId'");
}
}
