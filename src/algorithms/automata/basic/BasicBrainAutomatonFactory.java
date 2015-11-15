package algorithms.automata.basic;

import characteristics.IBrain;
import characteristics.Parameters;
import characteristics.Parameters.Direction;

import java.awt.Point;

import algorithms.automata.AbstractBrainAutomaton;
import algorithms.automata.Odometre;

public class BasicBrainAutomatonFactory {
	private static TEAM team=TEAM.B;

	public static void setTeam(TEAM team){
		BasicBrainAutomatonFactory.team=team;
	}

	/*********
	 * TOURNE *
	 **********/

	public static AbstractBasicBrainAutomaton tourne(double angle,Direction dir){
		return new Tourne(angle, dir, team.getMainBotStepTurnAngle());
	}

	public static AbstractBasicBrainAutomaton tourneStepG(){
		return tourne(team.getMainBotStepTurnAngle()+team.getMainBotStepTurnAngle()/2,Direction.LEFT);
	}

	public static AbstractBasicBrainAutomaton tourneStepD(){
		return tourne(team.getMainBotStepTurnAngle(),Direction.RIGHT);

	}

	public static AbstractBasicBrainAutomaton tourneG(){
		return tourne(Math.PI/2,Direction.LEFT);
	}

	public static AbstractBasicBrainAutomaton tourneD(){
		return tourne(Math.PI/2,Direction.RIGHT);

	}

	public static AbstractBasicBrainAutomaton tourneDemiG(){
		return tourne(Math.PI,Direction.LEFT);

	}
	public static AbstractBasicBrainAutomaton tourneDemiD(){
		return tourne(Math.PI,Direction.RIGHT);
	}
	
	public static AbstractBasicBrainAutomaton esquiveWreck(){
		return new Esquive(Math.PI/2, Direction.LEFT, team.getMainBotStepTurnAngle(),characteristics.IFrontSensorResult.Types.Wreck);
	}
	
	public static AbstractBasicBrainAutomaton esquiveWall(){
		return new Esquive(Math.PI, Direction.LEFT, team.getMainBotStepTurnAngle(),characteristics.IFrontSensorResult.Types.WALL);
	}
	

	/*********
	 * AVANCE *
	 **********/

	public static AbstractBasicBrainAutomaton avance(double distance){
		return new Avance(distance, team.getMainBotSpeed());
	}		

	public static AbstractBasicBrainAutomaton avanceStep(){
		return avance(team.getMainBotSpeed());
	}	

	/*********
	 * RECUL *
	 **********/
	public static AbstractBasicBrainAutomaton recule(double distance){
		return new Recule(distance, team.getMainBotSpeed());
	}

	public static AbstractBasicBrainAutomaton reculStep(){
		return recule(team.getMainBotSpeed());
	}	

	public static AbstractBasicBrainAutomaton fuite(){
		return new Fuite(100, team.getMainBotSpeed());
	}
	
	/*******
	 * TIRE *
	 ********/

	public static AbstractBasicBrainAutomaton tire(double angle){
		return new Tire(angle);
	}

	public static AbstractBasicBrainAutomaton tireDevant(){
		return tire(0);
	}

	public static AbstractBasicBrainAutomaton tireDerriere(){
		return tire(Math.PI);
	}

	public static AbstractBasicBrainAutomaton tireGauche(){
		return tire(Math.PI/2);
	}

	public static AbstractBasicBrainAutomaton tireDroite(){
		return tire(-Math.PI/2);
	}
	
	public static AbstractBasicBrainAutomaton deplacementRand(){
		return new AbstractBasicBrainAutomaton() {
			private AbstractBrainAutomaton current;
			private boolean doitAvance=true;
			
			@Override
			public void step() {
				current.step();
			}
			
			@Override
			public void activate() {
				if(Math.random()<0.6 || doitAvance){
					current=BasicBrainAutomatonFactory.avance(100+Math.random()*200);
					current.setDelegate(delegate);
					doitAvance=false;
				}else if(Math.random()<0.25){
					current=BasicBrainAutomatonFactory.tourne(Math.PI/4+Math.random()*Math.PI/2, Direction.LEFT);
					current.setDelegate(delegate);
					doitAvance=true;
				}else{
					current=BasicBrainAutomatonFactory.tourne(Math.PI/4+Math.random()*Math.PI/2, Direction.RIGHT);
					current.setDelegate(delegate);
					doitAvance=true;
				}
				current.activate();
			}
			
			@Override
			public void setDelegate(IBrain delegate) {
				if(current!=null)
				this.current.setDelegate(delegate);
				this.delegate=delegate;
			}
			
			@Override
			public boolean isFinished() {
				return current==null || current.isFinished();
			}
		};
	}
	
