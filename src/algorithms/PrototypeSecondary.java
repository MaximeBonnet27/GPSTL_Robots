package algorithms;

import java.util.ArrayList;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.basic.TirAVue;
import algorithms.automata.cycle.BrainCycleFactory;
import algorithms.automata.priorite.BrainPriorite;
import algorithms.automata.priorite.BrainPrioriteFactory;
import algorithms.automata.priorite.predicates.EnemieDetectedPredicat;
import algorithms.automata.priorite.predicates.IPredicate;
import algorithms.automata.priorite.predicates.TruePredicate;
import algorithms.automata.sequence.BrainSequence;
import algorithms.automata.sequence.BrainSequenceFactory;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

public class PrototypeSecondary extends Brain {

	private AbstractBrainAutomaton delegate;

	public PrototypeSecondary() {
		delegate=BrainPrioriteFactory.deplacementSansColisionSecondary();
		delegate.setDelegate(new Odometre(this, Parameters.teamASecondaryBotSpeed));
	}

	@Override
	public void activate() {
		delegate.activate();
	}

	@Override
	public void step() {
		if(getHealth()>0)
		delegate.step();
	}

}
