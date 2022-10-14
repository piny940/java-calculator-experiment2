package ch.idsia.agents.learning;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;

public class GAAgent extends BasicMarioAIAgent implements Agent, Comparable {
  private static String name = "GAAgent";
  private final int INPUT_NUM = 16;
  private int fitness = 0;
  private int[] gene;

  public GAAgent() {
    super(name);
    gene = new int[1 << INPUT_NUM];
  }

  public int getFitness() {
    return this.fitness;
  }

  public int compareTo(Object object) {
    GAAgent otherAgent = (GAAgent) object;
    return -(this.fitness - otherAgent.getFitness());
  }

  public int[] getGene() {
    return this.gene;
  }

  public void setGene(int index, int[] gene) {
    this.gene[index] = gene[index];
  }
}
