package com.cwc.common.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cwc.demo.dao.MyBatisDao;
import com.cwc.demo.dao.PermissionDao;
import com.cwc.demo.model.Permission;
import com.cwc.demo.model.RoleInfo;
import com.cwc.demo.model.UserInfo;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired  //数据库服务类
    private MyBatisDao suserService;
    @Autowired
    private PermissionDao dao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        UserInfo user = suserService.findUserByName(userName); 
        
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }else{
        	List<Permission> permissions = dao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
                }
            }
      /*  user.setRoles(suserService.findRolesByName(userName));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        // SecurityUser实现UserDetails并将SUser的Email映射为username
        //SecurityUser securityUser = new SecurityUser(user);
        for(RoleInfo role : user.getRoles()){
        	 authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }*/
              return new User(user.getUserName(),user.getPassword(),grantedAuthorities); 

              }
        }

}