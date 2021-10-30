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
    String[] titles = new String[]{"三只松鼠大礼包", "良品铺子大礼包", "王小卤虎皮凤爪", "卫龙网红零食礼包", "好利来半熟芝士",
            "李子柒蛋黄酥",""};
    //价格
    String[] prices = new String[]{"109元/件 共一件", "129元/件 共一件", "19.8元/袋 共三袋", "60.9元/件 共一件", "38元/盒 共两盒",
            "89.8元/两盒 共两盒",""};
    //图片集合
    int[] icons = new int[]{R.drawable.szzs, R.drawable.lppz, R.drawable.wxl,
            R.drawable.wl, R.drawable.hll, R.drawable.lzq,0};
    //详细信息
    String[] content = new String[]{
            "已发货\n商品已到达成都市\n预计明天14：30送达",
            "已送达\n商品已到达西南财经大学菜鸟驿站\n请及时签收",
            "待发货\n正在努力为您派件",
            "已发货\n商品已到达重庆市\n预计两天后送达",
            "待发货\n正在努力为您派件",
            "待发货\n商品缺货中"
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