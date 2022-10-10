
import main.classes.VersionController;
import org.junit.Assert;
import org.junit.Test;

public class VersioningTests {

    VersionController version = new VersionController();

    @Test
    public void testStartStateResultTrue_1() {
        Assert.assertEquals(1, version.nextVersion("1.2.3"));
    }

    @Test
    public void testStartStateResultTrue_2() {
        Assert.assertEquals(0, version.nextVersion("0.9.9"));
    }

    @Test
    public void testStartStateResultTrue_3() {
        Assert.assertEquals(2, version.nextVersion("1"));
    }

    @Test
    public void testStartStateResultTrue_4() {
        Assert.assertEquals(0, version.nextVersion("1.2.3.4.5.6.7.8"));
    }

    @Test
    public void testStartStateResultTrue_5() {
        Assert.assertEquals(0, version.nextVersion("9.9"));
    }

    /** EXCEPTIONS */
    @Test
    public void testInvalidBinaryCharsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> version.nextVersion("hello"));
    }

    /** START ENGINE */
    @Test
    public void assertEmptyStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> startEngine = new StartEngine(""));
    }
}