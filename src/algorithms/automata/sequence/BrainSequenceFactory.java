package algorithms.automata.sequence;

import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.priorite.BrainPrioriteFactory;
import java.util.ArrayList;
import algorithms.automata.AbstractBrainAutomaton;
public class BrainSequenceFactory {

	public static BrainSequence sequenceDemiTour(){
		ArrayList<AbstractBrainAutomaton> etapes = new ArrayList<>();
		etapes.add(BasicBrainAutomatonFactory.avance(100));
		etapes.add(BasicBrainAutomatonFactory.tourneDemiG());
		etapes.add(BasicBrainAutomatonFactory.avance(100));
		etapes.add(BasicBrainAutomatonFactory.tourneDemiD());
		return new BrainSequence(etapes);	
	}

	public static BrainSequence sequenceCercleG(){
		ArrayList<AbstractBrainAutomaton> etapes = new ArrayList<>();
		for(int i = 0; i < 100; ++i){
			etapes.add(BasicBrainAutomatonFactory.avance(1));
			etapes.add(BasicBrainAutomatonFactory.tourneStepG());
		}
		return new BrainSequence(etapes);
	}
	
	public static BrainSequence balayageTire(double angle, double step){
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		for(double i=-angle/2;i<angle/2;i+=step){
			list.add(BasicBrainAutomatonFactory.tire(i));
		}
		
		return new BrainSequence(list);
	}

	public static BrainSequence stage(){
		ArrayList<AbstractBrainAutomaton> etapes = new ArrayList<>();
		etapes.add(BasicBrainAutomatonFactory.tourneD());
		etapes.add(BrainPrioriteFactory.tourneGMur());
		return new BrainSequence(etapes);
	}


}
