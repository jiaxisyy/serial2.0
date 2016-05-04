package activity;



import com.hitek.serial.R;

import android.app.Activity;
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
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;



/**
 * 压力设置页面
 * Created by zuheng.lv on 2016/4/26.
 */
public class PressureActivity extends Activity implements View.OnClickListener {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getFloat("d330"))!=null&&!String.valueOf(msg.getData().getFloat("d330")).equals("")){
                        pressure_et_max1.setText(String.valueOf(msg.getData().getFloat("d330")));
                    }
                    if(String.valueOf(msg.getData().getFloat("d334"))!=null&&!String.valueOf(msg.getData().getFloat("d334")).equals("")){
                        pressure_et_min1.setText(String.valueOf(msg.getData().getFloat("d334")));
                    }
                    if(String.valueOf(msg.getData().getFloat("d338"))!=null&&!String.valueOf(msg.getData().getFloat("d338")).equals("")){
                        pressure_et_max2.setText(String.valueOf(msg.getData().getFloat("d338")));
                    }
                    if(String.valueOf(msg.getData().getFloat("d342"))!=null&&!String.valueOf(msg.getData().getFloat("d342")).equals("")){
                        pressure_et_min2.setText(String.valueOf(msg.getData().getFloat("d342")));
                    }

                    break;
            }
        }
    };


    private Button pressure_btn_back;
    private TextView pressure_et_max1,pressure_et_max2,pressure_et_min1,pressure_et_min2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pressure_layout);
        initView();
        initData();
        setData();
    }

    /**控件初始化*/
    public void initView(){
        pressure_et_max1 = (TextView)findViewById(R.id.pressure_et_max1);
        pressure_et_max2 = (TextView)findViewById(R.id.pressure_et_max2);
        pressure_et_min1 = (TextView)findViewById(R.id.pressure_et_min1);
        pressure_et_min2 = (TextView)findViewById(R.id.pressure_et_min2);
        pressure_et_max1.setOnClickListener(this);
        pressure_et_max2.setOnClickListener(this);
        pressure_et_min1.setOnClickListener(this);
        pressure_et_min2.setOnClickListener(this);
        pressure_btn_back = (Button) findViewById(R.id.pressure_btn_back);
        pressure_btn_back.setOnClickListener(this);


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
                while(true){

                    try{
                        float[]d330 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D,330,1);
                        float[]d334 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D,334,1);
                        float[]d338 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D,338,1);
                        float[]d342 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D,342,1);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("d330", d330[0]);
                        bundle.putFloat("d334", d334[0]);
                        bundle.putFloat("d338", d338[0]);
                        bundle.putFloat("d342", d342[0]);
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

    /**点击监听函数*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pressure_btn_back:
                Intent intent = new Intent(PressureActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.pressure_et_max1:
                showPopWindow(pressure_et_max1,Constants.Define.OP_DWORD_D,330);
                break;
            case R.id.pressure_et_max2:
                showPopWindow(pressure_et_max2,Constants.Define.OP_DWORD_D,338);
                break;
            case R.id.pressure_et_min1:
                showPopWindow(pressure_et_min1,Constants.Define.OP_DWORD_D,334);
                break;
            case R.id.pressure_et_min2:
                showPopWindow(pressure_et_min2,Constants.Define.OP_DWORD_D,342);
                break;
        }
    }

    private void showPopWindow(final TextView t, final int type, final int stadr) {
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
                PopUtils.setBackgroundAlpha(1.0f, PressureActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, PressureActivity.this);//设置popw出现时背景透明度
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
                    int[] i1 = {i};
                    MyApplication.getInstance().mdbuswritedword(type, i1, stadr, 1);
//                 setData();
                }
                pw.dismiss();
            }
        });
    }




}
