package org.pkg;

import java.net.UnknownHostException;
import org.jongo.Jongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoUtill {
	public static Jongo getDb() {
		DB db = null;
		try {
			db = new MongoClient(MongoConstant.DEVENV, 27017).getDB(MongoConstant.ENVDB);
			
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		Jongo jongo = new Jongo(db);

		return jongo;
	}

}
