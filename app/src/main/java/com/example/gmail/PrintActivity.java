package com.example.gmail;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ctk.sdk.PosApiHelper;
import java.util.Timer;

import test.apidemo.service.MyService;
/**
 * Created by Administrator on 2017/8/17.
 */

public class PrintActivity extends Activity {

    public String tag = "PrintActivity-Robert2";

    final int PRINT_TEST = 0;
    final int PRINT_UNICODE = 1;
    final int PRINT_BMP = 2;
    final int PRINT_BARCODE = 4;
    final int PRINT_CYCLE = 5;


    private RadioGroup rg = null;
    private RadioGroup rg_mode = null;
    private RadioButton rb_mode1 = null;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private int voltage_level;
    private int BatteryV;
    SharedPreferences preferences;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private RadioButton rb_high;
    private RadioButton rb_middle;
    private RadioButton rb_low;
    private RadioButton radioButton_4;
    private RadioButton radioButton_5;
    private Button gb_test;
    private Button gb_unicode;
  private Button btnBmp;


    private final static int ENABLE_RG = 10;
    private final static int DISABLE_RG = 11;

    TextView textViewMsg = null;
    TextView textViewGray = null;
    int ret = -1;
    private boolean m_bThreadFinished = true;

    private boolean is_cycle = false;
    private int cycle_num = 0;

    private int RESULT_CODE = 0;
    //private Pos pos;
    int IsWorking = 0;

    PosApiHelper posApiHelper = PosApiHelper.getInstance();

