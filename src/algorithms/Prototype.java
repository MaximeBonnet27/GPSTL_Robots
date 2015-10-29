package algorithms;

import java.util.ArrayList;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.cycle.*;
import algorithms.automata.parallele.BrainParallele;
import algorithms.automata.parallele.BrainParalleleFactory;
import algorithms.automata.priorite.*;
import algorithms.automata.sequence.*;
import algorithms.automata.priorite.predicates.*;
import robotsimulator.Brain;

public class Prototype extends Brain {

	private AbstractBrainAutomaton delegate;

	public Prototype() {
		//delegate = BrainParalleleFactory.balayageTire(Math.PI);
		/*ArrayList<AbstractBrainAutomaton> list = new ArrayList<>();
		list.add(BasicBrainAutomatonFactory.tourneD());
		list.add(new BrainParallele(BrainCycleFactory.avanceNonStop(), BrainParalleleFactory.balayageTire(Math.PI)));
		delegate=new BrainSequence(list);*/
		delegate=BrainCycleFactory.rotation(100);
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
