package ch.idsia.agents.learning;

public class GA4_1Agent extends GoUpAgent {
  int[] goRightGene = new int[GENE_LENGTH];

  public GA4_1Agent() {
    super();
    goRightGene = loadGene("GATask4_1-2022-10-15_10-44-35.xml");
  }

  @Override
  public boolean[] getAction() {

    if (distancePassedCells < 100) {
      updateActionFromGene(goRightGene);
    } else {
      updateHeightPoint();
      updateActionFromGene(gene);
    }
    return action;
  }

  @Override
  public GA4_1Agent clone() {
    return (GA4_1Agent) super.clone();
  }
}
