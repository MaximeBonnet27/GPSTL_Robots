package algorithms.automata.priorite.predicats;

import java.util.ArrayList;

import characteristics.IBrain;

public class PredicatComposite implements IPredicat {
	private ArrayList<IPredicat> composes;

	public PredicatComposite(ArrayList<IPredicat> composes) {
		super();
		this.composes = composes;
	}



	@Override
	public boolean verify(IBrain brain) {
		for (IPredicat iPredicat : composes) {
			if(!iPredicat.verify(brain))
				return false;
		}
		return true;
	}
}
