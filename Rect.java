import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.ResultSet;

public class Rect extends Shape {
	private int weidth;
	private int height;

	/**
	 * drawing a rect
	 * 
	 * @param xbegine
	 * @param ybegine
	 * @param xend
	 * @param yend
	 * @param c
	 */
	public static void draw(int xbegine, int ybegine, int xend, int yend,
			final Color c) {

		final Rect rect = new Rect();

		rect.setXbegin(xbegine);
		rect.setYbegin(ybegine);
		rect.weidth = Math.abs(xbegine - xend);
		rect.height = Math.abs(ybegine - yend);

		Graphics2D g;
		g = (Graphics2D) Paint.panel.getGraphics();
		g.setColor(c);
		g.draw3DRect(xbegine, ybegine, rect.weidth, rect.height, true);
		rect.setShapeColor(c);

		rect.insert();
	}

	/**
	 * insert rect to database
	 */
	public void insert() {
		Rect rect = this;

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "INSERT INTO `paint`.`rect` (`xstart`, `ystart`, `width`, `height`, `rectcolor`,`iduser_rect`) "
					+ "VALUES ('"
					+ rect.getXbegin()
					+ "','"
					+ rect.getYbegin()
					+ "','"
					+ rect.weidth
					+ "', '"
					+ rect.height
					+ "', '"
					+ Integer.toString(rect.getShapeColor().getRGB())
					+ "','"
					+ Login.user.getId() + "');";

			EntityManger.stmt.executeUpdate(sql);

		} catch (Exception e) {

			System.out.println("exption rect insert!");

		}

	}

	/**
	 * show rect in Paint frame
	 */
	public void show() {
		Rect rect = new Rect();

		try {
			EntityManger.stmt = EntityManger.conn.createStatement();

			String sql = "SELECT * FROM Paint.rect where iduser_rect="
					+ Login.user.getId() + ";";

			ResultSet rs = EntityManger.stmt.executeQuery(sql);

			while (rs.next()) {
				rect.setXbegin(rs.getInt(2));
				rect.setYbegin(rs.getInt(3));
				rect.weidth = rs.getInt(4);
				rect.height = rs.getInt(5);
				Color colors = new Color(Integer.parseInt(rs.getString(6)));

				Graphics2D g;
				g = (Graphics2D) Paint.panel.getGraphics();
				g.setColor(colors);

				g.draw3DRect(rect.getXbegin(), rect.getYbegin(), rect.weidth,
						rect.height, true);

			}

		} catch (Exception e) {
			System.out.println("Show rect exception!");

		}

	}
}
