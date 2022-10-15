package ch.idsia.agents.learning;

public class GATask4_2 extends BaseGeneticAlgorithm<GA4_2Agent> {
  public GATask4_2(String marioAIOptions) {
    super(marioAIOptions, "GATask4_2");
  }

  protected GA4_2Agent[] initializeCurrentGeneration() {
    GA4_2Agent[] current = new GA4_2Agent[SIZE];

    for (int i = 0; i < SIZE; i++) {
      current[i] = new GA4_2Agent();
    }

    return current;
  }

  protected GA4_2Agent[] initializeNextGeneration() {
    GA4_2Agent[] next = new GA4_2Agent[SIZE];

    for (int i = 0; i < SIZE; i++) {
      next[i] = new GA4_2Agent();
    }

    return next;
  }
}
