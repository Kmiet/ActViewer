import java.util.ArrayList;
import java.util.List;

public class DocumentNode {

    private DocumentNode parent;
    private DocNodeType type;
    private String id;
    private String content;
    private List<DocumentNode> Nodes;

    DocumentNode(DocNodeType type){
        this.content = "";
        this.type = type;
        this.Nodes = new ArrayList<>();
    }

    DocumentNode(DocumentNode parent, DocNodeType type, String content){
        this.parent = parent;
        this.type = type;
        this.content = content;
        this.Nodes = new ArrayList<>();
        parent.addNode(this);
    }

    DocumentNode(DocumentNode parent, DocNodeType type, String id, String content){
        this.parent = parent;
        this.type = type;
        this.id = id;
        this.content = content;
        this.Nodes = new ArrayList<>();
        parent.addNode(this);
    }

    private void addNode(DocumentNode node){
        this.Nodes.add(node);
    }

    @Override
    public String toString(){
        String result = this.content;
        for(DocumentNode node : this.Nodes){
            result = result + "\r\n" + node.toString();
        }
        return result;
    }

    public String find(){
        return null;
    }

    public String showTableOfContents(){
        String result = this.content;
        for(DocumentNode node : this.Nodes) {
            result = result + "\r\n" + node.content;
        }
        return result;
    }

    public DocNodeType getType() {
        return type;
    }

    public DocumentNode getParent() {
        return parent;
    }
}
