package com.dale.popup_demo;


import com.dale.popup_demo.fragment.BaseFragment;

/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public class PageInfo {
    public String title;
    public BaseFragment fragment;

    public PageInfo(String title, BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
