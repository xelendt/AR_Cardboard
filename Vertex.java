import java.awt.*;

// This class represents a point in 3d space
// it has an (x, y, z)

public class Vertex
{
	double xpos;
	double ypos;
	double zpos;

	public Vertex(double xpos, double ypos, double zpos)
	{
		this.xpos = xpos;
		this.ypos = ypos;
		this.zpos = zpos;
	}

	// a method to display the point at its position in "fake" 3D

	public void display2d(Graphics g, Dimension d, double screen, double xShift, double yShift, double zShift, double angle)
	{
		g.setColor(Color.BLACK);

		int cx = (int)(d.width/2);
		int cy = (int)(d.height/2);

		Point twoD = convert2d(d, screen, xShift, yShift, zShift, angle);

		g.fillOval((twoD.x), (twoD.y), 3, 3);
	}
	
	// this method takes the point from 3d, rotates it in 3d and then transforms it in 3d
	// after all of the transformations have taken place, then it is converted into 2d

	public Point convert2d(Dimension d, double screen, double xShift, double yShift, double zShift, double angle)
	{
		int cx = (int)(d.width/2);
		int cy = (int)(d.height/2);
		
		double xtemp = xpos * Math.cos(angle) - zpos * Math.sin(angle);
		double ztemp = xpos * Math.sin(angle) + zpos * Math.cos(angle);

		int y = -1*(int)(screen*(ypos*2500+cy + yShift)/(2*ztemp+screen+zShift));
		int x = (int)(screen*(xtemp*2500+cx + xShift)/(2*ztemp+screen+zShift));


		return new Point(x + cx, y + cy);
	}

}
