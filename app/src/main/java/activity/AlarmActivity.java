package activity;




import com.hitek.serial.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;

/**
 * 报警设置界面
 * Created by zuheng.lv on 2016/4/26.
 */
public class AlarmActivity extends Activity implements View.OnClickListener{
    private TextView alarm_et_oxy_max, alarm_et_oxy_min, alarm_et_oxy_flow, alarm_et_oxy_concentration, alarm_et_temp_max, alarm_et_temp_min;
    private Button alarm_btn_back;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getFloat("d280"))!=null &&!String.valueOf(msg.getData().getFloat("d280")).isEmpty()){
                        alarm_et_oxy_max.setText(String.valueOf(msg.getData().getFloat("d280")));
                    }
                    if(String.valueOf(msg.getData().getFloat("d284"))!=null &&!String.valueOf(msg.getData().getFloat("d284")).isEmpty()){
                        alarm_et_oxy_min.setText(String.valueOf(msg.getData().getFloat("d284")));
                    }
                    if(String.valueOf(msg.getData().getFloat("d296"))!=null &&!String.valueOf(msg.getData().getFloat("d296")).isEmpty()){
                        alarm_et_oxy_concentration.setText(String.valueOf(msg.getData().getFloat("d296")));
                    }
                    if(String.valueOf(msg.getData().getFloat("D300"))!=null &&!String.valueOf(msg.getData().getFloat("D300")).isEmpty()){
                        alarm_et_oxy_flow.setText(String.valueOf(msg.getData().getFloat("D300")));
                    }
                    if(String.valueOf(msg.getData().getFloat("D304"))!=null &&!String.valueOf(msg.getData().getFloat("D304")).isEmpty()){
                        alarm_et_temp_max.setText(String.valueOf(msg.getData().getFloat("D304")));
                    }
                    if(String.valueOf(msg.getData().getFloat("D308"))!=null &&!String.valueOf(msg.getData().getFloat("D308")).isEmpty()){
                        alarm_et_temp_min.setText(String.valueOf(msg.getData().getFloat("D308")));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);
        initView();
        initData();
        setData();
    }

    /**
     * 控件初始化
     */
    public void initView() {


    }

    /**
     * 数据初始化
     */
    public void initData() {
        //氧气压力上限
        alarm_et_oxy_max = (TextView) findViewById(R.id.alarm_et_oxy_max);
        // 氧气压力下限
        alarm_et_oxy_min = (TextView) findViewById(R.id.alarm_et_oxy_min);
        //氧气流量上限
        alarm_et_oxy_flow = (TextView) findViewById(R.id.alarm_et_oxy_flow);
        //氧气浓度下限
        alarm_et_oxy_concentration = (TextView) findViewById(R.id.alarm_et_oxy_concentration);
        //露点/温度上限

        alarm_et_temp_max = (TextView) findViewById(R.id.alarm_et_temp_max);
        //露点/温度上下限

        alarm_et_temp_min = (TextView) findViewById(R.id.alarm_et_temp_min);
        alarm_btn_back=(Button) findViewById(R.id.alarm_btn_back);
        alarm_btn_back.setOnClickListener(this);
        alarm_et_oxy_min.setOnClickListener(this);
        alarm_et_oxy_flow.setOnClickListener(this);
        alarm_et_oxy_concentration.setOnClickListener(this);
        alarm_et_temp_max.setOnClickListener(this);
        alarm_et_temp_min.setOnClickListener(this);
        alarm_et_oxy_max.setOnClickListener(this);
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
                        float[]d280 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 280, 1);
                        float[]d284 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 284, 1);
                        float[]d296 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 296, 1);
                        float[]d300 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 300, 1);
                        float[]d304 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 304, 1);
                        float[]d308 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 308, 1);

                        Bundle bundle = new Bundle();
                        bundle.putFloat("d280", d280[0]);
                        bundle.putFloat("d284", d284[0]);
                        bundle.putFloat("d296", d296[0]);
                        bundle.putFloat("d300", d300[0]);
                        bundle.putFloat("d304", d304[0]);
                        bundle.putFloat("d308", d308[0]);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alarm_btn_back:
                startActivity(new Intent(AlarmActivity.this,MainActivity.class));
                finish();
                break;


            case R.id.alarm_et_oxy_max:
                Log.d("btn", "alarm_et_oxy_max");
                showPopWindow(alarm_et_oxy_max,Constants.Define.OP_DWORD_D,280);
                Log.d("btn", "已经执行");
                break;
            case R.id.alarm_et_oxy_min:
                showPopWindow(alarm_et_oxy_min,Constants.Define.OP_DWORD_D,284);
                break;
            case R.id.alarm_et_oxy_flow:
                showPopWindow(alarm_et_oxy_flow,Constants.Define.OP_DWORD_D,300);
                break;
            case R.id.alarm_et_oxy_concentration:
                showPopWindow(alarm_et_oxy_concentration,Constants.Define.OP_DWORD_D,296);
                break;
            case R.id.alarm_et_temp_max:
                showPopWindow(alarm_et_temp_max,Constants.Define.OP_DWORD_D,304);
                break;
            case R.id.alarm_et_temp_min:
                showPopWindow(alarm_et_temp_min,Constants.Define.OP_DWORD_D,308);
                break;



        }

    }




    private void showPopWindow( final TextView t, final int type, final int stadr) {
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
                PopUtils.setBackgroundAlpha(1.0f, AlarmActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, AlarmActivity.this);//设置popw出现时背景透明度
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

                }



                pw.dismiss();
            }
        });
    }

}
