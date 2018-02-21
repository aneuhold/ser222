package homework12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class for a linear probing implementation of map.
 * 
 * @author Anton G Neuhold Jr
 *
 */
public class LinearProbingMap<Key, Value> implements Map<Key, Value> {
  public static final int INIT_CAPACITY = 997;
  
  private int m; // Size of the table
  private int n; // Number of keys in the map
  
  private Entry<Key, Value>[] entries;
  
  private int hash(Key k, int numCollisions) {
    return ((k.hashCode() & 0x7fffffff) + numCollisions) % m;
  }
  
  LinearProbingMap() {
    this(INIT_CAPACITY);
  }
  
  public LinearProbingMap(int m) {
    this.n = 0;
    this.m = m;
    entries = new Entry[m];
  }
  
  @Override
  public void put(Key key, Value val) {
    int hash = hash(key, 0);
    int i = 0;
    boolean added = false;
    while (i < m && !added) {
      if (entries[hash] == null ||
          entries[hash].deleted == true) {
        entries[hash] = new Entry(key, val);
        added = true;
        n++;
      } else if (entries[hash].key.equals(key)) {
        entries[hash] = new Entry(key, val);
        added = true;
      } else {
        i++;
        hash = hash(key, i);
      }
    }
  }

  @Override
  public Value get(Key key) {
    int hash = hash(key, 0);
    int i = 0;
    Value result = null;
    while (i < m && // the counter is less than the table size
        result == null && // the result is still not found
        entries[hash] != null) { // the current entry is not null
      if (!entries[hash].deleted &&
          entries[hash].key.equals(key)) {
        result = entries[hash].value;
      } else {
        i++;
        hash = hash(key, i);
      }
    }
    return result;
  }

  @Override
  public void remove(Key key) {
    int hash = hash(key, 0);
    int i = 0;
    boolean removed = false;
    while (i < m && // the counter is less than the table size and
        !removed && // the value has still not been removed and
        entries[hash] != null) { // the current entry is not null
      if (!entries[hash].deleted &&
          entries[hash].key.equals(key)) {
        entries[hash].deleted = true;
        removed = true;
        n--;
      } else {
        i++;
        hash = hash(key, i);
      }
    }
  }

  @Override
  public boolean contains(Key key) {
    int hash = hash(key, 0);
    int i = 0;
    boolean found = false;
    while (i < m && // the counter is less than the table size
        !found && // the result is still not found
        entries[hash] != null) { // the current entry is not null
      if (!entries[hash].deleted &&
          entries[hash].key.equals(key)) {
        found = true;
      } else {
        i++;
        hash = hash(key, i);
      }
    }
    return found;
  }

  @Override
  public boolean isEmpty() {
    return n == 0;
  }

  @Override
  public int size() {
    return n;
  }
  
  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedList<>();

    for (Entry e : entries)
      if (e != null && !e.deleted)
        queue.add((Key)e.key);
    return queue;
  }
  
  private class Entry<K, V> {
    public K key;
    public V value;
    public boolean deleted;
    
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.deleted = false;
    }
  }
}
