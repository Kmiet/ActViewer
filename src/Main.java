
public class Main {

    public static void main(String[] args){
        try {
            Cli config = new Cli();
            config.parse(args);

            String fileContent = new FileParser().parse(config.getFilename());
            System.out.println(fileContent);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
