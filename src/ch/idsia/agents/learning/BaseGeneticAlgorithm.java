package ch.idsia.agents.learning;

import java.util.Arrays;
import java.util.Random;

import ch.idsia.benchmark.mario.engine.GlobalOptions;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.EvaluationInfo;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.utils.FileManager;

public class BaseGeneticAlgorithm<CustomGAAgent extends GAAgent> {
  private final int SIZE = 100;
  private final int ELITE_NUM = 2;
  private final int MAX_GENERATION = 10000;
  private final float MUTATE_PROB = 0.1f;
  private final int INPUT_NUM = 16;
  private final int GENE_LENGTH = 1 << INPUT_NUM;
  private GAAgent[] currentGeneration;
  private GAAgent[] nextGeneration;
  private String marioAIOptions;
  private String name;
  private Random r = new Random();

  public BaseGeneticAlgorithm(String marioAIOptions, String name,
      CustomGAAgent[] initialCurrentGeneration,
      CustomGAAgent[] initialNextGeneration) {
    this.currentGeneration = initialCurrentGeneration;
    this.nextGeneration = initialNextGeneration;
    this.marioAIOptions = marioAIOptions;
    this.name = name;
  }

  public void learn() {
    for (int generation = 0; generation < MAX_GENERATION; generation++) {
      System.out.println("Generation" + generation);
      evaluate();

      Arrays.sort(currentGeneration);

      // 現世代での最高値を出力
      System.out.println(
          "currentGeneration[0]Fitness : " + currentGeneration[0].getFitness() + "\n"
              + "currentGeneration[0]Distance : "
              + currentGeneration[0].getDistance());
      if (generation % 100 == 99) {
        writeFile();
        playMario(currentGeneration[0], true);
      }

      updateNextGeneration();

      currentGeneration = nextGeneration;

      if (generation == MAX_GENERATION) {
        System.out.println("Generation[" + generation + "] : Playing!");
        playMario(currentGeneration[0], true);
        FileManager.write(currentGeneration[0], "gene.xml");
        System.out.println("Learning is stopped");
        break;
      }
    }
  }

  private int[] select() {
    /* 生存確率[i] = 適合度[i]/総計適合度 */
    double[] selectProbs = new double[SIZE]; // 各個体の生存確率
    double[] accumulateProbs = new double[SIZE]; // selectProbの累積値

    int[] parentsIndex = new int[2];

    /* 適合度の和を求める */
    double fitnessSum = 0;
    for (int i = ELITE_NUM; i < SIZE; i++) {
      fitnessSum += scaleFitness(currentGeneration[i].getFitness());
    }

    /* ルーレットを作る */
    for (int i = ELITE_NUM; i < SIZE; i++) {
      selectProbs[i] = scaleFitness(currentGeneration[i].getFitness()) / fitnessSum;
      accumulateProbs[i] = accumulateProbs[i - 1] + selectProbs[i];
    }

    /* ルーレットで選ぶ */
    for (int i = 0; i < 2; i++) { // 2回繰り返す
      /* 2から99までの乱数を作成し，99で割る */
      double selectedParent = (2.0 + (int) (r.nextInt(100) * 98.0) / 100.0) / 99.0;

      for (int j = ELITE_NUM + 1; j < SIZE; j++) {

        if (selectedParent < accumulateProbs[2]) {
          parentsIndex[i] = 2;
          break;
        }

        if (accumulateProbs[j - 1] < selectedParent
            && selectedParent < accumulateProbs[j]) {
          parentsIndex[i] = j;
          break;
        }

        /* 例外処理 */
        if (selectedParent > 1.0 || selectedParent < 0.0) {
          parentsIndex[i] = 2;
          break;
        }
      }
    }
    /* 返り値として，交叉する親の番号が入っている．要素数は2． */
    return parentsIndex;
  }

  private void cross(int[] parentsIndex, int resultIndex) {
    uniformCross(parentsIndex, resultIndex);
  }

  private void mutate(int mutateIndex) {
    for (int index = 0; index < GENE_LENGTH; index++) {
      float ran = r.nextFloat();
      if (ran < MUTATE_PROB) {
        nextGeneration[mutateIndex].setGene(index, nextGeneration[mutateIndex].getGene());
      }
    }
  }

  private void evaluate() {
    for (int i = 0; i < SIZE; i++) {
      BasicTask basicTask = playMario(currentGeneration[i], false);

      // 評価値
      EvaluationInfo evaluationInfo = basicTask.getEvaluationInfo();
      if (evaluationInfo == null) {
        System.out.println(basicTask.getEvaluationInfo());
        System.out.println(basicTask);
        return;
      }
      currentGeneration[i].setFitness();
      currentGeneration[i].setDistance();
    }
  }

  private void updateNextGeneration() {
    initiateNextGeneration();

    // 2個体エリートを残す
    for (int i = 0; i < ELITE_NUM; i++) {
      nextGeneration[i].copyGene(currentGeneration[i]);
    }

    for (int nextAgentIndex = ELITE_NUM; nextAgentIndex < SIZE; nextAgentIndex += 2) {
      int[] parentsIndex = select();
      cross(parentsIndex, nextAgentIndex);

      if (r.nextFloat() < MUTATE_PROB) {
        mutate(nextAgentIndex);
      }
    }
  }

  private void uniformCross(int[] parentsIndex, int resultIndex) {
    int sum = parentsIndex[0] + parentsIndex[1];
    for (int j = 0; j < GENE_LENGTH; j++) {
      // スコアが高い遺伝子ほど選ばれやすくする
      int ran = r.nextInt(sum);
      if (ran > parentsIndex[0]) {
        int[] parentsGeneA = currentGeneration[parentsIndex[0]].getGene();
        this.nextGeneration[resultIndex].setGene(j, parentsGeneA);
      } else {
        int[] parentsGeneB = currentGeneration[parentsIndex[1]].getGene();
        this.nextGeneration[resultIndex].setGene(j, parentsGeneB);
      }
    }
  }

  private BasicTask playMario(GAAgent agent, boolean visual) {
    MarioAIOptions marioAIOptions = new MarioAIOptions();
    BasicTask basicTask = new BasicTask(marioAIOptions);

    /* プレイ画面出力するか否か */
    marioAIOptions.setVisualization(visual);

    marioAIOptions.setArgs(this.marioAIOptions);
    marioAIOptions.setAgent(agent);
    basicTask.setOptionsAndReset(marioAIOptions);

    basicTask.runSingleEpisode(1);

    return basicTask;
  }

  private double scaleFitness(float fitness) {
    return fitness;
  }

  private void initiateNextGeneration() {
    GAAgent[] tmp = currentGeneration;
    currentGeneration = nextGeneration;
    nextGeneration = tmp;
    for (int i = 0; i < SIZE; i++) {
      nextGeneration[i].initializeGene();
    }
  }

  private void writeFile() {
    String fileName = this.name + "-"
        + GlobalOptions.getTimeStamp() + ".xml";
    FileManager.write(currentGeneration[0], fileName);
  }
}
