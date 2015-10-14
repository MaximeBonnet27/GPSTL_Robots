package algorithms.stages;

import characteristics.IFrontSensorResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

/**
 * Robot calculant la taille du terrain
 * @author maxime
 *
 */
public class Stage2 extends Brain{

  // Taches
  boolean firstTurnTask;
  boolean turnTask;
  boolean moveTask;
  
  boolean measureHeightTask;
  boolean measureWidthTask;

  // Nombre de commandes à faire
  int numTurns;

  // Durée angle droit
  double angle = (Math.PI / 2);
  double rapportAngle =  (angle / (Math.PI * 2));
  int turnSteps = (int) (rapportAngle * (Math.PI * 2) / (Parameters.teamBMainBotStepTurnAngle));

  // Mesures 
  int width, height;
  
  // Directions prises
  double[] directions = {Parameters.NORTH, Parameters.WEST, Parameters.SOUTH, Parameters.EAST};
  int indexDirection;
  @Override
  public void activate() {
    firstTurnTask = true;
    turnTask = true;
    moveTask = false;
    measureHeightTask = false;
    measureWidthTask = false;
    numTurns = 0;

    width = (int) (2 * Parameters.teamBMainBotFrontalDetectionRange);
    height = (int) (2 * Parameters.teamBMainBotFrontalDetectionRange);
    
    indexDirection = 0;
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
        if(numTurns <= turnSteps){
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
        if(numTurns <= turnSteps){
          stepTurn(Direction.LEFT);
          numTurns++;
        }
        else{
          turnTask = false;
          moveTask = true;
          numTurns = 0;
          indexDirection = (indexDirection + 1) % 4;
          if(directions[indexDirection] == Parameters.SOUTH){
            measureHeightTask = true;
          }
          else if(directions[indexDirection] == Parameters.EAST){
            measureWidthTask = true;
          }
        }
      }

    } // FIN TURN TASK
    /*
     * AVANCER
     */
    else if(moveTask){
      if(!(detectFront().getObjectType()==IFrontSensorResult.Types.WALL)){
        move();
        /*
         * MESURES
         */
        if(measureHeightTask){
          height += Parameters.teamBMainBotSpeed;
        }
        else if(measureWidthTask){
          width += Parameters.teamBMainBotSpeed;
        }
      }
      else {
        moveTask = false;
        turnTask = true;
        if(measureHeightTask){
          sendLogMessage("Height = " + height);
        }
        if(measureWidthTask){
          sendLogMessage("Width = " + width);
        }
        measureHeightTask = false;
        measureWidthTask = false;
      }
    }
  }
}
