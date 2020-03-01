package adapterandfacade.facade;

public class Amplifier {
    String name;
    Tuner tuner;
    DvdPlayer dvdPlayer;
    CdPlayer cdPlayer;

    public Amplifier(String name) {
        this.name = name;
    }

    public void setCdPlayer(CdPlayer cdPlayer) {
        this.cdPlayer = cdPlayer;
    }

    public void setDvd(DvdPlayer dvd) {
        this.dvdPlayer = dvd;
        System.out.println(name + "Amplifier setting DVD player to " + dvd.getName() + " DVD Player");
    }

    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }

    public void on() {

    }

    public void off() {

    }

    public void setSurroundSound() {
        System.out.println(name + " Amplifier surround sound on (5 speakers, 1 sub woofer)");
    }

    public void setVolume(int volume) {
        System.out.println(name + " Amplifier setting volume to " + volume);
    }
}
