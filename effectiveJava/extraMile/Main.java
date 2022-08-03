public class Main {
  public static void main(String[] args) {
    int[] array = {5, 10, 8, 4, 1, 6, 9, 3, 7, 2};
    int target = 3;

    SortStrategy mergeStrategy = new MergeSort();
    // SortStrategy insertionStrategy = new InsertionSort();
    BinarySearch.setStrategy(mergeStrategy);
    // BinarySearch.setStrategy(insertionStrategy);

    int resultIterative = BinarySearch.iterative(array, target);
    int resultRecursive = BinarySearch.recursive(array, target);
    System.out.println(resultIterative == 2);
    System.out.println(resultRecursive == 2);
  }
}