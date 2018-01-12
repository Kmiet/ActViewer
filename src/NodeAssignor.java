public class NodeAssignor {

    DocumentNode createTree(String fileContent){
        DocumentNode root = new DocumentNode(DocNodeType.Root);

        DocumentNode node = root;
        DocumentNode parent = null;
        DocNodeType type = DocNodeType.Undefined;
        String id = new String();
        String universalID = new String();
        String nodeContent = new String();

        for(String line : fileContent.replaceAll("\n", "/").split("/")){
            if(line.matches("Art\\. .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Article;
                String[] lineContent = line.split(" ");
                id = lineContent[1].replace(".", "");
                universalID = id;
                nodeContent = lineContent[0] + " " + lineContent[1];
                String lineTmp = line.replaceFirst("Art\\. [0-9a-z]{1,4}\\. ", "");
                lineContent = lineTmp.split(" ");
                if(lineContent.length > 0){
                    if(this.analyseSpecialCaseType(lineContent) != DocNodeType.Undefined ) {
                        node = new DocumentNode(this.getPriorityParent(type, node), type, id, universalID, nodeContent);
                        type = this.analyseSpecialCaseType(lineContent);
                        id = lineContent[0];
                        universalID = id;
                        nodeContent = lineTmp;
                    } else {
                        nodeContent = line;
                    }
                }
            } else if(line.matches("DZIAŁ .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Unit;
                id = line.split(" ")[1].replace(".", "");
                universalID = fromRoman(line.split(" ")[1].replace(".", ""));
                nodeContent = line;
            } else if(line.matches("Rozdział .*") || line.matches("^ROZDZIAŁ .*")) {
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Chapter;
                id = line.split(" ")[1].replace(".", "");
                universalID = fromRoman(line.split(" ")[1].replace(".", ""));
                nodeContent = line;
            } else if(line.matches("[A-Z ]{4,}.*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Title;
                id = line;
                universalID = id;
                nodeContent = line;
            } else if(line.matches("[0-9]{1,2}[a-z]{0,1}\\..*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Section;
                id = line.split(" ")[0].replace(".", "");
                universalID = id;
                nodeContent = line;
            } else if(line.matches("[0-9]{1,2}[a-z]{0,1}\\).*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Subsection;
                id = line.split(" ")[0].replace(")", "");
                universalID = id;
                nodeContent = line;
            } else if(line.matches("[a-z]\\).*")){
                parent = this.getPriorityParent(type, node);
                node = new DocumentNode(parent, type, id, universalID, nodeContent);
                type = DocNodeType.Letter;
                id = line.split(" ")[0].replace(")", "");
                universalID = id;
                nodeContent = line;
            } else {
                nodeContent = nodeContent + "\n" + line;
            }
        }
        node = new DocumentNode(this.getPriorityParent(type, node), type, id, universalID, nodeContent);
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

    private String fromRoman(String roman){
        String string = roman.replaceFirst("[a-z]{0,1}[A-B]{0,1}", "");
        boolean missingChar = false;
        if(string.length() != roman.length()){
            missingChar = true;
        }

        if(string.matches("[0-9]{1,3}")){
            return roman;
        }

        int res = 0;
        for (int i=0; i<string.length(); i++)
        {
            int s1 = romanValue(string.charAt(i));
            if (i+1 <string.length())
            {
                int s2 = romanValue(string.charAt(i+1));
                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
                i++;
            }
        }

        Integer tmp = new Integer(res);
        String result = tmp.toString();

        if(missingChar){
            result = result + roman.toCharArray()[roman.length()-1];
        }
        return result;

    }

    private int romanValue(char r)
    {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }
}
