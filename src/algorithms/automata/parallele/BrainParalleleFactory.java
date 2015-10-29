package algorithms.automata.parallele;

import algorithms.automata.cycle.BrainCycleFactory;

public class BrainParalleleFactory {

	public static BrainParallele avanceNonStopAndTire(){
		return new BrainParallele(BrainCycleFactory.avanceNonStop(), BrainCycleFactory.tireNonStopDevant());
	}
	
	public static BrainParallele balayageTire(double angle){
		return new BrainParallele(BrainCycleFactory.balayage(angle),BrainCycleFactory.tireNonStopDevant());
	}

}
