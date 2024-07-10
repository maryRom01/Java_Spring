package app.utils.fileLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileLoaderBin {

    public void writeFile(String filePath, List<Object> data) throws IOException {
        File file = new File(filePath);
        final ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
        data.forEach(object -> {
            try {
                oos.writeObject(object);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        oos.close();
    }

    public List<Object> readFile(String filePath) throws IOException {
        File file = new File(filePath);
        List<Object> objects = new ArrayList<>();
        final ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
        while (true) {
            try {
                Object obj = (Object) ois.readObject();
                objects.add(obj);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        ois.close();
        return objects;
    }
}
