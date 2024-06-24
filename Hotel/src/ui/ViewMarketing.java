package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.FacadeHoteleria;

public class ViewMarketing extends JPanel {
  private FacadeHoteleria facade;

  public ViewMarketing(FacadeHoteleria facade) {
    this.facade = facade;
    this.add(new JLabel("View Marketing"));
  }
}
