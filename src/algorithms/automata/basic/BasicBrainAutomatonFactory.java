package algorithms.automata.basic;

import characteristics.Parameters.Direction;
import algorithms.automata.AbstractBrainAutomaton;

public class BasicBrainAutomatonFactory {
	private static TEAM team=TEAM.B;

	public static void setTeam(TEAM team){
		BasicBrainAutomatonFactory.team=team;
	}

	/*********
	* TOURNE *
	**********/

	private static AbstractBasicBrainAutomaton tourne(double angle,Direction dir){
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

	/*********
	* AVANCE *
	**********/

	public static AbstractBasicBrainAutomaton avance(double distance){
		return new Avance(distance, team.getMainBotSpeed());
	}		

	public static AbstractBasicBrainAutomaton recule(double distance){
		return new Recule(distance, team.getMainBotSpeed());
	}

	/*******
	* TIRE *
	********/
	
	public static AbstractBasicBrainAutomaton tire(AbstractBrainAutomaton automateAction, double angle){
		return new Tire(automateAction, angle);
	}
	

}
