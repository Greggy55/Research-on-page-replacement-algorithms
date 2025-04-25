import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {

        int numberOfFrames = 5;
        int totalNumberOfPages = 2;
        int referenceStringLength = 10;

        Simulation simulation = new Simulation(
                numberOfFrames,
                totalNumberOfPages,
                referenceStringLength
        );

        simulation.start();
    }
}
