import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LatestGuests {
    public static void main(String[] args) {
        String input = args[0];
        String[] splitInput = input.split("\n");
        StringBuilder res = new StringBuilder();
        int testCaseNumber = 1;

        for (int index = 1; index < splitInput.length;) {
            int numCons = Integer.parseInt(splitInput[index].split(" ")[0]);
            int numGuests = Integer.parseInt(splitInput[index].split(" ")[1]);
            int numRotations = Integer.parseInt(splitInput[index].split(" ")[2]);

            ArrayList<Integer> clockWiseGuests = new ArrayList<Integer>();
            List<List<Integer>> consulates = new ArrayList<List<Integer>>(numCons);
            for (int i = 0; i < numCons; i++) consulates.add(new ArrayList<Integer>());

            for (int i = index + 1; i < index + 1 + numGuests; i++) {
                String line = splitInput[i];
                int startingConsulate = Integer.parseInt(line.split(" ")[0]);
                boolean clockwise = line.split(" ")[1].equals("C");
                if (clockwise) clockWiseGuests.add(i - index);
                consulates.get(startingConsulate).add(i - index);
            }

            for (int i = 0; i < numRotations - 1; i++) {
                List<List<Integer>> newConsulates = new ArrayList<List<Integer>>(numCons);
                for (int j = 0; j < numCons; j++) newConsulates.add(new ArrayList<Integer>());
                for (int j = 0; j < numCons; j++) {
                    int lastIndex = j == 0 ? numCons - 1 : j - 1;
                    int nextIndex = j == numCons - 1 ? 0 : j + 1;
                    for (Integer guest: consulates.get(lastIndex)) {
                        if (clockWiseGuests.contains(guest)) newConsulates.get(j).add(guest);
                    }
                    for (Integer guest: consulates.get(nextIndex)) {
                        if (! clockWiseGuests.contains(guest)) newConsulates.get(j).add(guest);
                    }
                }
            }

            HashMap<Integer, Integer> memories = new HashMap<Integer, Integer>();

            for (List<Integer> consulate : consulates) {
                for (Integer i : consulate) {
                    if (! memories.containsKey(i)) memories.put(i, 1);
                    else memories.put(i, memories.get(i) + 1);
                }
            }

            res.append("Case #");
            res.append(testCaseNumber++);
            res.append(":");
            for (int i = 0; i < numGuests; i++) {
                res.append(" ");
                res.append(memories.get(i));
            }
        }

        System.out.println(res);
    }
}
