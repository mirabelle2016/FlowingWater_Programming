
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class FlowingWater
{
	
	private static HashMap<Point,Integer> FlowWater(int[][] mat)
	{
		HashMap<Point,Integer> result=new HashMap<Point,Integer>();
		HashMap<Point,Integer> visited_pac=new HashMap<Point,Integer>();
		HashMap<Point,Integer> visited_atl=new HashMap<Point,Integer>();
		for(int i=0;i<mat[0].length;i++)
		{
			Point p=new Point(0,i);
			visited_pac.put(p, mat[0][i]);
			search(p,mat,visited_pac);
			
		}
		for(int i=0;i<mat.length;i++)
		{
			Point p=new Point(i,0);
			visited_pac.put(p, mat[i][0]);
			search(p,mat,visited_pac);
			
		}
		for(int i=0;i<mat[0].length;i++)
		{
			Point p=new Point(mat.length-1,i);
			visited_pac.put(p, mat[mat.length-1][i]);
			search(p,mat,visited_atl);
			
		}
		for(int i=0;i<mat.length;i++)
		{
			Point p=new Point(i,mat[0].length-1);
			visited_pac.put(p, mat[i][mat[0].length-1]);
			search(p,mat,visited_atl);
			
		}
		for(Point pp:visited_pac.keySet())
		{
			if(visited_atl.containsKey(pp))
			{
				result.put(pp, visited_atl.get(pp));
			}
		}
		
		return result;
	}
	private static void search(Point p, int[][] mat, HashMap<Point, Integer> visited) 
	{
		
		int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
		for(int i=0;i<4;i++)
		{
			Point new_p=new Point(p.x+dirs[i][0],p.y+dirs[i][1]);
			
			if(new_p.x<0||new_p.y<0||new_p.x>=mat.length||new_p.y>=mat[0].length)
				continue;
			
			if(mat[new_p.x][new_p.y]<mat[p.x][p.y]||visited.containsKey(new_p))
				continue;
			
			visited.put(new_p,mat[new_p.x][new_p.y]);
			
			search(new_p,mat,visited);
			
		}
	}
	public static void main(String[] args)
	{
		/*
		 * Test Code
		 * 
		 */

			int[][] mat=
			{
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12}
			};
			HashMap<Point,Integer> res=new HashMap<Point,Integer>();
			res=FlowWater(mat);
			Set s=res.entrySet();
			Iterator iter=s.iterator();
			while(iter.hasNext())
			{
				Entry e=(Entry) iter.next();
				Point p=(Point) e.getKey();
				System.out.println("index: ["+p.x+"]["+p.y+"]"+"Value: "+e.getValue());
			}
			
	}

}
