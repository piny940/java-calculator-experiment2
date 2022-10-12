package ch.idsia.agents.controllers;

import ch.idsia.benchmark.mario.engine.sprites.Mario;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 25, 2009
 * Time: 12:27:07 AM
 * Package: ch.idsia.agents.controllers;
 */

public class Task3Agent extends CustomBasicAgent {
  public Task3Agent() {
    super("Task3Agent");
    reset();
  }

  public boolean[] getAction() {
    if (isAboveEnemy()) {
      moveLeft(10);
    }
    if (isFrontEnemy()) {
      if (marioMode == 2) {
        setKeepFiring(true);
      } else {
        jump();
      }
    } else {
      setKeepFiring(false);
    }
    if (isFrontObstacle() || isFrontEnemy() || isFrontHole()) {
      jump();
    } else {
      action[Mario.KEY_JUMP] = false;
    }
    manageFiring();
    manageMovingLeft();
    return action;
  }

  public void reset() {
    action[Mario.KEY_RIGHT] = true;
    action[Mario.KEY_SPEED] = true;
  }
}
