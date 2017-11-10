package org.pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPage {

	public static void main(String[] args) throws IOException {

		// Make a URL to the web page
		URL url = null;
		int count = 0;//999999
		for (int i = 112447; i <= 130001; i++) {
			try {

				// url = new URL("http://postalpincode.in/api/pincode/"+i+"");
				url = new URL("https://pincode.saratchandra.in/api/pincode/" + i + "");

				// Get the input stream through URL Connection
				URLConnection con = url.openConnection();
				InputStream is = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String line = null;

				// read each line and write to System.out
				while ((line = br.readLine()) != null) {

					System.out.println(line);
					System.out.println(",");
					MongoUtill.getDb().getCollection("indiapostpincode").insert(line);
					System.out.println(count++ +"pincode"+i);
				}
			}

			catch (Exception e) {
				System.out.println("***********skip**********"+""+i);
				
			}
		}
	}
}