package no.hig.imt3281.ludo.backend;

/**
 * Main class for the program
 */
public class Main {
    private Main() {
    }

    /**
     * Main entry for the program.
     */
    public static void main(String[] args) {
        ServerEnvironment.initialize();
        ServerEnvironment.getNetworkManager().startListening();
    }
}

