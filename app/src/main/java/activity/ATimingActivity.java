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
import android.text.InputType;
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
 * A机时序设置界面
 * Created by zuheng.lv on 2016/4/26.
 */
public class ATimingActivity extends Activity implements View.OnClickListener {

    private TextView ATiming_1, ATiming_2, ATiming_3, ATiming_4, ATiming_5, ATiming_6, ATiming_7, ATiming_8, ATiming_9, ATiming_10, ATiming_11, ATiming_12, ATiming_13;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d("LOG",String.valueOf(msg.getData().getShort("d400")));
                    /**这里写UI更新函数*/
                    if(String.valueOf(msg.getData().getShort("d400"))!=null && !String.valueOf(msg.getData().getShort("d400")).equals("")){
                        ATiming_1.setText(String.valueOf(msg.getData().getShort("d400")));

                    }
                    if(String.valueOf(msg.getData().getShort("d402"))!=null && !String.valueOf(msg.getData().getShort("d402")).equals("")){
                        ATiming_2.setText(String.valueOf(msg.getData().getShort("d402")));

                    }
                    if(String.valueOf(msg.getData().getShort("d404"))!=null && !String.valueOf(msg.getData().getShort("d404")).equals("")){
                        ATiming_3.setText(String.valueOf(msg.getData().getShort("d404")));

                    }
                    if(String.valueOf(msg.getData().getShort("d406"))!=null && !String.valueOf(msg.getData().getShort("d406")).equals("")){
                        ATiming_4.setText(String.valueOf(msg.getData().getShort("d406")));

                    }
                    if(String.valueOf(msg.getData().getShort("d408"))!=null && !String.valueOf(msg.getData().getShort("d408")).equals("")){
                        ATiming_5.setText(String.valueOf(msg.getData().getShort("d408")));

                    }
                    if(String.valueOf(msg.getData().getShort("d410"))!=null && !String.valueOf(msg.getData().getShort("d410")).equals("")){
                        ATiming_6.setText(String.valueOf(msg.getData().getShort("d410")));

                    }
                    if(String.valueOf(msg.getData().getShort("d412"))!=null && !String.valueOf(msg.getData().getShort("d412")).equals("")){
                        ATiming_7.setText(String.valueOf(msg.getData().getShort("d412")));

                    }
                    if(String.valueOf(msg.getData().getShort("d414"))!=null && !String.valueOf(msg.getData().getShort("d414")).equals("")){
                        ATiming_8.setText(String.valueOf(msg.getData().getShort("d414")));

                    }
                    if(String.valueOf(msg.getData().getShort("d416"))!=null && !String.valueOf(msg.getData().getShort("d416")).equals("")){
                        ATiming_9.setText(String.valueOf(msg.getData().getShort("d416")));

                    }
                    if(String.valueOf(msg.getData().getShort("d418"))!=null && !String.valueOf(msg.getData().getShort("d418")).equals("")){
                        ATiming_10.setText(String.valueOf(msg.getData().getShort("d418")));

                    }
                    if(String.valueOf(msg.getData().getShort("d420"))!=null && !String.valueOf(msg.getData().getShort("d420")).equals("")){
                        ATiming_11.setText(String.valueOf(msg.getData().getShort("d420")));

                    }
                    if(String.valueOf(msg.getData().getShort("d422"))!=null && !String.valueOf(msg.getData().getShort("d422")).equals("")){
                        ATiming_12.setText(String.valueOf(msg.getData().getShort("d422")));

                    }
                    if(String.valueOf(msg.getData().getShort("d424"))!=null && !String.valueOf(msg.getData().getShort("d424")).equals("")){
                        ATiming_13.setText(String.valueOf(msg.getData().getShort("d424")));

                    }

