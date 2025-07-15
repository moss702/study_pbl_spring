package com.ikkikki.demo.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encode(String rawPassword) {
//        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
        return BCrypt.withDefaults().hashToString(10, rawPassword.toCharArray());
        //몇번 암호화 할지(단계높이기). 라이브러리 수정은 불가하니 그냥 숫자 때려넣어준다.
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }

}