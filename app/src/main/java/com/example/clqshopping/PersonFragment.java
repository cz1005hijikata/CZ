package com.example.clqshopping;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class PersonFragment extends Fragment {
    int num = 0;
    int money = 520;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private Uri imageUri;

    private PopupWindow pop;

    public PersonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 使用布局加载器加载
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        picture = (ImageView) view.findViewById(R.id.profile_image);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更换头像
                showPop();
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button button;
        final TextView theprice;
        final EditText fpnametext;
        button = getView().findViewById(R.id.givebtn);
        theprice = getView().findViewById(R.id.theprice);
        theprice.setText(money + "");
        fpnametext = getView().findViewById(R.id.fpnametext);
        fpnametext.setCursorVisible(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("今日已领取");
                button.setBackgroundColor(-1);
                if (num == 0) {
                    Toast.makeText(getActivity(), "成功领取：10元", Toast.LENGTH_SHORT).show();
                    num++;
                    money += 10;
                    theprice.setText(money + "");
                } else Toast.makeText(getActivity(), "今天已领取，明天再来咯", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPop() {
        View bottomView = View.inflate(getActivity(), R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);

        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        //设置背景可见度
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //取消操作后恢复背景
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        //设置窗体动画
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        //指定父视图，显示在父控件的某个位置
        pop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);  View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //相册
                    case R.id.tv_album:
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                                PERMISSION_GRANTED) {
                            //相册中的照片都是存储在SD卡上的，需要申请运行时权限，WRITE_EXTERNAL_STORAGE是危险权限，表示同时授予程序对SD卡的读和写的能力
                            //在Fragment中直接调用requestPermissions申请权限
                            //不能使用ActivityCompat.requestPermissions
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            openAlbum();
                        }
                        break;
                    //拍照

                    case R.id.tv_cancel:
                        //取消
                        closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    //提示开启权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "你拒绝打开权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                        Toast.makeText(getActivity(), "成功更换头像", Toast.LENGTH_SHORT).show();
                        Log.d("TestHow", "Successfully changed picture");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;



        }}
    public void closePopupWindow () {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }
}

