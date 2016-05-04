package activity;



import com.hitek.serial.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;



/**
 *模拟量设置界面
 * Created by zuheng.lv on 2016/4/26.
 */
public class AnalogActivity extends Activity implements View.OnClickListener {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:


                    /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getShortArray("d10")[0])!=null && !String.valueOf(msg.getData().getShortArray("d10")[0]).equals("")){
                        analog_et_oxy_original.setText(String.valueOf(msg.getData().getShortArray("d10")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d12")[0])!=null && !String.valueOf(msg.getData().getShortArray("d12")[0]).equals("")){
                        analog_et_flow_original.setText(String.valueOf(msg.getData().getShortArray("d12")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d14")[0])!=null && !String.valueOf(msg.getData().getShortArray("d14")[0]).equals("")){

                        analog_et_concentration_original.setText(String.valueOf(msg.getData().getShortArray("d14")[0]));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d16")[0])!=null && !String.valueOf(msg.getData().getShortArray("d16")[0]).equals("")){
                        analog_et_temp_original.setText(String.valueOf(msg.getData().getShortArray("d16")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d212")[0])!=null && !String.valueOf(msg.getData().getShortArray("d212")[0]).equals("")){

                        analog_et_oxy_current.setText(String.valueOf(msg.getData().getShortArray("d212")[0]));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d228")[0])!=null && !String.valueOf(msg.getData().getShortArray("d228")[0]).equals("")){
                        analog_et_flow_current.setText(String.valueOf(msg.getData().getShortArray("d228")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d244")[0])!=null && !String.valueOf(msg.getData().getShortArray("d244")[0]).equals("")){

                        analog_et_concentration_current.setText(String.valueOf(msg.getData().getShortArray("d244")[0]));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d260")[0])!=null && !String.valueOf(msg.getData().getShortArray("d260")[0]).equals("")){

                        analog_et_temp_current.setText(String.valueOf(msg.getData().getShortArray("d260")[0]));

                    }
                    break;
                case 2://(float)(Math.round(totalPrice*100)/100);
                    if(String.valueOf(msg.getData().getFloatArray("d200")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d200")[0]).equals("")){
                        analog_et_oxy_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d200")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d204")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d204")[0]).equals("")){
                        analog_et_oxy_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d204")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d208")[0])!=null && !String.valueOf(msg.getData().getShortArray("d208")[0]).equals("")){

                        analog_et_oxy_correction.setText(String.valueOf(msg.getData().getShortArray("d208")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d216")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d216")[0]).equals("")){
                        analog_et_flow_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d216")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d220")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d220")[0]).equals("")){

                        analog_et_flow_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d220")[0])*100)/100));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d224")[0])!=null && !String.valueOf(msg.getData().getShortArray("d224")[0]).equals("")){
                        analog_et_flow_correction.setText(String.valueOf(msg.getData().getShortArray("d224")[0]));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d232")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d232")[0]).equals("")){
                        analog_et_concentration_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d232")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d236")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d236")[0]).equals("")){

                        analog_et_concentration_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d236")[0])*100)/100));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d240")[0])!=null && !String.valueOf(msg.getData().getShortArray("d240")[0]).equals("")){

                        analog_et_concentration_correction.setText(String.valueOf(msg.getData().getShortArray("d240")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d248")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d248")[0]).equals("")){
                        analog_et_temp_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d248")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d252")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d252")[0]).equals("")){
                        analog_et_temp_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d252")[0])*100)/100));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d256")[0])!=null && !String.valueOf(msg.getData().getShortArray("d256")[0]).equals("")){

                        analog_et_temp_correction.setText(String.valueOf(msg.getData().getShortArray("d256")[0]));
                    }
                    break;
            }
        }
    };

    private Button analog_btn_back;
    private TextView analog_et_oxy_original,analog_et_flow_original,analog_et_concentration_original,analog_et_temp_original,analog_et_oxy_current,analog_et_flow_current,analog_et_concentration_current,analog_et_temp_current;
    private TextView analog_et_oxy_max,analog_et_flow_max,analog_et_concentration_max,analog_et_temp_max;
    private TextView analog_et_oxy_min,analog_et_flow_min,analog_et_concentration_min,analog_et_temp_min;
    private TextView analog_et_oxy_correction,analog_et_flow_correction,analog_et_concentration_correction,analog_et_temp_correction;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analog_layout);
        initView();
        initData();
        setData();
        getData();
    }

    /**控件初始化*/
    public void initView(){
        analog_et_oxy_original=(TextView)findViewById(R.id.analog_et_oxy_original);
        analog_et_flow_original=(TextView)findViewById(R.id.analog_et_flow_original);
        analog_et_concentration_original=(TextView)findViewById(R.id.analog_et_concentration_original);
        analog_et_temp_original=(TextView)findViewById(R.id.analog_et_temp_original);
        analog_et_oxy_current=(TextView)findViewById(R.id.analog_et_oxy_current);
        analog_et_flow_current=(TextView)findViewById(R.id.analog_et_flow_current);
        analog_et_concentration_current=(TextView)findViewById(R.id.analog_et_concentration_current);
        analog_et_temp_current=(TextView)findViewById(R.id.analog_et_temp_current);

        analog_et_oxy_max=(TextView)findViewById(R.id.analog_et_oxy_max);
        analog_et_flow_max=(TextView)findViewById(R.id.analog_et_flow_max);
        analog_et_concentration_max=(TextView)findViewById(R.id.analog_et_concentration_max);
        analog_et_temp_max=(TextView)findViewById(R.id.analog_et_temp_max);

        analog_et_oxy_min=(TextView)findViewById(R.id.analog_et_oxy_min);
        analog_et_flow_min=(TextView)findViewById(R.id.analog_et_flow_min);
        analog_et_concentration_min=(TextView)findViewById(R.id.analog_et_concentration_min);
        analog_et_temp_min=(TextView)findViewById(R.id.analog_et_temp_min);

        analog_et_oxy_correction=(TextView)findViewById(R.id.analog_et_oxy_correction);
        analog_et_flow_correction=(TextView)findViewById(R.id.analog_et_flow_correction);
        analog_et_concentration_correction=(TextView)findViewById(R.id.analog_et_concentration_correction);
        analog_et_temp_correction=(TextView)findViewById(R.id.analog_et_temp_correction);

        analog_et_oxy_max.setOnClickListener(this);
        analog_et_flow_max.setOnClickListener(this);
        analog_et_concentration_max.setOnClickListener(this);
        analog_et_temp_max.setOnClickListener(this);

        analog_et_oxy_min.setOnClickListener(this);
        analog_et_flow_min.setOnClickListener(this);
        analog_et_concentration_min.setOnClickListener(this);
        analog_et_temp_min.setOnClickListener(this);

        analog_et_oxy_correction.setOnClickListener(this);
        analog_et_flow_correction.setOnClickListener(this);
        analog_et_concentration_correction.setOnClickListener(this);
        analog_et_temp_correction.setOnClickListener(this);

        analog_btn_back = (Button) findViewById(R.id.analog_btn_back);
        analog_btn_back.setOnClickListener(this);
    }
    /**数据初始化*/
    public void initData(){


    }

    /**数据获取*/
    public void getData(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                while(true){

                    try {
                        //氧气压力最大值
                        float[]d200 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 200, 1);
                        //氧气压力最小值
                        float[]d204 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 204, 1);
                        //氧气压力修正值
                        short[]d208 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 208, 1);

                        //氧气流量最大值
                        float[] d216 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 216, 1);
                        //氧气流量最小值
                        float[] d220 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 220, 1);
                        //氧气流量修正值
                        short[] d224 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 224, 1);

                        //氧气浓度最大值
                        float[] d232 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 232, 1);
                        //氧气浓度最小值
                        float[] d236 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 236, 1);
                        //氧气浓度修正值
                        short[] d240 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 240, 1);

                        //氧气温度最大值
                        float[] d248 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 248, 1);
                        //氧气温度最小值
                        float[] d252 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 252, 1);
                        //氧气温度修正值
                        short[] d256 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 256, 1);
                        Bundle bundle = new Bundle();


                        bundle.putFloatArray("d200", d200);
                        bundle.putFloatArray("d204", d204);
                        bundle.putShortArray("d208", d208);
                        bundle.putFloatArray("d216", d216);
                        bundle.putFloatArray("d220", d220);
                        bundle.putShortArray("d224", d224);
                        bundle.putFloatArray("d232", d232);
                        bundle.putFloatArray("d236", d236);
                        bundle.putShortArray("d240", d240);
                        bundle.putFloatArray("d248", d248);
                        bundle.putFloatArray("d252", d252);
                        bundle.putShortArray("d256", d256);
                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 2;
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
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //氧气压力原始值
                    short[] d10 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 10, 1);
                    //氧气流量原始值
                    short[] d12 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 12, 1);
                    //氧气浓度  原始值
                    short[] d14 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 14, 1);
                    //氧气温度原始值
                    short[] d16 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 16, 1);

                    //氧气压力当前值
                    short[] d212 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 212, 1);

                    //氧气流量当前值
                    short[] d228 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 228, 1);

                    //氧气浓度当前值
                    short[] d244 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 244, 1);

                    //氧气温度当前值
                    short[] d260 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 260, 1);
                    Bundle bundle = new Bundle();
                    bundle.putShortArray("d10", d10);
                    bundle.putShortArray("d12", d12);
                    bundle.putShortArray("d14", d14);
                    bundle.putShortArray("d16", d16);

                    bundle.putShortArray("d212", d212);

                    bundle.putShortArray("d228", d228);

                    bundle.putShortArray("d244", d244);

                    bundle.putShortArray("d260", d260);
                    Message msg = new Message();
                    msg.setData(bundle);
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }


    /**点击监听函数*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.analog_btn_back:
                Intent intent = new Intent(AnalogActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.analog_et_oxy_max:
                showPopWindow(analog_et_oxy_max,Constants.Define.OP_DWORD_D,200);
                break;
            case R.id.analog_et_flow_max:
                showPopWindow(analog_et_flow_max,Constants.Define.OP_DWORD_D,216);
                break;
            case R.id.analog_et_concentration_max:
                showPopWindow(analog_et_concentration_max,Constants.Define.OP_DWORD_D,232);
                break;
            case R.id.analog_et_temp_max:
                showPopWindow(analog_et_temp_max,Constants.Define.OP_DWORD_D,248);
                break;
            case R.id.analog_et_oxy_min:
                showPopWindow(analog_et_oxy_min,Constants.Define.OP_DWORD_D,204);
                break;
            case R.id.analog_et_flow_min:
                showPopWindow(analog_et_flow_min,Constants.Define.OP_DWORD_D,220);
                break;
            case R.id.analog_et_concentration_min:
                showPopWindow(analog_et_concentration_min,Constants.Define.OP_DWORD_D,236);
                break;
            case R.id.analog_et_temp_min:
                showPopWindow(analog_et_temp_min,Constants.Define.OP_DWORD_D,252);
                break;
            case R.id. analog_et_oxy_correction:
                showPopWindow(analog_et_oxy_correction,Constants.Define.OP_DWORD_D,208);
                break;
            case R.id.analog_et_flow_correction:
                showPopWindow(analog_et_flow_correction,Constants.Define.OP_DWORD_D,224);
                break;
            case R.id.analog_et_concentration_correction:
                showPopWindow(analog_et_concentration_correction,Constants.Define.OP_DWORD_D,240);
                break;
            case R.id.analog_et_temp_correction:
                showPopWindow(analog_et_temp_correction,Constants.Define.OP_DWORD_D,256);
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
                PopUtils.setBackgroundAlpha(1.0f, AnalogActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, AnalogActivity.this);//设置popw出现时背景透明度
        final EditText TextView= (EditText) view.findViewById(R.id.editText);
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
                String s = TextView.getText().toString();
                if(s!=null&&!s.equals("")){
                    int i = Integer.parseInt(s);
                    int[] i1 = {i};
                    MyApplication.getInstance().mdbuswritedword(type, i1, stadr, 1);
//                 getData();
                }
                pw.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
