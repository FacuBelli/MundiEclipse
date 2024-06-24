package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

public class ViewRecepcion extends JPanel {
  FacadeHoteleria facade;

  public ViewRecepcion(FacadeHoteleria facade) {
    this.facade = facade;
    this.add(new JLabel("View Recepcion"));
  }
}