import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

    @Test
    public void substring() {
        Assert.assertEquals("lo", "Hello".substring(3));
    }
}
