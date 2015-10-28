package algorithms.automata.priorite.predicates;

import characteristics.IBrain;
import characteristics.IFrontSensorResult;

public class TruePredicate implements IPredicate {
	@Override
		public boolean verify(IBrain brain){
			return true;
		}
}
