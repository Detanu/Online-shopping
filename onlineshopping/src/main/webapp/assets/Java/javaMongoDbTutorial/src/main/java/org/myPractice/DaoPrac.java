package org.myPractice;

import java.util.Iterator;
import org.pkg.MongoUtill;

import com.google.gson.Gson;

public class DaoPrac {
	public MyPrac dataFetch()
	{
		String s="77.6024292";
		Iterator<MyPrac> itr=MongoUtill.getDb().getCollection("Mydata").aggregate("{$match:{'longitude':#}}",s)
				.and("{$group:{'_id':'$longitude','cities':{$push:{'name':'$name'}}}}")
				.and("{$project:{name:'$cities.name'}}")
				.as(MyPrac.class);
		MyPrac mp=new MyPrac();
		while(itr.hasNext())
		{
			
			mp=itr.next();
			//mp.set
			System.out.println(new Gson().toJson(mp));
		}
		return mp;
	}
	public static void main(String[] args) {
		new  DaoPrac().dataFetch();
	}

}
