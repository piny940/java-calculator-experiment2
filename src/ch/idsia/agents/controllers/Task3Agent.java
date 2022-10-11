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

public class Task3Agent extends CustomBasicAgent {
  public Task3Agent() {
    super("Task3Agent");
    reset();
  }

  public boolean[] getAction() {
    if (isFrontEnemy()) {
      if (marioMode == 2) {
        fire();
      } else {
        jump();
      }
    }
    if (isFrontObstacle() || isFrontEnemy() || isFrontHole()) {
      jump();
    } else {
      action[Mario.KEY_JUMP] = false;
    }
    manageFiring();
    return action;
  }

  public void reset() {
    action = new boolean[Environment.numberOfKeys];
    action[Mario.KEY_RIGHT] = true;
    action[Mario.KEY_SPEED] = true;
  }
}
