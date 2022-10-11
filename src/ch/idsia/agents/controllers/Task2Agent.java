package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 25, 2009
 * Time: 12:27:07 AM
 * Package: ch.idsia.agents.controllers;
 */

public class Task2Agent extends CustomBasicAgent {
  public Task2Agent() {
    super("Task2Agent");
    reset();
  }

  public boolean[] getAction() {
    if (isAboveEnemy()) {

    }
    if (isFrontObstacle() || isFrontEnemy() || isFrontHole()) {
      action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
    } else {
      action[Mario.KEY_JUMP] = false;
    }
    return action;
  }

  public void reset() {
    action = new boolean[Environment.numberOfKeys];
    action[Mario.KEY_RIGHT] = true;
    action[Mario.KEY_SPEED] = true;
  }
}
