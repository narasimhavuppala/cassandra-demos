/**
 * 
 */
package testCassandra.keyspace;

import java.util.StringJoiner;

import com.datastax.driver.core.Session;

/**
 * @author Shreemaan
 *
 */
public class KeySpaceUtil {
	
	public static void createKeyspace(String keyspaceName, 
			String replicationStrategy,
			int replicationFactor,
			Session session) {
		StringJoiner sb = new StringJoiner("")
				.add("CREATE KEYSPACE IF NOT EXISTS ")
				.add(keyspaceName)
				.add(" WITH replication = {")
				.add("'class':'")
				.add(replicationStrategy)
				.add("','replication_factor':")
				.add(String.valueOf(replicationFactor))
				.add(" };");

		String query = sb.toString();
		System.out.println(query);
		session.execute(query);
	}

}
