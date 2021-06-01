import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;

public class WebProcessor extends RecursiveTask <TreeSet<String>> {
    public static TreeSet<String> values = new TreeSet<>();
    Node urlNode;

    public WebProcessor(Node urlNode) {
        this.urlNode = urlNode;
    }

    @Override
    protected TreeSet<String> compute() {
        TreeSet<String> links = new TreeSet<>(Comparator.comparing(String::trim));
        links.add(urlNode.getValue());
        if (!values.contains(urlNode.getValue().trim()) && values.size() < 300) {
            values.add(urlNode.getValue().trim());
            List<WebProcessor> taskList = new ArrayList<>();
            if (urlNode.hasChildren()) {
                for (Node child : urlNode.getChildren()) {
                    WebProcessor task = new WebProcessor(child);
                    task.fork();
                    taskList.add(task);
                }
                for (WebProcessor task : taskList) {
                    links.addAll(task.join());
                }
            }
        }
        return links;
    }
}
