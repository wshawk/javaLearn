package com.hawk.example.spring_security.config;

import com.hawk.example.spring_security.model.UserDO;
import com.hawk.example.spring_security.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.config
 * @desc
 * @date 2021/10/27
 */
@Component
public class ProjectUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;
    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userService.getUserByUserName(username);
        if(userDO == null){
            throw new UsernameNotFoundException("账号不存在");
        }
        Set<String> authritiesSet = new HashSet<>(userDO.getPermissions().size() + userDO.getRoles().size());

        authritiesSet.addAll(userDO.getPermissions());
        // 这里需要注意，security里面角色和权限都是存在一个字段里面的，但是其角色会自动加上ROLE_前缀来将角色和权限进行区分，
        // 以便在进行判断是否有某角色时可以进行判断，但是我们一般使用是可以不用加前缀的，如@PreAuthorize("hasRole('role2')")，但是你加也没问题
        authritiesSet.addAll(userDO.getRoles().stream().map(role -> "ROLE_" + role).collect(Collectors.toList()));

        UserDetails userDetails = User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(authritiesSet.toArray(new String[authritiesSet.size()]))
                .build();
        return userDetails;
    }
}
