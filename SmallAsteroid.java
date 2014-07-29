import java.security.AllPermission;
import java.util.ArrayList;


public class SmallAsteroid extends BigAsteroid{
	
	public SmallAsteroid(double vX,double vY,double positionX,double positionY,ArrayList<StarWarObjects> o,ArrayList<Bullet> bullets)
	{
		super(vX,vY,positionX,positionY,o,bullets);
		circle.height = 30.0;
		circle.width = 30.0;
	}
	
	public void destroy()
	{
		System.out.println("Small destroyed");
		//t.stop();
		objects.remove(this);
		try {
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		return String.format("Smallasteroid");
	}

}
