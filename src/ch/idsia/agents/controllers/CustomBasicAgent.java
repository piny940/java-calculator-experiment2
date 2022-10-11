package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;

public abstract class CustomBasicAgent extends BasicMarioAIAgent implements Agent {
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
    return isEnemy(marioEgoRow, marioEgoCol + 1)
        || isEnemy(marioEgoRow, marioEgoCol + 2)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 1)
        || isEnemy(marioEgoRow + 1, marioEgoCol + 2);
  }
}