                    break;
            }
        }
    };

    private Button time_btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atimming_layout);
        initView();
        initData();
        setData();
    }

    /**
     * 控件初始化
     */
    public void initView() {
        time_btn_back = (Button) findViewById(R.id.time_btn_back);
        time_btn_back.setOnClickListener(this);
        ATiming_1 = (TextView) findViewById(R.id.ATiming_1);
        ATiming_2 = (TextView) findViewById(R.id.ATiming_2);
        ATiming_3 = (TextView) findViewById(R.id.ATiming_3);
        ATiming_4 = (TextView) findViewById(R.id.ATiming_4);
        ATiming_5 = (TextView) findViewById(R.id.ATiming_5);
        ATiming_6 = (TextView) findViewById(R.id.ATiming_6);
        ATiming_7 = (TextView) findViewById(R.id.ATiming_7);
        ATiming_8 = (TextView) findViewById(R.id.ATiming_8);
        ATiming_9 = (TextView) findViewById(R.id.ATiming_9);
        ATiming_10 = (TextView) findViewById(R.id.ATiming_10);
        ATiming_11 = (TextView) findViewById(R.id.ATiming_11);
        ATiming_12 = (TextView) findViewById(R.id.ATiming_12);
        ATiming_13 = (TextView) findViewById(R.id.ATiming_13);
        ATiming_1.setOnClickListener(this);
        ATiming_2.setOnClickListener(this);
        ATiming_3.setOnClickListener(this);
        ATiming_4.setOnClickListener(this);
        ATiming_5.setOnClickListener(this);
        ATiming_6.setOnClickListener(this);
        ATiming_7.setOnClickListener(this);
        ATiming_8.setOnClickListener(this);
        ATiming_9.setOnClickListener(this);
        ATiming_10.setOnClickListener(this);
        ATiming_11.setOnClickListener(this);
        ATiming_12.setOnClickListener(this);
        ATiming_13.setOnClickListener(this);


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
                        short[] d400 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 400, 1);
                        short[] d402 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 402, 1);
                        short[] d404 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 404, 1);
                        short[] d406 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 406, 1);
                        short[] d408 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 408, 1);
                        short[] d410 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 410, 1);
                        short[] d412 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 412, 1);
                        short[] d414 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 414, 1);
                        short[] d416 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 416, 1);
                        short[] d418 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 418, 1);
                        short[] d420 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 420, 1);
                        short[] d422 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 422, 1);
                        short[] d424 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 424, 1);

                        Bundle bundle = new Bundle();
                        bundle.putShort("d400", d400[0]);
                        bundle.putShort("d402", d402[0]);
                        bundle.putShort("d404", d404[0]);
                        bundle.putShort("d406", d406[0]);
                        bundle.putShort("d408", d408[0]);
                        bundle.putShort("d410", d410[0]);
                        bundle.putShort("d412", d412[0]);
                        bundle.putShort("d414", d414[0]);
                        bundle.putShort("d416", d416[0]);
                        bundle.putShort("d418", d418[0]);
                        bundle.putShort("d420", d420[0]);
                        bundle.putShort("d422", d422[0]);
                        bundle.putShort("d424", d424[0]);
                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 1;
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
            case R.id.time_btn_back:
                Intent intent = new Intent(ATimingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ATiming_1:
                showPopWindow(ATiming_1, Constants.Define.OP_WORD_D, 400);
                break;
            case R.id.ATiming_2:
                showPopWindow(ATiming_2, Constants.Define.OP_WORD_D, 402);
                break;
            case R.id.ATiming_3:
                showPopWindow(ATiming_3, Constants.Define.OP_WORD_D, 404);
                break;
            case R.id.ATiming_4:
                showPopWindow(ATiming_4, Constants.Define.OP_WORD_D, 406);
                break;
            case R.id.ATiming_5:
                showPopWindow(ATiming_5, Constants.Define.OP_WORD_D, 408);
                break;
            case R.id.ATiming_6:
                showPopWindow(ATiming_6, Constants.Define.OP_WORD_D, 410);
                break;
            case R.id.ATiming_7:
                showPopWindow(ATiming_7, Constants.Define.OP_WORD_D, 412);
                break;
            case R.id.ATiming_8:
                showPopWindow(ATiming_8, Constants.Define.OP_WORD_D, 414);
                break;
            case R.id.ATiming_9:
                showPopWindow(ATiming_9, Constants.Define.OP_WORD_D, 416);
                break;
            case R.id.ATiming_10:
                showPopWindow(ATiming_10, Constants.Define.OP_WORD_D, 418);
                break;
            case R.id.ATiming_11:
                showPopWindow(ATiming_11, Constants.Define.OP_WORD_D, 420);
                break;
            case R.id.ATiming_12:
                showPopWindow(ATiming_12, Constants.Define.OP_WORD_D, 422);
                break;
            case R.id.ATiming_13:
                showPopWindow(ATiming_13, Constants.Define.OP_WORD_D, 424);
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
                PopUtils.setBackgroundAlpha(1.0f, ATimingActivity.this);//设置Popw消失背景为透明
            }
        });
        PopUtils.setBackgroundAlpha(0.3f, ATimingActivity.this);//设置popw出现时背景透明度
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
                    short i = Short.parseShort(s);
                    short[] b2 = new short[1];
                    b2[0] = i;
                    MyApplication.getInstance().mdbuswriteword(type, b2, stadr, 1);
                }
//            	setData();  
                pw.dismiss();
            }
        });
    }
}
