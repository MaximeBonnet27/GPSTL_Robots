package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.cycle.BrainCycleFactory;
import algorithms.automata.priorite.*;
import algorithms.automata.sequence.*;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import algorithms.automata.priorite.predicates.*;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;
	private int fireLatence;

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
