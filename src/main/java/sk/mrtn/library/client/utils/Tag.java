package sk.mrtn.library.client.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by klaun on 22/09/16.
 *
 * Suitable for larger projects just not
 * to get lost within code.
 *
 * Example of use:
 *
 * object uses states represented by int, and descendants
 * creates custom states of object
 * If states are created by Tag.get("name"), then code
 * becomes clear for developer.
 * Names of states can be easily retrieved and
 * used for logging purposes, for develop purposes.
 *
 */
public class Tag {

    private static int gen;
    private static Map<Integer, String> tagNames;
    private static Map<String, Integer> tagsByName;

    static {
        tagNames = new HashMap<>();
        tagsByName = new HashMap<>();
    }

    public static int get(final String name) {
        Integer tag = tagsByName.get(name);
        if (tag == null) {
            tag = ++gen;
            tagsByName.put(name, tag);
            tagNames.put(tag, name);
        }
        return tag;
    }

    public static String getName(final int tag) {
        return tagNames.get(tag);
    }
}
