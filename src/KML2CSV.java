import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 * Converts Google earth KML file to CSV file.
 * 
 * KML has the geometric information of airport surfaces.
 * 
 * @author PEARSHIP
 * @date 2022. 5. 26.
 *
 */
public class KML2CSV {

	private Vector<GraphElement> airports = new Vector<GraphElement>();

	public KML2CSV() {

	}

	public void readKML(String filename) {
		try {
			Builder builder = new Builder();
			File xmlFile = new File(filename);
			Document doc;
			doc = builder.build(xmlFile);

			Element kml = doc.getRootElement();
			String url = kml.getNamespaceURI();

			Element document = kml.getFirstChildElement("Document", url);
			Elements folders = document.getChildElements("Folder", url);

			for (int i = 0; i < folders.size(); i++) {

				Element folder = folders.get(i);
				String airportName = folder.getFirstChildElement("name", url).getValue();
				GraphElement airport = new GraphElement(airportName);

				Elements subFolders = folder.getChildElements("Folder", url);

				for (int j = 0; j < subFolders.size(); j++) {

					Element subFolder = subFolders.get(j);
					String nodeType = subFolder.getFirstChildElement("name", url).getValue();
					Elements placemarks = subFolder.getChildElements("Placemark", url);

					for (int k = 0; k < placemarks.size(); k++) {

						Element placemark = placemarks.get(k);
						String id = placemark.getFirstChildElement("name", url).getValue();
						String description = placemark.getFirstChildElement("description", url).getValue();
						String coord = placemark.getFirstChildElement("Point", url).getFirstChildElement("coordinates", url).getValue();

						String[] connectedProperties = description.split(",");

						NodeElement node = new NodeElement(id, nodeType, coord);
						node.setConnectedProperties(connectedProperties);

						airport.addNode(node);
					}
				}

				for (NodeElement n : airport.getNodes()) {
					for (String connectedProperty : n.getConnectedProperties()) {
						String[] data = connectedProperty.split("/");
						String connectedNodeId = data[0].trim();
						String connectedNodeType = data[1];
						String linkId = n.getId() + "-" + connectedNodeId;
						String linkType = data[2];
						LinkElement link = new LinkElement(linkId, linkType);
						link.setNode1(n);
						link.setNode2(airport.getNode(connectedNodeId, connectedNodeType));
						airport.addLink(link);
					}
				}
				airport.dump();
				airports.add(airport);
			}
		} catch (ParsingException | IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCSV(String filename) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(filename));
			br.write(
					"*Airport,Link_Id,Node1_Id,Node1_Longitude,Node1_Latitude,Node1_Altitude,Node1_Type,Node2_Id,Node2_Longitude,Node2_Latitude,Node2_Altitude,Node2_Type,Link_Type\n");
			for (GraphElement airport : airports)
				for (LinkElement link : airport.getLinks())
					br.write(airport.getId() + "," + link.toString() + "\n");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
