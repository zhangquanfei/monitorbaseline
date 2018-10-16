package security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.PostConstruct;


public class CustomRealm extends AuthorizingRealm {

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        System.out.println("come in  认证方法");

        //token的用户名必须和被认证的一致。也就是必须有。可以之前可以做个判断，我没做。token取出来的用户名在数据库查不来肯定就错了。不用再认证了。
        String username = (String) token.getPrincipal();
        // 这个密码是数据库中查到的。将这个密码与token的密码交给shiro比对。
      //  String password = "123456";
        //这个是从数据库查出来的加密密码。他是由密码123456，盐值abcd，迭代次数1024次算出来存进去的。
        String password = "155a3130061c2afe406dded087f4bec5";
        /**
         *
         * String password = "155a3130061c2afe406dded087f4bec5";这个盐值的计算方法入如下：
         *   String source = "111111";
         String salt = "qwerty";
         int hashInterations = 1024;
         //方法一
         Md5Hash md5Hash = new Md5Hash(source,salt,hashInterations);
         String passworda_MD5 = md5Hash.toString();
         System.out.println(passworda_MD5);

         //方法二
         SimpleHash simpleHash = new SimpleHash("md5",source,salt,hashInterations);
         System.out.println(simpleHash.toString());
         *
         */
        //设置盐值.盐值是从数据库查到的
        String source = "qwerty";
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                username, password,ByteSource.Util.bytes(source) ,this.getName());
        return simpleAuthenticationInfo;
    }
    // @PostConstruct相当于bean节点的init-method配置。初始化这个方法
    /**
     *
     */
    @PostConstruct
    public void setCredentialMatcher(){
        HashedCredentialsMatcher credentialsMatcher =  new HashedCredentialsMatcher();
        //加密方法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        setCredentialsMatcher(credentialsMatcher);
    }
    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前用户信息，就是输入的用户名username。
        String principle = (String) principals.getPrimaryPrincipal();
        System.out.println("come in  授权方法 principle==="+principle);
        if("admin".equals(principle)){
            //添加用户权限信息
                info.addRole("admin");
        }
        info.addRole("user");
        //授权器就会根据添加的用户和shiro的roles[user]的user比对。一样就可以访问roles[user]前面的连接。
        return info;
    }

}
