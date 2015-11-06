package algorithms;

import java.util.ArrayList;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.priorite.*;
import algorithms.automata.sequence.*;
import characteristics.Parameters;
import algorithms.automata.priorite.predicates.*;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;
	private int fireLatence;

	public Prototype() {
		fireLatence=0;
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		list.add(BasicBrainAutomatonFactory.tourneD());
		
		
		
		ArrayList<AbstractBrainAutomaton> l=new ArrayList<>();
		l.add(new TirAVue());
		l.add(BrainPrioriteFactory.tourneGMur());
		
		ArrayList<IPredicate> pred=new ArrayList<>();
		pred.add(new EnemieDetectedPredicat());
		pred.add(new TruePredicate());
		list.add(new BrainPriorite(l, pred));
		
		delegate=new BrainSequence(list);
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

	@Override
	public void fire(double dir) {
		if(fireLatence>Parameters.bulletFiringLatency){
			super.fire(dir);
			fireLatence=0;
		}
		fireLatence++;
		
	}

	
}
