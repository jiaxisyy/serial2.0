package activity;



import java.util.Timer;
import java.util.TimerTask;

import com.hitek.serial.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;



/**
 * 参数设置界面
 * Created by zuheng.lv on 2016/4/26.
 */
public class ParameterActivity extends Activity implements View.OnClickListener, OnTouchListener{

    private TextView para_et_opendelay, para_et_startdelay;
    private Button para_btn_original,para_btn_setting,para_btn_factory;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getShort("d476"))!=null && !String.valueOf(msg.getData().getShort("d476")).equals("")){
                        para_et_opendelay.setText(String.valueOf(msg.getData().getShort("d476")));
                    }
                    if(String.valueOf(msg.getData().getShort("d478"))!=null && !String.valueOf(msg.getData().getShort("d478")).equals("")){
                        para_et_startdelay.setText(String.valueOf(msg.getData().getShort("d478")));
                    }

                    break;
            }
        }
    };

    private Button para_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_layout);
        initView();
        initData();
        setData();
    }


    /**
     * 控件初始化
     */
    public void initView() {
        para_btn_back = (Button) findViewById(R.id.para_btn_back);
        para_btn_original = (Button) findViewById(R.id.para_btn_original);
        para_btn_factory = (Button) findViewById(R.id.para_btn_factory);
        para_btn_setting = (Button) findViewById(R.id.para_btn_setting);
        para_btn_back.setOnClickListener(this);
        para_btn_original.setOnTouchListener(this);
        para_btn_factory.setOnTouchListener(this);
        para_btn_setting.setOnTouchListener(this);
        //空压机开机延时
        para_et_opendelay = (TextView) findViewById(R.id.para_et_opendelay);
        //待机启动延时
        para_et_startdelay = (TextView) findViewById(R.id.para_et_startdelay);
        para_et_opendelay.setOnClickListener(this);
        para_et_startdelay.setOnClickListener(this);
    }

    /**
     * 数据初始化
     */
    public void initData() {


    }

    /**
     * 数据获取
     */
    public void getData() {


    }

    /**
     * 数据存储
     */
    public void saveData() {

    }

    /**
     * 数据跟新
     */
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    try {


                        /**这里写数据获取与数据处理函数*/

                        short[]d476 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 476, 1);
                        short[]d478 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 478, 1);
                        Bundle bundle = new Bundle();
                        bundle.putShort("d476", d476[0]);
                        bundle.putShort("d478", d478[0]);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);

                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 点击监听函数
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.para_btn_back:
                Intent intent = new Intent(ParameterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.para_et_opendelay:
                showPopWindow(para_et_opendelay,Constants.Define.OP_WORD_D,476);
                break;
            case R.id.para_et_startdelay:
                showPopWindow(para_et_startdelay,Constants.Define.OP_WORD_D,478);
                break;



        }
    }




    private void showPopWindow( final TextView t, final int type,final int stadr) {
        View view = LayoutInflater.from(this).inflate(R.layout.ed_dialog,null);
        final PopupWindow pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,false);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setAnimationStyle(R.style.myanimation);
        pw.showAtLocation(view, Gravity.CENTER, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                PopUtils.setBackgroundAlpha(1.0f, ParameterActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, ParameterActivity.this);//设置popw出现时背景透明度
        final EditText editText= (EditText) view.findViewById(R.id.editText);
        TextView cancel= (TextView) view.findViewById(R.id.cancel);
        TextView sure= (TextView) view.findViewById(R.id.sure);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();

                if(s!=null&&!s.equals("")){
                    short sh= Short.valueOf(s);
                    short[] shr={sh};
                    MyApplication.getInstance().mdbuswriteword(type, shr, stadr, 1);
//                 setData();
                }

                pw.dismiss();
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        switch(v.getId()){
            case R.id.para_btn_original:
                if(event.getAction() ==MotionEvent.ACTION_DOWN){
                    byte[]m95 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m95, 95, 1);
                }else if(event.getAction() ==MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[]m95 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m95, 95, 1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                break;
            case R.id.para_btn_setting:
                if(event.getAction() ==MotionEvent.ACTION_DOWN){
                    byte[]m192 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m192, 192, 1);
                }else if(event.getAction() ==MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[]m192 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m192, 192, 1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);

                }
                break;
            case R.id.para_btn_factory:
                if(event.getAction() ==MotionEvent.ACTION_DOWN){
                    byte[]m193 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m193, 193, 1);

                }else if(event.getAction() ==MotionEvent.ACTION_UP){


                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[]m193 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m193, 193, 1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                break;

        }

        return false;
    }
}
