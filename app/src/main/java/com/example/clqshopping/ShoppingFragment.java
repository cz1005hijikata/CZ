package com.example.clqshopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingFragment extends Fragment {
    List<Map<String,Object>> listitem=new ArrayList<>();
    //商品名称
    String[] titles = new String[]{"华为手机", "华为笔记本", "华为平板", "华为智慧屏", "华为音箱",
            "华为手环",""};
    //价格
    String[] prices = new String[]{"4999元", "7399元", "2299元", "24999元", "999元",
            "999元",""};
    //图片集合
    int[] icons = new int[]{R.drawable.huawei_mate40_pro, R.drawable.matebook14, R.drawable.matepad,
            R.drawable.huawei_screen, R.drawable.huawei_sound, R.drawable.huawei_jawbone,0};
    //详细信息
    String[] content = new String[]{
            "HUAWEI Mate 40\nCPU型号：麒麟9000E\n运行内存：8GB\n 网络制式：支持全网5G\n上市时间：2020年12月\n剩余：5 台",
            "MateBook14\nCPU型号：第十代智能英特尔®酷睿™i7-10510U处理器\n运行内存：16GB\n触摸屏:支持（10点触控，防指纹)\n上市时间：2020年5月\n剩余：13 台",
            "MatePad10.4\nCPU型号：华为海思麒麟820\n运行内存：6G\n电池容量：7250mAh(典型值)\n分辨率：2000 x 1200\n 剩余: 10 台",
            "华为智慧屏\nCPU型号：鸿鹄898\n操作系统：HarmonyOS 1.1\n屏幕尺寸：65英寸\n音效：HUAWEI Histen音效技术\n上市时间：2020年4月\n 剩余: 8 台",
            "HUAWEI Sound智能音箱\n音效：帝瓦雷SAM®低音增强，帝瓦雷调音，360度均匀声场\n技术：支持人机交互通信\n上市时间：2020年11月\n 剩余：35 台",
            "华为手环B6\n匹配系统Android 4.4及以上版本；iOS 9.0及以上版本\n理论工作时间典型工作时间：3天（开启科学睡眠、智能心率）\n理论通话时间：最长8小时\n剩余: 58 只"
    };
    public ShoppingFragment() {
    }

    //列表显示
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopping, container, false);
        for(int i = 0; i < icons.length; i++){
            Map<String,Object> map = new HashMap<String, Object>(){};
            map.put("icons", icons[i]);
            map.put("titles", titles[i]);
            map.put("prices", prices[i]);
            listitem.add(map);
        }
        //添加适配器
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),listitem,R.layout.list_item
        ,new String[]{"icons","titles","prices"}
        ,new int[]{R.id.imageview,R.id.title,R.id.price});
        ListView listView = (ListView) v.findViewById(R.id.lv);
        listView.setAdapter(adapter);

        //listview的监听item事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(getActivity(), content[0], Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), content[1], Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), content[2], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), content[3], Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(), content[4], Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(), content[5], Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return v;
    }
}