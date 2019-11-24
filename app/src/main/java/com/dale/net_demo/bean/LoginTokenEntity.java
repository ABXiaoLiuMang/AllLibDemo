package com.dale.net_demo.bean;

import java.io.Serializable;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public class LoginTokenEntity {

    /**
     * status : 1
     * msg : 登录成功！
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTMuMTI1LjI0LjEzNTo4MDk0L2FwaS92MS9hdXRodG9rZW4iLCJpYXQiOjE1MjUxNzEwMTEsImV4cCI6MTUyNTE4MTgxMSwibmJmIjoxNTI1MTcxMDExLCJqdGkiOiIyYzNyZ2RvM0IyT3RPWGRpIiwic3ViIjo1Mjh9.pnKehHTmQiAsNELGj_LQOWjrxWZ0UvhrmOz8kLzm-_A","userinfo":{"id":528,"phone":"","username":"maomao","avatar":"http://13.125.24.135:8093/uploads/2018/03/07/2018030715060122570.png"}}
     * time_consumed : 0.04585599899292
     */

    private int status;
    private String msg;
    private DataBean data;
    private double time_consumed;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginTokenEntity{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", time_consumed=" + time_consumed +
                ", code='" + code + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public double getTime_consumed() {
        return time_consumed;
    }

    public void setTime_consumed(double time_consumed) {
        this.time_consumed = time_consumed;
    }

    public static class DataBean implements Serializable {
        /**
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTMuMTI1LjI0LjEzNTo4MDk0L2FwaS92MS9hdXRodG9rZW4iLCJpYXQiOjE1MjUxNzEwMTEsImV4cCI6MTUyNTE4MTgxMSwibmJmIjoxNTI1MTcxMDExLCJqdGkiOiIyYzNyZ2RvM0IyT3RPWGRpIiwic3ViIjo1Mjh9.pnKehHTmQiAsNELGj_LQOWjrxWZ0UvhrmOz8kLzm-_A
         * userinfo : {"id":528,"phone":"","username":"maomao","avatar":"http://13.125.24.135:8093/uploads/2018/03/07/2018030715060122570.png"}
         */

        private String token;
        private UserinfoBean userinfo;

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

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean implements Serializable{
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
}
