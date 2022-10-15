package ch.idsia.agents.learning;

public class GA4_1Agent extends GoUpAgent {
  int[] goRightGene = new int[GENE_LENGTH];
  int[] goUpGene1 = new int[GENE_LENGTH];
  int[] goUpGene2 = new int[GENE_LENGTH];

  public GA4_1Agent() {
    super();
    goRightGene = loadGene("GATask4_1-2022-10-15_10-44-35.xml");
    goUpGene1 = loadGene("GATask4_1-2022-10-15_15-19-53.xml");
    goUpGene2 = loadGene("GATask4_1-2022-10-15_15-53-02.xml");
  }

  @Override
  public boolean[] getAction() {

    if (distancePassedCells < 100) {
      updateActionFromGene(goRightGene);
    } else if (distancePassedCells < 112) {
      updateActionFromGene(goUpGene1);
    } else if (distancePassedCells < 150) {
      updateActionFromGene(goUpGene2);
    } else {
      updateHeightPoint();
      updateActionFromGene(this.gene);
    }
    return action;
  }

  @Override
  public GA4_1Agent clone() {
    return (GA4_1Agent) super.clone();
  }
}
