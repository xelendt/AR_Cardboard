import java.awt.*;

// each face is simply a set of points

public class Face
{
	Polygon poly;

	Vertex[] points;

	public Face(Vertex[] points)
	{
		this.points = points;
	}

	// this method takes all of the points and makes them into a single polygon
	// then, it draws the polygon, but only if it is in front of the viewer

	public void display(Graphics g, Dimension d, double screen, double xShift, double yShift, double zShift, double angle)
	{
		if ( ahead(xShift, yShift, zShift, angle) )
		{
			int[] xPoints = new int[points.length];
			int[] yPoints = new int[points.length];

			for(int i = 0; i < points.length; i ++)
			{
				xPoints[i] = points[i].convert2d(d, screen, xShift, yShift, zShift, angle).x;
				yPoints[i] = points[i].convert2d(d, screen, xShift, yShift, zShift, angle).y;
			}

			poly = new Polygon(xPoints, yPoints, points.length);

			g.drawPolygon(poly);
		}
	}
	
	// this method determines whether the viewer is ahead or behind the polygon
	// if the viewer is in front of any of them, it does not draw

	private boolean ahead(double xShift, double yShift, double zShift, double angle)
	{
		for (int i = 0; i < points.length; i ++)
		{
			if(zShift*-1 < points[i].zpos*Math.cos(angle) + points[i].xpos*Math.sin(angle))
			{
				return true;
			}
		}

		return false;
	}
}
