import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CliTest {

    @Test
    void testParser() {
        this.testNoOptions();
        this.testHelpOption();
        this.testTableOption();
        this.testFileOption();
        this.testUnitOption();
        this.testChapterOption();
        this.testArticleOption();
        this.testSectionOption();
        this.testPointOption();
        this.testLineOption();
    }

    @Test
    void testNoOptions() {
        assertThrows(MissingOptionException.class, () -> new Cli(new String("").split(" ")).getFile(), "Could not access any file. For more information run with option: h");
    }

    @Test
    void testHelpOption() {
        try{
            assertFalse(new Cli(new String("").split(" ")).checkHelp());
            assertTrue(new Cli(new String("-h").split(" ")).checkHelp());
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-h -f").split(" ")).checkHelp(), "Missing argument for option: f");
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testTableOption() {
        try{
            assertFalse(new Cli(new String("").split(" ")).checkTableArg());
            assertTrue(new Cli(new String("-t").split(" ")).checkTableArg());
            assertTrue(new Cli(new String("-t -f filename").split(" ")).checkTableArg());
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-t -f").split(" ")).checkTableArg(), "Missing argument for option: f");
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }
        /*
        Cli config = new Cli();
        assertThrows(ParseException.class, () -> config.parse(new String("-t").split("")), "");
        try {
            config.parse(new String("-t -f").split(" "));
        }catch (Exception e) {
            assertEquals(e.getMessage(),"Missing argument for option: f");
        }
        */
    }

    @Test
    void testFileOption() {
        try {
            assertEquals(new Cli(new String("-f filename").split(" ")).getFile(), "filename");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-f").split(" ")).getFile(), "Missing argument for option: f");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testUnitOption() {
        try {
            assertEquals(new Cli(new String("-u 3").split(" ")).getUnit(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-u").split(" ")).getUnit(), "Missing argument for option: u");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testChapterOption() {
        try {
            assertEquals(new Cli(new String("-c 3").split(" ")).getChapter(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-c").split(" ")).getChapter(), "Missing argument for option: c");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testArticleOption() {
        try {
            assertEquals(new Cli(new String("-a 3").split(" ")).getArticle(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-a").split(" ")).getArticle(), "Missing argument for option: a");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testSectionOption() {
        try {
            assertEquals(new Cli(new String("-s 3").split(" ")).getSection(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-s").split(" ")).getSection(), "Missing argument for option: s");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testPointOption() {
        try {
            assertEquals(new Cli(new String("-p 3").split(" ")).getPoint(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-p").split(" ")).getPoint(), "Missing argument for option: p");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testLineOption() {
        try {
            assertEquals(new Cli(new String("-l 3").split(" ")).getLine(), "3");
            assertThrows(MissingArgumentException.class, () -> new Cli(new String("-l").split(" ")).getLine(), "Missing argument for option: l");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
