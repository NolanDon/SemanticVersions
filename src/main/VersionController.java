package main;

import java.util.concurrent.CompletableFuture;
import main.classes.VersionRequest;
import main.classes.VersionRequest.STATUS;

public class VersionController {

    public String nextVersionFrom(String currentVersion)
    {
        VersionRequest result = run(currentVersion).join();

        if (result.getStatus() == STATUS.SUCCESS) {
            return result.getFinalVersion();
        } else {
            return result.getCurrentVersion();
        }
    }

    public CompletableFuture<VersionRequest> run(String currentVersion)
    {

        final VersionRequest request = new VersionRequest().setCurrentVersion(currentVersion);

        return this.determineFormat(request)
            .thenCompose(this::determineParts)
            .thenCompose(this::determineNextVersion)
            .thenCompose(this::assembleParts)
            .thenCompose(this::determineResult);
    }

    public CompletableFuture<VersionRequest> determineFormat(VersionRequest request)
    {
        return request.determineFormat();
    }

    public CompletableFuture<VersionRequest> determineParts(VersionRequest request)
    {
        return request.determineParts();
    }

    public CompletableFuture<VersionRequest> determineNextVersion(VersionRequest request)
    {
        return request.determineNextVersion();
    }

    public CompletableFuture<VersionRequest> assembleParts(VersionRequest request)
    {
        return request.assembleParts();
    }

    public CompletableFuture<VersionRequest> determineResult(VersionRequest request)
    {
        return request.determineResult();
    }

}