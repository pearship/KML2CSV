
public class NodeElement {

	private String id;
	private String type;
	private String coordinate;
	private String[] connectedProperties = {};

	public NodeElement(String id, String type, String coord) {
		this.id = id;
		this.type = type;
		this.coordinate = coord;
	}

	public boolean equals(Object o) {
		if (!(o instanceof NodeElement))
			return false;
		NodeElement n = (NodeElement) o;
		return id.equals(n.getId()) && type.equals(n.getType());
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public String getConnectedProperty(int i) {
		return connectedProperties[i];
	}

	public String[] getConnectedProperties() {
		return connectedProperties;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public void setConnectedProperties(String[] connected) {
		this.connectedProperties = connected;
	}

	public String toString() {
		return String.format("%s,%s,%s", id, coordinate, type);
	}
}