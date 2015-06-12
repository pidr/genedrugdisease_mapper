package genedrugmapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class QueryMain {

	public static void main(String[] args) {
		String source1 = "";
		String source2 = "";
		Properties configuration = new Properties();
		try {
			String currentDir = System.getProperty("user.dir");
			FileInputStream confFile = new FileInputStream(currentDir+"/services.conf");
			configuration.load(confFile);
			source1 = configuration.getProperty("SERVICE1");
			source2 = configuration.getProperty("SERVICE2");
			System.out.println(configuration.getProperty("SERVICE1"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryString = "\n" + 
				"\n" + 
				"SELECT ?subject ?predicate ?object \n" + 
				"WHERE {\n" + 
				"  ?subject ?predicate ?object\n" + 
				"}\n" + 
				"LIMIT 25";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.sparqlService(source1, queryString);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);
	}
}
