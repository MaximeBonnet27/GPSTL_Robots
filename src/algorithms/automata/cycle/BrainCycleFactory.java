package algorithms.automata.cycle;

import java.util.ArrayList;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.sequence.BrainSequence;
import algorithms.automata.sequence.BrainSequenceFactory;

public class BrainCycleFactory {

	public static BrainCycle tourneNonStop(){
		return new BrainCycle(BasicBrainAutomatonFactory.tourneStepD());
	}

	public static BrainCycle demiTourCycle(){
		return new BrainCycle(BrainSequenceFactory.sequenceDemiTour());
	}
	
	public static BrainCycle test(){
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		list.add(BasicBrainAutomatonFactory.tourneDemiD());
		list.add(BasicBrainAutomatonFactory.tourneDemiG());
		return new BrainCycle(new BrainSequence(list));
	}

	public static BrainCycle avance(){
		return new BrainCycle(BasicBrainAutomatonFactory.avance(100));
	}
}
