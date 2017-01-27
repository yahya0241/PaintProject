import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Circle extends Shape {

	private int radius = 0;

	/**
	 * draw a circle
	 * 
	 * @param xbegine
	 * @param ybegine
	 * @param xend
	 * @param yend
	 * @param c
	 */
	public static void draw(int xbegine, int ybegine, int xend, int yend,
			final Color c) {
		Circle circle = new Circle();

		circle.setXbegin(xbegine);
		circle.setYbegin(ybegine);
		circle.radius = Math.abs(circle.getXbegin() - xend);

		Graphics2D g;
		g = (Graphics2D) Paint.panel.getGraphics();
		g.setColor(c);
		g.drawOval(circle.getXbegin() - circle.radius / 2, circle.getYbegin()
				- circle.radius / 2, circle.radius, circle.radius);
		circle.setShapeColor(c);

		circle.insert();
	}

	/**
	 * insert a circle to database
	 */
	public void insert() {

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "INSERT INTO `paint`.`circle` (`xstart`, `ystart`, `radius`, `circlecolor`, `iduser_circle`) "
					+ "VALUES ('"
					+ this.getXbegin()
					+ "', '"
					+ this.getYbegin()
					+ "', '"
					+ this.radius
					+ "', '"
					+ Integer.toString(this.getShapeColor().getRGB())
					+ "','"
					+ Login.user.getId() + "')";

			EntityManger.stmt.executeUpdate(sql);

		} catch (SQLException e) {

			System.out.println("circlr insert exception");
			e.printStackTrace();
		}

	}

	/**
	 * show circle on Paint frame
	 */
	public void show() {

		Circle circle = new Circle();

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();
			String sql = "SELECT * FROM Paint.circle where iduser_circle="
					+ Login.user.getId() + ";";

			ResultSet rs = EntityManger.stmt.executeQuery(sql);

			while (rs.next()) {
				circle.setXbegin(rs.getInt(2));
				circle.setYbegin(rs.getInt(3));
				circle.radius = rs.getInt(4);

				Color colors = new Color(Integer.parseInt(rs.getString(5)));

				Graphics2D g;
				g = (Graphics2D) Paint.panel.getGraphics();
				g.setColor(colors);

				g.drawOval(circle.getXbegin() - circle.radius / 2,
						circle.getYbegin() - circle.radius / 2, circle.radius,
						circle.radius);

			}

		} catch (Exception e) {
			System.out.println("Show circle exception!");

		}

	}

}
