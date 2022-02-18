package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }


    @Value
    public static class AuthInfo {
        private String login;
        private String password;


        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        private String code;

        public String getCode() {
            return code;
        }
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");

    }

    @Value
    public static class NumberCardOne {
        private String number1;

        public String getNumber() {
            return number1;
        }
    }

    public static NumberCardOne getInnerCardOne(AuthInfo authInfo) {
        return new NumberCardOne("5559 0000 0000 0002");
    }

    @Value
    public static class NumberCardTwo {
        private String number2;

        public String getNumber() {
            return number2;
        }
    }
        public static NumberCardTwo getInnerCardTwo(AuthInfo authInfo) {
            return new NumberCardTwo("5559 0000 0000 0001");
        }
    }
