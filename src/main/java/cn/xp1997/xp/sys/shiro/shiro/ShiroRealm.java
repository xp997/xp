package cn.xp1997.xp.sys.shiro.shiro;



import cn.xp1997.xp.sys.shiro.entity.SysAuth;
import cn.xp1997.xp.sys.shiro.entity.SysRole;
import cn.xp1997.xp.sys.shiro.entity.SysUser;
import cn.xp1997.xp.sys.shiro.service.impl.SysAuthServiceImpl;
import cn.xp1997.xp.sys.shiro.service.impl.SysRoleServiceImpl;
import cn.xp1997.xp.sys.shiro.service.impl.SysUserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Autowired
    private SysAuthServiceImpl sysAuthService;

    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user=(SysUser) principals.getPrimaryPrincipal();
        System.out.println("=========权限");
        //查询角色列表
        List<SysRole> roleListByUser = sysRoleService.getRoleListByUser(user.getId());
        for (SysRole sysRole : roleListByUser) {
            authorizationInfo.addRole(sysRole.getRoleName());
            //查询权限列表
            List<SysAuth> authListByRole = sysAuthService.getAuthListByRole(sysRole.getId());
            for (SysAuth sysAuth : authListByRole) {
                //赋予权限
                authorizationInfo.addStringPermission(sysAuth.getAuthUrl());
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("========身份");
        SimpleAuthenticationInfo info;

        //将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //从转换后的token中获取用户名
        String username = upToken.getUsername();

        //查询数据库，得到用户
        SysUser user = sysUserService.getUserByUsername(username);
        if(user == null){
            return null;
        }
        //得到加密密码的盐值
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());

//        //得到盐值加密后的密码：只用于方便数据库测试，后期不会用到。
          //String md = new SimpleHash("MD5",upToken.getPassword(),username,1024).toString();
//        System.out.println(md);
//        logger.info("盐值加密后的密码："+md);

        info = new SimpleAuthenticationInfo(
                user, //用户
                user.getPassword(), //密码
                salt, //加密的盐值
                getName()  //realm name
        );
        return info;
    }

}
