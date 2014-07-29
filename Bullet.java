import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

public class Bullet extends StarWarObjects {
	
	Ellipse2D.Double b;
	double time = 0.01;
	ArrayList<Bullet> bulls;
	Thread t;
	public Bullet(double vX,double vY,double positionX,double positionY,ArrayList<StarWarObjects> o,ArrayList<Bullet> bulls)
	{
		super(vX,vY,o);
		b = new Ellipse2D.Double(positionX,positionY,10,10);
		this.bulls = bulls;
		t = new Thread(this);
		t.start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(count<1000)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			b.x += getVX()*time;
			b.y += getVY()*time;
			if(b.x > 500)
			{
				b.x = 10;
			}
			if(b.x <0)
			{
				b.x = 490;
			}
			if(b.y <0)
			{
				b.y =990;
			}
			if(b.y > 1000)
			{
				b.y = 10;
			}
			count++;
		}
		destroy();
	}
	
	public boolean intersects(BigAsteroid as)
	{
		return(as.circle.intersects(b.getBounds2D()));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.YELLOW);
		g2d.fill(b);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//t.stop();
		objects.remove(this);
		bulls.remove(this);
		System.out.println("bullets in Bullet "+bulls.size());
		System.out.println("objects in Bullet "+objects.size());
		try {
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
