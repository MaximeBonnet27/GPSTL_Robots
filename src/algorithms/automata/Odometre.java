package algorithms.automata;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.geom.Point2D;

import characteristics.IBrain;
import characteristics.IFrontSensorResult;
import characteristics.IRadarResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

public class Odometre implements IBrain {
	private static ArrayList<Odometre> obs=new ArrayList<>();
	private static Point lastPositionEnnemie=null;
	public static double PRECISION=Parameters.bulletRange;

	public static void addObserver(Odometre o){
		o.setLast(lastPositionEnnemie);
		obs.add(o);
	}

	public static void setLastPositionEnnemie(Point lastPositionEnnemie) {
		System.out.println("enemie pos:"+lastPositionEnnemie.getX()+" "+lastPositionEnnemie.getY());
		Odometre.lastPositionEnnemie = lastPositionEnnemie;
		for(int i=0;i<obs.size();i++){
			obs.get(i).setLast(lastPositionEnnemie);
		}

	}

	private IBrain delegate;
	private double x;
	private double y;
	private double step;
	private Point last;

	public Odometre(IBrain delegate,double step) {
		super();
		this.delegate = delegate;
		x=0;
		y=0;
		this.step=step;
	}

	public Point getLast() {
		return last;
	}

	public void resetLast(){
		last=null;
	}

	private void setLast(Point last) {
		this.last = last;
	}

	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	public void activate() {
		delegate.activate();
	}

	public void step() {
		delegate.step();
	}

	public void sendLogMessage(String message) {
		delegate.sendLogMessage(message);
	}

	public void move() {
		x+=step*Math.cos(getHeading());
		y+=step*Math.sin(getHeading());
		if(last!=null){
			if(Math.abs(x-last.getX())<PRECISION && Math.abs(y-last.getY())<PRECISION)
				resetLast();
		}
		delegate.move();
	}

	public void moveBack() {
		x-=step*Math.sin(getHeading());
		y-=step*Math.sin(getHeading());
		delegate.moveBack();
	}

	public void stepTurn(Direction dir) {
		delegate.stepTurn(dir);
	}

	public void broadcast(String message) {
		delegate.broadcast(message);
	}

	public ArrayList<String> fetchAllMessages() {
		return delegate.fetchAllMessages();
	}

	public double getHeading() {
		return delegate.getHeading();
	}

	public IFrontSensorResult detectFront() {
		return delegate.detectFront();
	}

	public ArrayList<IRadarResult> detectRadar() {
		return delegate.detectRadar();
	}

	public void fire(double direction) {
		delegate.fire(direction);
	}

	public double getHealth() {
		return delegate.getHealth();
	}

	public Point getPos(){
		Point p=new Point();
		p.setLocation(x, y);
		return p;
	}
}
