package algorithms.stages;

import java.util.Stack;

import javax.annotation.Generated;

import characteristics.IFrontSensorResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

public class Stage5 extends Brain {

	boolean firstTurn;
	boolean moveTask;
	boolean turnTask;
	boolean rdvTask;
	boolean enBas;
	boolean turnStarted;

	boolean robot2turnTask;
	boolean robot2moveTask;

	Stack<Double> directions;
	static int cpt = 0;
	double x;
	int nbTurn;
	int numeroRobot;
	double xRDV = 1500;

	int cptAvance;
	int cptTurn;

	boolean tourneEnRondTask;

	@Override
	public void activate() {
		firstTurn = true;
		moveTask = false;
		turnTask = true;
		rdvTask = false;
		enBas = false;
		turnStarted = false;
		directions = new Stack<>();
		directions.push(Parameters.NORTH);
		directions.push(Parameters.EAST);
		directions.push(Parameters.SOUTH);
		directions.push(Parameters.WEST);
		directions.push(Parameters.NORTH);

		nbTurn = 0;
		cptAvance = 0;
		cptTurn = 0;
		numeroRobot = cpt;
		x = Parameters.teamBMainBotFrontalDetectionRange;
		tourneEnRondTask = false;
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
					if(x >= xRDV - 200){
						rdvTask = true;
						robot2turnTask = true;
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
		else if(rdvTask){

			switch(numeroRobot){
			case 0 :
				if(++cptAvance >= 50){
					rdvTask = false;
					tourneEnRondTask =true;
					cptTurn = 0;
				}
				else{
					move();
				}

				break;
			case 1 :
				if(robot2turnTask){
					if(++cptTurn == 25){
						robot2moveTask = true;
						robot2turnTask = false;
					}
					else{
						stepTurn(Direction.LEFT);
					}
				}
				if(robot2moveTask){
					if(++cptAvance >= 50){
						rdvTask = false;
						tourneEnRondTask =true;
						cptTurn = 0;
					}
					else{
						move();
					}
				}
				break;
			case 2 :
				rdvTask = false;
				tourneEnRondTask =true;
				cptTurn = 0;
				break;
			}


		}
		else if(tourneEnRondTask){
			if(++cptTurn <= 50){
				stepTurn(Direction.RIGHT);
			}
			else{
				if(cptTurn % 3 == 0){
					move();
				}
				else if(cptTurn % 3 == 1){

					fire(getHeading() - Math.PI / 2);
				}
				else {stepTurn(Direction.LEFT);
				}
			}
		}
	}

	private static double HEADINGPRECISION = 0.001;

	private boolean isHeading(double dir) {
		return Math.abs(Math.sin(getHeading() - dir)) < HEADINGPRECISION;
	}
}