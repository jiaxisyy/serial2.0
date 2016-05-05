package activity;



import java.util.Timer;
import java.util.TimerTask;

import com.hitek.serial.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;



/**
 * 流量页面
 * Created by zuheng.lv on 2016/4/26.
 */
public class FlowActivity extends Activity implements View.OnClickListener, View.OnTouchListener {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                        /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getFloat("d264"))!=null && !String.valueOf(msg.getData().getFloat("d264")).equals("")){
                        flow_tv_totalflow.setText(String.valueOf((float) Math.round( msg.getData().getFloat("d264")* 10000) / 10000));
                    }
                    if(String.valueOf(msg.getData().getInt("d272"))!=null && !String.valueOf(msg.getData().getInt("d272")).equals("")){
                        flow_et_totalflow_correct.setText(String.valueOf(msg.getData().getInt("d272")));
                    }
                    break;
                case 2:


                    break;

            }
        }
    };

    private Button flow_btn_back,flow_btn_confirm,flow_btn_clean;
    private TextView flow_tv_totalflow;
    private TextView flow_et_totalflow_correct;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_layout);
        initView();
        initData();
        setData();
        getData();
    }

    /**控件初始化*/
    public void initView(){
        flow_tv_totalflow= (TextView)findViewById(R.id.flow_tv_totalflow);
        flow_et_totalflow_correct = (TextView)findViewById(R.id.flow_et_totalflow_correct);
        flow_btn_confirm =(Button)findViewById(R.id.flow_btn_confirm);
        flow_btn_clean = (Button)findViewById(R.id.flow_btn_clean);
        flow_btn_back = (Button) findViewById(R.id.flow_btn_back);
        flow_et_totalflow_correct.setOnClickListener(this);
        flow_btn_back.setOnClickListener(this);
        flow_btn_clean.setOnClickListener(this);
        flow_btn_clean.setOnTouchListener(this);
        flow_btn_confirm.setOnClickListener(this);
        flow_btn_confirm.setOnTouchListener(this);
    }
    /**数据初始化*/
    public void initData(){


    }

    /**数据获取*/
    public void getData(){


    }
    /**数据存储*/
    public void saveData(){

    }
    /**数据跟新*/
    public void setData(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                /**这里写数据获取与数据处理函数*/
                while (flag) {
                    try {
                        float[] d264 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 264, 1);
                        int[] d272 = MyApplication.getInstance().mdbusreaddword(Constants.Define.OP_DWORD_D, 272, 1);
                        Bundle bundle = new Bundle();
                        Message msg = new Message();
                        bundle.putFloat("d264", d264[0]);
                        bundle.putInt("d272", d272[0]);
                        msg.setData(bundle);
                        msg.what = 1;
                        handler.sendMessage(msg);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**点击监听函数*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flow_btn_back:
                Intent intent = new Intent(FlowActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.flow_et_totalflow_correct:
                if (flow_et_totalflow_correct.getText() != null && !flow_et_totalflow_correct.getText().equals("")) {
                    showPopWindow(flow_et_totalflow_correct,Constants.Define.OP_DWORD_D,272);
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.flow_btn_confirm:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m190 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m190,190,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m190 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m190,190,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                break;
            case R.id.flow_btn_clean:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m117 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m117 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }

                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag=false;
    }

    private void showPopWindow( final TextView t, final int type, final int stadr) {
        View view = LayoutInflater.from(this).inflate(R.layout.ed_dialog,null);
        final PopupWindow pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setAnimationStyle(R.style.myanimation);
        pw.showAtLocation(view, Gravity.CENTER, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                PopUtils.setBackgroundAlpha(1.0f, FlowActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, FlowActivity.this);//设置popw出现时背景透明度
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
                    int i = Integer.parseInt(s);
                    int[] i1={i};
                    MyApplication.getInstance().mdbuswritedword(type, i1, stadr, 1);
//                     getData();
                }



                pw.dismiss();
            }
        });
    }

}
