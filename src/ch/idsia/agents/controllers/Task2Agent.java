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

public class Task2Agent extends BasicMarioAIAgent implements Agent {
  public Task2Agent() {
    super("Task2Agent");
    reset();
  }

  private boolean isObstacle(int r, int c) {
    return getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BRICK
        || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH
        || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.FLOWER_POT_OR_CANNON;
  }

  private boolean isEnemy(int r, int c) {
    return getEnemiesCellValue(r, c) > 0;
  }

  private boolean isFrontObstacle() {
    return isObstacle(marioEgoRow, marioEgoCol + 1)
        || isObstacle(marioEgoRow, marioEgoCol + 2)
        || isObstacle(marioEgoRow + 1, marioEgoCol + 1)
        || isObstacle(marioEgoRow + 1, marioEgoCol + 2);
  }

  private boolean isFrontHole() {
    return !isObstacle(marioEgoRow + 1, marioEgoCol + 1)
        || !isObstacle(marioEgoRow + 1, marioEgoCol + 2);
  }

  private boolean isFrontEnemy() {
    return isEnemy(marioEgoRow, marioEgoCol + 1)
        || isEnemy(marioEgoRow, marioEgoCol + 2)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 1)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 2);
  }

  public boolean[] getAction() {
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
