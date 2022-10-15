package ch.idsia.agents.learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

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
    initializeGene();
    loadGene("GATask4_1-2022-10-15_10-44-35.xml");
  }

  private void initializeGene() {
    int random = r.nextInt(8);

    /* geneの初期値は乱数(0から31)で取得 */
    for (int i = 0; i < GENE_LENGTH; i++) {
      switch (random) {
        case 0:
          gene[i] = 0;
          break;
        case 1:
          gene[i] = 2;
          break;
        case 2:
          gene[i] = 8;
          break;
        case 3:
          gene[i] = 10;
          break;
        case 4:
          gene[i] = 16;
          break;
        case 5:
          gene[i] = 18;
          break;
        case 6:
          gene[i] = 24;
          break;
        case 7:
          gene[i] = 26;
          break;

      }
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

    updateActionFromGene(gene);

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

  private void updateActionFromGene(int[] gene) {
    int input = getGeneIndex();
    int act = gene[input]; // 遺伝子のinput番目の数値を読み取る
    for (int i = 0; i < Environment.numberOfKeys; i++) {
      action[i] = (act % 2 == 1); // 2で割り切れるならtrue
      act /= 2;
    }
  }

  private double probe(int x, int y, byte[][] scene) {
    int realX = x + 11;
    int realY = y + 11;
    return (scene[realX][realY] != 0) ? 1 : 0;
  }

  private int[] loadGene(String filename) {
    int[] gene = new int[GENE_LENGTH];

    try {
      File inputFile = new File(filename);
      Scanner scanner = new Scanner(inputFile);

      for (int i = 0; i < 1 << INPUT_NUM; i++) {
        String line = scanner.next();
        gene[i] = Integer.valueOf(line);
      }

      scanner.close();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
    }
    return gene;
  }

  public void setGeneFromFile(String filename) {
    this.gene = loadGene(filename);
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
