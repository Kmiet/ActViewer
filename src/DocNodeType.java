public enum DocNodeType {
    Root,
    Undefined,
    Unit,
    Chapter,
    Title,
    Article,
    Section,
    Subsection,
    Letter;

    public DocNodeType charToType(String s){
        switch (s){
            case "a":
                return DocNodeType.Article;
            case "s":
                return DocNodeType.Section;
            case "S":
                return DocNodeType.Subsection;
            case "l":
                return DocNodeType.Letter;
            case "u":
                return DocNodeType.Unit;
            case "c":
                return DocNodeType.Chapter;
            default:
                return DocNodeType.Undefined;
        }
    }

    public int getPriority(){
        switch(this){
            case Root:
                return 8;
            case Unit:
                return 7;
            case Chapter:
                return 6;
            case Title:
                return 5;
            case Article:
                return 4;
            case Section:
                return 3;
            case Subsection:
                return 2;
            case Letter:
                return 1;
            case Undefined:
                return 0;
            default:
                return 0;
        }
    }

    @Override
    public String toString(){
        switch (this){
            case Unit:
                return "DZIAŁ ";
            case Chapter:
                return "Rozdział ";
            case Article:
                return "Art. ";
            default:
                return "";
        }
    }
}
