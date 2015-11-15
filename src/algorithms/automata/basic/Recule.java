package algorithms.automata.basic;

public class Recule extends Avance {
	
	public Recule(double distance, double vitesse) {
		super(distance, vitesse);
	}


	@Override
	public void step() {
		if(!this.isFinished()){
			moveBack();
			sendLogMessage("recule");
			distanceParcourue += vitesse;
		}
	}
}
