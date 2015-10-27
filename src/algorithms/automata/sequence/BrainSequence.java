package algorithms.automata.sequence;

import java.util.ArrayList;

import algorithms.automata.AbstractBrainAutomaton;
import characteristics.IBrain;

public class BrainSequence extends AbstractBrainAutomaton {

	private ArrayList<AbstractBrainAutomaton> sequence;
	private int indice;

	public BrainSequence() {
		super();
		this.setSequence(new ArrayList<>());

	}


	public BrainSequence(ArrayList<AbstractBrainAutomaton> sequence) {
		super();
		this.setSequence(sequence);

	}

	private void setSequence(ArrayList<AbstractBrainAutomaton> s){
		this.sequence=s;
	}

	@Override
	public boolean isFinished() {
		return this.indice>=this.sequence.size();
	}

	@Override
	public void activate() {
		if(!this.sequence.isEmpty()){
			this.indice=0;
			for(int i=0;i<this.sequence.size();i++){
				this.sequence.get(i).activate();
			}
		}else
			this.indice=-1;
	}

	@Override
	public void step() {
		if(!this.isFinished()){
			AbstractBrainAutomaton current=sequence.get(indice);
			
			if(current.isFinished()){
				indice++;
				this.step();
			
			}else{
				current.step();
			}
		}
	}


	@Override
	public void setDelegate(IBrain delegate) {
		for(int i=0;i<this.sequence.size();i++){
			this.sequence.get(i).setDelegate(delegate);
		}
	}
}
