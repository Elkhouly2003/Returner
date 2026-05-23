package com.example.Returner.services;

import com.example.Returner.models.PasswordResetToken;
import com.example.Returner.repositories.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {


    private final PasswordResetTokenRepository tokenRepo;

    private final EmailService emailService;

    @Transactional
    public void generateAndSendOtp(String email) {
        // امسح الـ OTP القديم لو موجود
        tokenRepo.deleteByEmail(email);

        // توليد OTP عشوائي 6 أرقام
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        // حفظ في DB
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);
        tokenRepo.save(token);

        // إرسال الإيميل
        emailService.sendOtpEmail(email, otp);
    }

    @Transactional
    public boolean verifyOtp(String email, String otp) {
        Optional<PasswordResetToken> token = tokenRepo.findByEmailAndOtp(email, otp);
        
        if (token.isEmpty()) return false;

        System.out.println("OTP is valid");
        PasswordResetToken t = token.get();
        // تحقق من الـ expiry والاستخدام
        if (t.isUsed() || t.getExpiresAt().isBefore(LocalDateTime.now())) return false;

        // اعمله used عشان متتعملش استخدامه تاني
        t.setUsed(true);
        tokenRepo.save(t);

        return true;
    }
}