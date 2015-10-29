package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import algorithms.automata.AbstractBrainAutomaton;

public class Tire extends AbstractBasicBrainAutomaton {

	private double angle;
	private boolean tire;

	public Tire(double angle) {
		super();
		this.angle=angle;
	}

	@Override
	public boolean isFinished() {
		return tire;
	}


	@Override
	public void activate() {
		this.tire=false;
	}

	@Override
	public void step() {
		if(!isFinished()){
				fire(getHeading() + angle);
				this.tire=true;
		}
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}
}
