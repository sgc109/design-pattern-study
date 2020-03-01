package adapterandfacade.facade;

public class TheaterLights {
    public void dim(int brightness) {
        System.out.println("Theater Ceiling Lights dimming on " + brightness + "%");
    }

    public void on() {
        System.out.println("Theater Ceiling Lights on");
    }
}
