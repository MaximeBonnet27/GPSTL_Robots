package algorithms.automata.priorite.predicates;

import java.util.ArrayList;

import characteristics.IBrain;

public class PredicateComposite implements IPredicate {
	private ArrayList<IPredicate> composes;

	public PredicateComposite(ArrayList<IPredicate> composes) {
		super();
		this.composes = composes;
	}



	@Override
	public boolean verify(IBrain brain) {
		for (IPredicate iPredicat : composes) {
			if(!iPredicat.verify(brain))
				return false;
		}
		return true;
	}
}
