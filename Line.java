import java.awt.Color;
import java.awt.Graphics;
import java.sql.ResultSet;

public class Line extends Shape {

	/**
	 * drawing a line
	 * 
	 * @param xbegine
	 * @param ybegine
	 * @param xend
	 * @param yend
	 * @param c
	 */
	public static void draw(int xbegine, int ybegine, int xend, int yend,
			final Color c) {
		final Line line = new Line();

		line.setXbegin(xbegine);
		line.setYbegin(ybegine);

		line.setXend(xend);
		line.setYend(yend);

		Graphics g;
		g = Paint.panel.getGraphics();
		g.setColor(c);
		g.drawLine(line.getXbegin(), line.getYbegin(), line.getXend(),
				line.getYend());
		line.setShapeColor(c);
		line.insert();

	}

	/**
	 * insert to database
	 */
	public void insert() {
		Line line = this;

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "INSERT INTO `paint`.`line` (`xstart`, `ystart`, `xend`, `yend`, `linecolor`, `iduser`)"
					+ "VALUES ('"
					+ line.getXbegin()
					+ "','"
					+ line.getYbegin()
					+ "','"
					+ line.getXend()
					+ "','"
					+ line.getYend()
					+ "','"
					+ Integer.toString(line.getShapeColor().getRGB())
					+ "','"
					+ Login.user.getId() + "');";

			EntityManger.stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println("exption line insert!");

		}

	}

	/**
	 * show line on Paint frame
	 */
	public void show() {
		Line line = new Line();

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "SELECT * FROM paint.line where iduser="
					+ Login.user.getId() + ";";

			ResultSet rs = EntityManger.stmt.executeQuery(sql);

			while (rs.next()) {
				line.setXbegin(rs.getInt(2));
				line.setYbegin(rs.getInt(3));
				line.setXend(rs.getInt(4));
				line.setYend(rs.getInt(5));
				Color colors = new Color(Integer.parseInt(rs.getString(6)));
				line.setShapeColor(colors);

				Graphics g = Paint.panel.getGraphics();
				g.setColor(colors);

				g.drawLine(line.getXbegin(), line.getYbegin(), line.getXend(),
						line.getYend());

			}

		} catch (Exception e) {
			System.out.println("Show line exception!");

		}

	}
}