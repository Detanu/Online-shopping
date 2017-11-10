package org.pkg;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * This class will get the lat long values.
 */
public class LatitudeAndLongitudeWithPincode {
	public static void main(String[] args) throws Exception {
		System.setProperty("java.net.useSystemProxies", "true");
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		// System.out.println("Please enter a location:");
		List<AddressDetails> lt = new DAOaddress().dataF();
		AddressDetails ad = new AddressDetails();
		for (int i = 0; i < lt.size(); i++) {
			//String postcode = lt.get(i).getName();
			String latLongs[] = getLatLongPositions("721517");
			System.out.println("Latitude: " + latLongs[0] + " and Longitude: " + latLongs[1]);

			ad.setName(lt.get(i).getName());
			ad.setLatitude(latLongs[0]);
			ad.setLongitude(latLongs[1]);
			MongoUtill.getDb().getCollection("Mydata").insert(ad);
		}

	}

	public static String[] getLatLongPositions(String address) throws Exception {
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		// System.out.println("URL : "+api);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document, XPathConstants.STRING);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location");
				String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
				expr = xpath.compile("//geometry/location");
				String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
				return new String[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;
	}
}
