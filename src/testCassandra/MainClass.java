package testCassandra;



import java.util.UUID;
import java.util.stream.IntStream;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import testCassandra.keyspace.KeySpaceUtil;
import testCassandra.model.Book;

public class MainClass {
	static Session session;
	private static String TABLE_NAME = "Books";

	public static void main(String[] args) {
		Cluster cluster = Cluster.builder() // (1)
				.addContactPoint("127.0.0.1").build();
		session = cluster.connect(); // (2)

		ResultSet rs = session.execute("select release_version from system.local"); // (3)
		Row row = rs.one();
		System.out.println(row.getString("release_version"));
		
		KeySpaceUtil.createKeyspace("test", "SimpleStrategy", 2, session);
		testCassandra.columnfamily.ColumnFamilyUtility.createColumnFamily("test", TABLE_NAME, session);
		for(int i=0; i < 10000000; i++) {
			UUID uuid=UUIDs.timeBased();
		//	insertbookByTitle(new Book(uuid, i +"-title"));
			deleteBooks(uuid,i +"-title");
		}
		session.close();
	}

	

	
	public static  void insertbookByTitle(Book book) {
	    StringBuilder sb = new StringBuilder("INSERT INTO ")
	      .append("test.Books").append("(id, title) ")
	      .append("VALUES (").append(book.getId())
	      .append(", '").append(book.getTitle()).append("');");
	 
	    String query = sb.toString();
	    ResultSet rs =  session.execute(query);
	    System.out.println(rs.getExecutionInfo().getTriedHosts());
	}
	
	public static  void selectAllBooks() {
	    StringBuilder sb = new StringBuilder("Select * From ")
	      .append("test.Books;");
	 
	    String query = sb.toString();
	    ResultSet rs =  session.execute(query);
	    System.out.println(rs.getExecutionInfo().getTriedHosts());
	    System.out.println(rs.spliterator().getExactSizeIfKnown());
	}
	
	public static  void deleteBooks(UUID partionKey,String title) {
	    StringBuilder sb = new StringBuilder("delete  from ")
	      .append("test.Books ")
	     // .append("WHERE 1=1")
	      .append(" WHERE id=" + partionKey);
	 
	    String query = sb.toString();
	    ResultSet rs =  session.execute(query);
	    System.out.println(rs.getExecutionInfo().getTriedHosts());
	}

}
