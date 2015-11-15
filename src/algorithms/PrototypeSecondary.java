package algorithms;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;
import algorithms.automata.priorite.BrainPrioriteFactory;
import characteristics.Parameters;
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
