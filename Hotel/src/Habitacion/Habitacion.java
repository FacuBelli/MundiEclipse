package habitacion;

import habitacion.tipo.IHabitacionTipo;
import habitacion.state.IHabitacionState;

public class Habitacion {
  private int identificador;
  private IHabitacionTipo tipo;
  private IHabitacionState estado;
  private int capacidad;
  private double tarifa;
  private boolean balcon;
  private boolean tv;
  private boolean toallas;
  private boolean despertador;
  private String descripcion;

  public int getIdentificador() {
    return identificador;
  }

  public void setIdentificador(int identificador) {
    this.identificador = identificador;
  }

  public IHabitacionTipo getTipo() {
    return tipo;
  }

  public void setTipo(IHabitacionTipo tipo) {
    this.tipo = tipo;
  }

  public IHabitacionState getEstado() {
    return estado;
  }

  public void setEstado(IHabitacionState estado) {
    this.estado = estado;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  public double getTarifa() {
    return tarifa;
  }

  public void setTarifa(double tarifa) {
    this.tarifa = tarifa;
  }

  public boolean isBalcon() {
    return balcon;
  }

  public void setBalcon(boolean balcon) {
    this.balcon = balcon;
  }

  public boolean isTv() {
    return tv;
  }

  public void setTv(boolean tv) {
    this.tv = tv;
  }

  public boolean isToallas() {
    return toallas;
  }

  public void setToallas(boolean toallas) {
    this.toallas = toallas;
  }

  public boolean isDespertador() {
    return despertador;
  }

  public void setDespertador(boolean despertador) {
    this.despertador = despertador;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
