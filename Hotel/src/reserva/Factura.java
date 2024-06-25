package reserva;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Factura {
  GestorFacturacion gestorFacturacion;

  public Factura(GestorFacturacion gestorFacturacion) {
    this.gestorFacturacion = gestorFacturacion;
  }

  public GestorFacturacion getGestorFacturacion() {
    return gestorFacturacion;
  }

  public void imprimir() {
    // Recorrer pagos...
    JFrame frame = new JFrame("Factura");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(400, 600);
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);

    StringBuilder facturaContent = new StringBuilder();
    facturaContent.append("Factura\n");
    facturaContent.append("--------\n");

    double total = 0.0;
    for (Pago pago : gestorFacturacion.getPagos()) {
      facturaContent.append("Monto: ").append(pago.getMonto()).append("\n");
      facturaContent.append("Método de pago: ").append(pago.getMetodoDePago()).append("\n");
      facturaContent.append("---------------------\n");
      total += pago.getMonto();
    }

    facturaContent.append("Total: ").append(total).append("\n");
    facturaContent.append("Subtotal: ").append(gestorFacturacion.getSubtotal()).append("\n");

    textArea.setText(facturaContent.toString());

    JScrollPane scrollPane = new JScrollPane(textArea);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    frame.setVisible(true);

  }
}
