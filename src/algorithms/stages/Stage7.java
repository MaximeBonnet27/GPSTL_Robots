package algorithms.stages;

import java.awt.Point;

import characteristics.Parameters;
import robotsimulator.Brain;

public class Stage7 extends Stage1{

	private static int ROCKY = 0;
	private static int DARTY = 1;
	private static int CARREFOUR = 2;
	
	private static int UNDEFINED = 3;
	
	private int whoAmI;
	
	private int xCible, yCible;
	
	private double x, y;
	private double theta;
	
	private boolean tourTir;
	
	@Override
	public void activate() {
		
		x = Parameters.teamBMainBot1InitX;
		y = Parameters.teamBMainBot1InitY;
		
		theta = Parameters.teamBMainBot2InitHeading;
		
		xCible = 100;
		yCible = 100;
		
		tourTir = false;
		
		super.activate();
	}

	@Override
	public void step() {

		if(tourTir){
			fire(angleTir());
			tourTir = false;
		}
		else{
			super.step();
			tourTir = true;
		}
		
	}
	
	private double angleTir(){
		Point A = new Point();
		A.setLocation(x + Parameters.teamBMainBotSpeed * Math.cos(getHeading()), y + Parameters.teamBMainBotSpeed * Math.sin(getHeading()));
		
		Point B = new Point();
		B.setLocation(xCible, yCible);
		
		Point C = new Point();
		C.setLocation(x, y);
		return 0;
	}

}
