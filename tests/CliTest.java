import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CliTest {

    @Test
    void testFileOption() {
        Cli config = new Cli();
        assertEquals(config.parse(["-f", "filename"]).getFilename(), "filename");
    }
}
