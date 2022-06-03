import java.util.Vector;

public class GraphElement {

	private String id;
	private Vector<NodeElement> nodes = new Vector<NodeElement>();
	private Vector<LinkElement> links = new Vector<LinkElement>();

	public GraphElement(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Vector<NodeElement> getNodes() {
		return nodes;
	}

	public Vector<LinkElement> getLinks() {
		return links;
	}

	public NodeElement getNode(String id, String type) {
		for (NodeElement n : nodes)
			if (n.getId().equals(id) && n.getType().equals(type))
				return n;
		return null;
	}

	public LinkElement getLink(String id, String type) {
		for (LinkElement l : links)
			if (l.getId().equals(id) && l.getType().equals(type))
				return l;
		return null;
	}

	public void addNode(NodeElement n) {
		if (!containsNode(n))
			nodes.add(n);
	}

	public void addLink(LinkElement l) {
		if (!containsLink(l))
			links.add(l);
	}

	public boolean containsNode(NodeElement n) {
		return nodes.contains(n);
	}

	public boolean containsLink(LinkElement l) {
		return links.contains(l);
	}

	public void dump() {
		System.out.println(id);
		for (LinkElement l : links)
			System.out.println("\t" + l.toString());
	}
}
