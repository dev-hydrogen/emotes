package exposed.hydrogen.emotes.emotes.json;
import exposed.hydrogen.emotes.emotes.Emotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtils {
    public static final File LIGHT_JSON = new File(Emotes.DATA_FOLDER + "/" + Emotes.JSON_FILE_NAME);

    public static FileReader getReader(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
