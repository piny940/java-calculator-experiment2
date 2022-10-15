package ch.idsia.agents.learning;

public class GoRightAgent extends GAAgent {
  public void setFitness() {
    this.fitness = distancePassedCells;
  }
}
