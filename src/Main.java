
public class Main {

    public static void main(String[] args){
        try {
            Cli config = new Cli();
            config.parse(args);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
