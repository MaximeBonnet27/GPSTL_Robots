package algorithms.automata.priorite;

import java.util.ArrayList;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.cycle.*;
import algorithms.automata.priorite.*;
import algorithms.automata.sequence.*;
import algorithms.automata.priorite.predicates.*;

public class BrainPrioriteFactory {

	public static BrainPriorite tourneGMur(){
		ArrayList<AbstractBrainAutomaton> automata = new ArrayList<>();
		ArrayList<IPredicate> predicates = new ArrayList<>();

		automata.add(BasicBrainAutomatonFactory.tourneG());
		predicates.add(new MurDevantPredicate());
		automata.add(BasicBrainAutomatonFactory.avance(1));
		predicates.add(new TruePredicate());	
		return (new BrainPriorite(automata, predicates));
	}
}
