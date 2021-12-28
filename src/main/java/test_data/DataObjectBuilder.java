package test_data;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public <T> T buildDataObject(String filePath, Class<T> dataType) {
        String absoluteFilePath = System.getProperty("user.dir") + filePath;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath))
        ) {
            // Create Gson instance
            Gson gson = new Gson();

            // Convert json array data into loginCreds []
            return gson.fromJson(reader, dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
