class XorWhat {
    public static void main(String[] args) {
        String input = args[0];
        String[] splitInput = input.split("\n");
        StringBuilder res = new StringBuilder();
        int testCaseNumber = 1;

        for (int index = 1; index < splitInput.length;) {
            int numMods = Integer.parseInt(splitInput[index].split(" ")[1]);
            String[] initialValsStr = splitInput[index + 1].split(" ");
            int[] initialVals = new int[initialValsStr.length];
            for (int i = 0; i < initialValsStr.length; i++) {
                initialVals[i] = Integer.parseInt(initialValsStr[i]);
            }
            int[][] mods = new int[numMods][2]; // split (index + 2) to (index + 1 + numMods) .foreach line: split at spaces
            for (int i = index + 2; i < index + numMods + 2; i++) {
                String line = splitInput[i];
                String[] valsStr = line.split(" ");
                int[] vals = new int[valsStr.length];
                for(int j = 0; j < valsStr.length; j++) {
                    vals[j] = Integer.parseInt(valsStr[j]);
                }
                mods[i - index - 2] = vals;
            }
            XorWhatTestCase testCase = new XorWhatTestCase(initialVals, mods, testCaseNumber++);
            res.append(testCase.getOutput());
            res.append("\n");
            index += 2 + numMods;
        }

        System.out.println(res);
    }
}
