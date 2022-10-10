package main;

import main.classes.VersionController;
import main.classes.VersionRequest;

public class NextVersion {
    public static void main(String[] args) {}

    public NextVersion(String currentVersion) {

        VersionRequest versionRequest = new VersionRequest()
                .setCurrentVersion(currentVersion);

        Router router = new Router();
        VersionController fsmMachine = new VersionController();

        synchronized (fsmMachine) {
            fsmMachine.nextVersion(currentVersion);
        }
    }
}