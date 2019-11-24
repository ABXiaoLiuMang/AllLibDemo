package com.dale.pickerviewlib.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.dale.pickerviewlib.builder.OptionsPickerBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * 省市区选择器
 */
public class CityPickerView {

    private final Context mContext;
    // 省数据集合
    private ArrayList<String> mListProvince = new ArrayList<>();
    // 市数据集合
    private ArrayList<ArrayList<String>> mListCity = new ArrayList<>();
    // 区数据集合
    private ArrayList<ArrayList<ArrayList<String>>> mListArea = new ArrayList<>();
    private JSONArray mJsonArray;
    public OnCitySelectListener mOnCitySelectListener;
    private OptionsPickerView pvOptions,pvOptionsDialog,cityOptions;
    private int selectProvince=0;
    private String initProvince = "";


    public CityPickerView(Context context) {
        mContext = context;
        init();
    }

    public CityPickerView(Context context,String province) {
        mContext = context;
        this.initProvince = province;
        init();
    }

    private void init(){
        // 初始化Json对象
        initJsonData();
        // 初始化Json数据
        initJsonDatas();
        initCityData();
        pvOptions.setSelectOptions(selectProvince);
    }

    private void initCityData() {
        pvOptions = new OptionsPickerBuilder(mContext, (option1, option2, option3, v) -> {
            //返回的分别是三个级别的选中位置
            if(mOnCitySelectListener != null){
                if(mListCity.size() > option1 && mListCity.get(option1).size() > option2){
                    if(mListArea.size() > option1 && mListArea.get(option1).size() > option2
                            && mListArea.get(option1).get(option2).size() > option3){
                        String prov = mListProvince.get(option1);
                        String city = mListCity.get(option1).get(option2);
                        String area ="";
                        if(mListArea.get(option1).get(option2).size()>0){
                            area = mListArea.get(option1).get(option2).get(option3);
                        }

                        mOnCitySelectListener.onCitySelect(prov, city, area);
                    }
                }
            }
        })
                .setTitleText("选择开始地区")
                .setLabels("", "", "")//设置选择的三级单位
                .build();
        pvOptions.setPicker(mListProvince, mListCity, mListArea);//添加数据源

    }

    /**
     * 选择省份
     */
    public void initProvinceSelect() {
        pvOptionsDialog = new OptionsPickerBuilder(mContext, (option1, option2, option3, v) -> {
            selectProvince = option1;
            //返回的分别是三个级别的选中位置
            if(mOnCitySelectListener != null){
                String prov = mListProvince.get(option1);
                mOnCitySelectListener.onCitySelect(prov, "", "");
            }
        })
                .setTitleText("选择城市")//标题
                .setLabels("", "", "")//设置选择的三级单位
                .build();

        pvOptionsDialog.setPicker(mListProvince, null, null);//添加数据源
    }

    /**
     * 选择城市
     */
    public void initCitySelect() {
        cityOptions = new OptionsPickerBuilder(mContext, (option1, option2, option3, v) -> {
            //返回的分别是三个级别的选中位置
            if(mOnCitySelectListener != null){
                String city = mListCity.get(selectProvince).get(option1);
                mOnCitySelectListener.onCitySelect("", city, "");
            }
        })
                .setTitleText("选择城市")//标题
                .setLabels("", "", "")//设置选择的三级单位
                .build();

        cityOptions.setPicker(mListCity.get(selectProvince), null, null);//添加数据源
    }

    /** 从assert文件夹中读取省市区的json文件，然后转化为json对象 */
    private void initJsonData() {
        AssetManager assets = mContext.getAssets();
        try {
            InputStream is = assets.open("city.json");
            byte[] buf = new byte[is.available()];
            is.read(buf);
            String json = new String(buf, "UTF-8");
            mJsonArray = new JSONArray(json);
            is.close();
        } catch (Exception e) {
        }
    }

    /** 初始化Json数据，并释放Json对象 */
    private void initJsonDatas(){
        try {
            int count = mJsonArray.length();
            for (int i = 0; i <count ; i++) {
                JSONObject jsonP = mJsonArray.getJSONObject(i);// 获取每个省的Json对象
                String province = jsonP.getString("name");
                if(province.startsWith(initProvince)){
                    selectProvince = i;
                }
                ArrayList<String> options2Items_01 = new ArrayList<>();
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<>();
                JSONArray jsonCs = jsonP.getJSONArray("sub");
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonC = jsonCs.getJSONObject(j);// 获取每个市的Json对象
                    String city = jsonC.getString("name");
                    options2Items_01.add(city);// 添加市数据
                    ArrayList<String> options3Items_01_01 = new ArrayList<>();
                    if(!jsonC.isNull("sub")){
                        JSONArray jsonAs = jsonC.getJSONArray("sub");
                        for (int k = 0; k < jsonAs.length(); k++) {
                            JSONObject jsonD = jsonAs.getJSONObject(k);// 获取每个市的Json对象
                            options3Items_01_01.add(jsonD.getString("name"));// 添加区数据
                        }
                    }
                    options3Items_01.add(options3Items_01_01);

                }
                mListProvince.add(province);// 添加省数据
                mListCity.add(options2Items_01);
                mListArea.add(options3Items_01);
            }
        } catch (JSONException e) {
        }
        mJsonArray = null;
    }




    public interface OnCitySelectListener{
        void onCitySelect(String prov, String city, String area);
    }

    public void setOnCitySelectListener(OnCitySelectListener listener) {
        this.mOnCitySelectListener = listener;
    }

    public void show(){
        pvOptions.show();
    }

    public void showPovince(){
        pvOptionsDialog.show();
    }

    public void showCity(){
        cityOptions.show();
    }

}
