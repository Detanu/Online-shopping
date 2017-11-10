package org.pkg;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class DAOaddress {
	public List<AddressDetails> dataF() throws UnknownHostException {
		Iterator<AddressDetails> itr = MongoUtill.getDb().getCollection("Address").find("{}").as(AddressDetails.class);
		ArrayList<AddressDetails> addr = new ArrayList<AddressDetails>();
		while (itr.hasNext()) {
			addr.add(itr.next());
			System.out.println(new Gson().toJson(addr));
		}
		return addr;

	}

	public static void main(String[] args) throws UnknownHostException {
		new DAOaddress().dataF();
	}
}
