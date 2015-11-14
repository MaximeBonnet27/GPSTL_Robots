package algorithms.automata.priorite.predicates;

import characteristics.IBrain;
import characteristics.IFrontSensorResult;

public class MurDevantPredicate implements IPredicate {
	@Override
		public boolean verify(IBrain brain){
			return brain.detectFront().getObjectType() == IFrontSensorResult.Types.WALL;
		}
}
