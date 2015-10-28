package algorithms;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.cycle.*;
import algorithms.automata.priorite.*;
import algorithms.automata.sequence.*;
import algorithms.automata.priorite.predicates.*;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;

	public Prototype() {
		delegate = BrainSequenceFactory.stage();
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
