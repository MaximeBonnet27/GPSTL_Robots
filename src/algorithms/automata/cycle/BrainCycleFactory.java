package algorithms.automata.cycle;

import java.util.ArrayList;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.sequence.BrainSequence;

public class BrainCycleFactory {

	public static BrainCycle tourneNonStop(){
		return new BrainCycle(BasicBrainAutomatonFactory.tourneStepG());
	}
	
	public static BrainCycle test(){
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		list.add(BasicBrainAutomatonFactory.tourneDemiD());
		list.add(BasicBrainAutomatonFactory.tourneDemiG());
		return new BrainCycle(new BrainSequence(list));
	}
}
