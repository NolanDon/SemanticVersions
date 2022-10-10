package main;

import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;

public class VersionController {

    public String nextVersionFrom(String currentVersion) {
        return run(currentVersion).toString();
    }

    public CompletableFuture<VersionRequest> run(String currentVersion) {

        final VersionRequest request = new VersionRequest().setCurrentVersion(currentVersion);

        return this.determineFormat(request)
            .thenCompose(this::determineParts)
            .thenCompose(this::determineNextVersion)
            .thenCompose(this::assembleParts)
            .thenCompose(this::determineResult);
    }

    public CompletableFuture<VersionRequest> determineFormat(VersionRequest request) {
        return request.determineFormat();
    }

    public CompletableFuture<VersionRequest> determineParts(VersionRequest request) {
        return request.determineNextVersion();
    }

    public CompletableFuture<VersionRequest> determineNextVersion(VersionRequest request) {
        return request.determineNextVersion();
    }

    public CompletableFuture<VersionRequest> determineResult(VersionRequest request) {
        return request.determineResult();
    }

    public CompletableFuture<VersionRequest> assembleParts(VersionRequest request) {
        return request.determineNextVersion();
    }

}