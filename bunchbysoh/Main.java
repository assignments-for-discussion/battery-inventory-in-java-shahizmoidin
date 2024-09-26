package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();

    // Iterate through each battery capacity
    for (int capacity : presentCapacities) {

      // Calculate state of health as percentage
      double stateofhealth = (double) capacity / 120 * 100;

      // Categorize battery based on state of health
      if (stateofhealth > 80) {
        counts.healthy++;
      } else if (stateofhealth <= 80 && stateofhealth >= 63) {
        counts.exchange++;
      } else {
        counts.failed++;
      }
    }

    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");

    // Case 1: Standard case
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert (counts.healthy == 3);
    assert (counts.exchange == 2);
    assert (counts.failed == 1);

    // Case 2: Lowest capacity (0)
    int[] presentCapacitiesLowest = {0};
    CountsBySoH countsLowest = countBatteriesByHealth(presentCapacitiesLowest);
    assert (countsLowest.healthy == 0);
    assert (countsLowest.exchange == 0);
    assert (countsLowest.failed == 1);

    // Case 3: Highest capacity (120)
    int[] presentCapacitiesHighest = {120};
    CountsBySoH countsHighest = countBatteriesByHealth(presentCapacitiesHighest);
    assert (countsHighest.healthy == 1);
    assert (countsHighest.exchange == 0);
    assert (countsHighest.failed == 0);

    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
