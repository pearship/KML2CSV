
public class LinkElement {

	private String id;
	private String type;
	private NodeElement node1;
	private NodeElement node2;

	public LinkElement(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public boolean equals(Object o) {
		if (!(o instanceof LinkElement))
			return false;
		LinkElement l = (LinkElement) o;
		return type.equals(l.getType()) && containsNode(l.getNode1()) && containsNode(l.getNode2());
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNode1(NodeElement n) {
		node1 = n;
	}

	public void setNode2(NodeElement n) {
		node2 = n;
	}

	public NodeElement getNode1() {
		return node1;
	}

	public NodeElement getNode2() {
		return node2;
	}

	public boolean containsNode(NodeElement n) {
		if (node1 != null && node2 != null)
			return node1.equals(n) || node2.equals(n);
		return false;
	}

	public String toString() {
		return String.format("%s,%s,%s,%s", id, (node1 != null) ? node1.toString() : "-", (node2 != null) ? node2.toString() : "-", type);
	}
}