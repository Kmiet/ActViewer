import java.util.LinkedList;
import java.util.List;

public class DocumentNode {

    private DocumentNode parent;
    private List<DocumentNode> Nodes;

    DocumentNode(DocumentNode node){
        this.parent = node;
        this.Nodes = new LinkedList<>();
    }

    private void addNode(DocumentNode node){
        this.Nodes.add(node);
    }
}
