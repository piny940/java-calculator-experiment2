package ch.idsia.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ch.idsia.agents.learning.GAAgent;

public class FileManager {
  public static void write(GAAgent agent, String filename) {
    try {
      File outputFile = new File(filename);
      FileWriter writer = new FileWriter(outputFile);
      for (Integer i = 0; i < agent.getGene().length; i++) {
        writer.write(agent.getGene()[i] + "\n");
      }

      writer.close();
    } catch (IOException ex) {
      System.out.println("Failed to write file");
      System.exit(0);
    }
  }
}
