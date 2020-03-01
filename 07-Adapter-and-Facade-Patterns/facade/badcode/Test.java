package adapterandfacade.facade.badcode;

import adapterandfacade.facade.*;

public class Test {
    public static void main(String[] args) {
        String movie = "Raiders of the Lost Ark";

        PopcornPopper popper = new PopcornPopper();
        TheaterLights lights = new TheaterLights();
        Screen screen = new Screen();
        Amplifier amp = new Amplifier("Top-O-Line");
        DvdPlayer dvd = new DvdPlayer("Top-O-Line");
        Projector projector = new Projector("Top-O-Line");
        Tuner tuner = new Tuner(amp);
        CdPlayer cd = new CdPlayer(amp);
        dvd.setAmplifier(amp);
        amp.setCdPlayer(cd);
        amp.setTuner(tuner);

        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.setInput(dvd);
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);

        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}
