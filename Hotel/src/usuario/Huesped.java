package usuario;

public class Huesped extends User {
    private boolean contactoSMS;
    private boolean contactoWhatsApp;
    private boolean contactoEmail;

    public Huesped(String dni, String nombre, String apellido, String telefono, String email) {
        super(dni, nombre, apellido, telefono, email);
    }

    public boolean getContactoSMS() {
        return contactoSMS;
    }

    public void setContactoSMS(boolean contactoSMS) {
        this.contactoSMS = contactoSMS;
    }

    public boolean getContactoWhatsApp() {
        return contactoWhatsApp;
    }

    public void setContactoWhatsApp(boolean contactoWhatsApp) {
        this.contactoWhatsApp = contactoWhatsApp;
    }

    public boolean getContactoEmail() {
        return contactoEmail;
    }

    public void setContactoEmail(boolean contactoEmail) {
        this.contactoEmail = contactoEmail;
    }
}
