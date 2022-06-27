public class KML2CSVExample {

	public static void main(String[] args) {
		KML2CSV kml2csv = new KML2CSV();
		kml2csv.readKML("airports_220624.kml");
		kml2csv.writeCSV("airports_220624.csv");
	}
}
