package data.dao.jsondao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import data.dao.Dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonDao<T> implements Dao<T> {

    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private static final String STORAGE_DIRECTORY_PATH = "src/main/resources/data/";

    protected final String STORAGE_FILE_PATH;

    private final Class<T> tClass;

    protected JsonDao(Class<T> tClass) {
        this.tClass = tClass;
        STORAGE_FILE_PATH = STORAGE_DIRECTORY_PATH + tClass.getSimpleName() + ".json";
    }

    @Override
    public boolean create(T item) {
        File storageFile = new File(STORAGE_FILE_PATH);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (storageFile.canRead() && storageFile.canWrite()) {
            ArrayList<T> data;
            try (JsonReader reader = new JsonReader(new FileReader(storageFile))) {
                data = gson.fromJson(reader, TypeToken.getParameterized(ArrayList.class, tClass).getType());
            } catch (IOException e) {
                data = new ArrayList<>();
            }

            if (data == null) {
                data = new ArrayList<>();
            }

            if (data.contains(item)) { // If item is already contained, remove it
                return false;
            }

            data.add(item);

            try (FileWriter writer = new FileWriter(storageFile)) {
                gson.toJson(data, writer);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }


    @Override
    public List<T> findAll(Class<T> c) {
        ArrayList<T> data = new ArrayList<>();
        File storageFile = new File(STORAGE_FILE_PATH);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        if (storageFile.canRead()) {
            try (JsonReader reader = new JsonReader(new FileReader(storageFile))) {
                data = gson.fromJson(reader, TypeToken.getParameterized(ArrayList.class, tClass).getType());
            } catch (IOException e) {
                data = new ArrayList<>();
            }

            if (data == null) {
                data = new ArrayList<>();
            }
            return data;
        }
        return data;
    }

    @Override
    public boolean delete(T item) {
        File storageFile = new File(STORAGE_FILE_PATH);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        if (storageFile.canRead() && storageFile.canWrite()) {
            ArrayList<T> data;
            try (JsonReader reader = new JsonReader(new FileReader(storageFile))) {
                data = gson.fromJson(reader, TypeToken.getParameterized(ArrayList.class, tClass).getType());
            } catch (IOException e) {
                data = new ArrayList<>();
            }

            if (data == null) {
                data = new ArrayList<>();
            }

            data.remove(item);

            try (FileWriter writer = new FileWriter(storageFile)) {
                gson.toJson(data, writer);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll(Class<T> c) {
        File storageFile = new File(STORAGE_FILE_PATH);
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        if (storageFile.canWrite()) {
            try (FileWriter writer = new FileWriter(storageFile)) {
                gson.toJson(new ArrayList<>(), writer);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void close() {
        // Does nothing
    }
}
