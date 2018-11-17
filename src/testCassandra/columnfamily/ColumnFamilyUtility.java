package testCassandra.columnfamily;

import java.util.StringJoiner;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class ColumnFamilyUtility {
	
	public static void createColumnFamily(String keyspaceName,String tableName,Session session) {
		StringJoiner sb = new StringJoiner("")
				.add("CREATE TABLE IF NOT EXISTS ")
				.add(keyspaceName+"."+ tableName)
				.add("(")
				.add("id uuid PRIMARY KEY, ")
				.add("title text,")
				.add("subject text);");

		String query = sb.toString();
		System.out.println(query);
		ResultSet rs = session.execute(query);
		System.out.println(rs.getExecutionInfo().getTriedHosts());
	}

}
