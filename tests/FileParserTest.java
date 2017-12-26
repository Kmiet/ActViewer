import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    @Test
    void testParser(){
        this.testFileExists();
    }

    @Test
    void testFileExists(){
        assertThrows(InvalidPathException.class, () -> new FileParser().parse("Path:\\That\\Does\\Not\\Exist"), "ERROR: Wrong path to file");
    }
}
