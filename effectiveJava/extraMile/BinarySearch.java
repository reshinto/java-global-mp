public class BinarySearch {
  private static SortStrategy sortStrategy = new InsertionSort();

  public static void setStrategy(SortStrategy strategy) {
    sortStrategy = strategy;
  }

  public static int iterative(int[] array, int target) {
    long startTime = System.nanoTime();
    sortStrategy.sort(array);

    int result = iterativeHelper(array, target);

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.println("Total time for iterative binary search: " + duration + " ns");
    
    return result;
  }

  private static int iterativeHelper(int[] array, int target) {
    int left = 0;
    int right = array.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  public static int recursive(int[] array, int target) {
    long startTime = System.nanoTime();
    sortStrategy.sort(array);

    int result = recursiveHelper(array, target, 0, array.length - 1);

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.println("Total time for recursive binary search: " + duration + " ns");
    
    return result;
  }

  private static int recursiveHelper(int[] array, int target, int left, int right) {
    if (left > right) {
      return -1;
    }
    int mid = (left + right) / 2;
    if (array[mid] == target) {
      return mid;
    } else if (array[mid] < target) {
      return recursiveHelper(array, target, mid + 1, right);
    } else {
      return recursiveHelper(array, target, left, mid - 1);
    }
  }
}
