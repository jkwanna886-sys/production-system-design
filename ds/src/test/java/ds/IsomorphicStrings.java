package ds;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Isomorphic:similar.
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 * compact: same pattern, different letters.
 * only mapped permission can allow position visited.
 * note:strictly index align, can not cross.a[0] map to b[0](but can not a[0] map to b[1])
 */

/**
 * 	•	egg → add ✅ (e→a, g→d)
 * 	•	foo → bar ❌ (o→r and o→a conflict)
 * 	•	paper → title ✅ (p→t, a→i, e→l, r→e)
 *
 * [true] example:
 * e->g, g->e
 * a->d, d->a
 *-------------------------
 * [false] example:
 * foo -> bar:
 * f->b, b->f
 * o->a, a->o
 * o->r,##
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    @Test
    public void test2() {
        String s1 = "egd";
        String s2 = "adg";
        assertEquals(true, isomorphic3(s1, s2));

        String s3 = "foo";
        String s4 = "bar";
        assertEquals(false, isomorphic3(s3, s4));
    }

    public static void main(String[] args) {
        String s1 = "egd";
        String s2 = "adg";
// Output: true (e maps to a, g maps to d)
        System.out.println(isIsomorphic(s1, s2));
        System.out.println(isomorphic2(s1, s2));
        String s3 = "foo";
        String s4 = "bar";
// Output: false (o maps to both a and r)
        System.out.println(isIsomorphic(s3, s4));
        System.out.println(isomorphic(s3, s4));
//
//        String s5 = "paper";
//        String s6 = "title";
//// Output: true (p maps to t, a maps to i, e maps to l, r maps to e)
//        System.out.println(twoHashMap(s5, s6));
//        System.out.println(areIsomorphic(s5, s6));
    }

    /**
     * eg:
     * foo → bar ❌ (o→r and o→a conflict)
     * bar → foo(that's why we need second mapped check)
     * we use both two map, but use the similar logic, which is O(1) time, about "can not one-to-many",
     * in hash map. since although technic we can use only one map, for each key, we loop up all the values,
     * but this is uglier(complicate).
     *
     * core concept:
     * map f->b
     * map o->a
     * after this, when we try to map
     * the third 'o' to 'r', we found that,'o' already mapped before, but the mapped value not equals 'r'.
     * so failed. or on the contrary.
     *
     * map b->f
     * map a->0
     * after this, when we try to map
     * the 'r' to second '0', we found that,'a' already mapped before, but the mapped value not equals 'r'.
     *
     * time:O(n)
     * space:O(1): note that, the map size is at most 256 for asc,not relay on length n.
     * @param a
     * @param b
     * @return
     */
    // eg. foo->bar
    static boolean isIsomorphic(String a, String b) {
        if(a.length()!=b.length()) return false;

        // f->b
        // o->a
        //o->r(x)

        Map<Character,Character> mapS = new HashMap<>();
        // eg. bar->foo. we also need to try to map one to many.
        Map<Character,Character> mapT = new HashMap<>();

        int len = a.length();
        for(int i=0; i<len;i++) {
            // take out the chars.
            Character s = a.charAt(i);
            Character t = b.charAt(i);

            // key mapped before, but the corresponding value not equals current value.
            if((mapS.containsKey(s)&&!t.equals(mapS.get(s)))
                    ||(mapT.containsKey(t)&&!s.equals(mapT.get(t)))) {
                return false;
            }

            mapS.put(s, t);
            mapT.put(t,s);
        }
        return true;
    }

    // egg vs add
// at the "same position",  both appeared with the same "visit position".
// first, e vs a both at position 0, they didn't visit before, absolutely they can map one to another directly.
// secondly, g vs d at position 1,they didn't visit before, absolutely they can map one to another directly.
// third, g vs d at position 2, they both visited at "visited position 1",so can map one to another.
    static boolean isomorphic(String a, String b) {
        if(a.length()!=b.length()) return false;

        // record their visited position.(init 0 means not visited)
        int[] visitedA = new int[256];
        int[] visitedB = new int[256];

        for(int i=0; i<a.length();i++) {
            char charA = a.charAt(i);
            char charB = b.charAt(i);

            // foo vs bar
            // but since the current character might be visited before, we need to check whether they appeared with the same pattern.
            if(visitedA[charA]!=visitedB[charB]) return false;

            // if allow mapped, then record visited position.
            // record the visited position(+1 to distinct with "not visited yet").
            visitedA[charA] = i+1;
            visitedB[charB] = i+1;
        }

        return true;
    }

    static boolean isomorphic2(String s, String t) {
        if(s.length()!=t.length()) return false;

        int len=s.length();

        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();

        for(int i=0; i<len;i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if(mapS.getOrDefault(charS,0)!=mapT.getOrDefault(charT,0))
                return false;

            mapS.put(charS,i);
            mapT.put(charT,i);
        }

        return true;
    }

    /*
egg
add
*/
    static boolean isomorphic3(String s, String t) {
        if(s.length()!=t.length()) return false;

        int len = s.length();

        int[] visiteS = new int[256];
        int[] visiteT = new int[256];
        for(int i=0;i<len;i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if(visiteS[charS]!=visiteT[charT]) return false;

            visiteS[charS]=i+1;
            visiteT[charT]=i+1;
        }

        return true;
    }
}
