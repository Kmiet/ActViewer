import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class FileParser {

    private NodeAssignor assignor;

    FileParser(){
        this.assignor = new NodeAssignor();
    }

    DocumentNode parse(String filePath) throws InvalidPathException, IOException{

        if(Files.exists(Paths.get(filePath))){

            byte[] bytetxt = Files.readAllBytes(Paths.get(filePath));
            // encoding set to ISO Windows-1250, tried ISO-8859-2, ISO 8859-13 but was not working properly
            String docContent = this.clearOut(new String(bytetxt, "Windows-1250"));
            return this.assignor.createTree(docContent);
        } else {
            throw new InvalidPathException("Wrong path to file", "ERROR");
        }
    }

    private String clearOut(String content){
        return  content.replaceAll("-\r\n", "")
                .replaceAll(".Kancelaria Sejmu.*\r\n[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\r\n","")
                .replaceAll("\r\n.\r\n","")
                .replaceFirst("s2p", " ")
                .replaceFirst("KON.*\r\nRZECZY.*EJ\r\nz.*7 r.\r\n", "KONSTYTUCJA RZECZYPOSPOLITEJ\r\n")
                .replaceFirst("^.*\r\nUSTAWA\r\nz.*r.\r\no oc.*ów\r\n", "USTAWA o ochronie konkurencji i konsumentów\r\n");
    }
}
