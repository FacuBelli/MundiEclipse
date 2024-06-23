package reserva.metodoDePago;

public class MetodoDePago {
  private String detalles;
  private Boolean estado;

  public String getDetalles() {
    return detalles;
  }

  public void setDetalles(String detalles) {
    this.detalles = detalles;
  }

  public Boolean getEstado() {
    return estado;
  }

  public void setEstado(Boolean estado) {
    this.estado = estado;
  }

  public Boolean validarMetodo() {
    return false;
  }
}