	public static AbstractBasicBrainAutomaton aligneXOdometrie(){
		return new AbstractBasicBrainAutomaton() {
			
			double xdest;
			double x;
			double stepTurn=Parameters.teamAMainBotStepTurnAngle;
			double PRECISION=stepTurn;
			@Override
			public void step() {
				
				if(!isFinished()){
					double angleDir;
					
					if(x<xdest){
						angleDir=Math.abs(getHeading())%(2*Math.PI);
						
						if(angleDir>PRECISION){
							sendLogMessage("tourne "+Direction.LEFT);
							stepTurn(Direction.LEFT);
						}else if(angleDir<-PRECISION){
							sendLogMessage("tourne "+Direction.RIGHT);
							stepTurn(Direction.RIGHT);
						}else{
							sendLogMessage("avance");
							move();
						}
						
					}else{
						angleDir=Math.abs(getHeading()-Math.PI)%(2*Math.PI);
						
						if(angleDir>PRECISION){
							stepTurn(Direction.RIGHT);
							stepTurn(Direction.RIGHT);
						}else if(angleDir<-PRECISION){
							stepTurn(Direction.LEFT);
							stepTurn(Direction.LEFT);
						}else{
							sendLogMessage("avance");
							move();
						}
					}
				}
			}
			
			@Override
			public void activate() {
				
			}
			
			@Override
			public void setDelegate(IBrain delegate) {
				this.delegate=delegate;
			}
			
			@Override
			public boolean isFinished() {
				if(delegate==null)
					return true;
				
				if(delegate instanceof Odometre){
					Point last=((Odometre) delegate).getLast();
					if( last==null)
						return true;
					
					xdest=last.getX();
					x=((Odometre) delegate).getPos().getX();
					
					
					return Math.abs(xdest-x)<Odometre.PRECISION;
				}
				
				if(delegate instanceof AbstractBrainAutomaton){
					Point last=((AbstractBrainAutomaton) delegate).getOdometre().getLast();
					if(last==null)
						return true;
					
					xdest=last.getX();
					x=((AbstractBrainAutomaton) delegate).getOdometre().getPos().getX();
					return Math.abs(xdest-x)<Odometre.PRECISION;
				}
					return true;
			}
		};
	}

	
	public static AbstractBasicBrainAutomaton aligneYOdometrie(){
		return new AbstractBasicBrainAutomaton() {
			
			double ydest;
			double y;
			double stepTurn=Parameters.teamAMainBotStepTurnAngle;
			double PRECISION=stepTurn;
			
			@Override
			public void step() {
				
				if(!isFinished()){
					double angleDir;
					
					if(y<ydest){
						angleDir=Math.abs(getHeading()-Math.PI/2)%(2*Math.PI);
						if(angleDir>PRECISION){
							sendLogMessage("tourne "+Direction.LEFT);
							stepTurn(Direction.LEFT);
						}else if(angleDir<-PRECISION){
							sendLogMessage("tourne "+Direction.RIGHT);
							stepTurn(Direction.RIGHT);
						}else{
							sendLogMessage("avance");
							move();
						}
					}else{
						angleDir=Math.abs(getHeading()-(3*Math.PI)/2)%(2*Math.PI);
						if(angleDir>PRECISION){
							sendLogMessage("tourne "+Direction.RIGHT);
							stepTurn(Direction.RIGHT);
						}else if(angleDir<-PRECISION){
							sendLogMessage("tourne "+Direction.LEFT);
							stepTurn(Direction.LEFT);
						}else{
							sendLogMessage("avance");
							move();
						}
					}
				}
			}
			
			@Override
			public void activate() {
				
			}
			
			@Override
			public void setDelegate(IBrain delegate) {
				this.delegate=delegate;
			}
			
			@Override
			public boolean isFinished() {
				if(delegate==null)
					return true;
				
				if(delegate instanceof Odometre){
					Point last=((Odometre) delegate).getLast();
					if(last==null)
						return true;
					ydest=last.getY();
					y=((Odometre) delegate).getPos().getY();
					
					
					return Math.abs(ydest-y)<Odometre.PRECISION;
				}
				
				if(delegate instanceof AbstractBrainAutomaton){
					Point last=((AbstractBrainAutomaton) delegate).getOdometre().getLast();
					if(last==null)
						return true;
					ydest=last.getY();
					y=((AbstractBrainAutomaton) delegate).getOdometre().getPos().getY();
					return Math.abs(ydest-y)<Odometre.PRECISION;
				}
					return true;
			}
		};
	}
}
