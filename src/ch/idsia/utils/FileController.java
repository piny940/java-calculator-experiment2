package ch.idsia.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ch.idsia.agents.GAAgent;

public class FileController {
  public static void write(GAAgent agent, String filename) {
    try {
      File outputFile = new File(filename);
      FileWriter writer = new FileWriter(outputFile);
      for (Integer i = 0; i < agent.gene.length; i++) {
        writer.write(agent.gene[i] + "\n");
      }

      writer.close();
    } catch (IOException ex) {
      System.out.println("Failed to write file");
      System.exit(0);
    }
  }
}
