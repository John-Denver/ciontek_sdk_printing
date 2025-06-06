package com.ctk.sdk;


import android.os.IBinder;
import java.lang.reflect.Method;
import android.os.RemoteException;
import android.util.Log;
import android.graphics.Bitmap;
import java.util.List;


public class PosApiHelper {

    private static final String TAG = "PosApiHelper";
    public static final String POS_SERVICE = "posmanager";

    private ICiontekPosService mPosService;
    private static PosApiHelper mInstance;

    private PosApiHelper() {
        try {
            Class serviceManager = Class.forName("android.os.ServiceManager");
            Method method = serviceManager.getMethod("getService", String.class);
            IBinder b = (IBinder)method.invoke(serviceManager.newInstance(), POS_SERVICE);
            mPosService = ICiontekPosService.Stub.asInterface(b);
            Log.d(TAG, "get pos service success!");
            if(mPosService == null){
                Log.d(TAG, "get pos service null!");
            }
        } catch (Exception e) {
            Log.e(TAG, "get pos service Exception!");
            e.printStackTrace();
        }


    }

    public static PosApiHelper getInstance() {
        if (null == mInstance) {
            mInstance = new PosApiHelper();
        }
        return mInstance;
    }

    public String SysApiVerson(){
        return "v1.0.2";
    }
    synchronized public int installRomPackage(String romFilePath) throws RemoteException {
        if (mPosService != null) {
            return mPosService.installRomPackage(romFilePath);
        }
        return -5555;

    }

    public String getOSVersion() throws RemoteException {
        if (mPosService != null) {
            return mPosService.getOSVersion();
        }
        return null;

    }

    public String getDeviceId() throws RemoteException {
        if (mPosService != null) {
            return mPosService.getDeviceId();
        }
        return null;

    }
    synchronized public int SysLogSwitch(int level) throws RemoteException {
        if(mPosService != null){
            return  mPosService.Lib_LogSwitch(level);
        }
        return -5555;
    }

