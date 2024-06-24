package habitacion;

import habitacion.tipo.IHabitacionTipo;
import habitacion.state.IHabitacionState;

public class Habitacion {
    private int identificador;
    private String tipo; // Cambiado a String para el ejemplo
    private int capacidad;
    private double tarifa;
    private boolean balcon;
    private String descripcion;

    // Constructor con parámetros
    public Habitacion(int identificador, String tipo, int capacidad, double tarifa, boolean balcon, String descripcion) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.tarifa = tarifa;
        this.balcon = balcon;
        this.descripcion = descripcion;
    }

    // Getters y setters de los campos de la clase

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
