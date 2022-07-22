class Task1 extends Thread {
  public void run() {
    try {
      String name = "lfu 1";
      LFUCache lfu = new LFUCache(name); 
      for (int i = 0; i < 10; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      for (int i = 6; i < 10; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      for (int i = 1; i < 5; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      System.out.println(lfu.get(2));
      Thread.sleep(2000);
      System.out.println(lfu.get(2));
      Thread.sleep(1000);
      System.out.println(lfu.get(2));
      System.out.println(lfu.toString());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Task2 extends Thread {
  public void run() {
    try {
      String name = "lfu 2";
      LFUCache lfu = new LFUCache(name); 
      for (int i = 0; i < 10; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      for (int i = 6; i < 10; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      for (int i = 1; i < 5; i++) {
        System.out.println(name + " adding key " + i + " value" + i);
        lfu.put(i, "value" + i);
      }

      System.out.println(lfu.get(3));
      Thread.sleep(2000);
      System.out.println(lfu.get(3));
      Thread.sleep(1000);
      System.out.println(lfu.get(3));
      System.out.println(lfu.toString());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class Main {
  public static void main(String[] args) throws InterruptedException {
    // LFUCache lfu = new LFUCache("lfu 1");
    // LFUCache lfu2 = new LFUCache("lfu 2");
    Task1 task1 = new Task1();
    Task2 task2 = new Task2();

    task1.start();
    task2.start();

    // for (int i = 0; i < 10; i++) {
    //   System.out.println("lfu adding " + i + "value" + i);
    //   lfu.put(i, "value" + i);
    // }

    // for (int i = 6; i < 10; i++) {
    //   System.out.println("lfu adding " + i + "value" + i);
    //   lfu.put(i, "value" + i);
    // }

    // for (int i = 1; i < 5; i++) {
    //   System.out.println("lfu adding " + i + "value" + i);
    //   lfu.put(i, "value" + i);
    // }

    task1.join();
    task2.join();
  }
}
