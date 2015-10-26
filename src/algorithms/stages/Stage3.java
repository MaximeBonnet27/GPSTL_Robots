package algorithms.stages;

import java.util.Stack;

import characteristics.IFrontSensorResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

public class Stage3 extends Brain {

	boolean firstTurn;
	boolean moveTask;
	boolean turnTask;
	boolean spinTask;
	boolean enBas;
	boolean turnStarted;
	Stack<Double> directions;
	int cpt;
	double x;
	int nbTurn;
	
	double quarter = 3000.0 / 4;
	@Override
	public void activate() {
		firstTurn = true;
		moveTask = false;
		turnTask = true;
		spinTask = false;
		enBas = false;
		turnStarted = false;
		directions = new Stack<>();
		directions.push(Parameters.NORTH);
		directions.push(Parameters.EAST);
		directions.push(Parameters.SOUTH);
		directions.push(Parameters.WEST);
		directions.push(Parameters.NORTH);

		nbTurn = 0;
		
		cpt = 0;
		x = Parameters.teamBMainBotFrontalDetectionRange;

	}

	@Override
	public void step() {
		if(moveTask){
			if(detectFront().getObjectType()==IFrontSensorResult.Types.WALL){
				turnTask = true;
				moveTask = false;
			}
			else if(enBas){
				if(!(detectFront().getObjectType() == IFrontSensorResult.Types.TeamMainBot)){
					x += Parameters.teamBMainBotSpeed;
					if(x >= quarter){
						spinTask = true;
						moveTask = false;
						x = 0;
					}
					move();
				}
			}
			else {
				move();
			}
		}
		else if(turnTask){
			if(firstTurn){
				if(isHeading(directions.peek())){
					turnTask = false;
					moveTask = true;
					firstTurn = false;
					directions.pop();
				}
				else {
					stepTurn(Parameters.Direction.RIGHT);
				}
			}
			else {
				if(isHeading(directions.peek())){
					turnTask = false;
					moveTask = true;
					double dir = directions.pop();
					if(dir == Parameters.EAST){
						enBas = true;
					}
					else {
						enBas = false;
					}
				}
				else {
					stepTurn(Parameters.Direction.LEFT);
				}
			}
		}
		else if(spinTask){

			if(nbTurn++ == 199){
				nbTurn = 0;
				spinTask = false;
				moveTask = true;
			}
			stepTurn(Parameters.Direction.LEFT);

				
		}
	}

	private static double HEADINGPRECISION = 0.001;

	private boolean isHeading(double dir) {
		return Math.abs(Math.sin(getHeading() - dir)) < HEADINGPRECISION;
	}
}