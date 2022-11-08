package ch.idsia.agents.learning;

public class GA4_3Agent extends GoUpAgent {
  private float MARIO_MODE_POINT = 1f;
  private float marioModePoint = 0;

  private int[] goUpGene1 = new int[GENE_LENGTH];
  private int[] goUpGene2 = new int[GENE_LENGTH];
  private int[] goUpGene3 = new int[GENE_LENGTH];
  private int[] goUpGene4 = new int[GENE_LENGTH];
  private int[] goUpGene5 = new int[GENE_LENGTH];

  public GA4_3Agent() {
    goUpGene1 = loadGene("src/ch/idsia/genes/task4_3/GATask4_3-2022-10-15_23-38-39.xml");
    goUpGene2 = loadGene("src/ch/idsia/genes/task4_3/GATask4_3-2022-10-16_00-04-25.xml");
    goUpGene3 = loadGene("src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_20-21-26.xml");
    goUpGene4 = loadGene("src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_21-17-49.xml");
    goUpGene5 = loadGene("src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_21-49-52.xml");
    initializeGene();
  }

  public boolean[] getAction() {
    if (distancePassedCells < 40) {
      updateActionFromGene(goUpGene1);
    } else if (distancePassedCells < 65) {
      updateActionFromGene(goUpGene2);
    } else if (distancePassedCells < 85) {
      updateActionFromGene(goUpGene3);
    } else if (distancePassedCells < 110) {
      updateActionFromGene(goUpGene4);
    } else if (distancePassedCells < 123) {
      updateActionFromGene(goUpGene5);
    } else {
      updateMarioModePoint();
      updateHeightPoint();
      updateActionFromGene(gene);
    }

    return action;
  }

  public void setFitness() {
    this.fitness = (distancePassedCells) + heightPoint / timeSpent
        + (maxHeight) * MAX_HEIGHT_POINT_PER_HEIGHT
        + marioModePoint / timeSpent;
  }

  public void updateMarioModePoint() {
    marioModePoint += marioMode * MARIO_MODE_POINT / 30;
  }
}
