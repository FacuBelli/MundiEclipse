package reserva;

public class Factura {
  GestorFacturacion gestorFacturacion;

  public Factura(GestorFacturacion gestorFacturacion) {
    this.gestorFacturacion = gestorFacturacion;
  }

  public void imprimir() {
    System.out.println("Imprimiendo factura...");
    // Recorrer pagos...
  }
}
