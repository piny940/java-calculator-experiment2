package ch.idsia.agents.learning;

public class GA4_2Agent extends GoUpAgent {
  private int[] goUpGene1 = new int[GENE_LENGTH];
  private int[] goUpGene2 = new int[GENE_LENGTH];
  private int[] goUpGene3 = new int[GENE_LENGTH];

  public GA4_2Agent() {
    goUpGene1 = loadGene("src/ch/idsia/genes/task4_2/GATask4_2-2022-10-15_21-03-09.xml");
    goUpGene2 = loadGene("src/ch/idsia/genes/task4_2/GATask4_2-2022-10-15_21-37-46.xml");
    goUpGene3 = loadGene("src/ch/idsia/genes/task4_2/GATask4_2-2022-10-15_21-57-37.xml");
  }

  public boolean[] getAction() {
    if (distancePassedCells < 130) {
      updateActionFromGene(goUpGene1);
    } else if (distancePassedCells < 233) {
      updateActionFromGene(goUpGene2);
    } else {
      updateActionFromGene(goUpGene3);
    }
    return action;
  }
}
