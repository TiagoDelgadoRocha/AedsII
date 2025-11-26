public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        // Remove spaces and convert to lowercase for case-insensitive comparison
        String cleanStr = str.replaceAll("\\s+", "").toLowerCase();
        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "racecar";
        String test2 = "hello";
        String test3 = "A man a plan a canal Panama";

        System.out.println("\"" + test1 + "\" is a palindrome: " + isPalindrome(test1));
        System.out.println("\"" + test2 + "\" is a palindrome: " + isPalindrome(test2));
        System.out.println("\"" + test3 + "\" is a palindrome: " + isPalindrome(test3));
    }
}