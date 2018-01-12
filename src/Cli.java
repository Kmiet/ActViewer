import org.apache.commons.cli.*;

public class Cli {

    private CommandLine cmd;
    private Options options;

    Cli(String[] args) throws ParseException {
        this.options = this.setOptions();
        this.parse(args);
    }

    private Options setOptions(){
        Options options = new Options();
        options.addOption("f", "file",true,"Show file");
        options.addOption("h", "help",false, "Show help");
        options.addOption("c", "chapter",true,"Show chapter");
        options.addOption("a", "article",true,"Show article");
        options.addOption("s","section",true,"Show section");
        options.addOption("S", "subsection",true,"Show subsection");
        options.addOption("l", "letter",true,"Show line/letter");
        options.addOption("u", "unit",true,"Show unit");
        options.addOption("t", "table",false,"Show table of contents");

        return options;
    }

    private void parse(String[] arguments) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        this.cmd = parser.parse(this.options, arguments);
    }

    public boolean checkHelp(){
        if(this.cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("main <options> [arguments]", "Available options: ", options, "\nUsage examples: \n main -f konstytucja.txt -a 4 -s 2\n main -f uokik.txt -u II:III -t");
            return true;
        }
        return false;
    }

    public String getFile() throws MissingOptionException {
        if(this.cmd.hasOption("f")) {
            return this.cmd.getOptionValue("f");
        } else {
            throw new MissingOptionException("Could not access any file. For more information run with option: h");
        }
    }

    public boolean checkTableArg(){
        if(this.cmd.hasOption("t")) {
            return true;
        }
        return false;
    }

    public String getUnit() {
        return this.cmd.getOptionValue("u");
    }

    public String getChapter() {
        return this.cmd.getOptionValue("c");
    }

    public String getArticle() {
        return this.cmd.getOptionValue("a");
    }

    public String getSection() {
        return this.cmd.getOptionValue("s");
    }

    public String getSubsection() {
        return this.cmd.getOptionValue("S");
    }

    public String getLetter() {
        return this.cmd.getOptionValue("l");
    }

    public SearchQuery makeQuery() {
        SearchQuery query = new SearchQuery();
        DocNodeType type = DocNodeType.Undefined;
        String[] optionMarks = new String("u c a s S l").split(" ");
        for(String optionMark : optionMarks){
            if(this.cmd.hasOption(optionMark)){
                query.addElement(new SearchElement(query, type.charToType(optionMark), this.cmd.getOptionValue(optionMark)));
            }
        }
        return query;
    }
}
