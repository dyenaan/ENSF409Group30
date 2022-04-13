package edu.ucalgary.ensf409;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OutputToFile {
    public void writeFile(String filename, Family[] families) throws IOException {
        FileWriter writer = new FileWriter(filename + ".txt");
        writer.write("\n");
        for (int i = 0; i < families.length; i++) {
            writer.write("Family " + (i + 1) + "\n");
            ArrayList<Map<String,String>> bestHamper = families[i].getHamper();
            for (int j = 0; j < bestHamper.size(); j++) {
                writer.write(bestHamper.get(i).get("Name") + "\n");
            }
        }
        writer.close();
    }
}
