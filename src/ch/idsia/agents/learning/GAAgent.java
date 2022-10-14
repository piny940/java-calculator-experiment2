package ch.idsia.agents.learning;

import java.util.Random;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import ch.idsia.benchmark.mario.environments.Environment;

public class GAAgent extends BasicMarioAIAgent implements Comparable, Cloneable {
  private static String name = "GAAgent";
  private final int INPUT_NUM = 16;
  private final int GENE_LENGTH = 1 << INPUT_NUM;
  private int fitness = 0;
  private int[] gene = new int[GENE_LENGTH];
  private int distance = 0;

  private Random r = new Random();

  public GAAgent() {
    super(name);
    initiateGene();
  }

  private void initiateGene() {
    for (int i = 0; i < GENE_LENGTH; i++) {
      gene[i] = r.nextInt(1 << Environment.numberOfKeys - 1);
    }
  }

  public int getFitness() {
    return this.fitness;
  }

  public void setFitness(int fitness) {
    this.fitness = fitness;
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

  public int getDistance() {
    return this.distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  public boolean[] getAction() {
    int input = getGeneIndex();
    int act = gene[input]; // 遺伝子のinput番目の数値を読み取る
    for (int i = 0; i < Environment.numberOfKeys; i++) {
      action[i] = (act % 2 == 1); // 2で割り切れるならtrue
      act /= 2;
    }

    return action;
  }

  private int getGeneIndex() {
    int result = 0;

    /* enemies情報 */
    result += probe(-1, -1, enemies) * (1 << 15);
    result += probe(0, -1, enemies) * (1 << 14);
    result += probe(1, -1, enemies) * (1 << 13);
    result += probe(-1, 0, enemies) * (1 << 12);
    result += probe(1, 0, enemies) * (1 << 11);
    result += probe(-1, 1, enemies) * (1 << 10);
    result += probe(1, 1, enemies) * (1 << 9);

    /* オブジェクト情報 */
    result += probe(-1, -1, levelScene) * (1 << 8);
    result += probe(0, -1, levelScene) * (1 << 7);
    result += probe(1, -1, levelScene) * (1 << 6);
    result += probe(-1, 0, levelScene) * (1 << 5);
    result += probe(1, 0, levelScene) * (1 << 4);
    result += probe(-1, 1, levelScene) * (1 << 3);
    result += probe(1, 1, levelScene) * (1 << 2);

    result += (isMarioOnGround ? 1 : 0) * (1 << 1);
    result += (isMarioAbleToJump ? 1 : 0) * (1 << 0);
    return result;
  }

  private double probe(int x, int y, byte[][] scene) {
    int realX = x + 11;
    int realY = y + 11;
    return (scene[realX][realY] != 0) ? 1 : 0;
  }

  @Override
  public GAAgent clone() {

    GAAgent res = null;
    try {
      res = (GAAgent) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.toString());
    }

    return res;
  }
}
