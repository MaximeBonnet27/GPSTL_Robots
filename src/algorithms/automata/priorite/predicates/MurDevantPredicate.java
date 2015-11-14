package algorithms.automata.priorite.predicates;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import characteristics.IBrain;
import characteristics.IFrontSensorResult;

public class MurDevantPredicate implements IPredicate {
	@Override
		public boolean verify(IBrain brain){
			if(brain.detectFront().getObjectType() == IFrontSensorResult.Types.WALL){
				if(brain instanceof AbstractBrainAutomaton){
					Odometre o=((AbstractBrainAutomaton) brain).getOdometre();
					if(o!=null)
						o.resetLast();
				}
				return true;
			}
			return false;
		}
}
