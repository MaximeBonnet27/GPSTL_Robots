package algorithms.automata.basic;

import java.util.ArrayList;

import characteristics.IBrain;
import characteristics.IRadarResult;
import characteristics.Parameters;

public class TirAVue extends AbstractBasicBrainAutomaton {

	private double direction;

	public TirAVue() {
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}

	@Override
	public boolean isFinished() {
		ArrayList<IRadarResult> result = detectRadar();
		boolean opponentSecondaryBot=false;

		for(int i=0;i<result.size();i++){
			if(result.get(i).getObjectDistance()<Parameters.bulletRange){
				switch (result.get(i).getObjectType()) {
				case OpponentMainBot:
					
					direction=result.get(i).getObjectDirection();
					return false;

				case OpponentSecondaryBot:
					opponentSecondaryBot=true;
					direction=result.get(i).getObjectDirection();
					break;

				default:
					break;
				}
			}
		}

		return !opponentSecondaryBot;

	}

	@Override
	public void activate() {

	}

	@Override
	public void step() {
		if(!isFinished()){
			sendLogMessage("fire "+direction);
			fire(direction);
		}
	}

}
