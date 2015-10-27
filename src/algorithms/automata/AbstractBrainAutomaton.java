package algorithms.automata;

import characteristics.IBrain;
import robotsimulator.Brain;

public abstract class AbstractBrainAutomaton extends Brain{
	protected IBrain delegate;

	abstract public void setDelegate(IBrain delegate);

	abstract public boolean isFinished();
}
