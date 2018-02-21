package homework12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class for a two probe chain map, where the key is chosen based on which
 * chain is shorter. 
 * 
 * @author Anton G Neuhold Jr
 *
 */
public class TwoProbeChainMap<Key, Value> implements Map<Key, Value> {
  public static final int INIT_CAPACITY = 997;
  
  private int n; // Number of key-value pairs
  private int m; // Size of hash table
  private LinkedList<Entry>[] table;
  
  public TwoProbeChainMap() {
    this(INIT_CAPACITY);
  }
  
  public TwoProbeChainMap(int size) {
    n = 0;
    m = size;
    table = new LinkedList[m];
    for (int i = 0; i < m; i++)
      table[i] = new LinkedList();
  }
  
  private int hash(Key k) {
    return (k.hashCode() & 0x7fffffff) % m;
  }
  
  private int hash2(Key k) {
    return (hash(k) * 31) % m;
  }
  
  @Override
  public void put(Key key, Value val) {
    int hash1 = hash(key);
    int hash2 = hash2(key);
    
    boolean added = false;
    
    if (!overwrite(hash1, key, val)) // false if key isn't in hash1 location
      added = overwrite(hash2, key, val);
    else
      added = true;
    
    if(!added && table[hash1].size() <= table[hash2].size()) {
      table[hash1].add(new Entry(key, val));
      n++;
    } else if (!added) {
      table[hash1].add(new Entry(key, val));
      n++;
    }
  }
  
  /**
   * Attempts to find and overwrite a key if it already exists. Returns
   * false if it was not found, and true if it was, and was overwritten.
   * 
   * @param hash The chosen hash value in the table
   * @param key The key to add
   * @param val The value to add.
   * @return boolean True if found and overwritten
   */
  private boolean overwrite(int hash, Key key, Value val) {
    boolean added = false;
    for(Entry entry : table[hash]) {
      if(key.equals(entry.key)) {
        entry.value = val;
        added = true;
        // n not incremented because entry is overwritten
      }
    }
    return added;
  }

  @Override
  public Value get(Key key) {
    int hash1 = hash(key);
    int hash2 = hash2(key);
    
    for(Entry entry : table[hash1]) {
      if(key.equals(entry.key))
        return entry.value;
    }
    for (Entry entry : table[hash2]) {
      if(key.equals(entry.key))
        return entry.value;
    }
    return null;
  }

  /**
   * Removes the key and it's associated value from the map. If the key doesn't
   * exist then the map remains unchanged. 
   * 
   * @param key The key to remove along with it's value.
   */
  @Override
  public void remove(Key key) {
    int hash1 = hash(key);
    int hash2 = hash2(key);
    boolean removed = false;
    
    for(Entry entry : table[hash1]) {
      if(key.equals(entry.key)) { 
        table[hash1].remove(entry);
        removed = true;
      }
    }
    if (!removed) {
      for (Entry entry : table[hash2]) {
        if(key.equals(entry.key)) {
          table[hash2].remove(entry);
          removed = true; 
        }
      }
    }
    if (removed) { n--; }
  }

  @Override
  public boolean contains(Key key) {
    int hash1 = hash(key);
    int hash2 = hash2(key);
    
    for(Entry entry : table[hash1]) {
      if(key.equals(entry.key))
        return true;
    }
    for (Entry entry : table[hash2]) {
      if(key.equals(entry.key))
        return true;
    }
    return false;
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
    
    for (int i = 0; i < m; i++)
      for(Entry e : table[i])
        queue.add(e.key);
    
    return queue;
  }
  
  private class Entry {
    public Key key;
    public Value value;
    
    public Entry(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }
}
