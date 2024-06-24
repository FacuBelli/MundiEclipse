package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
  public static void main(String[] args) {
     
    JFrame app = new JFrame();
    app.setSize(1000, 800);

    JPanel viewWeb = new ViewWeb();
    viewWeb.setVisible(false);
    app.add(viewWeb);

    JPanel viewRecepcion = new ViewRecepcion();
    viewRecepcion.setVisible(false);
    app.add(viewRecepcion);

    JPanel viewMarketing = new ViewMarketing();
    viewMarketing.setVisible(false);
    app.add(viewMarketing);

    JPanel viewContaduria = new ViewContaduria();
    viewContaduria.setVisible(false);
    app.add(viewContaduria);

    app.repaint();

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

    app.setVisible(true);
  }
}
