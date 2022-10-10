package main.classes;

import java.util.concurrent.CompletableFuture;
import main.helpers.VersionMatcher.Format;

public class VersionRequest {

	private String currentVersion;
	private Format format;
	private String[] parts;
	private STATUS status;

	private enum STATUS
	{ SUCCESS, FAILED }

	public VersionRequest setCurrentVersion(String version) {
		this.currentVersion = version;
		return this;
	}

	public String getCurrentVersion() {
		return this.currentVersion;
	}

	public void setParts(String[] array) {
		this.parts = array;
	}
	public String[] getParts() {
		return this.parts;
	}

	public Format getFormat() {
		return this.format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}


	public VersionRequest failed() {
		this.status = STATUS.FAILED;
		return this;
	};
	public VersionRequest success() {
		this.status = STATUS.SUCCESS;
		return this;
	};

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
