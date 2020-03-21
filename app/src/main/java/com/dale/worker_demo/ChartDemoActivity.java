package com.dale.worker_demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.libdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ChartDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DataEntity> dataEntities;
    private MChartAdapter mChartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        creatData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //水平方向布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mChartAdapter = new MChartAdapter(dataEntities);
        View head = View.inflate(this,R.layout.item_chart_head,null);
//        mChartAdapter.addHeaderView(head);
        recyclerView.setAdapter(mChartAdapter);
        recyclerView.addItemDecoration(new MChartItemDecoration());


    }

    private void creatData() {
        dataEntities = new ArrayList<>();
        for (int i=1;i<=12;i++) {
            dataEntities.add(new DataEntity(i, i * 20 + 10, i * 20 + 40));
        }
    }

    private class MChartAdapter extends BaseQuickAdapter<DataEntity, BaseViewHolder> {
        public MChartAdapter(@Nullable List<DataEntity> data) {
            super(R.layout.item_chart, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, DataEntity item) {
            TextView tv_value = helper.getView(R.id.tv_value);
            tv_value.setHeight(item.bar);
        }


    }

    private class MChartItemDecoration extends RecyclerView.ItemDecoration {

        private  float lineWidth;
        private  float xTextsize;

        int radiusOvil;
        //Y 轴区域有效的坐标范围
        int valueMaxY;

        int itemMargin;


        int xColor = Color.parseColor("#999999");
        int ovilColor = Color.parseColor("#F15824");
        int selColor = Color.parseColor("#0B6286");
        int popColor = Color.parseColor("#e5343C45");
        private Paint paint;

        public MChartItemDecoration() {
            lineWidth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            radiusOvil=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            xTextsize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
            valueMaxY=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
            itemMargin=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(lineWidth);
            paint.setTextSize(xTextsize);
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(canvas, parent, state);

            int childCount = parent.getChildCount();
            int preX = 0;
            int preY = 0;
            for (int i = 0; i < childCount; i++) {
                View childAt = parent.getChildAt(i);
                int px = childAt.getLeft() + childAt.getWidth() / 2;
                int py = parent.getHeight();//在RecyclerView的底部绘制，坐标系以RecyclerView的区域为参考
                //绘制X轴坐标
                paint.setColor(xColor);
                drawXValue(canvas, paint, (parent.getChildLayoutPosition(childAt) + 1) + "月", px, py);
                DataEntity dataEntity = dataEntities.get(parent.getChildLayoutPosition(childAt));
                py = valueMaxY-dataEntity.fold;
                //绘制圆圈
                paint.setColor(ovilColor);
                canvas.drawOval(new RectF(px - radiusOvil, py - radiusOvil, px + radiusOvil, py + radiusOvil), paint);
                if (i > 0) {
                    canvas.drawLine(preX, preY, px, py, paint);
                }
                //记录当前的圆圈的坐标点，避免画线的时候再计算
                preX = px;
                preY = py;

            }
        }
        private void drawXValue(Canvas canvas, Paint paint, String value, int pX, int pY) {
            Rect rect = new Rect();
            paint.getTextBounds(value, 0, value.length(), rect);
            paint.setTextAlign(Paint.Align.CENTER);
            //centerY是负数
            canvas.drawText(value, pX, pY + rect.centerY(), paint);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(itemMargin, 0, itemMargin, 0);
        }
    }


    private class DataEntity {
        //月份
        int mouth;
        //柱状图数值
        int bar;
        //折线图数值
        int fold;

        public DataEntity(int mouth, int bar, int fold) {
            this.mouth = mouth;
            this.bar = bar;
            this.fold = fold;
        }
    }
}
