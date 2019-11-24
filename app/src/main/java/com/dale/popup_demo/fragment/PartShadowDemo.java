package com.dale.popup_demo.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.libdemo.R;
import com.dale.popup_demo.custom.CustomDrawerPopupView;
import com.dale.popup_demo.custom.CustomPartShadowPopupView;
import com.dale.popup_demo.custom.CustomPartShadowPopupView2;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.widget.VerticalRecyclerView;

import java.util.ArrayList;

/**
 * Description: 局部阴影的示例
 * Create by dance, at 2018/12/21
 */
public class PartShadowDemo extends BaseFragment implements View.OnClickListener {
    View ll_container;
    VerticalRecyclerView recyclerView;
    private CustomPartShadowPopupView popupView;

    private CustomDrawerPopupView drawerPopupView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_part_shadow_demo;
    }

    @Override
    public void init(View view) {
        ll_container = view.findViewById(R.id.ll_container);
        recyclerView = view.findViewById(R.id.recyclerView);

        view.findViewById(R.id.tv_all).setOnClickListener(this);
        view.findViewById(R.id.tv_price).setOnClickListener(this);
        view.findViewById(R.id.tv_sales).setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);
        view.findViewById(R.id.tv_filter).setOnClickListener(this);
        view.findViewById(R.id.tvCenter).setOnClickListener(this);
        view.findViewById(R.id.tvCenter2).setOnClickListener(this);

        drawerPopupView = new CustomDrawerPopupView(getContext());

        final ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }

        BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1,data) {
            @Override
            protected void convert(BaseViewHolder holder, String s) {
                int position = holder.getLayoutPosition();
                holder.setText(android.R.id.text1, "长按我试试 - " + position);
                //必须要在事件发生之前就watch
                final XPopup.Builder builder = new XPopup.Builder(getContext()).watchView(holder.itemView);
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        builder.asAttachList(new String[]{"置顶", "编辑", "删除"}, null,0,10, new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                toast(text);
                            }
                        }).show();
                        return true;
                    }
                });
            }
        };

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toast(data.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showPartShadow(final View v){
//        if(popupView!=null && popupView.isShow())return;
        if(popupView==null){
            popupView = (CustomPartShadowPopupView) new XPopup.Builder(getContext())
                    .atView(v)
//                    .isCenterHorizontal(true)
//                    .autoOpenSoftInput(true)
//                    .offsetX(200)
//                .dismissOnTouchOutside(false)
                    .setPopupCallback(new SimpleCallback() {
                        @Override
                        public void onShow() {
                            toast("显示了");
                        }
                        @Override
                        public void onDismiss() {
//                            popupView = null;
                        }
                    })
                    .asCustom(new CustomPartShadowPopupView(getContext()));
        }

        popupView.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
            case R.id.tv_price:
            case R.id.tv_sales:
                showPartShadow(v);
                break;
            case R.id.tv_filter:
                new XPopup.Builder(getContext())
                        .popupPosition(PopupPosition.Right)//右边
                        .hasStatusBarShadow(true) //启用状态栏阴影
                        .asCustom(drawerPopupView)
                        .show();
                break;
            case R.id.tv_select:
                new XPopup.Builder(getContext())
                        .atView(v)
                        .asCustom(new CustomPartShadowPopupView(getContext()))
                        .show();
                break;
            case R.id.tvCenter:
                new XPopup.Builder(getContext())
                        .atView(v)
                        .popupPosition(PopupPosition.Top)
                        .asCustom(new CustomPartShadowPopupView2(getContext()))
                        .show();
                break;
            case R.id.tvCenter2:
                new XPopup.Builder(getContext())
                        .atView(v)
                        .popupPosition(PopupPosition.Bottom)
                        .asCustom(new CustomPartShadowPopupView2(getContext()))
                        .show();
                break;
        }
    }
}
