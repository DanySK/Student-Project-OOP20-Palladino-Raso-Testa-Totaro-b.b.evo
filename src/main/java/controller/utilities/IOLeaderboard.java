package controller.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;


import com.google.common.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 *  Class that allows to print and read to external file a leaderboard.
 */
public final class IOLeaderboard {

    private static final Gson GSON  = new Gson();
    private static final String SEP = System.getProperty("file.separator");
    private static final String RES_PATH = System.getProperty("user.home")
                                           + SEP
                                           + "BrickBreaker-EVO-Resources" 
                                           + SEP
                                           + "Leaderboards"
                                           + SEP;
    private static final String FILE_NAME = "ranking.json";
    private static final String FILE_PATH = RES_PATH + FILE_NAME;
    private static File rankFile = new File(FILE_PATH);
    private static Map<String, Integer> jsonMap = new HashMap<>();

    private IOLeaderboard() {

    }

    /**
     *  Method that allows to print to external file a leaderboard in jsonformat.
     *  @param leaderboard - represent the map that needs to be converted.
     */
    public static void printInJsonFormat(final Map<String, Integer> leaderboard) {

        //Serialize in json
        final String gsonStringFormat = GSON.toJson(leaderboard);

        /*
        final Path filePath = Path.of(FILE_PATH);
        try {
            Files.deleteIfExists(filePath);
            if (rankFile.getParentFile().mkdirs()) {
                rankFile.createNewFile();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(rankFile.getAbsolutePath());*/

        //Print File
        final Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(rankFile, charset))) {
            wr.write(gsonStringFormat);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not write");
        }

    }

    /**
     *  Method that allows to print to read file a leaderboard in jsonformat.
     *  @return a map that represent the leaderboard.
     */
    public static Map<String, Integer> readLeaderboard() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            final String data = reader.readLine();
            reader.close();
            //Mapping data
            final Type mapType = new TypeToken<Map<String, Integer>>() {
                    private static final long serialVersionUID = -437203752652190282L;
            }.getType();

            jsonMap = GSON.fromJson(data, mapType);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOExcept");
        }
        return jsonMap;
    }

    /**
     *  Method that allows to get a directory path that contains ranking.
     *  @return a directory path that contains ranking.
     */
    public static String getDirPath() {
        return RES_PATH;
    }

    /**
     *  Method that allows to get a path of ranking file.
     *  @return a path of ranking file.
     */
    public static String getFilePath() {
        return FILE_PATH;
    }
}
