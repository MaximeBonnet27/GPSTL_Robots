package algorithms.automata.priorite.predicates;

import java.util.ArrayList;

import characteristics.IBrain;
import characteristics.IFrontSensorResult;
import characteristics.IRadarResult;
import characteristics.IRadarResult.Types;
import characteristics.Parameters;

public class MortDevant implements IPredicate {

	@Override
	public boolean verify(IBrain brain) {
		ArrayList<IRadarResult> result = brain.detectRadar();
		IRadarResult r;
		for(int i=0;i<result.size();i++){
			r=result.get(i);
			
			if(r.getObjectType()==Types.Wreck &&
				result.get(i).getObjectDistance()<(Parameters.bulletRange/2) &&
				isInFront(brain, result.get(i).getObjectDirection())){
				return true;				
			}
		}
		return false;
	}

	private boolean isInFront(IBrain brain,double dir){
		return (Math.abs(brain.getHeading()-dir)%(Math.PI*2)<Math.PI/3);
	}
}
