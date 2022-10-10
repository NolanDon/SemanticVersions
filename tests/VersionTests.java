import static main.enums.Format.*;
import main.VersionController;
import main.classes.VersionRequest;
import main.helpers.VersionMatcher;
import org.junit.Assert;
import org.junit.Test;

public class VersionTests {

    VersionController controller = new VersionController();
    VersionMatcher formatter = new VersionMatcher();
    VersionRequest request = new VersionRequest();

    @Test
    public void testNextVersion_1() {
        Assert.assertEquals("1.2.4", controller.nextVersionFrom("1.2.3"));
    }

    @Test
    public void testNextVersion_2() {
        Assert.assertEquals("20.0.3", controller.nextVersionFrom("20.0.2"));
    }

    @Test
    public void testNextVersion_3() {
        Assert.assertEquals("100", controller.nextVersionFrom("99"));
    }

    @Test
    public void testNextVersion_4() {
        Assert.assertEquals("1.2.5", controller.nextVersionFrom("1.2.4"));
    }

    @Test
    public void testNextVersion_5() {
        Assert.assertEquals("1.0.0", controller.nextVersionFrom("0.9.9"));
    }

    @Test
    public void testNextVersion_6() {
        Assert.assertEquals("2", controller.nextVersionFrom("1"));
    }

    @Test
    public void testNextVersion_7() {
        Assert.assertEquals("1.2.3.4.5.6.7.9", controller.nextVersionFrom("1.2.3.4.5.6.7.8"));
    }

    @Test
    public void testNextVersion_8() {
        Assert.assertEquals("10.0", controller.nextVersionFrom("9.9"));
    }

    @Test
    public void testFormattingVersion_FORMAT_DOUBLE_SHORT() {
        formatter.determineFormat(request.setCurrentVersion("9.9"));
        Assert.assertEquals(FORMAT_DOUBLE_SHORT, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_LONG() {
        formatter.determineFormat(request.setCurrentVersion("9.9.9"));
        Assert.assertEquals(FORMAT_SINGLE_LONG, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_DOUBLE_LONG() {
        formatter.determineFormat(request.setCurrentVersion("90.9.9"));
        Assert.assertEquals(FORMAT_DOUBLE_LONG, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_DOUBLE_SHORT() {
        formatter.determineFormat(request.setCurrentVersion("90.9"));
        Assert.assertEquals(FORMAT_DOUBLE_SHORT, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_VALUE() {
        formatter.determineFormat(request.setCurrentVersion("9"));
        Assert.assertEquals(FORMAT_SINGLE_VALUE, request.getFormat());
    }

    @Test
    public void testFormattedVersion_FORMAT_SINGLE_FAR() {
        formatter.determineFormat(request.setCurrentVersion("9.9.9.9"));
        Assert.assertEquals(FORMAT_SINGLE_FAR, request.getFormat());
    }

}