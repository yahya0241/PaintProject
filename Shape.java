import java.awt.Color;

public abstract class Shape {
	private int xbegin = 0;
	private int ybegin = 0;
	private int xend = 0;
	private int yend = 0;
	private Color shapeColor;

	public int getXbegin() {
		return xbegin;
	}

	public void setXbegin(int xbegin) {
		this.xbegin = xbegin;
	}

	public int getYbegin() {
		return ybegin;
	}

	public void setYbegin(int ybegin) {
		this.ybegin = ybegin;
	}

	public int getXend() {
		return xend;
	}

	public void setXend(int xend) {
		this.xend = xend;
	}

	public int getYend() {
		return yend;
	}

	public void setYend(int yend) {
		this.yend = yend;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public void setShapeColor(Color c) {
		this.shapeColor = c;
	}

	// method implement by shapes
	public abstract void show();

	public abstract void insert();

}
