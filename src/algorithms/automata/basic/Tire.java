package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import algorithms.automata.AbstractBrainAutomaton;

public class Tire extends AbstractBasicBrainAutomaton {

	private double angle;
	private boolean tourTir;
	private AbstractBrainAutomaton automateAction;

	public Tire(AbstractBrainAutomaton automateAction, double angle) {
		super();
		this.automateAction = automateAction;
		this.angle=angle;
	}

	@Override
		public boolean isFinished() {
			return automateAction.isFinished();
		}


	@Override
		public void activate() {
			this.tourTir = false;
			this.automateAction.activate();
		}

	@Override
		public void step() {
			if(!isFinished()){
				if(tourTir){
					delegate.fire(delegate.getHeading() + angle);
				}
				else {
					automateAction.step();
				}

				tourTir = !tourTir;	
			}
		}

	@Override
		public void setDelegate(IBrain delegate) {
			this.delegate=delegate;
			this.automateAction.setDelegate(delegate);
		}
}
