package activity;



import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.hitek.serial.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import utils.Constants;

/**
 * 运行监控页面
 * Created by zuheng.lv on 2016/4/26.
 */
public class MonitorActivity extends Activity implements View.OnClickListener, View.OnTouchListener {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**这里写UI更新函数*/

                    if(msg.getData().getString("i1")!=null){

                        momitor_tv_pressure.setText( msg.getData().getString("i1").toString());
                    }
                    if(msg.getData().getString("j1")!=null){
                        momitor_tv_totalflow.setText( msg.getData().getString("j1").toString());
                    }
                    if(msg.getData().getString("k1")!=null){
                        momitor_tv_flow.setText( msg.getData().getString("k1").toString());
                    }
                    if(msg.getData().getString("l1")!=null){

                        momitor_tv_concentration.setText( msg.getData().getString("l1").toString());
                    }
                    if(msg.getData().getIntArray("ints").length>0){
                        momitor_tv_runningtime.setText( msg.getData().getIntArray("ints")[0]+"");

                    }


                    momitor_btn_machine_a.setSelected(msg.getData().getBoolean("m11"));
                    break;

            }
        }
    };

    static Logger logger = Logger.getLogger("com.hitek.serial");
    static boolean is_run_flag = false;
    private Boolean  btn_flag = true;

    //循环标志
    private boolean flag = true;
    private TextView momitor_tv_pressure, momitor_tv_totalflow, momitor_tv_flow, momitor_tv_concentration, momitor_tv_runningtime;
    private Button momitor_btn_machine_a,momitor_btn_start,momitor_btn_stop;
    private Button monitor_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_layout);

        initView();
        initData();
        setData();
    }


    /**
     * 控件初始化
     */
    public void initView() {
        monitor_btn_back = (Button) findViewById(R.id.monitor_btn_back);
        monitor_btn_back.setOnClickListener(this);
        //氧气压力
        momitor_tv_pressure = (TextView) findViewById(R.id.momitor_tv_pressure);
        //流量统计
        momitor_tv_totalflow = (TextView) findViewById(R.id.momitor_tv_totalflow);
        //氧气流量
        momitor_tv_flow = (TextView) findViewById(R.id.momitor_tv_flow);
// 氧气浓度
        momitor_tv_concentration = (TextView) findViewById(R.id.momitor_tv_concentration);
        //A机运行时间
        momitor_tv_runningtime = (TextView) findViewById(R.id.momitor_tv_runningtime);

        momitor_btn_machine_a = (Button) findViewById(R.id.momitor_btn_machine_a);
        momitor_btn_start= (Button) findViewById(R.id.momitor_btn_start);
        momitor_btn_stop = (Button) findViewById(R.id.momitor_btn_stop);
        momitor_btn_stop.setOnTouchListener(this);
        momitor_btn_start.setOnTouchListener(this);
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
     * 数据跟新
     */
    public void setData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
//                float   a  =   123.2334f;
//                float  b   =  (float)(Math.round(a*100))/100;(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)

                /**这里写数据获取与数据处理函数*/
                Log.d("TAG", "run");
                while (flag) {
                    try {
                        //氧气压力值

                        float[] i = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 212, 1);
                        float i1 = (float) (Math.round(i[0] * 10)) / 10;


                        //流量统计
                        float[] j = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 264, 1);
                        float j1 = (float) (Math.round(j[0] * 10000)) / 10000;
                        //氧气流量
                        float[] k = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 228, 1);
                        float k1 = (float) (Math.round(k[0] * 100)) / 100;
                        //氧气浓度
                        float[] l = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 244, 1);
                        float l1 = (float) (Math.round(l[0] * 100)) / 100;
                        //A机运行时间
                        int[] ints = MyApplication.getInstance().mdbusreaddword(Constants.Define.OP_DWORD_D, 458, 1);
                        //A机运行状态
                        byte[]m11 =  MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_M,11,1);

                        Bundle bundle = new Bundle();


                        bundle.putString("i1", String.valueOf(i1));
                        bundle.putString("j1", String.valueOf(j1));
                        bundle.putString("k1", String.valueOf(k1));
                        bundle.putString("l1", String.valueOf(l1));
                        bundle.putIntArray("ints", ints);
                        if(m11[0] == 1){
                            bundle.putBoolean("m11",true);
                        }else{
                            bundle.putBoolean("m11",false);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
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
            /**返回按键*/
            case R.id.monitor_btn_back:
                Intent intent = new Intent(MonitorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        flag=false;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {

            case R.id.momitor_btn_start:
                if(event.getAction()==MotionEvent.ACTION_UP){
                    btn_flag=false;
                    momitor_btn_start.setSelected(btn_flag);

                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,10,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btn_flag=true;
                    momitor_btn_start.setSelected(btn_flag);

                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,10,1);

                }
                break;
            case R.id.momitor_btn_stop:
                if(event.getAction()==MotionEvent.ACTION_UP){
                    btn_flag=false;
                    momitor_btn_stop.setSelected(btn_flag);
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,101,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);

                }
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btn_flag=true;
                    momitor_btn_stop.setSelected(btn_flag);

                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,101,1);


                }
                break;


        }
        return false;
    }
}