    synchronized public int SysGetRand(byte[] rnd) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_GetRand(rnd);
        }
        return -5555;
    }


    synchronized public int SysUpdate() throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_Update_32550();
        }
        return -5555;
    }


    synchronized public int SysGetVersion(byte[] buf) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_GetVersion(buf);
        }
        return -5555;
    }

    synchronized public int SysReadSN(byte[] SN) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_ReadSN(SN);
        }
        return -5555;
    }

    synchronized public int SysWriteSN(byte[] SN) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_WriteSN(SN);
        }
        return -5555;
    }

    synchronized public int SysReadChipID(byte[] buf,int len) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_ReadChipID(buf,len);
        }
        return -5555;
    }

    synchronized public int IccOpen(byte slot, byte vccMode, byte[] atr) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_IccOpen(slot,vccMode,atr);
        }
        return -5555;
    }

    synchronized public int IccClose(byte slot) throws RemoteException {
        if (mPosService != null) {
            Log.d("PosManagerService","Lib_IccClose");
            return mPosService.Lib_IccClose(slot);
        }
        return -5555;
    }

    synchronized public int IccCommand(byte slot, byte[] apduSend, byte[]  apduResp) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_IccCommand(slot,apduSend,apduResp);
        }
        return -5555;
    }

    synchronized public int IccCheck(byte slot) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_IccCheck(slot);
        }
        return -5555;
    }

    //3.0.5
    synchronized public int SC_ApduCmd(byte bslot, byte[] pbInApdu, int usInApduLen, byte[]pbOut,byte[] pbOutLen) throws RemoteException {
        if (mPosService != null) {
            return mPosService.SC_ApduCmd(bslot,pbInApdu,usInApduLen,pbOut,pbOutLen);
        }
        return -5555;
    }


    synchronized public int PrintInit() throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnInit();
        }
        return -5555;
    }


    synchronized public int PrintInit(int gray,int fontHeight, int fontWidth, int fontZoom) throws RemoteException {
        if (mPosService != null) {
            int ret = -1;
            ret =  mPosService.Lib_PrnInit();
            if(ret!=0){
                return  ret;
            }

            //setGray
            ret = mPosService.Lib_PrnSetGray(gray);
            if(ret!=0){
                return  ret;
            }


            //setFont
            ret = mPosService.Lib_PrnSetFont((byte)fontHeight,(byte)fontWidth,(byte)fontZoom);
            if(ret!=0){
                return  ret;
            }

            return  ret;
        }
        return -5555;

    }


    synchronized public int PrintSetFont(byte AsciiFontHeight, byte ExtendFontHeight, byte Zoom) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetFont( AsciiFontHeight,  ExtendFontHeight,  Zoom);
        }
        return -5555;
    }

    synchronized public int PrintSetGray(int nLevel) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetGray( nLevel);
        }
        return -5555;
    }


    synchronized public int PrintSetSpace(byte x, byte y) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetSpace( x,  y);
        }
        return -5555;
    }



    synchronized public int PrintGetFont( byte[] AsciiFontHeight,  byte[] ExtendFontHeight,  byte[] Zoom) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnGetFont( AsciiFontHeight,  ExtendFontHeight,  Zoom);
        }
        return -5555;
    }

    synchronized public int PrintStep(int pixel) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnStep( pixel);
        }
        return -5555;
    }


    synchronized public int PrintSetVoltage(int voltage) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetVoltage( voltage);
        }
        return -5555;
    }


    synchronized public int PrintIsCharge(int ischarge) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnIsCharge( ischarge);
        }
        return -5555;
    }


    synchronized public int PrintSetLinPixelDis(char LinDistance) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_SetLinPixelDis( LinDistance);
        }
        return -5555;
    }


    synchronized public int PrintStr(String str) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnStr(str);
        }
        return -5555;
    }


    synchronized public int PrintBmp(Bitmap bitmap) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnBmp(bitmap);
        }
        return -5555;
    }


    synchronized public int PrintBarcode(String contents, int desiredWidth,int desiredHeight,String barcodeFormat) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnBarcode(contents,desiredWidth,desiredHeight,barcodeFormat);
        }
        return -5555;

    }



    synchronized public int PrintQrCode_Cut(String contents, int desiredWidth,int desiredHeight, String barcodeFormat) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrintCutQrCode(contents,desiredWidth,desiredHeight,barcodeFormat);
        }
        return -5555;

    }



    synchronized public int PrintCutQrCode_Str(String contents, String printTxt ,int distance,
                                               int desiredWidth,int desiredHeight, String barcodeFormat) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrintCutQrCodeStr(contents,printTxt,distance,desiredWidth,desiredHeight,barcodeFormat);
        }
        return -5555;
    }



    synchronized public int PrintStart() throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnStart();
        }
        return -5555;
    }


    synchronized public int PrintSetLeftIndent(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetLeftIndent( x);
        }
        return -5555;
    }


    synchronized public int PrintSetAlign(int X) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetAlign( X);
        }
        return -5555;
    }


    synchronized public int PrintCharSpace(int X) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetCharSpace( X);
        }
        return -5555;
    }


    synchronized public int PrintSetLineSpace(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetLineSpace( x);
        }
        return -5555;
    }


    synchronized public int PrintSetLeftSpace(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetLeftSpace( x);
        }
        return -5555;
    }



    synchronized public int PrintSetSpeed(int iSpeed) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetSpeed( iSpeed);
        }
        return -5555;
    }



    synchronized public int PrintCheckStatus( ) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnCheckStatus( );
        }
        return -5555;
    }


    synchronized public int PrintFeedPaper(int step) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnFeedPaper( step);
        }
        return -5555;
    }


    synchronized public int PrintSetMode(int mode) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetMode(mode);
        }
        return -5555;
    }



    synchronized public int PrintSetUnderline(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetUnderline(x);
        }
        return -5555;
    }



    synchronized public int PrintSetReverse(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetReverse(x);
        }
        return -5555;
    }



    synchronized public int PrintSetBold(int x) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnSetBold(x);
        }
        return -5555;
    }


    synchronized public int PrintLogo(byte[] logo) throws RemoteException {
        if (mPosService != null) {
            return mPosService.Lib_PrnLogo(logo);
        }
        return -5555;
    }



    /*---------------------------  Fiscal  APIs---------------------------------------------------*/

    synchronized public int fiscalOpen(int baudrate,int size, int stop, char parity, char cflow) throws RemoteException {

        if (mPosService != null) {
            return mPosService.fiscalOpen(baudrate,size,stop,parity,cflow);
        }
        return -5555;

    }

    synchronized public int fiscalClose() throws RemoteException {

        if (mPosService != null) {
            return mPosService.fiscalClose();
        }
        return -5555;

    }

    synchronized public int fiscalWrite(byte[] data) throws RemoteException {
        if (mPosService != null) {
            return mPosService.fiscalWrite(data);
        }
        return -5555;

    }

    synchronized public int fiscalRead(byte[] buffer,int bufLen,int timeout) throws RemoteException {
        if (mPosService != null) {
            return mPosService.fiscalRead(buffer,bufLen,timeout);
        }
        return -5555;

    }

}
