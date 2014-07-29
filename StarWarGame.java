import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class StarWarGame extends Applet implements KeyListener,Runnable{
	
	ArrayList<StarWarObjects> objects;
	Plane plane;
	ArrayList<Bullet> bullets;
	Thread t;
	public void init()
	{
		setSize(1000, 500);
		setBackground(Color.BLACK);
		objects = new ArrayList<>();
		bullets = new ArrayList<>();
		int[] x = {250,225,275};
		int[] y = {250,350,350};
		plane = new Plane(x,y,objects);
		plane.bullets = bullets;
		objects.add(plane);
		objects.add(new BigAsteroid(2, 2, 100, 100, objects,bullets));
		addKeyListener(this);
		t = new Thread(this);
		t.start();
	}
	

	
	
	public void paint(Graphics g)
	{
		try{
			//System.out.println("objects in paint "+objects.size());
		
		for(StarWarObjects ob : objects)
		{
			ob.draw(g);
		}
		}
		catch(Exception e)
		{
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getExtendedKeyCode());
		if(arg0.getExtendedKeyCode() == 37)
		{
			//System.out.println("uu");
			plane.rotateAntiClockWise();
		}
		else if(arg0.getExtendedKeyCode() == 38)
		{
			//System.out.println("aa");
			plane.accelerate();
		}
		else if(arg0.getExtendedKeyCode()==39)
		{
			///System.out.println("cc");
			plane.rotateClockWise();
		}
		if(arg0.getExtendedKeyCode() == 32)
		{
			//1System.out.println("ss");
			plane.shoot();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("ss");
		
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
