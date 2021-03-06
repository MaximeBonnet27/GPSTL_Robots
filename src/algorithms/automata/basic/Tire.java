package algorithms.automata.basic;

import characteristics.IBrain;

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
				sendLogMessage("fire "+angle);
				this.tire=true;
		}
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}
}
