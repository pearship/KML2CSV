public class KML2CSVExample {

	public static void main(String[] args) {
		KML2CSV kml2csv = new KML2CSV();
		kml2csv.readKML("airports.kml");
		kml2csv.writeCSV("airports.csv");
	}
}