    Intent mPrintServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_print);
        //linearLayout = (LinearLayout) this.findViewById(R.id.widget_layout_print);
        textViewMsg = this.findViewById(R.id.textView_msg);
        textViewGray = this.findViewById(R.id.textview_Gray);
        rg = this.findViewById(R.id.rg_Gray_type);
        rb_high = findViewById(R.id.RadioButton_high);
        rb_middle = findViewById(R.id.RadioButton_middle);
        rb_low = findViewById(R.id.radioButton_low);
        radioButton_4 = findViewById(R.id.radioButton_4);
        radioButton_5 = findViewById(R.id.radioButton_5);
        gb_test = findViewById(R.id.button_test);
        gb_unicode = findViewById(R.id.button_unicode);
        btnBmp = findViewById(R.id.btnBmp);


        /*printer mode*/
        rg_mode = this.findViewById(R.id.rg_Gray_mode2);
        rb_mode1 = this.findViewById(R.id.RadioButton_mode1);

        try {
            init_Gray();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (printThread != null && !printThread.isThreadFinished()) {

                    Log.e(tag, "Thread is still running...");
                    return;
                }

                String strGray=getResources().getString(R.string.selectGray);

                if (checkedId == R.id.radioButton_low) {
                    textViewGray.setText(strGray + "3");
                    try {
                        posApiHelper.PrintSetGray(3);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    setValue(3);
                } else if (checkedId == R.id.RadioButton_middle) {
                    textViewGray.setText(strGray + "2");
                    try {
                        posApiHelper.PrintSetGray(2);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    setValue(2);
                } else if (checkedId == R.id.RadioButton_high) {
                    textViewGray.setText(strGray + "1");
                    try {
                        posApiHelper.PrintSetGray(1);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    setValue(1);
                } else if (checkedId == R.id.radioButton_4) {
                    textViewGray.setText(strGray + "4");
                    try {
                        posApiHelper.PrintSetGray(4);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    setValue(4);
                } else if (checkedId == R.id.radioButton_5) {
                    textViewGray.setText(strGray + "5");
                    try {
                        posApiHelper.PrintSetGray(5);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    setValue(5);
                }
            }
        });

        /*print mode*/
        rb_mode1.setChecked(true);

        handler.sendEmptyMessage(0x34);
        rg_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (printThread != null && !printThread.isThreadFinished()) {

                    Log.e(tag, "Thread is still running...");
                    return;
                }

                if (checkedId == R.id.RadioButton_mode1) {
                    try {
                        posApiHelper.PrintSetMode(0);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    handler.sendEmptyMessage(0x34);
                }else {
                    handler.sendEmptyMessage(0x34);
                    try {
                        posApiHelper.PrintSetMode(0);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private void setValue(int val) {
        sp = getSharedPreferences("Gray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("value", val);
        editor.commit();
    }

    private int getValue() {
        sp = getSharedPreferences("Gray", MODE_PRIVATE);
        int value = sp.getInt("value", 2);
        return value;
    }

    private void init_Gray() throws RemoteException {
        int flag = getValue();
        posApiHelper.PrintSetGray(flag);

        String strGray=getResources().getString(R.string.selectGray);

        if (flag == 3) {
            rb_low.setChecked(true);
            textViewGray.setText(strGray+"3");
        }else if(flag == 2){
            rb_middle.setChecked(true);
            textViewGray.setText(strGray+"2");
        }else if(flag == 1){
            rb_high.setChecked(true);
            textViewGray.setText(strGray+"1");
        }else if(flag == 4){
            radioButton_4.setChecked(true);
            textViewGray.setText(strGray+"4");
        }else if(flag == 5){
            radioButton_5.setChecked(true);
            textViewGray.setText(strGray+"5");
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onResume();
        filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onPause();
        QuitHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("onKeyDown", "keyCode = " + keyCode);

        Log.d("ROBERT2 onKeyDown", "keyCode = " + keyCode);
        Log.d("ROBERT2 onKeyDown", "IsWorking== " + IsWorking);
        if (keyCode == event.KEYCODE_BACK) {
            if (IsWorking == 1)
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onClickTest(View v) {
        if (printThread != null && !printThread.isThreadFinished()) {
            Log.e(tag, "Thread is still running...");
            return;
        }

        printThread = new Print_Thread(PRINT_TEST);
        printThread.start();
    }

    public void onClickUnicodeTest(View v) {
        if (printThread != null && !printThread.isThreadFinished()) {
            Log.e(tag, "Thread is still running...");
            return;
        }

        printThread = new Print_Thread(PRINT_UNICODE);
        printThread.start();

    }

    public void OnClickBarcode(View view) {
        if (printThread != null && !printThread.isThreadFinished()) {
            Log.e(tag, "Thread is still running...");
            return;
        }

        printThread = new Print_Thread(PRINT_BARCODE);
        printThread.start();
    }

    public void onClickBmp(View view) {
        if (printThread != null && !printThread.isThreadFinished()) {
            Log.e(tag, "Thread is still running...");
            return;
        }

        printThread = new Print_Thread(PRINT_BMP);
        printThread.start();

    }

    public void onClickCycle(View v) {
        if (printThread != null && !printThread.isThreadFinished()) {
            Log.e(tag, "Thread is still running...");
            return;
        }

        if (is_cycle == false) {
            is_cycle = true;
            preferences = getSharedPreferences("count", MODE_PRIVATE);

            cycle_num = preferences.getInt("count", 0);
            SendMsg("total cycle num =" + cycle_num);
            Log.e(tag, "Thread is still 3000ms...");
            handlers.postDelayed(runnable, 3000);

        }else{
            handlers.removeCallbacks(runnable);
            is_cycle = false;
        }


    }


    public void QuitHandler() {
        is_cycle = false;
        gb_test.setEnabled(true);
        btnBmp.setEnabled(true);
        gb_unicode.setEnabled(true);
        handlers.removeCallbacks(runnable);
    }

    Handler handlers = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            Log.e(tag, "TIMER log...");
            printThread = new Print_Thread(PRINT_CYCLE);
            printThread.start();

            Log.e(tag, "TIMER log2...");
            if (RESULT_CODE == 0) {
                editor = preferences.edit();
                editor.putInt("count", ++cycle_num);
                editor.commit();
                Log.e(tag, "cycle num=" + cycle_num);
                SendMsg("cycle num =" + cycle_num);
            }
            handlers.postDelayed(this, 15000);

        }
    };

    Print_Thread printThread = null;

    public class Print_Thread extends Thread {

        String content = "1234567890";
        int type;

        public boolean isThreadFinished() {
            return m_bThreadFinished;
        }

        public Print_Thread(int type) {
            this.type = type;
        }

        public void run() {
            Log.d("Robert2", "Print_Thread[ run ] run() begin");
            Message msg = Message.obtain();
            Message msg1 = new Message();

            synchronized (this) {

                m_bThreadFinished = false;
                try {
                    ret = posApiHelper.PrintInit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.e(tag, "init code:" + ret);

                ret = getValue();
                Log.e(tag, "getValue():" + ret);

                try {
                    posApiHelper.PrintSetGray(ret);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                Log.e(tag, "PrintSetGray():" );

                try {
                    ret = posApiHelper.PrintCheckStatus();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                Log.e(tag, "PrintCheckStatus():" );
                if (ret == -1) {
                    RESULT_CODE = -1;
                    Log.e(tag, "Lib_PrnCheckStatus fail, ret = " + ret);
                    SendMsg("Error, No Paper ");
                    m_bThreadFinished = true;
                    return;
                } else if (ret == -2) {
                    RESULT_CODE = -1;
                    Log.e(tag, "Lib_PrnCheckStatus fail, ret = " + ret);
                    SendMsg("Error, Printer Too Hot ");
                    m_bThreadFinished = true;
                    return;
                } else if (ret == -3) {
                    RESULT_CODE = -1;
                    Log.e(tag, "voltage = " + (BatteryV * 2));
                    SendMsg("Battery less :" + (BatteryV * 2));
                    m_bThreadFinished = true;
                    return;
                }
                else
                {
                    RESULT_CODE = 0;
                }
                Log.d("Robert2", "Lib_PrnStart type= "+type );


                switch (type) {
                    case PRINT_TEST:
                        Log.d("Robert2", "Lib_PrnStart ret START0 " );
                        SendMsg("PRINT_TEST");
                        msg.what = DISABLE_RG;
                        handler.sendMessage(msg);

                        try {
                            posApiHelper.PrintSetFont((byte) 24, (byte) 24, (byte) 0x00);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            posApiHelper.PrintStr("中文:你好，好久不见。\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("英语:Hello, Long time no see   ￡ ：2089.22\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("意大利语Italian :Ciao, non CI vediamo da Molto Tempo.\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("西班牙语:España, ¡Hola! Cuánto tiempo sin verte!\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("法语:Bonjour! Ça fait longtemps!\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("ABCDEFGHIJKLMNHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNHIJKLMNOPQRSTUVWXYZ\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("12345678901234567890123456789012345678901234567890+_)(*&^%$#@!~\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("                                         \n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("                                         \n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        SendMsg("Printing... ");
                        try {
                            ret = posApiHelper.PrintStart();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        msg1.what = ENABLE_RG;
                        handler.sendMessage(msg1);

                        Log.d("Robert2", "Lib_PrnStart ret = " + ret);

                        if (ret != 0) {
                            RESULT_CODE = -1;
                            Log.e("liuhao", "Lib_PrnStart fail, ret = " + ret);
                            if (ret == -1) {
                                SendMsg("No Print Paper ");
                            } else if(ret == -2) {
                                SendMsg("too hot ");
                            }else if(ret == -3) {
                                SendMsg("low voltage ");
                            }else{
                                SendMsg("Print fail ");
                            }
                        } else {
                            RESULT_CODE = 0;
                            SendMsg("Print Finish ");
                        }
                        Log.d("Robert2", "Lib_PrnStart ret9 " );
                        break;


                    case PRINT_UNICODE:
                        Log.d("Robert2", "Lib_PrnStart ret START11 " );
                        final long starttime = System.currentTimeMillis();
                        Log.e("Robert2", "PRINT_UNICODE starttime = " + starttime);

                        SendMsg("PRINT_UNICODE");
                        msg.what = DISABLE_RG;
                        handler.sendMessage(msg);
                        try {
                            posApiHelper.PrintSetFont((byte) 24, (byte) 24, (byte) 0x00);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            posApiHelper.PrintStr("中文:你好，好久不见。\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("英语: ￡20.00 ，￡20.00 ，￡20.00 Hello, Long time no see\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("西班牙语:España, ¡Hola! Cuánto tiempo sin verte!\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("法语:Bonjour! Ça fait longtemps!\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("Italian :Ciao, non CI vediamo da Molto Tempo.\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }


                        SendMsg("Printing... ");
                        //ret = posApiHelper.PrintCtnStart();
                        try {
                            ret = posApiHelper.PrintStart();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            posApiHelper.PrintSetFont((byte) 16, (byte) 16, (byte) 0x33);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 1; i < 3; i++) {
                            try {
                                posApiHelper.PrintSetFont((byte) 24, (byte) 24, (byte) 0x33);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("打印第：" + i + "次\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("商户存根MERCHANT COPY\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintSetFont((byte) 24, (byte) 24, (byte) 0x00);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("商户名称(MERCHANT NAME):\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("中国银联直连测试\n");
                            } catch (RemoteException e) {
                                try {
                                    throw new RuntimeException(e);
                                } catch (RuntimeException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            try {
                                posApiHelper.PrintStr("商户编号(MERCHANT NO):\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("    001420183990573\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("终端编号(TERMINAL NO):00026715\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("操作员号(OPERATOR NO):12345678\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            //	posApiHelper.PrintStr("\n");
                            try {
                                posApiHelper.PrintStr("发卡行(ISSUER):01020001 工商银行\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("卡号(CARD NO):\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("    9558803602109503920\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("收单行(ACQUIRER):03050011民生银行\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("交易类型(TXN. TYPE):消费/SALE\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("卡有效期(EXP. DATE):2013/08\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            //	posApiHelper.PrintStr("\n");
                            try {
                                posApiHelper.PrintStr("批次号(BATCH NO)  :000023\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("凭证号(VOUCHER NO):000018\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("授权号(AUTH NO)   :987654\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("日期/时间(DATE/TIME):\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("    2008/01/28 16:46:32\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("交易参考号(REF. NO):200801280015\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("金额(AMOUNT):  RMB:2.55\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            //	posApiHelper.PrintStr("\n");
                            try {
                                posApiHelper.PrintStr("备注/REFERENCE\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintSetFont((byte) 16, (byte) 16, (byte) 0x00);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("持卡人签名(CARDHOLDER SIGNATURE)\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("- - - - - - - - - - - - - - - - - - - - - - - -\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            //	posApiHelper.PrintStr("\n");
                            try {
                                posApiHelper.PrintStr("  本人确认以上交易，同意将其计入本卡帐户\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("  I ACKNOWLEDGE SATISFACTORY RECEIPT\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("\n\n\n\n\n\n\n\n\n\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }

                            //  ret = posApiHelper.PrintCtnStart();
                            try {
                                ret = posApiHelper.PrintStart();
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            // if (ret != 0) break;
                        }

                        msg1.what = ENABLE_RG;
                        handler.sendMessage(msg1);
                        Log.d("", "Lib_PrnStart ret = " + ret);
                        if (ret != 0) {
                            RESULT_CODE = -1;
                            Log.e("liuhao", "Lib_PrnStart fail, ret = " + ret);
                            if (ret == -1) {
                                SendMsg("No Print Paper ");
                            } else if(ret == -2) {
                                SendMsg("too hot ");
                            }else if(ret == -3) {
                                SendMsg("low voltage ");
                            }else{
                                SendMsg("Print fail ");
                            }
                        } else {
                            RESULT_CODE = 0;
                            SendMsg("Print Finish ");

                            final long endttime = System.currentTimeMillis();
                            Log.e("printtime", "PRINT_UNICODE endttime = " + endttime);
                            final long totaltime = starttime - endttime;
                            //SendMsg("Print Finish totaltime" + totaltime);
                            SendMsg("Print finish" );
                        }

                        //ret = posApiHelper.PrintClose();
                        break;


                    case PRINT_BMP:
                        SendMsg("PRINT_BMP");
                        msg.what = DISABLE_RG;
                        handler.sendMessage(msg);

                        //   Bitmap bmp = BitmapFactory.decodeResource(PrintActivity.this.getResources(), R.mipmap.metrolinx1bitdepth);
                        final long start_BmpD = System.currentTimeMillis();

                        Bitmap bmp1 = BitmapFactory.decodeResource(PrintActivity.this.getResources(), R.mipmap.test001);
                        final long end_BmpD = System.currentTimeMillis();
                        final long decodetime = end_BmpD - start_BmpD;
                        final long start_PrintBmp = System.currentTimeMillis();
                        try {
                            ret = posApiHelper.PrintBmp(bmp1);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            posApiHelper.PrintStr("\n");
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                        if (ret == 0) {
                            try {
                                posApiHelper.PrintStr("\n\n\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                posApiHelper.PrintStr("\n");
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }

                            SendMsg("Printing... ");
                            // ret = posApiHelper.PrintCtnStart();
                            try {
                                ret = posApiHelper.PrintStart();
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                            msg1.what = ENABLE_RG;
                            handler.sendMessage(msg1);

                            Log.d("", "Lib_PrnStart ret = " + ret);
                            if (ret != 0) {
                                RESULT_CODE = -1;
                                Log.e("liuhao", "Lib_PrnStart fail, ret = " + ret);
                                if (ret == -1) {
                                    SendMsg("No Print Paper ");
                                } else if(ret == -2) {
                                    SendMsg("too hot ");
                                }else if(ret == -3) {
                                    SendMsg("low voltage ");
                                }else{
                                    SendMsg("Print fail ");
                                }
                            } else {
                                final long end_PrintBmp = System.currentTimeMillis();

                                RESULT_CODE = 0;
                                final long PrintTime = start_PrintBmp - end_PrintBmp;
                                SendMsg("Print Finish");
                                // SendMsg("Print Finish BMP decodetime="+decodetime + "PrintBmpTime"+PrintTime);
                            }
                        } else {
                            RESULT_CODE = -1;
                            SendMsg("Lib_PrnBmp Failed");
                        }
                        break;
                    default:
                        break;
                }
                m_bThreadFinished = true;
            }
        }
    }


    public class BatteryReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            voltage_level = intent.getExtras().getInt("level");// ��õ�ǰ����
            Log.e("wbw", "current  = " + voltage_level);
            BatteryV = intent.getIntExtra("voltage", 0);  //电池电压
            Log.e("wbw", "BatteryV  = " + BatteryV);
            Log.e("wbw", "V  = " + BatteryV * 2 / 100);
            //	m_voltage = (int) (65+19*voltage_level/100); //放大十倍
            //   Log.e("wbw","m_voltage  = " + m_voltage );
        }
    }

    // 在Activity中，我们通过ServiceConnection接口来取得建立连接与连接意外丢失的回调

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.MyBinder binder = (MyService.MyBinder)service;
//            binder.getService();// 获取到的Service即MyService
            MyService.MyBinder binder = (MyService.MyBinder) service;
            MyService myService = binder.getService();

            myService.setCallback(new MyService.CallBackPrintStatus() {
                @Override
                public void printStatusChange(String strStatus) {
                    SendMsg(strStatus);
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void SendMsg(String strInfo) {
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("MSG", strInfo);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case DISABLE_RG:
                    IsWorking = 1;
                    rb_high.setEnabled(false);
                    rb_middle.setEnabled(false);
                    rb_low.setEnabled(false);
                    radioButton_4.setEnabled(false);
                    radioButton_5.setEnabled(false);
                    break;

                case ENABLE_RG:
                    IsWorking = 0;
                    rb_high.setEnabled(true);
                    rb_middle.setEnabled(true);
                    rb_low.setEnabled(true);
                    radioButton_4.setEnabled(true);
                    radioButton_5.setEnabled(true);
                    break;

                case 0x34:

                    gb_test.setEnabled(true);
                    gb_unicode.setEnabled(true);
                    btnBmp.setEnabled(true);

                    gb_test.setBackgroundColor(getResources().getColor(R.color.item_image_select));
                    gb_unicode.setBackgroundColor(getResources().getColor(R.color.item_image_select));
                    btnBmp.setBackgroundColor(getResources().getColor(R.color.item_image_select));

                    break;

                case 0x56:
                    //gb_unicode.setVisibility(View.INVISIBLE);
                    gb_test.setBackgroundColor(Color.GRAY);
                    gb_unicode.setBackgroundColor(Color.GRAY);
                    btnBmp.setBackgroundColor(Color.GRAY);

                    gb_test.setEnabled(false);
                    gb_unicode.setEnabled(false);
                    btnBmp.setEnabled(false);

                   break;

                default:
                    Bundle b = msg.getData();
                    String strInfo = b.getString("MSG");
                    textViewMsg.setText(strInfo);
                    break;
            }
        }
    };

}
