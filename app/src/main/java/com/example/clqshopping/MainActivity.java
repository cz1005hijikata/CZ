package com.example.clqshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<Fragment> listFragment;
    BottomNavigationView navigation;

    //初始化视图
    private void initView() {
        //视图翻页工具
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //底部导航栏
        navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        //添加两个页面
        listFragment = new ArrayList<>();
        listFragment.add(new ShoppingFragment());
        listFragment.add(new PersonFragment());
        //给vieewPager设置适配器。
        MyFragAdapter myFragAdapter = new MyFragAdapter(getSupportFragmentManager(), this, listFragment);
        //添加适配器
        viewPager.setAdapter(myFragAdapter);
        //viewPager点击底部导航栏 （setCurrentItem）
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0, true);
                        return true;
                    case R.id.navigation_person:
                        viewPager.setCurrentItem(1, true);
                        return true;
                }
                return false;
            }
        });

        //viewPager切页（在此调用的是onPageSelected方法）
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //在屏幕滚动过程中不断被调用。
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），手指抬起来就会立即执行这个方法
            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }

            //这个方法在手指操作屏幕的时候发生变化。有三个值：0（END）,1(PRESS) , 2(UP) 。
            @Override
            public void onPageScrollStateChanged(int state) {
            }
            //三个方法的执行顺序为：用手指拖动翻页时，
            // 最先执行一遍onPageScrollStateChanged（1），
            // 然后不断执行onPageScrolled，
            // 放手指的时候，直接立即执行一次onPageScrollStateChanged（2），
            // 然后立即执行一次onPageSelected，
            // 然后再不断执行onPageScrollStateChanged，
            // 最后执行一次onPageScrollStateChanged（0）。
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //创建右上角按钮
    @Override
    public boolean onCreateOptionsMenu(Menu rightup) {
        getMenuInflater().inflate(R.menu.rightup, rightup);
        return true;
    }

    //创建action bar内容
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //关于应用
            case R.id.about_item:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
                //日历
            case R.id.calendar_item:
                Intent intent2 = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent2);
                break;
                //退出应用
            case R.id.exit_item:
                new AlertDialog.Builder(MainActivity.this).setTitle("温馨提示").setMessage("确定退出应用")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            default:
                break;
        }
        return true;
    }
}