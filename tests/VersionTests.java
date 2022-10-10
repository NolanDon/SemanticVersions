import java.util.concurrent.CompletableFuture;
import main.VersionController;
import main.classes.VersionRequest;
import main.helpers.VersionMatcher;
import main.helpers.VersionMatcher.Format;
import org.junit.Assert;
import org.junit.Test;

public class VersionTests {

    VersionController controller = new VersionController();
    VersionMatcher formatter = new VersionMatcher();
    VersionRequest request = new VersionRequest();

    @Test
    public void testStartStateResultTrue_1() {
        Assert.assertEquals(CompletableFuture.completedFuture(Format.FORMAT_SINGLE_VALUE), controller.nextVersionFrom("1.2.3"));
    }

    @Test
    public void testStartStateResultTrue_2() {
        Assert.assertEquals("1.0.0", controller.nextVersionFrom("0.9.9"));
    }

    @Test
    public void testStartStateResultTrue_3() {
        Assert.assertEquals("2", controller.nextVersionFrom("1"));
    }

    @Test
    public void testStartStateResultTrue_4() {
        Assert.assertEquals("1.2.3.4.5.6.7.9", controller.nextVersionFrom("1.2.3.4.5.6.7.8"));
    }

    @Test
    public void testStartStateResultTrue_5() {
        Assert.assertEquals("1.0", controller.nextVersionFrom("9.9"));
    }

    /** FORMATTED VERSION MATCHER
     *
     *  compares regex to return the format of current version
     *
     * @return int (enum InitialVersions.java)
     * **/

    // LEADING DOUBLE

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_SHORT() {
        formatter.determineFormat(request.setCurrentVersion("9.9"));
        Assert.assertEquals(Format.FORMAT_SINGLE_SHORT, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_LONG() {
        formatter.determineFormat(request.setCurrentVersion("9.9.9"));
        Assert.assertEquals(Format.FORMAT_SINGLE_LONG, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_DOUBLE_LONG() {
        formatter.determineFormat(request.setCurrentVersion("90.9.9"));
        Assert.assertEquals(Format.FORMAT_DOUBLE_LONG, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_DOUBLE_SHORT() {
        formatter.determineFormat(request.setCurrentVersion("90.9"));
        Assert.assertEquals(Format.FORMAT_DOUBLE_SHORT, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_VALUE() {
        formatter.determineFormat(request.setCurrentVersion("9"));
        Assert.assertEquals(Format.FORMAT_SINGLE_VALUE, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_FAR() {
        formatter.determineFormat(request.setCurrentVersion("9.9.9.9"));
        Assert.assertEquals(Format.FORMAT_SINGLE_FAR, request.getFormat());
    }

//    @Test
//    public void testFormattedVersion_FAIL_2() {
//        Assert.assertEquals(Format.ERROR,formatter.determineInitialState(request.setCurrentVersion("100.0.0")));
//    }
//
//    @Test
//    public void testFormattedVersion_PASS() {
//        Assert.assertEquals(Format.FORMAT_MAJOR_LEADING_DOUBLE,formatter.determineInitialState(request.setCurrentVersion("10.0.0")));
//    }

}