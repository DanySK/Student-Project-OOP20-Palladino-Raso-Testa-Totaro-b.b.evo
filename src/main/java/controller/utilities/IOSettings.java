package controller.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import com.google.gson.Gson;

import model.settings.GameSettingsImpl;
import model.settings.Settings;

/**
 *  Class that allows to print and read to external file the game's settings.
 */
public final class IOSettings {
    private static final Gson GSON  = new Gson();
    private static final String SEP = System.getProperty("file.separator");
    private static final String RES_PATH = System.getProperty("user.home")
                                           + SEP
                                           + "BrickBreaker-EVO-Resources" 
                                           + SEP
                                           + "Settings"
                                           + SEP;

    private static final String FILE_NAME = "settings.json";
    private static final String FILE_PATH = RES_PATH + FILE_NAME;
    private static File rankFile = new File(FILE_PATH);
    private static GameSettingsImpl jsonSettings;

    private IOSettings() {

    }

    /**
     *  Method that allows to print to external file game's settings in jsonformat.
     *  @param gameSettings - represent the settings entity that needs to be converted.
     */
    public static void printInJsonFormat(final Settings gameSettings) throws IOException, FileNotFoundException {

        //Serialize in json
        final String gsonStringFormat = GSON.toJson(gameSettings);

        //Print File
        final Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(rankFile, charset));) {
            wr.write(gsonStringFormat);
            wr.flush();
            wr.close();
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
    }

    /**
     *  Method that allows to read game's settings in file in jsonformat.
     *  @return a map that represent the entity settings.
     */
    public static Settings readSettings() throws IOException, FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            final String data = reader.readLine();
            reader.close();
            jsonSettings = GSON.fromJson(data, GameSettingsImpl.class);

        } catch (FileNotFoundException fileExcept) {
            fileExcept.printStackTrace();
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
        return jsonSettings;
    }

    /**
     *  Method that allows to get a directory path that contains game's settings.
     *  @return a directory path that contains game's settings.
     */
    public static String getDirPath() {
        return RES_PATH;
    }

    /**
     *  Method that allows to get a path of game's settings file.
     *  @return a path of game's settings file.
     */
    public static String getFilePath() {
        return FILE_PATH;
    }
}
