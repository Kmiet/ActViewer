import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CliTest {

    @Test
    void testParser() {
        this.testNoOptions();
        this.testFileOption();
        this.testChaptersOption();
        this.testArticlesOption();
        this.testSectionsOption();
        this.testPointsOption();
        this.testLinesOption();
    }

    @Test
    void testNoOptions() {
        Cli config = new Cli();
        try {
            config.parse(new String("").split(" "));
            assertEquals(config.getFilename(), "");
            assertEquals(config.getChapters(), "");
            assertEquals(config.getArticles(), "");
            assertEquals(config.getSections(), "");
            assertEquals(config.getPoints(), "");
            assertEquals(config.getLines(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Has to run with option: f");
        }
    }

    @Test
    void testFileOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f filename").split(" "));
            assertEquals(config.getFilename(), "filename");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f").split(" "));
            assertEquals(config.getFilename(), "filename");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: f");
        }
    }

    @Test
    void testChaptersOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f file -c 4").split(" "));
            assertEquals(config.getChapters(), "4");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -c 1:3").split(" "));
            assertEquals(config.getChapters(), "1:3");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -c").split(" "));
            assertEquals(config.getChapters(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: c");
        }
    }

    @Test
    void testArticlesOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f file -a 4").split(" "));
            assertEquals(config.getArticles(), "4");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -a 1:3").split(" "));
            assertEquals(config.getArticles(), "1:3");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -a").split(" "));
            assertEquals(config.getArticles(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: a");
        }
    }

    @Test
    void testSectionsOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f file -s 4").split(" "));
            assertEquals(config.getSections(), "4");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -s 1:3").split(" "));
            assertEquals(config.getSections(), "1:3");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -s").split(" "));
            assertEquals(config.getSections(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: s");
        }
    }

    @Test
    void testPointsOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f file -p 4").split(" "));
            assertEquals(config.getPoints(), "4");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -p 1:3").split(" "));
            assertEquals(config.getPoints(), "1:3");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -p").split(" "));
            assertEquals(config.getPoints(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: p");
        }
    }

    @Test
    void testLinesOption() {
        Cli config = new Cli();
        try {
            config.parse(new String("-f file -l 4").split(" "));
            assertEquals(config.getLines(), "4");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -l 1:3").split(" "));
            assertEquals(config.getLines(), "1:3");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "");
        }

        try {
            config.parse(new String("-f file -l").split(" "));
            assertEquals(config.getLines(), "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Missing argument for option: l");
        }
    }
}
