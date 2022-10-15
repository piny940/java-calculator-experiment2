package ch.idsia.agents.learning;

public class GATask4_1 extends BaseGeneticAlgorithm<GA4_1Agent> {
  private final int SIZE = 100;
  protected final int MAX_GENERATION = 10000;
  protected final int CHECK_PLAY_CYCLE = 10;

  public GATask4_1(String marioAIOptions) {
    super(marioAIOptions, "GATask4_1");
  }

  protected GA4_1Agent[] initializeCurrentGeneration() {
    GA4_1Agent[] current = new GA4_1Agent[SIZE];

    for (int i = 0; i < SIZE; i++) {
      current[i] = new GA4_1Agent();
    }

    return current;
  }

  protected GA4_1Agent[] initializeNextGeneration() {
    GA4_1Agent[] next = new GA4_1Agent[SIZE];

    for (int i = 0; i < SIZE; i++) {
      next[i] = new GA4_1Agent();
    }

    return next;
  }
}
