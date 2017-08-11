package com.example.administrator.yitao.Activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yitao.Fragment.MeFragment;
import com.example.administrator.yitao.Fragment.ShopFragment;
import com.example.administrator.yitao.Fragment.UnLoginFragment;
import com.example.administrator.yitao.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ma_title)
    TextView mTitle;
    @BindView(R.id.ma_vp)
    ViewPager mVp;
    @BindViews({R.id.ma_bazaar, R.id.ma_chat, R.id.ma_people, R.id.ma_identity})
    TextView[] tv;
    @BindColor(R.color.colorPrimary)
    int colorPrimary;//选择控键颜色
    @BindColor(R.color.text_color_hint)
    int text_color_hint;//默认控键颜色
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        tv[0].setSelected(true);
        tv[0].setTextColor(colorPrimary);
    }

    private void init() {
        mVp.setAdapter(mFragmentAdapter);
       mVp.addOnPageChangeListener(OnPageListener);
    }

    @OnClick({R.id.ma_bazaar, R.id.ma_chat, R.id.ma_people, R.id.ma_identity})
    public void onViewClicked(View view) {
        for (int i = 0; i < tv.length; i++) {
            tv[i].setSelected(false);
            tv[i].setTextColor(text_color_hint);
            tv[i].setTag(i);
        }
        mVp.setCurrentItem((Integer) view.getTag());
        view.setSelected(true);
        ((TextView)view).setTextColor(colorPrimary);

       /* switch (view.getId()) {
            case R.id.ma_bazaar:
                mTitle.setText("市场");
                break;
            case R.id.ma_chat:
                mTitle.setText("消息");
                break;
            case R.id.ma_people:
                mTitle.setText("通讯录");
                break;
            case R.id.ma_identity:
                mTitle.setText("我的");
                break;
        }*/
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000){
            exitTime = System.currentTimeMillis();
            showTost("再按一次退出程序");
        }else finish();
    }
    FragmentStatePagerAdapter mFragmentAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()){
        @Override
        public int getCount() {
            return tv.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ShopFragment();
                case 1:
                    return new MeFragment();
                case 2:
                    return new MeFragment();
                case 3:
                    return new UnLoginFragment();
            }
            return null;
        }
    };
    ViewPager.OnPageChangeListener OnPageListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (TextView textview:tv
                    ) {
                textview.setSelected(false);
                textview.setTextColor(text_color_hint);
            }
            tv[position].setSelected(true);
            tv[position].setTextColor(colorPrimary);
            mTitle.setText(tv[position].getText());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
