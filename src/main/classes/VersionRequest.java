package main.classes;

import java.util.concurrent.CompletableFuture;
import main.helpers.VersionMatcher;

public class VersionRequest {

	private String currentVersion;
	private int format;
	private String parts;
	private STATUS status;
	private String finalVersion = null;
	private String tmpVersion;
	private int delimiter;

	private final VersionMatcher matcher = new VersionMatcher();

	public enum STATUS
	{ SUCCESS, FAILED }


	public VersionRequest failed() {
		this.status = STATUS.FAILED;
		return this;
	}

	public VersionRequest success() {
		this.status = STATUS.SUCCESS;
		return this;
	}

	public int getDelimiter() { return this.delimiter; }
	public void setDelimiter(int value) { this.delimiter = value; }

	public String getCurrentVersion() {
		return this.currentVersion;
	}
	public VersionRequest setCurrentVersion(String version) {
		this.currentVersion = version;
		return this;
	}

	public STATUS getStatus() { return this.status; }

	public String getFinalVersion() { return this.finalVersion; }
	public VersionRequest setFinalVersion(String version) {
		this.finalVersion = version;
		return this;
	}

	public String getSplitVersion() {
		return this.parts;
	}
	public void setSplitVersion(String version) {
		this.parts = version;
	}

	public int getFormat() {
		return this.format;
	}
	public void setFormat(int format) {
		this.format = format;
	}

	public String getNewVersion() { return this.tmpVersion; }
	public void setNewVersion(int version) { this.tmpVersion = String.valueOf(version); }


	public CompletableFuture<VersionRequest> determineFormat() {

		return matcher.determineFormat(this);
	}

	public CompletableFuture<VersionRequest> determineParts() {

		return matcher.determineParts(this);
	}

	public CompletableFuture<VersionRequest> assembleParts() {

		return matcher.assembleParts(this);
	}

	public CompletableFuture<VersionRequest> determineNextVersion() {

		return matcher.determineNextVersion(this);
	}

	public CompletableFuture<VersionRequest> determineResult() {

		return matcher.determineResult(this);
	}
}
