package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

public class ViewWeb extends JPanel {
  private FacadeHoteleria facade;
  private JFrame frame;

  public ViewWeb(FacadeHoteleria facade) {
    this.facade = facade;
    this.add(new JLabel("View Web"));
    this.setVisible(true);

    frame = new JFrame("Sistema de Gestión Hotelera");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);

    // CARGA HUESPED
    JButton btnCargarHuesped = new JButton("Cargar Nuevo Huesped");
    btnCargarHuesped.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mostrarFormularioCargaHuesped();
      }
    });
    panel.add(btnCargarHuesped);

    frame.setVisible(true);
  }

  // Método para mostrar el formulario de carga de huésped
  private void mostrarFormularioCargaHuesped() {
    // Aquí implementas la lógica para mostrar el formulario de carga de huésped
  }
}
