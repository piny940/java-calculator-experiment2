package ch.idsia.agents.learning;

public class GoUpAgent extends GAAgent {
  protected float POINT_PER_SECOND_PER_HEIGHT = 0;
  protected float heightPoint = 0;
  protected float maxHeight = 0;
  protected float MAX_HEIGHT_POINT_PER_HEIGHT = 1;

  public void setFitness() {
    this.fitness = (distancePassedCells) + heightPoint / timeSpent
        + (maxHeight) * MAX_HEIGHT_POINT_PER_HEIGHT;
  }

  protected void updateHeightPoint() {
    heightPoint += getHeight() / 30 * POINT_PER_SECOND_PER_HEIGHT;
    maxHeight = Math.max(maxHeight, getHeight());
  }

  private float getHeight() {
    return 15 - marioFloatPos[1] / 16;
  }
}
