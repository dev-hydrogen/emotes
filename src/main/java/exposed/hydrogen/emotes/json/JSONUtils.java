package exposed.hydrogen.emotes.json;
import exposed.hydrogen.emotes.Emotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtils {
    public static final File EMOTES_JSON = new File(Emotes.DATA_FOLDER + File.separator + Emotes.JSON_FILE_NAME);

    public static FileReader getReader(File file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
