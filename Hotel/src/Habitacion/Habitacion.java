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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
