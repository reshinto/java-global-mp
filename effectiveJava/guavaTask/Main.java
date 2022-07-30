import com.google.common.cache.Cache;

class Task1 extends Thread {
  public void run() {
    String name = "lru 1";
    Cache<Integer, String> lru = LRUCache.createCache();
    System.out.println(lru.size());

    for (int i=0; i<10; i++) {
      lru.put(i, "value" + i);
    }

    for (int i = 0; i < 6; i++) {
      System.out.println(name + " using " + lru.getIfPresent(i));
    }

    for (int i = 10; i < 12; i++) {
      System.out.println(name + " adding key " + i + " value" + i);
      lru.put(i, "value" + i);
    }

    System.out.println(lru.getIfPresent(0));
    System.out.println(lru.getIfPresent(1));
    System.out.println(lru.getIfPresent(2));
    System.out.println(lru.getIfPresent(6));
    System.out.println(lru.getIfPresent(10));


    System.out.println(lru.size());
    System.out.println(lru.stats());
  }
}

class Task2 extends Thread {
  public void run() {
    String name = "lru 2";
    Cache<Integer, String> lru = LRUCache.createCache();
    System.out.println(lru.size());

    for (int i=0; i<10; i++) {
      lru.put(i, "value" + i);
    }

    for (int i = 0; i < 6; i++) {
      System.out.println(name + " using " + lru.getIfPresent(i));
    }

    for (int i = 10; i < 12; i++) {
      System.out.println(name + " adding key " + i + " value" + i);
      lru.put(i, "value" + i);
    }

    System.out.println(lru.getIfPresent(0));
    System.out.println(lru.getIfPresent(1));
    System.out.println(lru.getIfPresent(2));
    System.out.println(lru.getIfPresent(6));
    System.out.println(lru.getIfPresent(10));


    System.out.println(lru.size());
    System.out.println(lru.stats());
  }
}

public class Main{
  public static void main(String[] args) throws InterruptedException {
    Task1 task1 = new Task1();
    Task2 task2 = new Task2();

    task1.start();
    task2.start();

    task1.join();
    task2.join();
  }
}