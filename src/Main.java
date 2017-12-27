
public class Main {

    public static void main(String[] args){
        try {
            Cli config = new Cli(args);

            if(!config.checkHelp()){
                DocumentNode doc = new FileParser().parse(config.getFile());

                System.out.println(doc.toString());

                if(config.checkTableArg()){
                    System.out.println(doc.showTableOfContents());
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
