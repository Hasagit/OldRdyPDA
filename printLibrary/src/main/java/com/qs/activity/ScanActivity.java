package com.qs.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qs.demo3506.R;
import com.qs.service.ScanService;

public class ScanActivity extends Activity {

    private Button mBtnOpen = null;
    private Button mBtnClose = null;
    private Button mBtnScan = null;
    private EditText mTv = null;

//    private PosApi mApi = null;

    private byte mGpioPower = 0x1E ;//PB14
    private byte mGpioTrig = 0x29 ;//PC9

    private int mCurSerialNo = 3; //usart3
    private int mBaudrate = 4; //9600

//    private ScanBroadcastReceiver scanBroadcastReceiver;

//    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
//        mApi = App.getInstance().getPosApi();

//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addAction(PosApi.ACTION_POS_COMM_STATUS);
//        registerReceiver(receiver, mFilter);

        initViews();

//        scanBroadcastReceiver = new ScanBroadcastReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("ismart.intent.scandown");
//        this.registerReceiver(scanBroadcastReceiver, intentFilter);

//        player= MediaPlayer.create(getApplicationContext(), R.raw.beep);
    }

    private void initViews(){
        mBtnOpen = (Button)this.findViewById(R.id.btn_open);
        mBtnClose = (Button)this.findViewById(R.id.btn_close);
        mBtnScan = (Button)this.findViewById(R.id.btn_scan);
        mTv = (EditText)this.findViewById(R.id.tv);

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDevice();
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                closeDevice();
            	mTv.setText("");
            }
        });
        
        mBtnScan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                    	ScanService.mApi.gpioControl(mGpioTrig, 0, 0);
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                    	ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
                        break;
                    }

                    default:

                        break;
                }
                return false;
            }
        });


    }
    
    private void openDevice(){
        //open power
    	
    	ScanService.mApi.gpioControl(mGpioPower,0,1);

    	ScanService.mApi.extendSerialInit(mCurSerialNo, mBaudrate, 1, 1, 1, 1);
        
    }

    private void closeDevice(){
        //close power
    	ScanService.mApi.gpioControl(mGpioPower,0,0);
    	ScanService.mApi.extendSerialClose(mCurSerialNo);
    }

//    BroadcastReceiver receiver = new BroadcastReceiver(){
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // TODO Auto-generated method stub
//            String action  = intent.getAction();
//            if(action.equalsIgnoreCase(PosApi.ACTION_POS_COMM_STATUS)){
//                int cmdFlag =intent.getIntExtra(PosApi.KEY_CMD_FLAG, -1);
//                int status	=intent.getIntExtra(PosApi.KEY_CMD_STATUS , -1);
//                int bufferLen = intent.getIntExtra(PosApi.KEY_CMD_DATA_LENGTH, 0);
//                byte [] buffer =intent.getByteArrayExtra(PosApi.KEY_CMD_DATA_BUFFER);
//
//                switch(cmdFlag){
//                    case PosApi.POS_EXPAND_SERIAL_INIT:
//                        if(status == PosApi.COMM_STATUS_SUCCESS){
//                            mTv.append("open success \n");
//                        }else{
//                            mTv.append("open fail \n");
//                        }
//
//                        break;
//                    case PosApi.POS_EXPAND_SERIAL3:
//                        if(buffer == null) return;
//
//                        StringBuffer sb = new StringBuffer();
//                        for(int i = 0;i<buffer.length;i++){
//                            if(buffer[i]==0x0D){
//                                sb.append("\n");
//                            }else{
//                                sb.append((char)buffer[i]);
//                            }
//                            //Log.d("hello", "char :"+(char)buffer[i]+"   isLowerCase:"+Character.isUpperCase((char)buffer[i]));
//                        }
//
//                        player.start();
//
//                        mTv.append(sb.toString());
//                        
//                        Intent intentBroadcast = new Intent();
//    					Intent intentBroadcast1 = new Intent();
//    					intentBroadcast.setAction("com.qs.scancode");
//    					intentBroadcast1.setAction("com.zkc.scancode");
//    					intentBroadcast.putExtra("code",sb.toString().trim());
//    					intentBroadcast1.putExtra("code",sb.toString().trim());
//    					sendBroadcast(intentBroadcast);
//    					sendBroadcast(intentBroadcast1);
//
//                        break;
//
//                }
//
//                buffer = null;
//            }
//        }
//
//    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        unregisterReceiver(receiver);
//        unregisterReceiver(scanBroadcastReceiver);
//        closeDevice();
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		openDevice();
	}

//	class ScanBroadcastReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // TODO Auto-generated method stub
//        	ScanService.mApi.gpioControl(mGpioTrig, 0, 0);
//                try {
//                    Thread.sleep(100);
//                }catch (Exception e){
//                }
//                ScanService.mApi.gpioControl(mGpioTrig, 0, 1);
//        }
//    }
	
}
