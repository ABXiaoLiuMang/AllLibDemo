package com.dale.net_demo.bean;

import java.io.Serializable;

/**
 * create by Dale
 * create on 2019/7/23
 * description:
 */
public class TokenData {
    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTMuMTI1LjI0LjEzNTo4MDk0L2FwaS92MS9hdXRodG9rZW4iLCJpYXQiOjE1MjUxNzEwMTEsImV4cCI6MTUyNTE4MTgxMSwibmJmIjoxNTI1MTcxMDExLCJqdGkiOiIyYzNyZ2RvM0IyT3RPWGRpIiwic3ViIjo1Mjh9.pnKehHTmQiAsNELGj_LQOWjrxWZ0UvhrmOz8kLzm-_A
     * userinfo : {"id":528,"phone":"","username":"maomao","avatar":"http://13.125.24.135:8093/uploads/2018/03/07/2018030715060122570.png"}
     */

    private String token;
    private LoginTokenEntity.DataBean.UserinfoBean userinfo;

    @Override
    public String toString() {
        return "DataBean{" +
                "token='" + token + '\'' +
                ", userinfo=" + userinfo +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginTokenEntity.DataBean.UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(LoginTokenEntity.DataBean.UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean implements Serializable {
        /**
         * id : 528
         * phone :
         * username : maomao
         * avatar : http://13.125.24.135:8093/uploads/2018/03/07/2018030715060122570.png
         */

        private int id;
        private String phone;
        private String username;
        private String avatar;

        @Override
        public String toString() {
            return "UserinfoBean{" +
                    "id=" + id +
                    ", phone='" + phone + '\'' +
                    ", username='" + username + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
