package algorithms.automata.basic;

import characteristics.Parameters;

public enum TEAM {
	A,B;
	
	public double getMainBotStepTurnAngle(){
		if(this==A)
			return Parameters.teamAMainBotStepTurnAngle;
		else
			return Parameters.teamBMainBotStepTurnAngle;
	}
}
