package habitacion;

import habitacion.state.Disponible;
import habitacion.state.IHabitacionState;
import habitacion.tipo.IHabitacionTipo;

public class HabitacionBuilder {
  Habitacion habitacion;

  public HabitacionBuilder() {
    this.clear();
  }

  public HabitacionBuilder clear() {
    habitacion = new Habitacion();
    habitacion.setEstado(new Disponible(habitacion));
    return this;
  }

  public HabitacionBuilder identificador(int identificador) {
    habitacion.setIdentificador(identificador);
    return this;
  }

  public HabitacionBuilder tipo(IHabitacionTipo tipo) {
    habitacion.setTipo(tipo);
    return this;
  }

  public HabitacionBuilder capacidad(int capacidad) {
    habitacion.setCapacidad(capacidad);
    return this;
  }

  public HabitacionBuilder tarifa(double tarifa) {
    habitacion.setTarifa(tarifa);
    return this;
  }

  public HabitacionBuilder balcon(boolean bool) {
    habitacion.setBalcon(bool);
    return this;
  }

  public HabitacionBuilder tv(boolean bool) {
    habitacion.setTv(bool);
    return this;
  }

  public HabitacionBuilder toallas(boolean bool) {
    habitacion.setToallas(bool);
    return this;
  }

  public HabitacionBuilder despertador(boolean bool) {
    habitacion.setDespertador(bool);
    return this;
  }

  public HabitacionBuilder descripcion(String descripcion) {
    habitacion.setDescripcion(descripcion);
    return this;
  }

  public HabitacionBuilder estado(IHabitacionState estado) {
    habitacion.setEstado(estado);
    return this;
  }

  public Habitacion build() {
    return habitacion;
  }
}
