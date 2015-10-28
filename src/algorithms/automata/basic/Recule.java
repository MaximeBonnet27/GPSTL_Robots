package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

public class Recule extends Avance {
	
	public Recule(double distance, double vitesse) {
		super(distance, vitesse);
	}


	@Override
	public void step() {
		if(!this.isFinished()){
			delegate.moveBack();
			distanceParcourue += vitesse;
		}
	}
}
