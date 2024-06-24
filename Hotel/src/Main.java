import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import facade.FacadeHoteleria;
import habitacion.Habitacion;
import ui.ViewContaduria;
import ui.ViewMarketing;
import ui.ViewRecepcion;
import ui.ViewWeb;

public class Main {
  private static List<Habitacion> habitaciones;
  public static void main(String[] args) {

    habitaciones = new ArrayList<>();


    FacadeHoteleria facade = new FacadeHoteleria();
    JFrame app = new JFrame();
    app.setSize(800, 600);
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    app.getContentPane().setLayout(new BoxLayout(app.getContentPane(), BoxLayout.Y_AXIS));

    JPanel viewWeb = new ViewWeb(facade);
    viewWeb.setVisible(false);

    JPanel viewRecepcion = new ViewRecepcion(facade);
    viewRecepcion.setVisible(false);

    JPanel viewMarketing = new ViewMarketing(facade);
    viewMarketing.setVisible(false);

    JPanel viewContaduria = new ViewContaduria(facade);
    viewContaduria.setVisible(false);

    JPanel buttons = new JPanel();
    JButton bWeb = new JButton("Web");
    bWeb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewWeb.setVisible(true);
        viewRecepcion.setVisible(false);
        viewMarketing.setVisible(false);
        viewContaduria.setVisible(false);
      }
    });
    buttons.add(bWeb);

    JButton bRecepcion = new JButton("Recepción");
    bRecepcion.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewWeb.setVisible(false);
        viewRecepcion.setVisible(true);
        viewMarketing.setVisible(false);
        viewContaduria.setVisible(false);
      }
    });
    buttons.add(bRecepcion);

    JButton bMarketing = new JButton("Marketing");
    bMarketing.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewWeb.setVisible(false);
        viewRecepcion.setVisible(false);
        viewMarketing.setVisible(true);
        viewContaduria.setVisible(false);
      }
    });
    buttons.add(bMarketing);

    JButton bContaduria = new JButton("Contaduría");
    bContaduria.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewWeb.setVisible(false);
        viewRecepcion.setVisible(false);
        viewMarketing.setVisible(false);
        viewContaduria.setVisible(true);
      }
    });
    buttons.add(bContaduria);

    app.add(buttons);
    app.add(viewWeb);
    app.add(viewRecepcion);
    app.add(viewMarketing);
    app.add(viewContaduria);

    app.setVisible(true);
  }
}
