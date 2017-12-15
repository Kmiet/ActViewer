import org.apache.commons.cli.*;

public class Cli {

    private String filename;
    private String chapters;
    private String articles;
    private String sections;
    private String points;
    private String lines;

    private Options setOptions(){
        Options options = new Options();
        options.addOption("f",true,"Path to file");
        options.addOption("h", false, "Show help");
        options.addOption("c",true,"Show chapter [number of chapter]");
        options.addOption("a",true,"Number of article");
        options.addOption("s",true,"Number of section");
        options.addOption("p",true,"Number of point");
        options.addOption("l",true,"Number of line/letter");

        return options;
    }

    public void parse(String[] arguments) throws ParseException {

        Options options = this.setOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, arguments);

        if(cmd.hasOption("f")){
            this.filename = cmd.getOptionValue("f");
        } else{
            throw new ParseException("Has to run with option: f");
        }

        if(cmd.hasOption("c")){
            this.chapters = cmd.getOptionValue("c");
        }

        if(cmd.hasOption("a")){
            this.articles = cmd.getOptionValue("a");
        }

        if(cmd.hasOption("s")){
            this.sections = cmd.getOptionValue("s");
        }

        if(cmd.hasOption("p")){
            this.points = cmd.getOptionValue("p");
        }

        if(cmd.hasOption("l")){
            this.lines = cmd.getOptionValue("l");
        }
    }

    public String getFilename() {
        return filename;
    }

    public String getChapters() {
        return chapters;
    }

    public String getArticles() {
        return articles;
    }

    public String getSections() {
        return sections;
    }

    public String getPoints() {
        return points;
    }

    public String getLines() {
        return lines;
    }
}
