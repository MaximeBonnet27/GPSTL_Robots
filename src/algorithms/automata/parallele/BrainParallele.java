package algorithms.automata.parallele;

import algorithms.automata.AbstractBrainAutomaton;
import characteristics.IBrain;

public class BrainParallele extends AbstractBrainAutomaton {
	private AbstractBrainAutomaton a1,a2;
	private boolean state;
	
	public BrainParallele(AbstractBrainAutomaton a1,AbstractBrainAutomaton a2) {
		this.a1=a1;
		this.a2=a2;
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.a1.setDelegate(delegate);
		this.a2.setDelegate(delegate);

	}

	@Override
	public boolean isFinished() {
		return this.a1.isFinished() || this.a2.isFinished();
	}

	@Override
	public void activate() {
		state=false;
		this.a1.activate();
		this.a2.activate();
	}

	@Override
	public void step() {
		if(!this.isFinished()){
			if(state){
				this.a2.step();
			}else{
				this.a1.step();
			}
			state=!state;
		}
	}

}
