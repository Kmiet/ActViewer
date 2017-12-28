import java.util.LinkedList;
import java.util.List;

public class SearchQuery {

    private List<SearchElement> Elements;

    SearchQuery(){
        this.Elements= new LinkedList<>();
    }

    public void addElement(SearchElement element){
        this.Elements.add(element);
    }

    public List<SearchElement> getElements() {
        return Elements;
    }
}
