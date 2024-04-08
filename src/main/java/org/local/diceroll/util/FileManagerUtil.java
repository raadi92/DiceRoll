package org.local.diceroll.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManagerUtil {

    private static final Logger logger = Logger.getLogger(FileManagerUtil.class.getName());

    private FileManagerUtil() {
    }

    public static void writeStringIntoFile(String string, File selectedFile) {
        try (FileWriter writer = new FileWriter(selectedFile)) {
            writer.write(string);
            logger.info("File saved: " + selectedFile.getAbsolutePath());

        } catch (IOException e) {
            logger.info("An error occurred while writing the string to the file: " + e.getMessage());
        }

    }

    public static List<String> loadFromFile(File selectedFile) {
        ArrayList<String> dicePools = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                logger.log(Level.INFO, "reading line: {0}", line);
                dicePools.add(line);
            }

        } catch (IOException e) {
            logger.info("An error occurred while writing the string to the file: " + e.getMessage());
        }
        return dicePools;

    }

}
