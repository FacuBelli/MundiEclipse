package habitacion.tipo;

public class Simple implements IHabitacionTipo {
  private final String nombre = "Simple";

  @Override
  public String getNombre() {
    return nombre;
  }
}
