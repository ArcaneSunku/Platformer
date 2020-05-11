package git.sunku.engine.toolbox;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private Cache(int size) {
        this.size = size;
    }

    public static <K, V> Cache<K, V> newInstance(int size) {
        return new Cache<K, V>(size);
    }

    public void setMaxSize(int size) {
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

}