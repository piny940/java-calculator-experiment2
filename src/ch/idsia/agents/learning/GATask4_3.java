package ch.idsia.agents.learning;

public class GATask4_3 extends BaseGeneticAlgorithm<GA4_3Agent> {
  protected float MUTATE_PROB = 0.15f;

  public GATask4_3(String marioAIOptions) {
    super(marioAIOptions, "GATask4_3");
  }

  protected GA4_3Agent[] initializeCurrentGeneration() {
    GA4_3Agent[] current = new GA4_3Agent[SIZE];
    for (int i = 0; i < SIZE; i++) {
      current[i] = new GA4_3Agent();
    }
    return current;
  }

  protected GA4_3Agent[] initializeNextGeneration() {
    GA4_3Agent[] next = new GA4_3Agent[SIZE];
    for (int i = 0; i < SIZE; i++) {
      next[i] = new GA4_3Agent();
    }
    return next;
  }
}
