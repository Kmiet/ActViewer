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
            String docContent = this.clearOut(new String(bytetxt, "UTF-8"));
            return this.assignor.createTree(docContent);
        } else {
            throw new InvalidPathException("Wrong path to file", "");
        }
    }

    private String clearOut(String content){
        return  content.replaceAll("-\n", "")
                .replaceAll(".Kancelaria Sejmu.*\n[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\n","")
                .replaceAll("\n.\n","")
                .replaceFirst("s2p", " ")
                .replaceFirst(".KON.*\nRZECZY.*EJ\nz.*7 r.\n", "KONSTYTUCJA RZECZYPOSPOLITEJ\n")
                .replaceFirst("^.*\nUSTAWA\nz.*r.\no oc.*ów\n", "USTAWA o ochronie konkurencji i konsumentów\n");
    }
}
