
public class Main {

    public static void main(String[] args){
        try {
            Cli config = new Cli(args);

            if(!config.checkHelp()){
                DocumentNode doc = new FileParser().parse(config.getFile());
                NodeFinder finder = new NodeFinder(config.makeQuery(), doc);

                DocumentNode[] list = finder.searchDocument();
                String output = new String();
                if(config.checkTableArg()){
                    for(DocumentNode node : list) {
                        output = output + node.showTableOfContents() + "\n";
                    }
                    if(list.length == 0) { output = output + doc.showTableOfContents(); }
                    System.out.print(output);
                    System.exit(0);
                }
                for(DocumentNode node : list){
                    output = output + "\n" + node.toString() + "\n";
                }
                if(list.length == 0){
                    output = output + doc.toString();
                }
                System.out.print(output);
            }
        } catch (Exception e) {
            System.out.println("ERROR - " + e.getMessage());
        }
    }
}
