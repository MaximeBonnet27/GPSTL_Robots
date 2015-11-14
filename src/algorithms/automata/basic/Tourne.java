package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

public class Tourne extends AbstractBasicBrainAutomaton {
	private double angle;
	private Direction direction;
	private double currentAngle;
	private double stepAngle;
	
	public Tourne(double angle,Direction direction,double stepAngle) {
		super();
		this.angle=angle;
		this.direction=direction;
		this.stepAngle=stepAngle;
	}

	@Override
	public boolean isFinished() {
		return Math.abs(currentAngle)>=Math.abs(angle);
	}

	@Override
	public void activate() {
		this.currentAngle=0;
	}

	@Override
	public void step() {
		if(!this.isFinished()){
			stepTurn(direction);
			sendLogMessage("tourne "+direction);
			this.currentAngle+=Math.abs(this.stepAngle);
		}
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}
}
