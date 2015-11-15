package algorithms.automata.priorite.predicates;

import java.awt.Point;
import java.util.ArrayList;
import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import characteristics.IBrain;
import characteristics.IRadarResult;
import characteristics.Parameters;
import characteristics.IRadarResult.Types;

public class EnemieDetectedPredicat implements IPredicate {
	 private double range;
	 
	
	public EnemieDetectedPredicat(double range) {
		super();
		this.range = range;
	}

	@Override
	public boolean verify(IBrain brain) {
		ArrayList<IRadarResult> result = brain.detectRadar();
		IRadarResult r;
		for(int i=0;i<result.size();i++){
			r=result.get(i);
			
			if((r.getObjectType()==Types.OpponentMainBot || r.getObjectType()==Types.OpponentSecondaryBot) &&
				result.get(i).getObjectDistance()<Parameters.bulletRange &&
				isInFront(brain, result.get(i).getObjectDirection())){
				tryToSendLocation(brain,r.getObjectDirection(),r.getObjectDistance());
				return true;				
			}
		}
		
		
		return false;
	}

	private boolean isInFront(IBrain brain,double dir){
		return (Math.abs(brain.getHeading()-dir)%(Math.PI*2)<range);
	}
	
	private void tryToSendLocation(IBrain brain,double dir,double dist){
		if(brain instanceof AbstractBrainAutomaton){
			Odometre odometre=((AbstractBrainAutomaton) brain).getOdometre();
			if(odometre!=null){
				double x=odometre.getX()+dist*Math.cos(dir);
				double y=odometre.getY()+dist*Math.sin(dir);
				if(Math.abs(x)<3000 && Math.abs(y)<3000){
					Point pos=new Point();
					pos.setLocation(x,y);
					Odometre.setLastPositionEnnemie(pos);	
				}
			}
		}
	}

}
