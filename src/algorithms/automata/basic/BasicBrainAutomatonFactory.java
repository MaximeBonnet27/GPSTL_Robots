package algorithms.automata.basic;

import characteristics.Parameters.Direction;

public class BasicBrainAutomatonFactory {
	private static TEAM team=TEAM.B;

	public static void setTeam(TEAM team){
		BasicBrainAutomatonFactory.team=team;
	}

	private static AbstractBasicBrainAutomaton tourne(double angle,Direction dir){
		return new Tourne(angle, dir, team.getMainBotStepTurnAngle());
	}

	public static AbstractBasicBrainAutomaton tourneStepG(){
		return tourne(team.getMainBotStepTurnAngle()+team.getMainBotStepTurnAngle()/2,Direction.LEFT);
	}

	public static AbstractBasicBrainAutomaton tourneStepD(){
		return tourne(team.getMainBotStepTurnAngle()+team.getMainBotStepTurnAngle()/2,Direction.RIGHT);

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



}
