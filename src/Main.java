import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Capacity of the seats
        int capacity = 10;

        // Preference list of candidates
        Map<String, List<String>> preferenceList = new HashMap<>();
        preferenceList.put("Candidate 1", Arrays.asList("Seat A", "Seat B", "Seat C"));
        preferenceList.put("Candidate 2", Arrays.asList("Seat B", "Seat A", "Seat C"));
        preferenceList.put("Candidate 3", Arrays.asList("Seat C", "Seat B", "Seat A"));
        preferenceList.put("Candidate 4", Arrays.asList("Seat A", "Seat C", "Seat B"));

        // Merit list of candidates
        Map<String, Integer> meritList = new HashMap<>();
        meritList.put("Candidate 1", 80);
        meritList.put("Candidate 2", 75);
        meritList.put("Candidate 3", 85);
        meritList.put("Candidate 4", 70);

        // Allocate seats to candidates
        Map<String, String> seatAllocation = new HashMap<>();
        Set<String> allocatedSeats = new HashSet<>();
        for (Map.Entry<String, Integer> entry : sortByValue(meritList).entrySet()) {
            String candidate = entry.getKey();
            for (String seat : preferenceList.get(candidate)) {
                if (!allocatedSeats.contains(seat) && capacity > 0) {
                    seatAllocation.put(candidate, seat);
                    allocatedSeats.add(seat);
                    capacity--;
                    break;
                }
            }
        }

        // Print seat allocation
        for (Map.Entry<String, String> entry : seatAllocation.entrySet()) {
            System.out.println(entry.getKey() + " has been allocated " + entry.getValue());
        }
    }

    // Helper method to sort map by value
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}