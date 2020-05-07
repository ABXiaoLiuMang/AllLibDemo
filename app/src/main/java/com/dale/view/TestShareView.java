//package com.dale.view;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.dale.libdemo.R;
//import com.dale.utils.SizeUtils;
//import com.zxing.qrcode.view.QRCodeEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShareView extends LinearLayout implements RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
//
//    int gravity;
//    int ImgPadding;
//    float ImgMinSize;
//    float ImgMaxSize;
//    private Context mContext;
//    private ImageView imgBg;
//    private ImageView imgCode;
//    private TextView shareText;
//    private TextView shareUrl;
//    private RadioGroup radiaGroup;
//    private SeekBar seek_bar;
//    private RecyclerView recyclerView;
//    private int codeSize;
//    private int codeColor;
//
//
//    public ShareView(Context context) {
//        this(context, null);
//    }
//
//    public ShareView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        mContext = context;
//        init(context, attrs);
//    }
//
//    private void init(Context context, AttributeSet attrs) {
//        if (attrs != null) {
//            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShareView);
//            gravity = typedArray.getInt(R.styleable.ShareView_ImgGravity, 0);
//            ImgPadding = (int) typedArray.getDimension(R.styleable.ShareView_ImgPadding, SizeUtils.dp2px(20));
//            ImgMinSize = typedArray.getDimension(R.styleable.ShareView_ImgMinSize, SizeUtils.dp2px(80));
//            ImgMaxSize = typedArray.getDimension(R.styleable.ShareView_ImgMaxSize, SizeUtils.dp2px(150));
//            codeColor = typedArray.getColor(R.styleable.ShareView_codeColor, Color.parseColor("#000000"));
//            typedArray.recycle();
//        }
//    }
//
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        LayoutInflater.from(mContext).inflate(R.layout.item_share_view, this, true);
//        imgBg = findViewById(R.id.imgBg);
//        imgCode = findViewById(R.id.imgCode);
//        shareText = findViewById(R.id.shareText);
//        shareUrl = findViewById(R.id.shareUrl);
//        radiaGroup = findViewById(R.id.radiaGroup);
//        seek_bar = findViewById(R.id.seek_bar);
//        recyclerView = findViewById(R.id.recyclerView);
//        radiaGroup.setOnCheckedChangeListener(this);
//        seek_bar.setOnSeekBarChangeListener(this);
//        codeSize = (int) ImgMinSize;
//        setGravity(gravity);
//        initRecyclerView();
//    }
//
//    private void initRecyclerView() {
//        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 9));
//        ShareAdapter adapter = new ShareAdapter();
//        adapter.setOnItemClickListener((adapter1, view, position) -> {
//            String s = (String) adapter1.getItem(position);
//            codeColor = Color.parseColor(s);
//            refreshCodeBg();
//        });
//        recyclerView.setAdapter(adapter);
//    }
//
//
//    public void setShareUrl(String url) {
//        shareUrl.setText(url);
//        Glide.with(getContext()).load("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg").into(imgBg);
//        refreshCodeBg();
//    }
//
//    private void refreshCodeBg() {
//        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(shareUrl.getText().toString(), SizeUtils.dp2px(80), codeColor);
//        imgCode.setImageBitmap(bitmap);
//    }
//
//    public void setShareText(String text) {
//        shareText.setText(text);
//    }
//
//
//    public void setGravity(int ImgGravity) {
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(codeSize, codeSize);
//        layoutParams.setMargins(ImgPadding, ImgPadding, ImgPadding, ImgPadding);
//        switch (ImgGravity) {
//            case 0:
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                break;
//            case 1:
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                break;
//            case 2:
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                break;
//            case 3:
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                break;
//            case 4:
//                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//                break;
//        }
//        imgCode.setLayoutParams(layoutParams);
//        this.gravity = ImgGravity;
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId) {
//            case R.id.rb_topLeft:
//                setGravity(0);
//                break;
//            case R.id.rb_topRight:
//                setGravity(1);
//                break;
//            case R.id.rb_bottomLeft:
//                setGravity(2);
//                break;
//            case R.id.rb_bottomRight:
//                setGravity(3);
//                break;
//            case R.id.rb_center:
//                setGravity(4);
//                break;
//        }
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        codeSize = (int) (ImgMinSize + ((ImgMaxSize - ImgMinSize) / 100 * progress));
//        setGravity(gravity);
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    class ShareAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
//        int postion = 0;
//
//        public ShareAdapter() {
//            super(R.layout.item_share_color);
//            List<String> data = new ArrayList<>();
//            data.add("#000000");
//            data.add("#01BB33");
//            data.add("#f4333c");
//            data.add("#3F51B5");
//            data.add("#FF4081");
//            data.add("#66a1b4");
//            data.add("#158BF4");
//            data.add("#db6372");
//            data.add("#ff6155");
//            setNewData(data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, String item) {
//            View mView = helper.getView(R.id.mView);
//            mView.setBackgroundColor(Color.parseColor(item));
//        }
//    }
//}
