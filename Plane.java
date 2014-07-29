import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Arc2D;
import java.util.ArrayList;


public class Plane extends StarWarObjects{
	
	protected Polygon triangle;
	private double acceleration = 2;
	private double angle = 270;
 	double bulletVelocity = 50;
	private double xCentroid;
	private double yCentroid;
	ArrayList<Bullet> bullets;
	double time = 0.5;
	Thread t;
	public Plane(int[] xPoints,int[] yPoints,ArrayList<StarWarObjects> o)
	{
		super(o);
		triangle = new Polygon(xPoints, yPoints, 3);
		xCentroid = (xPoints[0] + xPoints[1] + xPoints[2])/3;
		yCentroid = (yPoints[0] + yPoints[1] + yPoints[2])/3;
	    t = new Thread(this);
	    t.start();
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillPolygon(triangle);
//		System.out.print("x "+triangle.getBounds().x);
//		System.out.println();
//		System.out.println("y "+triangle.getBounds().y);
		return;
	}
	
	public void rotateClockWise()
	{
		double theta =0.17444;
		xCentroid = (triangle.xpoints[0] + triangle.xpoints[1] + triangle.xpoints[2] )/3;
		yCentroid = (triangle.ypoints[0]+triangle.ypoints[1]+triangle.ypoints[2])/3;
		double vectorX0 = triangle.xpoints[0] - xCentroid;
		double vectorY0 = triangle.ypoints[0] - yCentroid;
		double vectorX1 = triangle.xpoints[1] - xCentroid;
		double vectorY1 = triangle.ypoints[1] - yCentroid;
		double vectorX2 = triangle.xpoints[2] - xCentroid;
		double vectorY2 = triangle.ypoints[2] - yCentroid;
		double magnitude0 = Math.sqrt(Math.pow(vectorX0, 2)+Math.pow(vectorY0, 2));
		double magnitude1 = Math.sqrt(Math.pow(vectorX1, 2)+Math.pow(vectorY1, 2));
		double magnitude2 = Math.sqrt(Math.pow(vectorX2, 2)+Math.pow(vectorY2, 2));
		double angle1 = Math.atan2(vectorY0,vectorX0);
		double angle2 = Math.atan2(vectorY1,vectorX1);
		double angle3 = Math.atan2(vectorY2,vectorX2);
		triangle.xpoints[0] = (int)((Math.cos(angle1+theta))*magnitude0 + xCentroid);
		//System.out.print(triangle.xpoints[0]+" ");
		triangle.xpoints[1] = (int)((Math.cos(angle2+theta))*magnitude1 + xCentroid);
		//System.out.print(triangle.xpoints[1]+" ");
		triangle.xpoints[2] = (int)((Math.cos(angle3+theta))*magnitude2 + xCentroid);
		//System.out.print(triangle.xpoints[2]+" ");
		triangle.ypoints[0] = (int)((Math.sin(angle1+theta))*magnitude0 + yCentroid);
		//System.out.print(triangle.ypoints[0]+" ");
		triangle.ypoints[1] = (int)((Math.sin(angle2+theta))*magnitude1 + yCentroid);
		//System.out.print(triangle.ypoints[1]+" ");
		triangle.ypoints[2] = (int)((Math.sin(angle3+theta))*magnitude2 + yCentroid);
		//System.out.println(triangle.ypoints[2]+" ");
		angle = Math.toDegrees(angle1)+10;
		//System.out.println(angle);
	}
	
	public void rotateAntiClockWise()
	{
		double theta =0.17444;
		xCentroid = (triangle.xpoints[0] + triangle.xpoints[1] + triangle.xpoints[2] )/3;
		yCentroid = (triangle.ypoints[0]+triangle.ypoints[1]+triangle.ypoints[2])/3;
		double vectorX0 = triangle.xpoints[0] - xCentroid;
		double vectorY0 = triangle.ypoints[0] - yCentroid;
		double vectorX1 = triangle.xpoints[1] - xCentroid;
		double vectorY1 = triangle.ypoints[1] - yCentroid;
		double vectorX2 = triangle.xpoints[2] - xCentroid;
		double vectorY2 = triangle.ypoints[2] - yCentroid;
		double magnitude0 = Math.sqrt(Math.pow(vectorX0, 2)+Math.pow(vectorY0, 2));
		double magnitude1 = Math.sqrt(Math.pow(vectorX1, 2)+Math.pow(vectorY1, 2));
		double magnitude2 = Math.sqrt(Math.pow(vectorX2, 2)+Math.pow(vectorY2, 2));
		double angle1 = Math.atan2(vectorY0,vectorX0);
		double angle2 = Math.atan2(vectorY1,vectorX1);
		double angle3 = Math.atan2(vectorY2,vectorX2);
		triangle.xpoints[0] = (int)((Math.cos(angle1-theta))*magnitude0 + xCentroid);
		triangle.xpoints[1] = (int)((Math.cos(angle2-theta)*magnitude1 + xCentroid));
		triangle.xpoints[2] = (int)((Math.cos(angle3-theta)*magnitude2 + xCentroid));
		triangle.ypoints[0] = (int)((Math.sin(angle1-theta)*magnitude0 + yCentroid));
		triangle.ypoints[1] = (int)((Math.sin(angle2-theta)*magnitude1 + yCentroid));
		triangle.ypoints[2] = (int)((Math.sin(angle3-theta))*magnitude2 + yCentroid);
		angle = Math.toDegrees(angle1) - 10;
		//System.out.println(angle);
			
	}
	
	public void accelerate()
	{
		
		if(Math.sqrt(Math.pow(getVX(), 2)+Math.pow(getVY(), 2))<=10)
		{setVX(getVX() + acceleration*Math.cos(Math.toRadians(angle)));
		setVY(getVY() + acceleration*Math.sin(Math.toRadians(angle)));}
	}
	
	public void shoot()
	{
		if(bullets.size()<2)
		{
			Bullet bul = new Bullet(bulletVelocity*Math.cos(Math.toRadians(angle)),bulletVelocity*Math.sin(Math.toRadians(angle)),triangle.xpoints[0],triangle.ypoints[0],objects,bullets); 
			objects.add(bul);
			bullets.add(bul);
		}
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.exit(0);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			triangle.translate((int)(getVX()*time),(int)(getVY()*time));
			if(triangle.getBounds().x- triangle.getBounds().width/2 > 1000)
			{
				triangle.translate(-1000 + triangle.getBounds().width,0);
			}
			if(triangle.getBounds().x +triangle.getBounds().width/2 < 0)
			{
				triangle.translate(1000 - triangle.getBounds().width, 0);
			}
			if(triangle.getBounds().y - triangle.getBounds().height/2 > 500)
			{
				triangle.translate(0, -500+ triangle.getBounds().height);
			}
			if(triangle.getBounds().y +triangle.getBounds().height/2 < 0)
			{
				triangle.translate(0, 500 - triangle.getBounds().height);
			}
			try{
			for(StarWarObjects s : objects)
			{
				if(s instanceof BigAsteroid)
				{
					if(((BigAsteroid) s).isHittingplane(this))
						destroy();
				}
			}}
			catch(Exception e)
			{
				
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
