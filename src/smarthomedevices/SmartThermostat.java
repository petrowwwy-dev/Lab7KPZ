package smarthomedevices;

public class SmartThermostat implements Switchable, TemperatureControllable {
    @Override public void powerOn() { System.out.println("Thermostat powered on."); }
    @Override public void powerOff() { System.out.println("Thermostat powered off."); }
    @Override public void setTemperature(double temp) { System.out.println("Temperature set to " + temp + "°C."); }
}
