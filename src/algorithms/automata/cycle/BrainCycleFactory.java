package algorithms.automata.cycle;

import java.util.ArrayList;

import com.sun.swing.internal.plaf.basic.resources.basic;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.basic.TEAM;
import algorithms.automata.sequence.BrainSequence;
import algorithms.automata.sequence.BrainSequenceFactory;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

public class BrainCycleFactory {

	public static BrainCycle tourneNonStop(){
		return new BrainCycle(BasicBrainAutomatonFactory.tourneStepD());
	}
	
	public static BrainCycle demiTourCycle(){
		return new BrainCycle(BrainSequenceFactory.sequenceDemiTour());
	}
	
	public static BrainCycle avanceNonStop(){
		return new BrainCycle(BasicBrainAutomatonFactory.avanceStep());
	}
	
	public static BrainCycle reculNonStop(){
		return new BrainCycle(BasicBrainAutomatonFactory.reculStep());
	}
	
	public static BrainCycle tireNonStop(double angle){
		return new BrainCycle(BasicBrainAutomatonFactory.tire(angle));
	}
	
	public static BrainCycle tireNonStopDevant(){
		return new BrainCycle(BasicBrainAutomatonFactory.tireDevant());
	}
	
	public static BrainCycle tireNonStopDerriere(){
		return new BrainCycle(BasicBrainAutomatonFactory.tireDerriere());
	}
	
	public static BrainCycle tireNonStopGauche(){
		return new BrainCycle(BasicBrainAutomatonFactory.tireGauche());
	}
	
	public static BrainCycle tireNonStopDroite(){
		return new BrainCycle(BasicBrainAutomatonFactory.tireDroite());
	}

	public static BrainCycle balayageTire(double angle,double step){
		return new BrainCycle(BrainSequenceFactory.balayageTire(angle, step));
	}
	
	public static BrainCycle balayage(double angle){
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		list.add(BasicBrainAutomatonFactory.tourne(angle, Direction.LEFT));
		list.add(BasicBrainAutomatonFactory.tourne(angle, Direction.RIGHT));
		return new BrainCycle(new BrainSequence(list));
	}
	
	public static BrainCycle rotation(double rayon){
		int nbStep=(int) (rayon/((1-Math.cos(Parameters.teamAMainBotStepTurnAngle))*Parameters.teamAMainBotSpeed));
		ArrayList<AbstractBrainAutomaton> list=new ArrayList<>();
		System.out.println(nbStep);
		for(int i=0;i<nbStep;i++){
			list.add(BasicBrainAutomatonFactory.avanceStep());
		}
		list.add(BasicBrainAutomatonFactory.tourneStepG());
		return new BrainCycle(new BrainSequence(list));
	}
	
}
