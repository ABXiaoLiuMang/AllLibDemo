package com.dale.pickerviewlib.configure;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.dale.pickerviewlib.R;
import com.dale.pickerviewlib.WheelView;
import com.dale.pickerviewlib.listener.CustomListener;
import com.dale.pickerviewlib.listener.OnOptionsSelectChangeListener;
import com.dale.pickerviewlib.listener.OnOptionsSelectListener;
import com.dale.pickerviewlib.listener.OnTimeSelectChangeListener;
import com.dale.pickerviewlib.listener.OnTimeSelectListener;

import java.util.Calendar;

public class PickerOptions {

    //constant
    private static final int PICKER_VIEW_BTN_COLOR_NORMAL = 0xFFaa0000;//默认取消确定按钮颜色值
    private static final int PICKER_VIEW_BG_COLOR_TITLE = 0xFFFFFFFF;//默认标题背景颜色值
    private static final int PICKER_VIEW_COLOR_TITLE = 0xFF333333;//默认标题颜色
    private static final int PICKER_VIEW_BG_COLOR_DEFAULT = 0xFFFFFFFF;//默认滚轮背景颜色值

    public static final int TYPE_PICKER_OPTIONS = 1;
    public static final int TYPE_PICKER_TIME = 2;

    public OnOptionsSelectListener optionsSelectListener;
    public OnTimeSelectListener timeSelectListener;
    public View.OnClickListener cancelListener;

    public OnTimeSelectChangeListener timeSelectChangeListener;
    public OnOptionsSelectChangeListener optionsSelectChangeListener;
    public CustomListener customListener;


    //options picker
    public String label1, label2, label3;//单位字符
    public int option1, option2, option3;//默认选中项
    public int x_offset_one, x_offset_two, x_offset_three;//x轴偏移量

    public boolean cyclic1 = false;//是否循环，默认否
    public boolean cyclic2 = false;
    public boolean cyclic3 = false;

    public boolean isRestoreItem = false; //切换时，还原第一项


    //time picker
    public boolean[] type = new boolean[]{true, true, true, false, false, false};//显示类型，默认显示： 年月日

    public Calendar date;//当前选中时间
    public Calendar startDate;//开始时间
    public Calendar endDate;//终止时间
    public int startYear;//开始年份
    public int endYear;//结尾年份

    public boolean cyclic = false;//是否循环
    public boolean isLunarCalendar = false;//是否显示农历

    public String label_year, label_month, label_day, label_hours, label_minutes, label_seconds;//单位
    public int x_offset_year, x_offset_month, x_offset_day, x_offset_hours, x_offset_minutes, x_offset_seconds;//单位


    public PickerOptions(int buildType) {
        if (buildType == TYPE_PICKER_OPTIONS) {
            layoutRes = R.layout.pickerview_options;
        } else {
            layoutRes = R.layout.pickerview_time;
        }
    }

    //******* general field ******//
    public int layoutRes;
    public ViewGroup decorView;
    public int textGravity = Gravity.CENTER;
    public Context context;

    public String textContentConfirm;//确定按钮文字
    public String textContentCancel;//取消按钮文字
    public String textContentTitle;//标题文字

    public int textColorConfirm = PICKER_VIEW_BTN_COLOR_NORMAL;//确定按钮颜色
    public int textColorCancel = PICKER_VIEW_BTN_COLOR_NORMAL;//取消按钮颜色
    public int textColorTitle = PICKER_VIEW_COLOR_TITLE;//标题颜色

    public int bgColorWheel = PICKER_VIEW_BG_COLOR_DEFAULT;//滚轮背景颜色
    public int bgColorTitle = PICKER_VIEW_BG_COLOR_TITLE;//标题背景颜色

    public int textSizeSubmitCancel = 17;//确定取消按钮大小
    public int textSizeTitle = 18;//标题文字大小
    public int textSizeContent = 18;//内容文字大小

    public int textColorOut = 0xFFa8a8a8; //分割线以外的文字颜色
    public int textColorCenter = 0xFF2a2a2a; //分割线之间的文字颜色
    public int dividerColor = 0xFFcccccc; //分割线的颜色
    public int outSideColor = -1; //显示时的外部背景色颜色,默认是灰色

    public float lineSpacingMultiplier = 2.5f; // 条目间距倍数 默认2.5
    public boolean isDialog;//是否是对话框模式

    public boolean cancelable = true;//是否能取消
    public boolean isCenterLabel = false;//是否只显示中间的label,默认每个item都显示
    public Typeface font = Typeface.DEFAULT;//字体样式
    public WheelView.DividerType dividerType = WheelView.DividerType.FILL;//分隔线类型

}