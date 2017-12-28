import java.util.ArrayList;
import java.util.List;

public class NodeFinder {

    private SearchQuery query;
    private DocumentNode document;

    NodeFinder(SearchQuery query, DocumentNode doc){
        this.query = query;
        this.document = doc;
    }

    public DocumentNode[] searchDocument() throws ElementNotFoundException {
        List<DocumentNode> list = new ArrayList<>();
        DocumentNode node = this.document;
        for(SearchElement element : this.query.getElements()) {
            if(element.isMultipleSearch()) {
                List<DocumentNode> tempList = this.findMultipleNodes(node, element.getElementType(), element.getElementId(), element.getElementLastArticleId());
                for(DocumentNode nd : tempList){
                    list.add(nd);
                }
                return list.toArray(new DocumentNode[list.size()]);
            } else {
                node = this.find(node, element.getElementType(), element.getElementId());
            }
        }
        if(node != null) {
            list.add(node);
        } else {
            throw new ElementNotFoundException("Matching element not found");
        }
        return list.toArray(new DocumentNode[list.size()]);
    }

    private DocumentNode find(DocumentNode node, DocNodeType type, String id) throws ElementNotFoundException {
        DocumentNode result = null;
        DocumentNode temporary = null;
        if(node.getType() == type && (node.getUniversalID().equals(id) || node.getId().equals(id))){
            return node;
        } else {
            for(DocumentNode child : node.getChildrenNodes()) {
                temporary = this.find(child, type, id);
                if (temporary != null && result != null && (temporary.getUniversalID().equals(result.getUniversalID()) && temporary.getId().equals(result.getId())) && temporary.getType().equals(result.getType())){
                    throw new ElementNotFoundException("Multiple Matches Found");
                } else if (temporary != null && result !=null){
                    temporary = null;
                } else if (temporary != null){
                    result = temporary;
                    temporary = null;
                }
            }
        }
        return result;
    }

    private List<DocumentNode> findMultipleNodes(DocumentNode node, DocNodeType type, String id, String lastId) throws ElementNotFoundException {
        List<DocumentNode> list = new ArrayList<>();
        DocumentNode first = this.find(node, type, id);
        DocumentNode last = this.find(node, type, lastId);
        List<DocumentNode> children = first.getParent().getChildrenNodes();
        for (int i = children.indexOf(first); i < children.size(); i++) {
            if (children.get(i).getType() == type && (children.get(i).getUniversalID().equals(lastId)) || children.get(i).getId().equals(lastId)){
                break;
            } else if (children.get(i).getType() == type){
                list.add(children.get(i));
            }
        }
        list.add(last);
        return list;
    }
}
