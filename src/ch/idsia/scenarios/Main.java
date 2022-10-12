package ch.idsia.scenarios;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.agents.Agent;
import ch.idsia.agents.LearningWithGA;

public final class Main {
  public static void main(String[] args) {
    final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

    LearningWithGA ga = new LearningWithGA("-lde on -ltb off -ld 2 -ls 0 -le g");
    ga.learn();
    // marioAIOptions.setAgent(agent);

    // marioAIOptions.setArgs("-lde on -ltb off -ld 2 -ls 0 -le g");

    // final BasicTask basicTask = new BasicTask(marioAIOptions);
    // basicTask.setOptionsAndReset(marioAIOptions);
    // basicTask.doEpisodes(1, true, 1);
    System.exit(0);
  }

}
