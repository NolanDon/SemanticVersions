package main;

import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;

public class VersionController {

    public CompletableFuture<VersionRequest> nextVersionFrom(String currentVersion) {

        final VersionRequest request = new VersionRequest().setCurrentVersion(currentVersion);

        return this.determineFormat(request)
            .thenCompose(this::determineInitialState)
            .thenCompose(this::determineNextVersion)
            .thenCompose(this::determineResult);
    }

    public CompletableFuture<VersionRequest> determineFormat(VersionRequest request) {
        return request.determineFormat();
    }

    public CompletableFuture<VersionRequest> determineInitialState(VersionRequest request) {
        return request.determineInitialState();
    }

    public CompletableFuture<VersionRequest> determineNextVersion(VersionRequest request) {
        return request.determineNextVersion();
    }

    public CompletableFuture<VersionRequest> determineResult(VersionRequest request) {
        return request.determineResult();
    }
}