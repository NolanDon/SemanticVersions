package main;

import main.classes.router.Router;

public class NextVersion {
    public static void main(String[] args) {}

    public NextVersion(String currentVersion) {

        VersionRequest versionRequest = new VersionRequest()
                .setCurrentVersion(currentVersion);

        Router router = new Router();
        FSMVersionController fsmMachine = new FSMVersionController();

        synchronized (fsmMachine) {
            fsmMachine.nextVersion(currentVersion);
        }
    }
}