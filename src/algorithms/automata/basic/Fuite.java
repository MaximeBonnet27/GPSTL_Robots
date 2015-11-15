package algorithms.automata.basic;

import java.util.ArrayList;
import characteristics.IRadarResult;
import characteristics.Parameters;

public class Fuite extends Recule {
	
	public Fuite(double distance, double vitesse) {
		super(distance,vitesse);
	}

	@Override
	public boolean isFinished() {
		ArrayList<IRadarResult> result = detectRadar();

		for(int i=0;i<result.size();i++){
			if(result.get(i).getObjectDistance()<Parameters.bulletRange){
				switch (result.get(i).getObjectType()) {
				case OpponentMainBot:
					return false;

				case OpponentSecondaryBot:
					return false;

				default:
					break;
				}
			}
		}

		return super.isFinished();
	}

}
