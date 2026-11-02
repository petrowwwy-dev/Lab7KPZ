package smarthomedevices;

/** Роль: пристрій із регулюванням яскравості (клієнт — сценарії освітлення). */
public interface Dimmable {
    void setBrightness(int level);
}
