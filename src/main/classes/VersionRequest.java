package main.classes;

import java.util.concurrent.CompletableFuture;
import main.helpers.VersionMatcher.Format;

public class VersionRequest {

	private String currentVersion;
	private Format format;

	public VersionRequest setCurrentVersion(String version) {
		this.currentVersion = version;
		return this;
	}

	public String getCurrentVersion() {
		return this.currentVersion;
	}

	public Format getFormat() {
		return this.format;
	}

	public CompletableFuture<VersionRequest> determineFormat() {

		return CompletableFuture.completedFuture(this);
	}

	public CompletableFuture<VersionRequest> determineInitialState() {

		return CompletableFuture.completedFuture(this);
	}

	public CompletableFuture<VersionRequest> determineNextVersion() {

		return CompletableFuture.completedFuture(this);
	}

	public CompletableFuture<VersionRequest> determineResult() {

		return CompletableFuture.completedFuture(this);
	}
}
