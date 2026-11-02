package smarthomedevices;

import smarthomedevices.legacy.SmartDeviceLegacy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        demoIsp();
    }

    private static void demoIsp() {
        System.out.println("=== Task 4: ISP (smart home devices) ===");

        System.out.println("-- Legacy: «жирний» інтерфейс --");
        SmartDeviceLegacy.ISmartDevice legacyLight = new SmartDeviceLegacy.SmartLight();
        try {
            legacyLight.brewCoffee();
        } catch (UnsupportedOperationException e) {
            System.out.println("[LEGACY BUG] " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("-- Refactored --");
        SmartThermostat thermostat = new SmartThermostat();
        SmartLight light = new SmartLight();
        SmartCoffeeMaker coffeeMaker = new SmartCoffeeMaker();

        turnOffAll(List.of(thermostat, light, coffeeMaker));
        heat(thermostat, 22.5);
        dim(light, 40);
        morningCoffee(coffeeMaker);

        System.out.println();
    }

    private static void turnOffAll(List<? extends Switchable> devices) {
        devices.forEach(Switchable::powerOff);
    }

    private static void heat(TemperatureControllable device, double temp) {
        device.setTemperature(temp);
    }

    private static void dim(Dimmable device, int level) {
        device.setBrightness(level);
    }

    private static void morningCoffee(CoffeeBrewer device) {
        device.brewCoffee();
    }
}
