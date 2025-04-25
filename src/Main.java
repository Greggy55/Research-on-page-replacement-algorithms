import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 4;
        int totalNumberOfPages = 5;
        int referenceStringLength = 12;

        Simulation simulation = new Simulation(
                numberOfFrames,
                totalNumberOfPages,
                referenceStringLength
        );

        simulation.start();
    }
}
