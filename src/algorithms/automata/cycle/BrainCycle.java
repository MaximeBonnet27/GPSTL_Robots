package algorithms.automata.cycle;

import algorithms.automata.AbstractBrainAutomaton;
import characteristics.IBrain;

public class BrainCycle extends AbstractBrainAutomaton {
	private AbstractBrainAutomaton automaton;
	
	public BrainCycle() {
		super();
	}

	
	public BrainCycle(AbstractBrainAutomaton automaton) {
		super();
		this.automaton = automaton;
	}


	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void activate() {
		this.automaton.activate();
	}

	@Override
	public void step() {
		if(this.automaton.isFinished()){
			this.activate();
			this.step();
		}else{
			this.automaton.step();
		}
	}


	@Override
	public void setDelegate(IBrain delegate) {
		this.automaton.setDelegate(delegate);
	}
}
