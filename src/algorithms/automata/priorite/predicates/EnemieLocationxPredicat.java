package algorithms.automata.priorite.predicates;

import java.awt.Point;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import characteristics.IBrain;
import characteristics.Parameters;

public class EnemieLocationxPredicat implements IPredicate {

	@Override
	public boolean verify(IBrain brain) {
		if(brain instanceof AbstractBrainAutomaton){
			Odometre odometre=((AbstractBrainAutomaton) brain).getOdometre();
			if(odometre!=null){
				Point pos=odometre.getLast();
				if(pos!=null){
					double x=pos.getX();
					return Math.abs(x-odometre.getPos().getX())>Odometre.PRECISION;
				}
			}
		}
		return false;
	}

}
