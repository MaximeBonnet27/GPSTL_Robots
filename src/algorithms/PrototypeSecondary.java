package algorithms;

import java.util.ArrayList;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.basic.TirAVue;
import algorithms.automata.priorite.BrainPriorite;
import algorithms.automata.priorite.BrainPrioriteFactory;
import algorithms.automata.priorite.predicates.EnemieDetectedPredicat;
import algorithms.automata.priorite.predicates.IPredicate;
import algorithms.automata.priorite.predicates.TruePredicate;
import algorithms.automata.sequence.BrainSequence;
import robotsimulator.Brain;

public class PrototypeSecondary extends Brain {

	private AbstractBrainAutomaton delegate;

	public PrototypeSecondary() {
				ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
				list.add(BasicBrainAutomatonFactory.tourneD());
				list.add(BrainPrioriteFactory.tourneGMur());
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


}
