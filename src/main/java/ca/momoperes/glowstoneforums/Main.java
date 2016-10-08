package ca.momoperes.glowstoneforums;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File hookFile = new File("hook.txt");
        if (!hookFile.exists()) {
            System.out.println("Please insert webhook data into hook.txt file.");
            hookFile.createNewFile();
            System.exit(0);
        }
        List<String> lines = Files.readAllLines(hookFile.toPath());
        if (lines.size() == 0) {
            System.out.println("Please insert webhook data into hook.txt file.");
            hookFile.createNewFile();
            System.exit(0);
        }
        System.out.println("Starting poll...");
        DiscordHook.WEBHOOK = lines.get(0);
        new Thread(new ForumsPuller()).start();
    }
}
