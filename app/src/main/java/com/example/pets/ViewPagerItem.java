//package com.example.pets;
//
//import android.animation.ArgbEvaluator;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewPagerItem extends AppCompatActivity {
//
//    ViewPager viewPager;
//    Adapter adapter;
//    List<Model> models;
//    Integer[] colors = null;
//    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager);
//
//        models = new ArrayList<>();
//        models.add(new Model(R.drawable.images1 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!","Kucing"));
//        models.add(new Model(R.drawable.images2 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!23","Anjing"));
//        models.add(new Model(R.drawable.images3 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!432","Kura-Kura"));
//
//        adapter = new Adapter(models,this);
//
//        viewPager = findViewById(R.id.viewPagerItem);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(120,0,120,0);
//
//        Integer[] colors_temp = {
//                getResources().getColor(R.color.color1),
//                getResources().getColor(R.color.color2),
//                getResources().getColor(R.color.color3),
//                getResources().getColor(R.color.color4),
//                getResources().getColor(R.color.color5)
//        };
//
//        colors = colors_temp;
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position < (adapter.getCount() - 1) && position < (colors.length -1)){
//                    viewPager.setBackgroundColor(
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//                else  {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//
//    }
//
//}
//
//package com.example.pets;
//
//        import android.animation.ArgbEvaluator;
//        import android.os.Bundle;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.viewpager.widget.ViewPager;
//
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class ViewPagerItem extends AppCompatActivity {
//
//    ViewPager viewPager;
//    Adapter adapter;
//    List<Model> models;
//    Integer[] colors = null;
//    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager);
//
//        models = new ArrayList<>();
//        models.add(new Model(R.drawable.images1 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!","Kucing"));
//        models.add(new Model(R.drawable.images2 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!23","Anjing"));
//        models.add(new Model(R.drawable.images3 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!432","Kura-Kura"));
//
//        adapter = new Adapter(models,this);
//
//        viewPager = findViewById(R.id.viewPagerItem);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(120,0,120,0);
//
//        Integer[] colors_temp = {
//                getResources().getColor(R.color.color1),
//                getResources().getColor(R.color.color2),
//                getResources().getColor(R.color.color3),
//                getResources().getColor(R.color.color4),
//                getResources().getColor(R.color.color5)
//        };
//
//        colors = colors_temp;
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position < (adapter.getCount() - 1) && position < (colors.length -1)){
//                    viewPager.setBackgroundColor(
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position + 1]
//                            )
//                    );
//                }
//                else  {
//                    viewPager.setBackgroundColor(colors[colors.length - 1]);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//
//    }
//
//}
////package com.example.pets;
////
////import android.animation.ArgbEvaluator;
////import android.os.Bundle;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.viewpager.widget.ViewPager;
////
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class ViewPagerItem extends AppCompatActivity {
////
////    ViewPager viewPager;
////    Adapter adapter;
////    List<Model> models;
////    Integer[] colors = null;
////    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_view_pager);
////
////        models = new ArrayList<>();
////        models.add(new Model(R.drawable.images1 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!","Kucing"));
////        models.add(new Model(R.drawable.images2 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!23","Anjing"));
////        models.add(new Model(R.drawable.images3 ,"Hallo aku lagi belum punya majikan nich adopsi aku yukk!!432","Kura-Kura"));
////
////        adapter = new Adapter(models,this);
////
////        viewPager = findViewById(R.id.viewPagerItem);
////        viewPager.setAdapter(adapter);
////        viewPager.setPadding(120,0,120,0);
////
////        Integer[] colors_temp = {
////                getResources().getColor(R.color.color1),
////                getResources().getColor(R.color.color2),
////                getResources().getColor(R.color.color3),
////                getResources().getColor(R.color.color4),
////                getResources().getColor(R.color.color5)
////        };
////
////        colors = colors_temp;
////
////        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
////            @Override
////            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                if (position < (adapter.getCount() - 1) && position < (colors.length -1)){
////                    viewPager.setBackgroundColor(
////                            (Integer) argbEvaluator.evaluate(
////                                    positionOffset,
////                                    colors[position],
////                                    colors[position + 1]
////                            )
////                    );
////                }
////                else  {
////                    viewPager.setBackgroundColor(colors[colors.length - 1]);
////                }
////            }
////
////            @Override
////            public void onPageSelected(int position) {
////
////            }
////
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////            }
////        });
////
////
////    }
////
////}
