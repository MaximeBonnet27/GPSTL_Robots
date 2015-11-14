package algorithms.automata.parallele;

import algorithms.automata.Odometre;
import algorithms.automata.basic.BasicBrainAutomatonFactory;
import algorithms.automata.cycle.BrainCycleFactory;

public class BrainParalleleFactory {

	public static BrainParallele avanceNonStopAndTire(){
		return new BrainParallele(BrainCycleFactory.avanceNonStop(), BrainCycleFactory.tireNonStopDevant());
	}
	
	public static BrainParallele balayageTire(double angle,double step){
		return new BrainParallele(BrainCycleFactory.avanceNonStop(),BrainCycleFactory.balayageTire(angle,step));
	}
	
	public static BrainParallele esquive(){
		return new BrainParallele(BasicBrainAutomatonFactory.recule(Odometre.PRECISION), BasicBrainAutomatonFactory.tourneDemiD());
	}

}
