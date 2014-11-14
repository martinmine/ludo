package no.hig.imt3281.ludo.backend;

/**
 * Created by Martin on 27.10.2014.
 */
public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        ServerEnvironment.initialize();
        ServerEnvironment.getNetworkManager().startListening();
    }
}

