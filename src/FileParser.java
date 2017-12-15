import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileParser {

    public String parse(String filePath) throws IOException{

        if(Files.exists(Paths.get(filePath))){

            byte[] bytetxt = Files.readAllBytes(Paths.get(filePath));
            // encoding set to ISO Windows-1250, tried ISO-8859-2, ISO 8859-13 but was not working properly
            String docContent = this.clearOut(new String(bytetxt, "Windows-1250"));

            return docContent;
        } else {
            throw new IOException("Wrong path to file");
        }
    }

    private String clearOut(String content){
        return  content.replaceAll("-\r\n", "")
                .replaceAll(".Kancelaria Sejmu.*\r\n[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\r\n","")
                .replaceAll("\r\n.\r\n","")
                .replaceFirst("KON.*\r\nRZECZY.*EJ\r\nz.*7 r.\r\n", "KONSTYTUCJA RZECZYPOSPOLITEJ\r\n")
                .replaceFirst("^.*\r\nUSTAWA\r\nz.*r.\r\no oc.*ów\r\n", "USTAWA o ochronie konkurencji i konsumentów\r\n");
    }
}
