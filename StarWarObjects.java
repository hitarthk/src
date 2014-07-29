import java.awt.Graphics;
import java.util.ArrayList;

public abstract class StarWarObjects implements Runnable{
	
	private double vX;
	private double vY;
	
	protected ArrayList<StarWarObjects> objects;
	public StarWarObjects(double vX,double vY,ArrayList<StarWarObjects> o)
	{
		this.vX = vX;
		this.vY = vY;
		objects = o;
		
	}
	
	public abstract void destroy();
	
	public StarWarObjects(ArrayList<StarWarObjects> o)
	{
		vX = 0;
		vY = 0;
		objects = o;
	}
	
	public double getVX()
	{
		return vX;
	}
	
	public double getVY()
	{
		return vY;
	}
	
	public void setVX(double n)
	{
		vX = n;
	}
	
	public void setVY(double n)
	{
		vY = n;
	}
	public abstract void draw(Graphics g);
	
	public abstract void run();
}
