public class InsertionSort implements SortStrategy {
  @Override
  public void sort(int[] arr) {
    long startTime = System.nanoTime();

    sortHelper(arr);
    
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.println("Total time for Insertion sort: " + duration + " ns");
  }
  
  private static void sortHelper(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      int key = arr[i];
      int j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }
}
