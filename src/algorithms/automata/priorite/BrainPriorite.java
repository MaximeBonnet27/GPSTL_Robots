package algorithms.automata.priorite;

import algorithms.automata.AbstractBrainAutomaton;
import characteristics.IBrain;
import algorithms.automata.priorite.predicates.IPredicate;
import java.util.HashMap;
import java.util.ArrayList;
public class BrainPriorite extends AbstractBrainAutomaton {

	ArrayList<AbstractBrainAutomaton> automata;
	ArrayList<IPredicate> predicates;

	boolean finished;

	int currentIndex;
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
			return finished;
		}

	@Override
		public void activate() {
			for(AbstractBrainAutomaton automaton : automata) {
				automaton.activate();
			}		
			this.finished = false;
			for(int i = 0; i < automata.size(); ++i){
				broadcast("YOOOO");
				if(predicates.get(i).verify(this)){
					currentIndex = i;	
					break;
				}

			}
		}

	@Override
		public void step() {
			if(!isFinished()){
				int upperBound;
				if(!automata.get(currentIndex).isFinished()){
					upperBound = currentIndex;
				}
				else {
					upperBound = automata.size();
				}
				for(int i = 0; i < upperBound; ++i){
					if(predicates.get(i).verify(this)){
						automata.get(i).activate();		
						currentIndex = i;	
						break;
					}
				}
				if(currentIndex == automata.size()){
					finished = true;
				}
				else{
					automata.get(currentIndex).step();
				}
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
