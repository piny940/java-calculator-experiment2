package ch.idsia.agents.learning;

public class GA4_3Agent extends GoUpAgent {
  private float MARIO_MODE_POINT = 1f;
  private float marioModePoint = 0;

  private int[] goUpGene1;
  private int[] goUpGene2;
  private int[] goUpGene3;
  private int[] goUpGene4;
  private int[] goUpGene5;
  private int[] goUpGene6;
  private int[] goUpGene7;

  private String gene1Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-10-15_23-38-39.xml";
  private String gene2Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-10-16_00-04-25.xml";
  private String gene3Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_20-21-26.xml";
  private String gene4Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_21-17-49.xml";
  private String gene5Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-11-06_21-49-52.xml";
  private String gene6Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-11-08_15-30-26.xml";
  private String gene7Path = "src/ch/idsia/genes/task4_3/GATask4_3-2022-11-08_16-56-04.xml";

  public GA4_3Agent() {
    initializeGene();
  }

  public boolean[] getAction() {
    if (distancePassedCells < 40) {
      // Use gene1
      if (goUpGene1 == null) {
        goUpGene1 = loadGene(gene1Path);
      }
      updateActionFromGene(goUpGene1);
    } else if (distancePassedCells < 65) {
      // Use gene2
      if (goUpGene2 == null) {
        goUpGene2 = loadGene(gene2Path);
      }
      goUpGene1 = null;
      updateActionFromGene(goUpGene2);
    } else if (distancePassedCells < 85) {
      // Use gene3
      if (goUpGene3 == null) {
        goUpGene3 = loadGene(gene3Path);
      }
      goUpGene2 = null;
      updateActionFromGene(goUpGene3);
    } else if (distancePassedCells < 110) {
      // Use gene4
      if (goUpGene4 == null) {
        goUpGene4 = loadGene(gene4Path);
      }
      goUpGene3 = null;
      updateActionFromGene(goUpGene4);
    } else if (distancePassedCells < 123) {
      // Use gene5
      if (goUpGene5 == null) {
        goUpGene5 = loadGene(gene5Path);
      }
      goUpGene4 = null;
      updateActionFromGene(goUpGene5);
    } else if (distancePassedCells < 134) {
      // Use gene6
      if (goUpGene6 == null) {
        goUpGene6 = loadGene(gene6Path);
      }
      goUpGene5 = null;
      updateActionFromGene(goUpGene6);
    } else if (distancePassedCells < 150) {
      // Use gene7
      if (goUpGene7 == null) {
        goUpGene7 = loadGene(gene6Path);
      }
      goUpGene6 = null;
      updateActionFromGene(goUpGene6);
    } else {
      // Learning
      goUpGene4 = null;
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
