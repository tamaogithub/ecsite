package com.portfolio.ecsite.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(
                        user -> new CustomUserDetails(
                                user.getUsername(),
                                user.getPassword(),
                                //Listの一つの要素（ADMINかUSER）を取得するようにプライベートメソッドに切り出す
                                toGrantedAuthorityList(user.getAuthority())
                        )
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Given username is not found.(username = '" + username + "')'"
                        )
                );
    }

    private List<GrantedAuthority> toGrantedAuthorityList(User.Authority authority) {
        //要素（USERかADMIN）が一つのListを生成して返す
        return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
    }
}
