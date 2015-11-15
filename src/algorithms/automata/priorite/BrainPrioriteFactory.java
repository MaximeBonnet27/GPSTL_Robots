package algorithms.automata.priorite;

import java.util.ArrayList;

import algorithms.automata.*;
import algorithms.automata.basic.*;
import algorithms.automata.cycle.*;
import algorithms.automata.priorite.predicates.*;

public class BrainPrioriteFactory {

	public static BrainPriorite tourneGMur(){
		ArrayList<AbstractBrainAutomaton> automata = new ArrayList<>();
		ArrayList<IPredicate> predicates = new ArrayList<>();

		automata.add(BasicBrainAutomatonFactory.tourneG());
		predicates.add(new MurDevantPredicate());
		automata.add(BrainCycleFactory.avanceNonStop());
		predicates.add(new TruePredicate());	
		return (new BrainPriorite(automata, predicates));
	}
	
	
	public static BrainPriorite deplacementSansColisionSecondary(){
		ArrayList<AbstractBrainAutomaton> automata=new ArrayList<>();
		ArrayList<IPredicate> predicates= new ArrayList<>();
		
		automata.add(BasicBrainAutomatonFactory.recule(100));
		predicates.add(new EnemieDetectedPredicat(Math.PI));
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new AlieDetectedPredicat());
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new MurDevantPredicate());
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new MortDevant());
		
		automata.add(BrainCycleFactory.deplacementRand());
		predicates.add(new TruePredicate());
		
		return (new BrainPriorite(automata, predicates));
		
	}
	
	public static BrainPriorite deplacementSansColisionMain(){
		ArrayList<AbstractBrainAutomaton> automata=new ArrayList<>();
		ArrayList<IPredicate> predicates= new ArrayList<>();
		
		automata.add(new TirAVue());
		predicates.add(new EnemieDetectedPredicat(2*Math.PI));
		
		automata.add(BasicBrainAutomatonFactory.aligneXOdometrie());
		predicates.add(new EnemieLocationxPredicat());
		
		automata.add(BasicBrainAutomatonFactory.aligneYOdometrie());
		predicates.add(new EnemieLocationyPredicat());
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new AlieDetectedPredicat());
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new MurDevantPredicate());
		
		automata.add(BasicBrainAutomatonFactory.tourneD());
		predicates.add(new MortDevant());
		
		
		
		automata.add(BrainCycleFactory.deplacementRand());
		predicates.add(new TruePredicate());
		
		return (new BrainPriorite(automata, predicates));
		
	}
}
