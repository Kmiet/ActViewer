public class SearchElement {

    private DocNodeType type;
    private String id;
    private boolean isMultipleSearch;
    private String lastArticleID;

    SearchElement(SearchQuery query, DocNodeType type, String id){
        this.type = type;
        this.id = id;
        this.isMultipleSearch = false;
        if(this.id.contains(":")){
            String[] tmp = this.id.split(":");
            this.id = tmp[0];
            this.lastArticleID = tmp[1];
            this.isMultipleSearch = true;
        }
    }

    public DocNodeType getElementType() {
        return this.type;
    }

    public String getElementId() {
        return this.id;
    }

    public String getElementLastArticleId() {
        return lastArticleID;
    }

    public boolean isMultipleSearch(){
        return this.isMultipleSearch;
    }
}
