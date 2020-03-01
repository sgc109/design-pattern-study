package adapterandfacade.facade.withfacade;

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

        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade(amp, tuner, dvd, cd,
                        projector, screen, lights, popper);
        homeTheater.watchMovie(movie);
        homeTheater.endMovie();


    }
}
