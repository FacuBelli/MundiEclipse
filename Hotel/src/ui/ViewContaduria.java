package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

public class ViewContaduria extends JPanel {
  FacadeHoteleria facade;

  public ViewContaduria(FacadeHoteleria facade) {
    this.facade = facade;
    this.add(new JLabel("View Contaduria"));
  }
}
