package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.IFrontSensorResult;
import characteristics.IFrontSensorResult.Types;
import characteristics.Parameters.Direction;

public class Esquive extends Tourne {

	private Types type;
	public Esquive(double angle, Direction direction, double stepAngle,Types type) {
		super(angle, direction, stepAngle);
		this.type=type;
	}

	@Override
	public void setDelegate(IBrain delegate) {
		this.delegate=delegate;
	}

	@Override
	public boolean isFinished() {
		return detectFront().getObjectType()!=type && !super.isFinished() ;
	}

}
