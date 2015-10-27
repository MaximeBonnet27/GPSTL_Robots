package algorithms;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.cycle.BrainCycleFactory;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;
	
	public Prototype() {
		delegate=BrainCycleFactory.test();
		delegate.setDelegate(this);
	}

	@Override
	public void activate() {
		delegate.activate();
	}

	@Override
	public void step() {
		delegate.step();
	}

}
