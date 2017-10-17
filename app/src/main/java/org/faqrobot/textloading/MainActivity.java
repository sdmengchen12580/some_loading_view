package org.faqrobot.textloading;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.zyao89.view.zloading.ZLoadingDialog;

import org.faqrobot.textloading.loadingview.Titanic;
import org.faqrobot.textloading.loadingview.TitanicTextView;
import org.faqrobot.textloading.loadingview.Typefaces;

import java.util.Timer;
import java.util.TimerTask;

import static com.zyao89.view.zloading.Z_TYPE.CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.CIRCLE_CLOCK;
import static com.zyao89.view.zloading.Z_TYPE.DOUBLE_CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.ELASTIC_BALL;
import static com.zyao89.view.zloading.Z_TYPE.INFECTION_BALL;
import static com.zyao89.view.zloading.Z_TYPE.INTERTWINE;
import static com.zyao89.view.zloading.Z_TYPE.LEAF_ROTATE;
import static com.zyao89.view.zloading.Z_TYPE.PAC_MAN;
import static com.zyao89.view.zloading.Z_TYPE.ROTATE_CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.SEARCH_PATH;
import static com.zyao89.view.zloading.Z_TYPE.SINGLE_CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.SNAKE_CIRCLE;
import static com.zyao89.view.zloading.Z_TYPE.STAR_LOADING;
import static com.zyao89.view.zloading.Z_TYPE.TEXT;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**1.loading动画*/
        initLoadingView();
        /**2.显示不同的第三方的加载控件*/
        init_3_loading_view(R.id.show_1,CIRCLE);
        init_3_loading_view(R.id.show_2,CIRCLE_CLOCK);
        init_3_loading_view(R.id.show_3,STAR_LOADING);
        init_3_loading_view(R.id.show_4,LEAF_ROTATE);
        init_3_loading_view(R.id.show_5,DOUBLE_CIRCLE);
        init_3_loading_view(R.id.show_6,PAC_MAN);
        init_3_loading_view(R.id.show_7,ELASTIC_BALL);
        init_3_loading_view(R.id.show_8,INFECTION_BALL);
        init_3_loading_view(R.id.show_9,INTERTWINE);
        init_3_loading_view(R.id.show_10,TEXT);
        init_3_loading_view(R.id.show_11,SEARCH_PATH);
        init_3_loading_view(R.id.show_12,ROTATE_CIRCLE);
        init_3_loading_view(R.id.show_13,SINGLE_CIRCLE);
        init_3_loading_view(R.id.show_14,SNAKE_CIRCLE);

    }

    /**1.loading字体的加载动画*/
    private void initLoadingView() {
        /**开始dialog*/
        findViewById(R.id.mybutton_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * dismiss和hide方法都可以隐藏对话框，在需要的时候也可以用show方法调用显示。但是，这两者是有区别的。
                 * dismiss方法会释放对话框所占的资源，而hide方法不会。activity退出前必须调用dismiss方法关闭对话框。
                 * 如果对话框上有progressbar,你会发现，调用dismiss方法后，再调用show方法，出来的对话框，上面的progressbar不再会转动，
                 * 而调用dialog.hide()方法的则没有问题。
                 * 所以，最正确的调用方法是，在activity的onDestory方法里调用dismiss方法，其他地方都用hide方法隐藏对话框。
                 */
                /**计时器的时间*/
                final int[] number = {0};
                /**Timer定时器，到时间隐藏dialog*/
                final Timer timer;
                /**自定义dialog*/
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_dialog_loading, null);
                /**通过view对象去绑定布局解析到的控件id*/
                /**loadingview*/
                final TitanicTextView tv = (TitanicTextView) view.findViewById(R.id.my_text_view);
                /**字体格式*/
                tv.setTypeface(Typefaces.get(MainActivity.this, "Satisfy-Regular.ttf"));
                /**start 动画*/
                final Titanic titanic = new Titanic();
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                /**通过build去设置dialog的属性——setTitle*/
                builder.setView(view);
                /**设置点击对话框外部区域不关闭对话框*/
                builder.setCancelable(false);
                final AlertDialog[] dialog = new AlertDialog[1];
                dialog[0] = builder.show();
                /**dialog显示之后让textview显示动画*/
                titanic.start(tv);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        number[0] += 1;
                        if(number[0] ==3){
                            timer.cancel();
                            /**停止动画和dialog*/
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    titanic.cancel();
                                    dialog[0].dismiss();
                                    number[0] = 0;
                                }
                            });
                        }
                    }
                },0,1000);
            }
        });
    }

    /**2.显示不同的第三方的加载控件*/
    private void init_3_loading_view(int id, final com.zyao89.view.zloading.Z_TYPE type) {
        /**计时器的时间*/
        final int[] number = {0};
        /**Timer定时器，到时间隐藏dialog*/
        final Timer[] timer = new Timer[1];


        // TODO: 2017/10/17
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ZLoadingDialog dialog = new ZLoadingDialog(MainActivity.this);
                dialog.setLoadingBuilder(type)//设置类型
                        .setLoadingColor(Color.BLACK)//颜色
                        .setHintText("Loading...")
                        .setHintTextSize(16) // 设置字体大小 dp
                        .setHintTextColor(Color.GRAY)  // 设置字体颜色
                        .show();

                timer[0] = new Timer();
                timer[0].schedule(new TimerTask() {
                    @Override
                    public void run() {
                        number[0] += 1;
                        if(number[0]==3){
                            timer[0].cancel();
                            dialog.dismiss();
                        }
                    }
                },0,1000);
            }
        });
    }

}
