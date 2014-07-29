import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;


public class BigAsteroid extends StarWarObjects {

	protected Ellipse2D.Double circle;
	Thread t;
	protected ArrayList<Bullet> bullets;
	double time = 0.01;
	public BigAsteroid(double vX,double vY,double positionX,double positionY,ArrayList<StarWarObjects> o,ArrayList<Bullet> bullets)
	{
		super(vX,vY,o);
		circle = new Ellipse2D.Double(positionX,positionY,50,50);
		this.bullets = bullets;
		t = new Thread(this);
		t.start();
	}
	
	public void destroy()
	{
		objects.add(new SmallAsteroid(getVX()-5, getVY(), circle.getX(), circle.getY(), objects,bullets));
		objects.add(new SmallAsteroid(getVX(), getVY()-5, circle.getX(), circle.getY(), objects,bullets));
		objects.add(new SmallAsteroid(getVX()-5, getVY()-5, circle.getX(), circle.getY(), objects,bullets));
		objects.add(new SmallAsteroid(getVX()+5, getVY(), circle.getX(), circle.getY(), objects,bullets));
		//t.stop();
		System.out.println("objects in big asteroid "+objects.size());
		objects.remove(this);
		System.out.println("objects in big asteroid "+objects.size());
		System.out.println("bullets in big asteroid "+bullets.size());
		
		try {
			
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in finalizing big asteroid");
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GRAY);
		g2d.fill(circle);
	}

	public boolean isHittingplane(Plane p)
	{
		if(p.triangle.intersects(this.circle.getBounds2D()))
			return true;
		else
			return false;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		boolean check = true;
		while(check)
		{
			circle.x += getVX()*time;
			circle.y += getVY()*time;
			if(circle.x == 500)
			{
				circle.x = 0;
			}
			if(circle.x == 0)
			{
				circle.x = 500;
			}
			if(circle.y == 0)
			{
				circle.y = 1000;
			}
			if(circle.y == 1000)
			{
				circle.y = 0;
			}
			try
			{
			for(Bullet b : bullets)
			{
				if(b.intersects(this))
				{
					System.out.println(this);
					b.destroy();
					this.destroy();
					check = false;
				}
				
			}
			}
			catch(Exception e)
			{}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String toString()
	{
		return String.format("Bigasteroid");
	}
}
