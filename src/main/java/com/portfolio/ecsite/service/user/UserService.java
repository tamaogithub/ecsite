package com.portfolio.ecsite.service.user;


import com.portfolio.ecsite.repository.user.UserRecord;
import com.portfolio.ecsite.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserEntity> findAll(int limit ,long offset){
    //取得したList<itemRecord>をstream()でItemEntityに変換し、最後にList<ItemEntity>に変換する
    return userRepository.findAll(limit, offset)
            .stream()
            .map(record -> new UserEntity(
                    record.getId(),
                    record.getUserName(),
                    record.getPassword(),
                    record.getAuthority(),
                    record.getCampany(),
                    record.getAddress(),
                    record.getPhone()))
            .collect(Collectors.toList());
    }
   @PreAuthorize("hasAuthority('ADMIN')")
    public void create(String userName, String password, String authority, String campany, String address, String phone) {
//        //パスワードをエンコード
        var encodedPassword = passwordEncoder.encode(password);
        var record = new UserRecord(null,userName,encodedPassword,authority,campany,address,phone);
        userRepository.insert(record);
    }

    @Transactional
    public UserEntity find(Long userId) {
        // レコードが1件も取得できない事も考慮にいれて Optional を返すようにする
        return userRepository.select(userId)
                .map(record -> new UserEntity(
                        record.getId(),
                        record.getUserName(),
                        record.getPassword(),
                        record.getAuthority(),
                        record.getCampany(),
                        record.getAddress(),
                        record.getPhone()))
                .orElseThrow(() -> new UserEntityNotFoundException(userId));
    }

    public int findTotal() {
        return userRepository.selectListCount();
    }

    @Transactional
    public void update(Long userId, String userName,String password, String authority, String campany, String address, String phone) {
        //パスワードをエンコード
        var encodedPassword = passwordEncoder.encode(password);
        userRepository.update(userId, userName, encodedPassword, authority, campany, address, phone);
    }
}