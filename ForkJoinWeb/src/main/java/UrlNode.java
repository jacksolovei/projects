import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class UrlNode implements Node {
    String url;
    HashSet<Node> urlNodes;

    public UrlNode(String url) {
        this.url = url;
    }

    @Override
    public HashSet<Node> getChildren() {
        urlNodes = new HashSet<>();
        String nodeUrl = this.getValue();
        String trimmedLink = nodeUrl.trim();
        String space = nodeUrl.substring(0, nodeUrl.indexOf("http"));
        try {
            Thread.sleep(150);
            Document doc = Jsoup.connect(trimmedLink).get();
            Elements elements = doc.select("a");
            List<String> attributes = elements.eachAttr("abs:href");
            for (String att : attributes) {
                if (att.startsWith(trimmedLink) && !att.contains("#")) {
                    if (space.isEmpty()) {
                        urlNodes.add(new UrlNode("\t" + att));
                    } else {
                        urlNodes.add(new UrlNode("\t" + space + att));
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return urlNodes;
    }

    @Override
    public String getValue() {
        return url;
    }

    @Override
    public boolean hasChildren() {
        return this.getChildren().size() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UrlNode)) return false;
        UrlNode urlNode = (UrlNode) o;
        return Objects.equals(url, urlNode.url) && Objects.equals(urlNodes, urlNode.urlNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, urlNodes);
    }
}
