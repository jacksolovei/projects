import java.util.HashSet;

public interface Node {
    HashSet<Node> getChildren();

    String getValue();

    boolean hasChildren();
}
