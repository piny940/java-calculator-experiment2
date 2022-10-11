package ch.idsia.agents.controllers;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public abstract class CustomBasicAgent extends BasicMarioAIAgent implements Agent {
  private int[] actionCounts = new int[Environment.numberOfKeys];

  public CustomBasicAgent(String name) {
    super(name);
  }

  protected boolean[] updateAction() {
    boolean[] result = new boolean[Environment.numberOfKeys];

    for (int i = 0; i < actionCounts.length; i++) {
      if (actionCounts[i] > 0) {
        actionCounts[i]--;
      }
      result[i] = actionCounts[i] > 0;
    }
    return result;
  }

  protected void jump(int flame) {
    if (flame < 0) {
      this.actionCounts[Mario.KEY_JUMP] = Integer.MAX_VALUE;
    } else {
      this.actionCounts[Mario.KEY_JUMP] = flame;
    }
  }

  protected void moveRight() {
    this.actionCounts[Mario.KEY_RIGHT] = Integer.MAX_VALUE;
  }

  protected void moveLeft(int flame) {
    if (flame < 0) {
      this.actionCounts[Mario.KEY_LEFT] = Integer.MAX_VALUE;
    } else {
      this.actionCounts[Mario.KEY_LEFT] = flame;
    }
  }

  protected void dash(boolean dashing) {
    if (dashing) {
      this.actionCounts[Mario.KEY_SPEED] = Integer.MAX_VALUE;
    } else {
      this.actionCounts[Mario.KEY_SPEED] = 0;
    }
  }
}
