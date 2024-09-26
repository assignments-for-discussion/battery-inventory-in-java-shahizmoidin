package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  }

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();

    for (int capacity : presentCapacities) {
        if (capacity >= 85) {
            counts.healthy++;
        } else if (capacity >= 70) {
            counts.exchange++;
        } else {
            counts.failed++;
        }
    }

    return counts;
  }

static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");

    // Original test case
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    
    // Edge case: mix of healthy, exchange, and failed
    int[] test1 = {85, 84, 69};
    counts = countBatteriesByHealth(test1);
    assert(counts.healthy == 1);
    assert(counts.exchange == 1);
    assert(counts.failed == 1);

    // Edge case: all healthy
    int[] test2 = {90, 91, 99, 100};
    counts = countBatteriesByHealth(test2);
    assert(counts.healthy == 4);
    assert(counts.exchange == 0);
    assert(counts.failed == 0);

    // Edge case: all exchange
    int[] test3 = {70, 71, 75, 80, 84};
    counts = countBatteriesByHealth(test3);
    assert(counts.healthy == 0);
    assert(counts.exchange == 5);
    assert(counts.failed == 0);

    // Edge case: all failed
    int[] test4 = {50, 60, 69, 10};
    counts = countBatteriesByHealth(test4);
    assert(counts.healthy == 0);
    assert(counts.exchange == 0);
    assert(counts.failed == 4);

    System.out.println("All test cases passed!\n");
}

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
