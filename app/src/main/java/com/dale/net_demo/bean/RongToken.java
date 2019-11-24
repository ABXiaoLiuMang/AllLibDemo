package com.dale.net_demo.bean;

/**
 * create by Dale
 * create on 2019/8/15
 * description: 融云token
 */
public class RongToken {

    /**
     * code : 200
     * userId : 1111jdldjasfklj
     * token : Dj6Xtg44kOdzAN/F3BTDe3iqKNyiC438+Xh7zvoR1h2kw0of9/QcCh9J8oe8QG5H1AR5mzTpBX2Ie4KIw/HOF+H1Qm2ZRzVp
     */

    private int code;
    private String userId;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RongToken{" +
                "code=" + code +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
