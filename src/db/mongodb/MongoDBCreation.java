package db.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MongoDBCreation {
	public static void main(String []args) throws ParseException{
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);
		
		
		db.getCollection("users").drop();
		db.getCollection("items").drop();
		
		IndexOptions options = new IndexOptions().unique(true);
		db.getCollection("users").createIndex(new Document("user_id",1), options);
		db.getCollection("items").createIndex(new Document("item_id",1), options);
		
		db.getCollection("users").insertOne(
				new Document().append("user_id", "1111")
				.append("password", "3229c1097c00d497a0fd282d586be050")
				.append("first_name", "John")
				.append("second_name", "Smith"));
		
		mongoClient.close();
		System.out.println("Import Complete");
	}
}
