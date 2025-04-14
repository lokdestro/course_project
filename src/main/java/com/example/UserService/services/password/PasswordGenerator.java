package com.example.UserService.services.password;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Service
public class PasswordGenerator {

    public static String generatePasswordFromEmail(String email) {
        try {
            // 1. Нормализация email (приведение к нижнему регистру и удаление пробелов)
            String normalizedEmail = email.toLowerCase().trim();

            // 2. Добавление "соли" к email
            String saltedEmail = addSalt(normalizedEmail);

            // 3. Хеширование с помощью SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(saltedEmail.getBytes(StandardCharsets.UTF_8));

            // 4. Преобразование хеша в Base64 строку
            String base64Hash = Base64.getEncoder().encodeToString(hashBytes);

            // 5. Применение правил для создания надежного пароля
            return applyPasswordRules(base64Hash);

        } catch (NoSuchAlgorithmException e) {
            // В случае ошибки используем fallback-генерацию
            return fallbackPasswordGeneration(email);
        }
    }

    private static String addSalt(String email) {
        // Добавляем статическую соль и динамическую часть (длина email)
        return "SALT_" + email.length() + "_" + email + "_SECURE_SALT";
    }

    private static String applyPasswordRules(String baseString) {
        // 1. Берем первые 16 символов
        String shortened = baseString.substring(0, Math.min(16, baseString.length()));

        // 2. Заменяем специальные символы
        shortened = shortened.replace('=', '!')
                .replace('/', '$')
                .replace('+', '@');

        // 3. Гарантируем наличие цифр и заглавных букв
        Random random = new Random();
        char[] chars = shortened.toCharArray();

        // Гарантированно добавляем хотя бы одну цифру
        chars[random.nextInt(chars.length)] = (char) ('0' + random.nextInt(10));

        // Гарантированно добавляем хотя бы одну заглавную букву
        chars[random.nextInt(chars.length)] = (char) ('A' + random.nextInt(26));

        return new String(chars);
    }

    private static String fallbackPasswordGeneration(String email) {
        // Fallback метод на случай проблем с SHA-256
        return "Pwd" + email.hashCode() + "!";
    }

    // Пример использования
    public static void main(String[] args) {
        String email = "user@example.com";
        String password = generatePasswordFromEmail(email);
        System.out.println("Generated password: " + password);
        // Пример вывода: Generated password: 3A$bC9@XyZ!5qR7T
    }
}
