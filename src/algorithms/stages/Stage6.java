package algorithms.stages;

import characteristics.IFrontSensorResult;
import characteristics.Parameters;
import characteristics.Parameters.Direction;
import robotsimulator.Brain;

public class Stage6 extends Brain{

  // Taches
  boolean firstTurnTask;
  boolean turnTask;
  boolean moveTask;

  boolean searchHeightTask;
  boolean searchWidthTask;

  boolean circleTask;

  // Coordonnées robot
  int x, y;

  // Nombre de commandes à faire
  int numTurns, numMoves;
  int circleStep;

  // Durée angle droit
  double angle = (Math.PI / 2);
  double rapportAngle =  (angle / (Math.PI * 2));
  int turnSteps = (int) (rapportAngle * (Math.PI * 2) / (Parameters.teamBMainBotStepTurnAngle));

  // Coordonnées importantes

  int widthTerrain = 3020;
  int heightTerrain = 2020;

  int xRendezVous = 400;
  int yRendezVous = 1500;

  int radius = 200;

  // Directions prises
  double[] directions = {Parameters.NORTH, Parameters.WEST, Parameters.SOUTH, Parameters.EAST};
  int indexDirection;

  @Override
  public void activate() {
    firstTurnTask = true;
    turnTask = true;
    moveTask = false;

    searchHeightTask = false;
    searchWidthTask = false;
    circleTask = false;

    x = (int) Parameters.teamBMainBotFrontalDetectionRange;
    y = (int) Parameters.teamBMainBotFrontalDetectionRange;

    numTurns = 0;
    numMoves = 0;

    circleStep = 0;
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
        sendLogMessage("TURN");
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
            searchHeightTask = true;
          }
          else if(directions[indexDirection] == Parameters.EAST){
            searchWidthTask = true;
          }

        }
      }

    } // FIN TURN TASK
    /*
     * AVANCER
     */
    else if(moveTask){
      if(!(detectFront().getObjectType()==IFrontSensorResult.Types.WALL)){
        if(searchHeightTask){
          if(y < yRendezVous + radius){
            move();
            y += Parameters.teamBMainBotSpeed;
          }
          else{
            searchHeightTask = false;
            turnTask = true;
          }
        }
        else if(searchWidthTask){
          if(x < xRendezVous){
            move();
            x += Parameters.teamBMainBotSpeed;
          }
          else{
            searchWidthTask = false;
            circleTask = true;
            moveTask = false;
          }
        }
        else {
          move();
        }
      }
      else {
        moveTask = false;
        turnTask = true;
      }
    }
    /*
     * TOURNER EN ROND
     */
    else if(circleTask){
      sendLogMessage("CIRCLE");
      if(circleStep % 3 == 0){
        stepTurn(Direction.LEFT);
      }
      else if(circleStep % 3 == 1) {
        move();
      }
      else {
        fire(getHeading() - Math.PI / 2);
      }
      circleStep++;
    }
  }

}
