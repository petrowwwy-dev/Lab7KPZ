package smarthomedevices;

public class SmartLight implements Switchable, Dimmable {
    @Override public void powerOn() { System.out.println("Light turned on."); }
    @Override public void powerOff() { System.out.println("Light turned off."); }
    @Override public void setBrightness(int level) { System.out.println("Brightness set to " + level + "%."); }
}
