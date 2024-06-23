package usuario;

import java.util.List;

import reserva.metodoDePago.MetodoDePago;

public class Huesped extends User {
  private List<MetodoDePago> metodosDePago;
  private Boolean contactoSMS;
  private Boolean contactoWhatsApp;
  private Boolean contactoEmail;

  public Huesped(String dni, String nombre, String apellido, String telefono, String email) {
    super(dni, nombre, apellido, telefono, email);
  }

  public List<MetodoDePago> getMetodosDePago() {
    return metodosDePago;
  }

  public void setMetodosDePago(List<MetodoDePago> metodosDePago) {
    this.metodosDePago = metodosDePago;
  }

  public Boolean getContactoSMS() {
    return contactoSMS;
  }

  public void setContactoSMS(Boolean contactoSMS) {
    this.contactoSMS = contactoSMS;
  }

  public Boolean getContactoWhatsApp() {
    return contactoWhatsApp;
  }

  public void setContactoWhatsApp(Boolean contactoWhatsApp) {
    this.contactoWhatsApp = contactoWhatsApp;
  }

  public Boolean getContactoEmail() {
    return contactoEmail;
  }

  public void setContactoEmail(Boolean contactoEmail) {
    this.contactoEmail = contactoEmail;
  }
}
