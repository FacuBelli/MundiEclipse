import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import facade.FacadeHoteleria;
import habitacion.tipo.Simple;
import habitacion.tipo.Suite;
import ui.ViewContaduria;
import ui.ViewMarketing;
import ui.ViewRecepcion;
import ui.ViewWeb;
import usuario.Huesped;

public class Main {
  public static void main(String[] args) {
    FacadeHoteleria facade = new FacadeHoteleria();

    // Register guests
    Huesped guest1 = facade.registrarHuesped("123456789", "John", "Doe", "123456789", "john@example.com");
    Huesped guest2 = facade.registrarHuesped("987654321", "Jane", "Doe", "987654321", "jane@example.com");
    Huesped guest3 = facade.registrarHuesped("111111111", "Alice", "Smith", "111111111", "alice@example.com");
    Huesped guest4 = facade.registrarHuesped("222222222", "Bob", "Johnson", "222222222", "bob@example.com");

    // Create rooms
    facade.cargarHabitacion(101, new Simple(), 2, 100.0, true, "Standard room with balcony");
    facade.cargarHabitacion(102, new Simple(), 2, 100.0, false, "Standard room without balcony");
    facade.cargarHabitacion(201, new Suite(), 4, 200.0, true, "Luxury suite with balcony");
    facade.cargarHabitacion(202, new Suite(), 4, 200.0, false, "Luxury suite without balcony");
    facade.cargarHabitacion(301, new Simple(), 2, 100.0, false, "Standard room without balcony");
    facade.cargarHabitacion(302, new Suite(), 4, 200.0, true, "Luxury suite with balcony");
    facade.cargarHabitacion(401, new Simple(), 2, 100.0, true, "Standard room with balcony");
    facade.cargarHabitacion(402, new Suite(), 4, 200.0, false, "Luxury suite without balcony");

    // Make reservations
    Date startDate = new Date();
    Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 3)); // 3 days later

    facade.crearReserva(guest1, facade.obtenerHabitacion(101), startDate, endDate, 300.0);
    facade.crearReserva(guest2, facade.obtenerHabitacion(201), startDate, endDate, 600.0);
    facade.crearReserva(guest3, facade.obtenerHabitacion(301), startDate, endDate, 300.0);
    facade.crearReserva(guest4, facade.obtenerHabitacion(401), startDate, endDate, 600.0);

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
