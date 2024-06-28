package models;
import java.util.LinkedList;
public class HashtableC<K, V> {
    private static int size = 500;
    private LinkedList<Entry<K, V>>[] buckets;

    public HashtableC() {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                return entry.getValue();
            }
        }
        return null;
    }
    public long printAll(Callback<V> callback) {
        long startTime = System.nanoTime();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                callback.call(entry.getValue());
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public int hash(K key) {
        //return key.hashCode();
        //return simpleHash((String) key);
        return multiplicativehash(key);
    }

    private int getBucketIndex(K key) {
        return Math.abs(hash(key)) % size;
    }

    ////////////////////////////////////////////////////////
    public int simpleHash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * 31 + key.charAt(i);
        }
        return Math.abs(hash);
    }
    public int multiplicativehash(K key) {
        int hashCode = key.hashCode();
        final int A = 31;
        final int PRIME = 0x01000193; // 16777619
        int hash = A * hashCode;
        hash ^= (hash >>> 16);
        hash *= PRIME;
        return Math.abs(hash);
    }
    public interface Callback<V> {
        void call(V value);
    }
}
