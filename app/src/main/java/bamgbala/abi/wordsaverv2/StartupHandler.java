package bamgbala.abi.wordsaverv2;

import android.content.res.AssetManager;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by abi on 24.11.14.
 */
public class StartupHandler {
    public static void checkFiles(AssetManager manager) throws Exception {
        if (!Environment.getExternalStorageDirectory().canRead())
            throw new Exception("Could not read from external storage");
        if(!new File(FileHandler.file_english).exists()){
            new File(FileHandler.file_english).createNewFile();
            copy(manager.open(FileHandler.file_english), new File(FileHandler.file_english));
        }
        if(!new File(FileHandler.file_arabic).exists()){
            new File(FileHandler.file_arabic).createNewFile();
            copy(manager.open(FileHandler.file_arabic), new File(FileHandler.file_arabic));
        }
    }

    public static void copy(InputStream src, File dst) throws IOException {
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = src.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        src.close();
        out.close();
    }

}
