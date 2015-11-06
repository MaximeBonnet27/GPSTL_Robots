package algorithms.automata.priorite.predicates;

import java.util.ArrayList;

import characteristics.IBrain;
import characteristics.IRadarResult;
import characteristics.Parameters;

public class EnemieDetectedPredicat implements IPredicate {
	@Override
	public boolean verify(IBrain brain) {
		ArrayList<IRadarResult> result = brain.detectRadar();
		boolean opponentMainBot=false;
		boolean opponentSecondaryBot=false;

		for(int i=0;i<result.size();i++){
			if(result.get(i).getObjectDistance()<Parameters.bulletRange){
				switch (result.get(i).getObjectType()) {
				case OpponentMainBot:
					opponentMainBot=true;
					break;
				case OpponentSecondaryBot:
					opponentSecondaryBot=true;
					break;

				default:
					break;
				}
			}
		}

		return opponentMainBot||opponentSecondaryBot;
	}

}
