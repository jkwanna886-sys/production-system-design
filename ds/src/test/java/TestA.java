import org.junit.jupiter.api.Test;

public class TestA {
    /**
     * Input: num1 = "11", num2 = "123"
     * Output: "134"
     */
    @Test
    public void test() {
        String s1 = "11";
        String s2 = "123";


        int len = Math.max(s1.length(), s2.length());

        char[] result = new char[len];

        int p1 = s1.length() - 1;
        int p2 = s2.length() - 1;

        for(int i= len-1; i>=0; i--) {
            // need to convert char to int(numeric) to operation
            int c = 0;
            if(p1>=0) {
                c += s1.charAt(p1) - '0';
                p1--;
            }
            if(p2>=0) {
                c += s2.charAt(p2)- '0';
                p2--;
            }
            // need to convert int back to char
            char t = (char)(c + '0');
            result[i] = t;
        }

        System.out.println(result);
    }
}
