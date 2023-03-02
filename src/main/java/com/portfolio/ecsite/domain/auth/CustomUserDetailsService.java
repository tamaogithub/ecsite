package com.portfolio.ecsite.domain.auth;

import com.portfolio.ecsite.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    // UserRepositoryをDIする（@Autowired不要）
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // loadUserByUsername()をオーバーライド
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(
                        user -> new CustomUserDetails(
                                user.getUserName(),
                                user.getPassword(),
                                //Listの一つの要素（ADMINかMAKERかSHOP）を取得するようにプライベートメソッドに切り出す
                                toGrantedAuthorityList(user.getAuthority())
                        )
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Given username is not found.(username = '" + username + "')'"
                        )
                );
    }

    private List<GrantedAuthority> toGrantedAuthorityList(String authority) {
        //要素（ADMINかMAKERかSHOP）が一つのListを生成して返す
        return Collections.singletonList(new SimpleGrantedAuthority(authority));
    }
}
