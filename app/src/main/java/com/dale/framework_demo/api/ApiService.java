package com.dale.framework_demo.api;

import com.dale.net.NetCall;
import com.dale.net.http.GET;

/**
 * create by Dale
 * create on 2019/8/13
 * description:
 */
public interface ApiService {

    String API_HOST = "http://api2.ibaozou.com/";

    /**
     * 暴走漫画首页
     * @return
     */
    @GET("api/v8/home")
    NetCall<String> getHome();

    /**
     * 暴走漫画全部
     * @return
     */
    @GET("api/v8/series")
    NetCall<String> getSeries();

    /**
     * 暴走漫画全部
     * @return
     */
    @GET("api/v8/cartoons")
    NetCall<String> getArticles();

    /**
     * 暴走漫画单个item章节列表
     * @return
     */
    @GET("api/v8/series/")
    NetCall<String> getArticlesItems();

    /**
     * 暴走漫画内容详情
     * @return
     */
    @GET("api/v8/articles/")
    NetCall<String> getArticlesInfo();

    /**
     * 暴走漫画全部章节
     * @return
     */
    @GET("api/v8/series/")
    NetCall<String> getArticlesAll();
}
