public class CombinationsTester {

    public static void main(String[] args) {

        char[] chars = {'A', 'B', 'C', 'D', 'E'};
        char[] chars2 = {'F', 'G', 'H', 'I', 'J'};
        char[] variations = new char[chars.length];

        variate(0, chars, chars2, variations);
        }
    private static void variate(int index, char[] chars, char[] chars2, char[] variations) {

        if (index >= variations.length) {

            consolePrint(variations);

            return;
        }

        variations[index] = chars[index];
        variate(index + 1, chars, chars2, variations);

        variations[index] = chars2[index];
        variate(index + 1, chars, chars2, variations);
    }
    private static void consolePrint(char[] variations) {

        StringBuilder out = new StringBuilder();

        for (char variation : variations) {

            out.append(variation).append(" ");
        }

        out.append(System.lineSeparator());

        System.out.print(out);
    }

}

