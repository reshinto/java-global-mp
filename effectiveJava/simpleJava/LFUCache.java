import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;


public class LFUCache extends Thread {
  private HashMap<Integer, String> cache = new HashMap<>();
  private HashMap<Integer, Integer> freq = new HashMap<>();
  private HashMap<Integer, LinkedHashMap<Integer, LocalDateTime>> freqItems = new HashMap<>();
  private int minFreqKey = 1;
  private static final int MAX_SIZE = 8;
  private int totalCacheEviction = 0;
  private ArrayList<Long> timesSpent = new ArrayList<>();
  private String name;

  public LFUCache(String name) {
    this.name = name;
  }

  public String get(int key) {
    if (!this.cache.containsKey(key)) return null;
        
    LocalDateTime toDateTime = LocalDateTime.now();
    this.printLastAccess(key, toDateTime);

    this.updateKeyFreq(key, toDateTime);
    return this.cache.get(key);
  }

  private void updateKeyFreq(int key, LocalDateTime toDateTime) {
    int oldFreqKey = this.freq.get(key);
    int newFreqKey = oldFreqKey + 1;
    
    this.freq.put(key, newFreqKey);
    this.freqItems.get(oldFreqKey).remove(key);
    this.addFreqItem(newFreqKey);
    if (oldFreqKey == this.minFreqKey && this.freqItems.get(oldFreqKey).isEmpty() ) {
        this.minFreqKey+=1;
    }
    this.freqItems.get(newFreqKey).put(key, toDateTime);
  }

  private void printLastAccess(int key, LocalDateTime toDateTime) {
    int oldFreqKey = this.freq.get(key);

    LocalDateTime fromDateTime = this.freqItems.get(oldFreqKey).get(key); 
    long timeDiff = fromDateTime.until(toDateTime, java.time.temporal.ChronoUnit.SECONDS);
    System.out.println(String.format("Time-based on last access (%d seconds)", timeDiff));
  }

  public void put(int key, String value) {
    long startTime = System.nanoTime();
    if (LFUCache.MAX_SIZE == 0) return;

    if (this.cache.containsKey(key)) {
        this.handleKeyDuplicate(key, value);
        return;
    }

    this.handleFrequency();
    
    this.cache.put(key, value);
    this.freq.put(key, 1);
    this.addFreqItem(1);
    this.freqItems.get(1).put(key, LocalDateTime.now());
    this.minFreqKey = 1;
    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000;
    this.timesSpent.add(duration);
    System.out.println(this.name + " Time-based on put: " + duration + " ms");
  }

  private void handleKeyDuplicate(int key, String value) {
    this.cache.put(key, value);
    LocalDateTime toDateTime = LocalDateTime.now();
    this.updateKeyFreq(key, toDateTime);
  }

  private void addFreqItem(int key) {
    if (!this.freqItems.containsKey(key)) {
        this.freqItems.put(key, new LinkedHashMap<>());
    }
  }

  private void handleFrequency() {
    if (this.cache.size() >= LFUCache.MAX_SIZE) {
      this.removeLeastFrequent();
    } 
  }

  private void removeLeastFrequent() {
    int minKeyToRemove = this.freqItems.entrySet().iterator().next().getValue().entrySet().iterator().next().getKey();
    System.out.println(String.format("%s: Removing key %d and value %s", this.name, minKeyToRemove, this.cache.get(minKeyToRemove)));

    this.totalCacheEviction++;
    this.freq.remove(minKeyToRemove);
    this.cache.remove(minKeyToRemove);
    this.freqItems.get(this.minFreqKey).remove(minKeyToRemove);
  }

  public String toString() {
    String evictionPolicy = "Eviction policy: Least Frequently Used";
    String cacheEviction = String.format("Total cache eviction: %d", this.totalCacheEviction);
    String cacheSize = String.format("Cache size: %d", this.cache.size());
    String averageTimeSpent = String.format("Average time spent on adding new value: %o ms", this.getAverageTimeSpent());
    return String.format("%s%n%s%n%s%n%s%n%s%n%s", this.name, evictionPolicy, cacheEviction, cacheSize, averageTimeSpent, this.cache);
  }

  private long getAverageTimeSpent() {
      long sum = 0;
      for (int i=0; i< this.timesSpent.size(); i++) {
            sum += this.timesSpent.get(i);
      }
      return sum / this.timesSpent.size();
  }
}