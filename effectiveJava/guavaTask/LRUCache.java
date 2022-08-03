import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class LRUCache {
  private static int MAX_SIZE = 8;

  public static Cache<Integer, String> createCache() {
    RemovalListener<Integer, String> removalListener = new RemovalListener<Integer, String>() {
      public void onRemoval(RemovalNotification<Integer, String> removal) {
        System.out.println("Removing key: " + removal.getKey() + ", value: " + removal.getValue());
      }
    };

    return CacheBuilder
      .newBuilder()
      .maximumSize(LRUCache.MAX_SIZE)
      .removalListener(removalListener)
      .recordStats()
      .build();
  }
}
