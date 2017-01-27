import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
	private int id;
	private String username;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsename() {
		return username;
	}

	public void setUsename(String usename) {
		this.username = usename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * return users in database
	 * 
	 * @return
	 */
	public static ArrayList<User> show_Users() {

		ArrayList<User> userlist = new ArrayList<User>();

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "SELECT * FROM paint.users;";
			ResultSet rs = EntityManger.stmt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsename(rs.getString(2));
				user.setPassword(rs.getString(3));
				userlist.add(user);
			}
		} catch (SQLException e) {
			System.out.println("error in show users!");

		}
		return userlist;
	}

	/**
	 * Recored a user in database
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static int signUp(String username, char[] password) {

		int i = 0;

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			ArrayList<User> userlist = User.show_Users();

			for (User user : userlist) {
				if (user.getUsename().equals(username)) {
					return -1;
				}
			}

			String sql = "INSERT INTO `paint`.`users` (`username`, `password`)"
					+ " VALUES ('" + username + "', '" + new String(password)
					+ "');";

			i = EntityManger.stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("error in insert user!");
		}
		return i;

	}
}
