package ch.idsia.agents.learning;

public class GoUpAgent extends GAAgent {
  private float POINT_PER_SECOND_PER_HEIGHT = 0;
  private float heightPoint = 0;
  private float maxHeight = 0;
  private float MAX_HEIGHT_POINT_PER_HEIGHT = 3;

  public void setFitness() {
    this.fitness = (distancePassedCells - 130) + heightPoint / timeSpent
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
