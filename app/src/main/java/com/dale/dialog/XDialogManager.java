package com.dale.dialog;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XDialogManager {
    private List<XDialogEntity> queueList = new ArrayList<>();



    /**
     * 普通的显示弹框
     */
    public synchronized void show(View basePopupView) {
        if (basePopupView == null) {
            return;
        }
        queue(basePopupView, 3);
    }

    /**
     * 普通的显示弹窗
     * 但是需要自己加入优先级
     *
     *  不设置优先级 等级，默认 最后 ，暂定位——10
     */
    public synchronized void show(View basePopupView,int priority){
        if(basePopupView ==null){
            return;
        }
        if (priority<1){
            priority = 10;
        }
        queue(basePopupView,priority);
    }


    /**
     * 入队 然后然后 按照优先级排序
     *
     * @param view     要显示的视图
     * @param priority 优先级 0 最高
     */
    private void queue(View view, int priority) {
        XDialogEntity xDialogEntity = XDialogEntity.newEntity(view, priority);
        if (!queueList.contains(xDialogEntity)) {
            queueList.add(xDialogEntity);
            //依附状态更改
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {

                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if(xShowDialogEntity == null){
                        return;
                    }
                    if (xShowDialogEntity.getBasePopupView() == view) {//
                        queueList.remove(xShowDialogEntity);
                    }
                    showSingleDialog();
                }
            });

            Collections.sort(queueList, (o1, o2) -> o1.getPriority() - o2.getPriority());

            showSingleDialog();
        }
    }

    private XDialogEntity xShowDialogEntity;


    /**
     * 先显示优先级高的弹框，然后再隐藏低优先级的弹框
     */
    private synchronized void showSingleDialog() {
        if (queueList.size() == 0) {
            xShowDialogEntity = null;
            return;
        }

        //获取到优先级最高的弹框 0的优先级最高
        xShowDialogEntity = queueList.get(0);
        xShowDialogEntity.show();

        //然后显示 隐藏低优先级弹框
        for (XDialogEntity xDialogEntity : queueList) {
            if (xShowDialogEntity != xDialogEntity) {
                if (xDialogEntity.isShow()) {
                    xDialogEntity.dismiss();
                }
            }
        }


    }


    /**
     * 移除
     * @param xDialogEntity 弹框
     */
    public void remove(XDialogEntity xDialogEntity) {
        queueList.remove(xDialogEntity);
    }


    public boolean delegateBackPress() {
        if (xShowDialogEntity != null && xShowDialogEntity.isShow()) {
            xShowDialogEntity.dismiss();
            return true;
        } else {
            return false;
        }
    }


    //清空数据  如果Dialog在显示的时候  隐藏Dialog
    public void clear() {
        try {
            for (XDialogEntity xDialogEntity : queueList) {
                xDialogEntity.dismiss();
            }
            queueList.clear();
        }catch (Exception e){
        }

        try {
            if (xShowDialogEntity != null){
                xShowDialogEntity.dismiss();
            }
            xShowDialogEntity = null;
        }catch (Exception e){
        }
    }
}

