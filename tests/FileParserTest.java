import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @Test
    void testParser(){
        this.testFileExists();
        this.testParseMethod();
    }

    @Test
    void testFileExists(){
        assertThrows(IOException.class, () -> new FileParser().parse("Path that does not exist"), "Wrong path to file");
    }

    @Test
    void testParseMethod(){
        assertThrows(IOException.class, () -> new FileParser().parse(""), "Has to run with option: f");
    }
}
