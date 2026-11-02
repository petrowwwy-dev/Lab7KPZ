package smarthomedevices;

/** Роль: пристрій, який можна вмикати/вимикати. Реалізують усі пристрої екосистеми. */
public interface Switchable {
    void powerOn();

    void powerOff();
}
