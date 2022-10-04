package ch.idsia.scenarios;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.ForwardAgent;

public final class Main {
    public static void main(String[] args) {
        final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

        // Stage parameters
        marioAIOptions.setLevelDifficulty(0);

        // Set Agent
        final Agent agent = new ForwardAgent();
        marioAIOptions.setAgent(agent);

        int seed = 99;
        marioAIOptions.setLevelRandSeed(seed);

        final BasicTask basicTask = new BasicTask(marioAIOptions);
        basicTask.setOptionsAndReset(marioAIOptions);
        basicTask.doEpisodes(1, true, 1);
        System.exit(0);
    }

}
