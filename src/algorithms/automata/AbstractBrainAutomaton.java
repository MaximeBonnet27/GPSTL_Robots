package algorithms.automata;

import characteristics.IBrain;
import robotsimulator.Brain;
import robotsimulator.FrontSensorResult;
import java.util.ArrayList;
import characteristics.IFrontSensorResult;
import characteristics.IRadarResult;
import characteristics.Parameters;
public abstract class AbstractBrainAutomaton extends Brain{
	protected IBrain delegate;
	
	abstract public void setDelegate(IBrain delegate);

	abstract public boolean isFinished();

	public void sendLogMessage(String message) { message+=" "+posString();delegate.sendLogMessage(message); }
	public void move(){ delegate.move(); }
	public void moveBack(){ delegate.moveBack(); }
	public void stepTurn(Parameters.Direction dir){ delegate.stepTurn(dir); }
	public void broadcast(String message){ delegate.broadcast(message); }
	public ArrayList<String> fetchAllMessages(){ return delegate.fetchAllMessages(); }
	public double getHeading(){ return delegate.getHeading(); }
	public FrontSensorResult detectFront(){ return (FrontSensorResult) delegate.detectFront(); }
	public ArrayList<IRadarResult> detectRadar(){ return delegate.detectRadar(); } 
	public void fire(double direction){ delegate.fire(direction); } 
	public double getHealth(){ return delegate.getHealth(); }
	
	public Odometre getOdometre(){
		if(delegate==null)
			return null;
		
		if(delegate instanceof Odometre)
			return (Odometre) delegate;
		
		if(delegate instanceof AbstractBrainAutomaton)
			return ((AbstractBrainAutomaton)delegate).getOdometre();
		
		return null;
	}
	
	public String posString(){
		Odometre o=getOdometre();
		if(o!=null){
			return "x:"+o.getX()+" y:"+o.getY();
		}
		return "";
	}
}
