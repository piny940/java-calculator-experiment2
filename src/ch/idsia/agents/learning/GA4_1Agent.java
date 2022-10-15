package ch.idsia.agents.learning;

public class GA4_1Agent extends GoUpAgent {
  int[] goRightGene = new int[GENE_LENGTH];
  int[] goUpGene1 = new int[GENE_LENGTH];
  int[] goUpGene2 = new int[GENE_LENGTH];
  int[] goUpGene3 = new int[GENE_LENGTH];
  int[] goUpGene4 = new int[GENE_LENGTH];

  public GA4_1Agent() {
    super();
    goRightGene = loadGene("src/ch/idsia/genes/task4_1/GATask4_1-2022-10-15_10-44-35.xml");
    goUpGene1 = loadGene("src/ch/idsia/genes/task4_1/GATask4_1-2022-10-15_15-19-53.xml");
    goUpGene2 = loadGene("src/ch/idsia/genes/task4_1/GATask4_1-2022-10-15_15-53-02.xml");
    goUpGene3 = loadGene("src/ch/idsia/genes/task4_1/GATask4_1-2022-10-15_16-03-00.xml");
    goUpGene4 = loadGene("src/ch/idsia/genes/task4_1/GATask4_1-2022-10-15_16-11-52.xml");
  }

  @Override
  public boolean[] getAction() {

    if (distancePassedCells < 100) {
      updateActionFromGene(goRightGene);
    } else if (distancePassedCells < 112) {
      updateActionFromGene(goUpGene1);
    } else if (distancePassedCells < 150) {
      updateActionFromGene(goUpGene2);
    } else if (distancePassedCells < 240) {
      updateActionFromGene(goUpGene3);
    } else {
      updateActionFromGene(goUpGene4);
    }
    return action;
  }
}
