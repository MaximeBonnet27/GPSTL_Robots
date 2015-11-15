package algorithms.automata.priorite;

import algorithms.automata.AbstractBrainAutomaton;
import characteristics.IBrain;
import algorithms.automata.priorite.predicates.IPredicate;
import java.util.ArrayList;

public class BrainPriorite extends AbstractBrainAutomaton {

	ArrayList<AbstractBrainAutomaton> automata;
	ArrayList<IPredicate> predicates;

	boolean finished;

	int currentIndex;
	int indexTrue;

	public BrainPriorite() {
		super();
	}


	public BrainPriorite(ArrayList<AbstractBrainAutomaton> automata, ArrayList<IPredicate> predicates) {
		super();
		this.automata = automata;
		this.predicates = predicates;
	}


	@Override
	public boolean isFinished() {
		for(int i = 0; i < predicates.size(); ++i){
			if(predicates.get(i).verify(this)){	
				indexTrue = i;	
				return false;
			}
		}
		return true;
	}

	@Override
	public void activate() {
		for(AbstractBrainAutomaton automaton : automata) {
			automaton.activate();
		}		

		for(int i = 0; i < automata.size(); ++i){
			if(predicates.get(i).verify(this)){
				currentIndex = i;
				indexTrue=i;
				break;
			}
		}
	}

	@Override
	public void step() {
		if(!isFinished()){
			if(currentIndex>indexTrue || (currentIndex<indexTrue && automata.get(currentIndex).isFinished())){
				currentIndex=indexTrue;
				automata.get(currentIndex).activate();
				
			}
			if(automata.get(currentIndex).isFinished()){
				automata.get(currentIndex).activate();
			}
			automata.get(currentIndex).step();
		}
	}


	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate = delegate;
		for(AbstractBrainAutomaton automaton : automata){	
			automaton.setDelegate(delegate);
		}	
	}
}
