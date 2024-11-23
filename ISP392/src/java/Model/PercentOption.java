/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trung
 */
public class PercentOption {
    private int percentId;
    private double percent_value;

    public PercentOption() {
    }

    public PercentOption(int percentId, double percent_value) {
        this.percentId = percentId;
        this.percent_value = percent_value;
    }

    public int getPercentId() {
        return percentId;
    }

    public void setPercentId(int percentId) {
        this.percentId = percentId;
    }

    public double getPercent_value() {
        return percent_value;
    }

    public void setPercent_value(double percent_value) {
        this.percent_value = percent_value;
    }
    
}
