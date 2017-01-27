import java.sql.DriverManager;
import java.sql.SQLException;

public class EntityManger {

	static java.sql.Connection conn = null;
	static java.sql.Statement stmt = null;

	// Create connection with database
	static {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/paint",
					"root", "");

		} catch (SQLException e) {

			System.out.println("sql exception");

		} catch (Exception e) {
			System.out.println("create connection exception!");

		}
	}

	/**
	 * close connection with database
	 */
	public static void close() {
		try {

			if (stmt != null)
				stmt.close();

		} catch (SQLException e) {
			System.out.println("close statement exception!");
		}

		try {

			if (conn != null)
				conn.close();

		} catch (SQLException e) {

			System.out.println("close connection exception!");
		}
	}

}
