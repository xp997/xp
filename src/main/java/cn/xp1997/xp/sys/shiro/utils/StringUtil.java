package cn.xp1997.xp.sys.shiro.utils;

import java.util.Arrays;

public class StringUtil {


    public static String upFirstCap(String parm){
        char[] chars = parm.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }
        return Arrays.toString(chars);
    }

}
