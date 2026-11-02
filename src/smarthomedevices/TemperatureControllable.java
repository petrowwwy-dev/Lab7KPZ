package smarthomedevices;

/** Роль: пристрій із регулюванням температури (клієнт — програма опалення). */
public interface TemperatureControllable {
    void setTemperature(double temp);
}
