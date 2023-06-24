
/*
 * method to process a length of application
 * @author nurulhannan
 *
 */
public class CalculateLength {

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
