package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

public class Avance extends AbstractBasicBrainAutomaton {
	
	protected double distance;
	protected double distanceParcourue;
	protected double vitesse;
	
	public Avance(double distance, double vitesse) {
		super();
		this.distance = distance;
		this.distanceParcourue = 0;
		this.vitesse = vitesse;
	}

	@Override
	public boolean isFinished() {
		return distanceParcourue >= distance;
	}

	@Override
	public void activate() {
		distanceParcourue=0;
	}

	@Override
	public void step() {
		if(!this.isFinished()){
			move();
			sendLogMessage("avance");
			distanceParcourue += vitesse;
		}
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}
}
