package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;
import ch.idsia.benchmark.mario.engine.sprites.Mario;

public abstract class CustomBasicAgent extends BasicMarioAIAgent implements Agent {
  private boolean keepFiring = false;
  private boolean willFire = false;
  private int leftCount = 0;

  public CustomBasicAgent(String name) {
    super(name);
  }

  protected boolean isObstacle(int r, int c) {
    return getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BRICK
        || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH
        || getReceptiveFieldCellValue(r, c) == GeneralizerLevelScene.FLOWER_POT_OR_CANNON;
  }

  protected boolean isEnemy(int r, int c) {
    return getEnemiesCellValue(r, c) > 0;
  }

  protected boolean isFallingEnemy(int r, int c) {
    return isEnemy(r, c) && !isObstacle(r + 1, c);
  }

  protected boolean isFrontObstacle() {
    return isObstacle(marioEgoRow, marioEgoCol + 1)
        || isObstacle(marioEgoRow, marioEgoCol + 2)
        || isObstacle(marioEgoRow + 1, marioEgoCol + 1)
        || isObstacle(marioEgoRow + 1, marioEgoCol + 2);
  }

  protected boolean isFrontHole() {
    return !isObstacle(marioEgoRow + 1, marioEgoCol + 1)
        || !isObstacle(marioEgoRow + 1, marioEgoCol + 2);
  }

  protected boolean isFrontEnemy() {
    return isEnemy(marioEgoRow - 1, marioEgoCol + 1)
        || isEnemy(marioEgoRow - 1, marioEgoCol + 2)
        || isEnemy(marioEgoRow, marioEgoCol + 1)
        || isEnemy(marioEgoRow, marioEgoCol + 2)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 1)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 2);
  }

  protected boolean isAboveEnemy() {
    return isFallingEnemy(marioEgoRow - 1, marioEgoCol)
        || isFallingEnemy(marioEgoRow - 1, marioEgoCol + 1)
        || isFallingEnemy(marioEgoRow - 2, marioEgoCol)
        || isFallingEnemy(marioEgoRow - 2, marioEgoCol + 1);
  }

  protected void jump() {
    action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
  }

  protected void fire() {
    action[Mario.KEY_SPEED] = false;
    this.willFire = true;
  }

  protected void moveLeft(int flame) {
    this.leftCount = flame;
  }

  protected void setKeepFiring(boolean keepFiring) {
    this.keepFiring = keepFiring;
    action[Mario.KEY_SPEED] = keepFiring;
  }

  protected void manageFiring() {
    if (this.keepFiring) {
      action[Mario.KEY_SPEED] = !action[Mario.KEY_SPEED];
    } else if (this.willFire) {
      action[Mario.KEY_SPEED] = true;
      this.willFire = false;
    }
  }

  protected void manageMovingLeft() {
    if (leftCount > 0) {
      action[Mario.KEY_LEFT] = true;
      action[Mario.KEY_RIGHT] = false;
      leftCount--;
    } else {
      action[Mario.KEY_LEFT] = false;
      action[Mario.KEY_RIGHT] = true;
    }
  }
}
