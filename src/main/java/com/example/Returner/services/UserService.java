package com.example.Returner.services;

import com.example.Returner.models.User;
import com.example.Returner.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final OtpService otpService;
    private final PasswordEncoder encoder;

    @Transactional
    public void resetPassword(String email, String otp, String newPassword) {
        // تحقق من الـ OTP
        boolean valid = otpService.verifyOtp(email, otp);
        if (!valid) throw new RuntimeException("Invalid or expired OTP");

        // جيب الـ User وغير الباسورد
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
    }
}