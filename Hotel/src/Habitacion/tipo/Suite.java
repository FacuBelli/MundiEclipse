package Habitacion.tipo;

public class Suite implements IHabitacionTipo {
	  private final String nombre = "Suite";

	  @Override
	  public String getNombre() {
	    return nombre;
	  }
	}
