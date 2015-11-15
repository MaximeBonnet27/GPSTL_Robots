package algorithms;

import algorithms.automata.*;
import algorithms.automata.priorite.*;
import characteristics.Parameters;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;

	public Prototype() {
		delegate=BrainPrioriteFactory.deplacementSansColisionMain();
		Odometre od=new Odometre(this, Parameters.teamAMainBotSpeed);
		Odometre.addObserver(od);
		delegate.setDelegate(od);
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
