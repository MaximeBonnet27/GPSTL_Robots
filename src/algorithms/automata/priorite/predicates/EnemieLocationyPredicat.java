package algorithms.automata.priorite.predicates;

import java.awt.Point;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import characteristics.IBrain;
import characteristics.Parameters;

public class EnemieLocationyPredicat implements IPredicate {

	@Override
	public boolean verify(IBrain brain) {
		if(brain instanceof AbstractBrainAutomaton){
			Odometre odometre=((AbstractBrainAutomaton) brain).getOdometre();
			if(odometre!=null){
				
				Point pos=odometre.getLast();
				if(pos!=null){
					double y=pos.getY();
					return Math.abs(y-odometre.getPos().getY())>Odometre.PRECISION;
				}
			}
		}
		return false;
	}
}
