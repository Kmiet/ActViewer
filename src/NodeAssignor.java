public class NodeAssignor {

    DocumentNode createTree(String fileContent){
        DocumentNode root = new DocumentNode(DocNodeType.Root);

        DocumentNode node = root;
        DocumentNode parent = null;
        DocNodeType type = DocNodeType.Undefined;
        String id = new String();
        String nodeContent = new String();

        for(String line : fileContent.replaceAll("\r\n", "/").split("/")){
            if(line.matches("Art\\. .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Article;
                String[] lineContent = line.split(" ");
                id = lineContent[1];
                nodeContent = lineContent[0] + " " + lineContent[1];
                String lineTmp = line.replaceFirst("Art\\. [0-9a-z]{1,4}\\. ", "");
                lineContent = lineTmp.split(" ");
                if(lineContent.length > 0){
                    if(this.analyseSpecialCaseType(lineContent) != DocNodeType.Undefined ) {
                        node = new DocumentNode(this.getPriorityParent(type, node), type, id, nodeContent);
                        type = this.analyseSpecialCaseType(lineContent);
                        id = lineContent[0];
                        nodeContent = lineTmp;
                    } else {
                        nodeContent = line;
                    }
                }
            } else if(line.matches("DZIAŁ .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Unit;
                id = line.split(" ")[1].replace(".", "");
                nodeContent = line;
            } else if(line.matches("Rozdział .*") || line.matches("^ROZDZIAŁ .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Chapter;
                id = line.split(" ")[1].replace(".", "");
                nodeContent = line;
            } else if(line.matches("[A-Z ]{4,}.*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Title;
                id = line;
                nodeContent = line;
            } else if(line.matches("[0-9]{1,2}[a-z]{0,1}\\..*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Section;
                id = line.split(" ")[0];
                nodeContent = line;
            } else if(line.matches("[0-9]{1,2}[a-z]{0,1}\\).*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Subsection;
                id = line.split(" ")[0];
                nodeContent = line;
            } else if(line.matches("[a-z]\\).*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, nodeContent);
                type = DocNodeType.Letter;
                id = line.split(" ")[0];
                nodeContent = line;
            } else {
                nodeContent = nodeContent + "\r\n" + line;
            }
        }
        node = new DocumentNode(this.getPriorityParent(type, node), type, id, nodeContent);
        return root;
    }

    private DocumentNode getPriorityParent(DocNodeType type, DocumentNode node){
        if(node.equals(null)) {
            return null;
        } else {
            while(node.getType().getPriority() < type.getPriority()){
               node = node.getParent();
            }
            if(node.getType().getPriority() == type.getPriority()) {
                return node.getParent();
            }
            return node;
        }
    }

    private DocNodeType analyseSpecialCaseType(String[] line){
        DocNodeType type = DocNodeType.Undefined;
        if(line[0].matches("[A-Z ]{4,}.*")){
            type = DocNodeType.Title;
        } else if(line[0].matches("[0-9]{1,2}[a-z]{0,1}\\..*")){
            type = DocNodeType.Section;
        } else if(line[0].matches("[0-9]{1,2}[a-z]{0,1}\\).*")){
            type = DocNodeType.Subsection;
        } else if(line[0].matches("[a-z]\\).*")){
            type = DocNodeType.Letter;
        }
        return type;
    }
}
