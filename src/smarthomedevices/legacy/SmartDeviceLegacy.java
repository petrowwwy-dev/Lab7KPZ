package smarthomedevices.legacy;

// Вихідний «поганий» код (Variant 1, ISP) — збережено для порівняння. Не використовувати.
// Порушення ISP: «жирний» ISmartDevice змушує кожен пристрій реалізовувати методи,
// які йому не потрібні, і кидати UnsupportedOperationException.
public class SmartDeviceLegacy {

    public interface ISmartDevice {
        void powerOn();
        void powerOff();
        void setTemperature(double temp);
        void setBrightness(int level);
        void brewCoffee();
    }

    public static class SmartThermostat implements ISmartDevice {
        @Override public void powerOn() { System.out.println("Thermostat powered on."); }
        @Override public void powerOff() { System.out.println("Thermostat powered off."); }
        @Override public void setTemperature(double temp) { System.out.println("Temperature set to " + temp + "°C."); }

        @Override public void setBrightness(int level) { throw new UnsupportedOperationException("Thermostat does not support lighting adjustment."); }
        @Override public void brewCoffee() { throw new UnsupportedOperationException("Thermostat cannot brew coffee."); }
    }

    public static class SmartLight implements ISmartDevice {
        @Override public void powerOn() { System.out.println("Light turned on."); }
        @Override public void powerOff() { System.out.println("Light turned off."); }
        @Override public void setBrightness(int level) { System.out.println("Brightness set to " + level + "%."); }

        @Override public void setTemperature(double temp) { throw new UnsupportedOperationException("Light cannot set temperature."); }
        @Override public void brewCoffee() { throw new UnsupportedOperationException("Light cannot brew coffee."); }
    }

    public static class SmartCoffeeMaker implements ISmartDevice {
        @Override public void powerOn() { System.out.println("Coffee maker powered on."); }
        @Override public void powerOff() { System.out.println("Coffee maker powered off."); }
        @Override public void brewCoffee() { System.out.println("Brewing fresh coffee..."); }

        @Override public void setTemperature(double temp) { throw new UnsupportedOperationException("Coffee maker controls temperature internally."); }
        @Override public void setBrightness(int level) { throw new UnsupportedOperationException("Coffee maker has no dimmable lighting."); }
    }
}
