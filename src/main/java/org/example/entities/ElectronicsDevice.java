package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class ElectronicsDevice extends Product{


    private int voltage;

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "ElectronicsDevice{" +
                "voltage=" + voltage +
                ", id=" + id +
                '}';
    }
}
