package server;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;

class Database {

    private final Map<String, String> fastStorage = new HashMap<>();

    private final HashMap<String, String> operationSuccess = new HashMap<>();
    private final HashMap<String, String> badRequest = new HashMap<>();

    public Database() {
        synchronized (this) {
            operationSuccess.put("response", "OK");
            badRequest.put("response", "ERROR");
            badRequest.put("reason", "No such key");
        }
    }

    public synchronized String get(String key) {
        if (fastStorage.containsKey(key)) {
            operationSuccess.put("value", reentrantReader(key));
            return new Gson().toJson(operationSuccess);
        } else {
            return new Gson().toJson(badRequest);
        }
    }

    public synchronized String set(String key, String value) {
        reentrantWriter(key, value);
        operationSuccess.remove("value");
        return new Gson().toJson(operationSuccess);
    }

    public synchronized String delete(String key) {
        if (fastStorage.containsKey(key)) {
            reentrantWriter(key, "");
            operationSuccess.remove("value");
            return new Gson().toJson(operationSuccess);
        } else {
            return new Gson().toJson(badRequest);
        }
    }

    public synchronized String exit() {
        operationSuccess.remove("value");
        return new Gson().toJson(operationSuccess);
    }

    public void reentrantWriter(String key, String value) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        if ("".equals(value)) {
            fastStorage.remove(key);
        } else {
            fastStorage.put(key, value);
        }
        try (FileWriter writer = new FileWriter("src/server/data/db.json")) {
            writer.write(new Gson().toJson(fastStorage));
            writeLock.unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reentrantReader(String key) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        readLock.lock();
        String value = fastStorage.get(key);
        readLock.unlock();
        return value;
    }
}