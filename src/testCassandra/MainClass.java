package testCassandra;



import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import testCassandra.keyspace.KeySpaceUtil;
import testCassandra.keyspace.columnfamily.ColumnFamilyUtility;
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
		ColumnFamilyUtility.createColumnFamily("test", TABLE_NAME, session);
		
		
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
	
	public static  void deleteBooks(String title) {
	    StringBuilder sb = new StringBuilder("delete  from ")
	      .append("test.Books ")
	      .append("WHERE 1=1");
	 
	    String query = sb.toString();
	    ResultSet rs =  session.execute(query);
	    System.out.println(rs.getExecutionInfo().getTriedHosts());
	}

}
