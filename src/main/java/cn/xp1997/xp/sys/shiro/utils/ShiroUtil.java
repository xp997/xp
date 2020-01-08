package cn.xp1997.xp.sys.shiro.utils;

import cn.xp1997.xp.sys.shiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * shiro工具类
 */
public class ShiroUtil {

    /**
     *
     * @param password 密码
     * @param salt 盐值
     * @param hashCount 加密次数
     * @return 加密后的密码
     */
    public static String saltMd5(String password, String salt, int hashCount){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash result = new SimpleHash(hashAlgorithmName, password, byteSalt, hashCount);
        return result.toString();

    }

    /**
     * 默认1024 次加密
     * @param password 密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    public static String saltMd5(String password, String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        SimpleHash result = new SimpleHash(hashAlgorithmName, password, byteSalt, 1024);
        return result.toString();
    }

    /**
     * 获取当前登录用户信息
     */
    public static SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 判断登录状态
     */
    public static boolean isLogin(){
        return SecurityUtils.getSubject().isAuthenticated();
    }


}
