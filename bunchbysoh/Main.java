package bunchbysoh;
package dsa.nemo;

public class Main {
  // Class to store counts of batteries by their health status
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };
    public int invalid = 0;  // Count for invalid battery capacities
  }

  // Method to classify batteries based on their state of health
  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    int ratedCapacity = 120;  // Maximum battery capacity
    for (int presentCapacity : presentCapacities) {
      // Skip invalid capacities (negative or too high)
      if (presentCapacity < 0 || presentCapacity > ratedCapacity) {
        counts.invalid++;
        continue;
      }
      double stateOfHealth = 100.0 * presentCapacity / ratedCapacity;
      // Check battery health and categorize
      if (stateOfHealth > 80) {
        counts.healthy++;
      } else if (stateOfHealth > 62) {
        counts.exchange++;
      } else {
        counts.failed++;
      }
    }
    return counts;
  }

  // Test cases for checking if classification works properly
  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    // Original test
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Done counting :)\n");
    assert counts.healthy == 2 : "Test 1 failed";
    assert counts.exchange == 3 : "Test 1 failed";
    assert counts.failed == 1 : "Test 1 failed";
    // Test with capacities on boundary values
    int[] presentCapacitiesBoundary = {96, 74};
    counts = countBatteriesByHealth(presentCapacitiesBoundary);
    assert counts.healthy == 0 : "Test 2 failed";
    assert counts.exchange == 1 : "Test 2 failed";
    assert counts.failed == 1 : "Test 2 failed";
    // Test with max and min capacities
    int[] presentCapacitiesMaxMin = {120, 0};
    counts = countBatteriesByHealth(presentCapacitiesMaxMin);
    assert counts.healthy == 1 : "Test 3 failed";
    assert counts.failed == 1 : "Test 3 failed";
    // Test where all capacities are the same
    int[] sameCapacities = {90, 90, 90};
    counts = countBatteriesByHealth(sameCapacities);
    assert counts.exchange == 3 : "Test 4 failed";
    // Test with invalid capacities
    int[] invalidCapacities = {0, -10, 50};
    counts = countBatteriesByHealth(invalidCapacities);
    assert counts.failed == 1 : "Test 5 failed";
    assert counts.invalid == 2 : "Test 5 failed";
    // Test with empty input
    int[] emptyCapacities = {};
    counts = countBatteriesByHealth(emptyCapacities);
    assert counts.healthy == 0 : "Test 6 failed";
    // Test near boundary values
    int[] boundaryTest = {97, 96, 81, 80, 63, 62, 61};
    counts = countBatteriesByHealth(boundaryTest);
    assert counts.healthy == 2 : "Test 7 failed";
    assert counts.exchange == 3 : "Test 7 failed";
    assert counts.failed == 2 : "Test 7 failed";
    // Test slight boundary differences
    int[] slightBoundaryTest = {79, 81, 63, 61, 120, 1, -5, 121};
    counts = countBatteriesByHealth(slightBoundaryTest);
    assert counts.healthy == 2 : "Test 8 failed";
    assert counts.failed == 2 : "Test 8 failed";
    assert counts.invalid == 3 : "Test 8 failed";
    // Test with a larger data set
    int[] largeDataSet = {100, 105, -15, 50, 120, 78, 62, 0, 90, 85, 130};
    counts = countBatteriesByHealth(largeDataSet);
    assert counts.healthy == 3 : "Test 9 failed";
    assert counts.failed == 2 : "Test 9 failed";
    assert counts.invalid == 4 : "Test 9 failed";
    System.out.println("All test cases passed successfully!");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
    testBucketingByHealth();  // Run the tests
  }
}
}
