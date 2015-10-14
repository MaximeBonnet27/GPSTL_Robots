package algorithms.stages;

import characteristics.IFrontSensorResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

/**
 * Robot suivant les murs
 * @author maxime
 *
 */
public class Stage1 extends Brain{

  // Taches
  boolean firstTurnTask;
  boolean turnTask;
  boolean moveTask;

  // Nombre de commandes à faire
  int numTurns;

  // Durée angle droit
  double angle = (Math.PI / 2);
  double rapportAngle =  (angle / (Math.PI * 2));
  int turnSteps = (int) (rapportAngle * (Math.PI * 2) / (Parameters.teamBMainBotStepTurnAngle));

  @Override
  public void activate() {
    firstTurnTask = true;
    turnTask = true;
    moveTask = false;

    numTurns = 0;

  }

  @Override
  public void step() {
    /*
     * TOURNER 
     */
    if(turnTask){
      /*
       * PREMIER VIRAGE 
       */
      if(firstTurnTask){
        sendLogMessage("STAGE 1 : FIRST TURN");
        if(numTurns < turnSteps){
          stepTurn(Direction.RIGHT);
          numTurns++;
        }
        else{
          firstTurnTask = false;
          turnTask = false;
          moveTask = true;
          numTurns = 0;
        }
      } // FIN FIRST TURN TASK
      else {
        sendLogMessage("STAGE 1 : TURN");
        if(numTurns < turnSteps){
          stepTurn(Direction.LEFT);
          numTurns++;
        }
        else{
          turnTask = false;
          moveTask = true;
          numTurns = 0;
        }
      }

    } // FIN TURN TASK
    /*
     * AVANCER
     */
    else if(moveTask){
      sendLogMessage("STAGE 1 : MOVE");
      if(!(detectFront().getObjectType()==IFrontSensorResult.Types.WALL)){
        move();
      }
      else {
        moveTask = false;
        turnTask = true;
      }
    }
  }

}
