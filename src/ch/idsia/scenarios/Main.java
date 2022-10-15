package ch.idsia.scenarios;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.agents.Agent;
import ch.idsia.agents.GAAgent;
import ch.idsia.agents.LearningWithGA;

public final class Main {
  public static void main(String[] args) {
    final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

    LearningWithGA ga = new LearningWithGA(
        "-lco off -lb on -le off -lhb off -lg on -ltb on -lhs off -lca on -lde on -ld 5 -ls 133829");
    ga.learn();
    // GAAgent agent = new GAAgent();
    // agent.loadGene(
    // "/Users/ansai/Documents/kyoto-university/java-calculator-experiment2/LearningWithGA-2022-10-14_22-50-00.xml");
    // marioAIOptions.setAgent(agent);

    // marioAIOptions.setArgs("-lco off -lb on -le off -lhb off -lg on -ltb on -lhs
    // off -lca on -lde on -ld 5 -ls 133829");

    // final BasicTask basicTask = new BasicTask(marioAIOptions);
    // basicTask.setOptionsAndReset(marioAIOptions);
    // basicTask.doEpisodes(1, true, 1);
    System.exit(0);
  }

}
