package ch.idsia.scenarios;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.Task2Agent;

public final class MainTask2 {
    public static void main(String[] args) {
        final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

        final Agent agent = new Task2Agent();
        marioAIOptions.setAgent(agent);

        marioAIOptions.setArgs("-lca off -lhs off -ld 1 -ls 0 -le off");

        final BasicTask basicTask = new BasicTask(marioAIOptions);
        basicTask.setOptionsAndReset(marioAIOptions);
        basicTask.doEpisodes(1, true, 1);
        System.exit(0);
    }

}
