package adapterandfacade.facade;

public class DvdPlayer {
    String name;
    Amplifier amplifier;
    String playingMovieTitle;

    public DvdPlayer(String name) {
        this.name = name;
    }

    public void setAmplifier(Amplifier amplifier) {
        this.amplifier = amplifier;
    }

    public void on() {
        System.out.println(name + " DVD Player on");
    }

    public void play(String title) {
        this.playingMovieTitle = title;
        System.out.println(name + " DVD Player playing \"" + title + "\"");
    }

    public String getName() {
        return name;
    }

    public void stop() {
        System.out.println(name + " DVD Player stopped \"" + playingMovieTitle + "\"");
    }

    public void eject() {
        System.out.println(name + " DVD Player eject");
    }

    public void off() {
        System.out.println(name + " DVD Player off");
    }
}
