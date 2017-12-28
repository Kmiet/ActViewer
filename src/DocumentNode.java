import java.util.ArrayList;
import java.util.List;

public class DocumentNode {

    private String universalID;

    private DocumentNode parent;
    private DocNodeType type;
    private String id;
    private String content;
    private List<DocumentNode> Nodes;

    DocumentNode(DocNodeType type){
        this.content = "";
        this.id = "";
        this.type = type;
        this.Nodes = new ArrayList<>();
    }

    DocumentNode(DocumentNode parent, DocNodeType type, String content){
        this.parent = parent;
        this.type = type;
        this.id = "";
        this.universalID = "";
        this.content = content;
        this.Nodes = new ArrayList<>();
        parent.addNode(this);
    }

    DocumentNode(DocumentNode parent, DocNodeType type, String id, String universalID, String content){
        this.parent = parent;
        this.type = type;
        this.id = id;
        this.universalID = universalID;
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

    public String showTableOfContents(){
        String result = this.type.toString() + " " + this.id;
        for(DocumentNode node : this.Nodes) {
            result = result + "\r\n" + node.type.toString() + " " + node.id;
        }
        return result;
    }

    public DocNodeType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUniversalID() {
        return universalID;
    }

    public DocumentNode getParent() {
        return parent;
    }

    public List<DocumentNode> getChildrenNodes() {
        return Nodes;
    }
}
