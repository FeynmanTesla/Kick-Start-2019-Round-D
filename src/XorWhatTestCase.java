class XorWhatTestCase {
    private int[] array;
    private int[][] mods;
    private int testCaseNumber;

    XorWhatTestCase(int[] initialVals, int[][] modsIn, int testCaseNumberIn) {
        this.array = initialVals;
        this.mods = modsIn;
        this.testCaseNumber = testCaseNumberIn;
    }

    private static int getXorSum(int[] subInt) {
        int sum = 0;
        for (int i: subInt) {
            sum = sum ^ i;
        }
        return sum;
    }

    private static int numSetBits(int n) {
        int num = 0;
        while (n > 0) {
            num += n & 1;
            n >>= 1;
        }
        return num;
    }

    private boolean subIntervalIsXorEven(int i, int j) {
        int[] subInt = new int[j - i];
        System.arraycopy(this.array, i, subInt, 0, j);
        return numSetBits(getXorSum(subInt)) % 2 == 0;
    }

    private int[] getLargestXorEvenSubInterval() {
        int maxLength = 0;
        int maxI = 0;
        int maxJ = 0;
        for(int i = 0; i < this.array.length; i++) {
            for(int j = this.array.length - 1; j > i; j--) {
                if (subIntervalIsXorEven(i, j)) {
                    maxI = i;
                    maxJ = j;
                    maxLength = j-i;
                }
            }
        }

        int[] res = new int[maxLength];
        System.arraycopy(this.array, maxI, res, 0, maxJ - maxI);
        return res;
    }

    private void makeMod(int[] mod) {
        this.array[mod[0]] = mod[1];
    }

    String getOutput() {
        StringBuilder res = new StringBuilder();
        res.append("Case #");
        res.append(this.testCaseNumber);
        res.append(":");
        for (int[] mod : this.mods) {
            res.append(" ");
            makeMod(mod);
            res.append(getLargestXorEvenSubInterval().length);
        }
        return res.toString();
    }
}
