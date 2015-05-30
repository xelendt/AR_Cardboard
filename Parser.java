import java.awt.*;
import java.util.ArrayList;
import java.io.*;

// this takes the ".obj" file that is given and then returns the proper arrayList

// the information we need from the ".obj" file is the verticies and which verticies go with which face
// a line in the file that accounts for a vertex has (x, y, z) and looks like this:
// "v 0.123 0.456 0.789"
// the faces were a bit different. the lines with faces referred to the indicies of each vertex
// also, since we are not dealing with surface normals or texture coordinates, we only need part of each face line:
// "f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3"
// where v1, v2, and v3 are vertex indicies
// vt1, vt2, and vt3 are texture coordinate indicies 
// vn1, vn2, and vn3 are surface normal indicies

public class Parser
{
	ArrayList<Vertex> points = new ArrayList<Vertex>();

	public Parser()
	{
	}

	public ArrayList<Face> getFaces ( File file ) throws FileNotFoundException, IOException
	{
		ArrayList<Face> faces = new ArrayList<Face>();
		
		points.clear();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		try
		{
			String line;

			while ((line = reader.readLine()) != null )
			{
				if (line.startsWith("v "))
				{
					double x = Double.valueOf(line.split(" ")[1]);
					double y = Double.valueOf(line.split(" ")[2]);
					double z = Double.valueOf(line.split(" ")[3]);

					points.add(new Vertex(x,y,z));
				}

				if (line.startsWith("f "))
				{

					Vertex[] verticies = new Vertex[3];

					for(int i = 0; i < 3; i ++)
					{
						try
						{
							verticies[i] = points.get(Integer.valueOf(line.split(" ")[i+1].split("/")[0])-1);
						}
						catch(IndexOutOfBoundsException e)
						{
							System.out.println("off by 1 error");
						}
					}

					faces.add(new Face(verticies));
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Error: fix '.obj' files to be loaded");
		}
		
		return faces;
	}
}
