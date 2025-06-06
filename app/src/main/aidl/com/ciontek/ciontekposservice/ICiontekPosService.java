package com.ciontek.ciontekposservice;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

public interface ICiontekPosService extends IInterface {
    int Lib_GetRand(byte[] bArr) throws RemoteException;

    int Lib_GetVersion(byte[] bArr) throws RemoteException;

    int Lib_IccCheck(byte b) throws RemoteException;

    int Lib_IccClose(byte b) throws RemoteException;

    int Lib_IccCommand(byte b, byte[] bArr, byte[] bArr2) throws RemoteException;

    int Lib_IccOpen(byte b, byte b2, byte[] bArr) throws RemoteException;

    int Lib_LogSwitch(int i) throws RemoteException;

    int Lib_PrintCutQrCode(String str, int i, int i2, String str2) throws RemoteException;

    int Lib_PrintCutQrCodeStr(String str, String str2, int i, int i2, int i3, String str3) throws RemoteException;

    int Lib_PrnBarcode(String str, int i, int i2, String str2) throws RemoteException;

    int Lib_PrnBmp(Bitmap bitmap) throws RemoteException;

    int Lib_PrnCheckStatus() throws RemoteException;

    int Lib_PrnFeedPaper(int i) throws RemoteException;

    int Lib_PrnGetFont(byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException;

    int Lib_PrnInit() throws RemoteException;

    int Lib_PrnIsCharge(int i) throws RemoteException;

    int Lib_PrnLogo(byte[] bArr) throws RemoteException;

    int Lib_PrnSetAlign(int i) throws RemoteException;

    int Lib_PrnSetBold(int i) throws RemoteException;

    int Lib_PrnSetCharSpace(int i) throws RemoteException;

    int Lib_PrnSetFont(byte b, byte b2, byte b3) throws RemoteException;

    int Lib_PrnSetGray(int i) throws RemoteException;

    int Lib_PrnSetLeftIndent(int i) throws RemoteException;

    int Lib_PrnSetLeftSpace(int i) throws RemoteException;

    int Lib_PrnSetLineSpace(int i) throws RemoteException;

    int Lib_PrnSetMode(int i) throws RemoteException;

    int Lib_PrnSetReverse(int i) throws RemoteException;

    int Lib_PrnSetSpace(byte b, byte b2) throws RemoteException;

    int Lib_PrnSetSpeed(int i) throws RemoteException;

    int Lib_PrnSetUnderline(int i) throws RemoteException;

    int Lib_PrnSetVoltage(int i) throws RemoteException;

    int Lib_PrnStart() throws RemoteException;

    int Lib_PrnStep(int i) throws RemoteException;

    int Lib_PrnStr(String str) throws RemoteException;

    int Lib_ReadChipID(byte[] bArr, int i) throws RemoteException;

    int Lib_ReadSN(byte[] bArr) throws RemoteException;

    int Lib_SetLinPixelDis(char c) throws RemoteException;

    int Lib_Update_32550() throws RemoteException;

    int Lib_WriteSN(byte[] bArr) throws RemoteException;

    int SC_ApduCmd(byte b, byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws RemoteException;

    boolean addAppToInstallWhiteList(String str) throws RemoteException;

    boolean addAppToUninstallBlackList(String str) throws RemoteException;

    boolean delAppFromInstallWhiteList(String str) throws RemoteException;

    boolean delAppFromUninstallBlackList(String str) throws RemoteException;

    boolean disableAppInstallWhiteList() throws RemoteException;

    boolean disableAppUninstallBlackList() throws RemoteException;

    boolean enableAppInstallWhiteList() throws RemoteException;

    boolean enableAppUninstallBlackList() throws RemoteException;

    int fiscalClose() throws RemoteException;

    int fiscalOpen(int i, int i2, int i3, char c, char c2) throws RemoteException;

    int fiscalRead(byte[] bArr, int i, int i2) throws RemoteException;

    int fiscalWrite(byte[] bArr) throws RemoteException;

    List<String> getAppInstallWhiteList() throws RemoteException;

    List<String> getAppUninstallBlackList() throws RemoteException;

    String getDeviceId() throws RemoteException;

    String getOSVersion() throws RemoteException;

    int installRomPackage(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICiontekPosService {
        private static final String DESCRIPTOR = "com.ciontek.ciontekposservice.ICiontekPosService";
        static final int TRANSACTION_Lib_GetRand = 5;
        static final int TRANSACTION_Lib_GetVersion = 7;
        static final int TRANSACTION_Lib_IccCheck = 14;
        static final int TRANSACTION_Lib_IccClose = 12;
        static final int TRANSACTION_Lib_IccCommand = 13;
        static final int TRANSACTION_Lib_IccOpen = 11;
        static final int TRANSACTION_Lib_LogSwitch = 4;
        static final int TRANSACTION_Lib_PrintCutQrCode = 26;
        static final int TRANSACTION_Lib_PrintCutQrCodeStr = 27;
        static final int TRANSACTION_Lib_PrnBarcode = 25;
        static final int TRANSACTION_Lib_PrnBmp = 24;
        static final int TRANSACTION_Lib_PrnCheckStatus = 38;
        static final int TRANSACTION_Lib_PrnFeedPaper = 39;
        static final int TRANSACTION_Lib_PrnGetFont = 19;
        static final int TRANSACTION_Lib_PrnInit = 16;
        static final int TRANSACTION_Lib_PrnIsCharge = 22;
        static final int TRANSACTION_Lib_PrnLogo = 28;
        static final int TRANSACTION_Lib_PrnSetAlign = 32;
        static final int TRANSACTION_Lib_PrnSetBold = 43;
        static final int TRANSACTION_Lib_PrnSetCharSpace = 33;
        static final int TRANSACTION_Lib_PrnSetFont = 18;
        static final int TRANSACTION_Lib_PrnSetGray = 36;
        static final int TRANSACTION_Lib_PrnSetLeftIndent = 31;
        static final int TRANSACTION_Lib_PrnSetLeftSpace = 35;
        static final int TRANSACTION_Lib_PrnSetLineSpace = 34;
        static final int TRANSACTION_Lib_PrnSetMode = 40;
        static final int TRANSACTION_Lib_PrnSetReverse = 42;
        static final int TRANSACTION_Lib_PrnSetSpace = 17;
        static final int TRANSACTION_Lib_PrnSetSpeed = 37;
        static final int TRANSACTION_Lib_PrnSetUnderline = 41;
        static final int TRANSACTION_Lib_PrnSetVoltage = 21;
        static final int TRANSACTION_Lib_PrnStart = 30;
        static final int TRANSACTION_Lib_PrnStep = 20;
        static final int TRANSACTION_Lib_PrnStr = 23;
        static final int TRANSACTION_Lib_ReadChipID = 10;
        static final int TRANSACTION_Lib_ReadSN = 8;
        static final int TRANSACTION_Lib_SetLinPixelDis = 29;
        static final int TRANSACTION_Lib_Update_32550 = 6;
        static final int TRANSACTION_Lib_WriteSN = 9;
        static final int TRANSACTION_SC_ApduCmd = 15;
        static final int TRANSACTION_addAppToInstallWhiteList = 46;
        static final int TRANSACTION_addAppToUninstallBlackList = 51;
        static final int TRANSACTION_delAppFromInstallWhiteList = 47;
        static final int TRANSACTION_delAppFromUninstallBlackList = 52;
        static final int TRANSACTION_disableAppInstallWhiteList = 45;
        static final int TRANSACTION_disableAppUninstallBlackList = 50;
        static final int TRANSACTION_enableAppInstallWhiteList = 44;
        static final int TRANSACTION_enableAppUninstallBlackList = 49;
        static final int TRANSACTION_fiscalClose = 55;
        static final int TRANSACTION_fiscalOpen = 54;
        static final int TRANSACTION_fiscalRead = 57;
        static final int TRANSACTION_fiscalWrite = 56;
        static final int TRANSACTION_getAppInstallWhiteList = 48;
        static final int TRANSACTION_getAppUninstallBlackList = 53;
        static final int TRANSACTION_getDeviceId = 3;
        static final int TRANSACTION_getOSVersion = 2;
        static final int TRANSACTION_installRomPackage = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICiontekPosService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICiontekPosService)) {
                return new Proxy(obj);
            }
            return (ICiontekPosService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            byte[] _arg0;
            byte[] _arg02;
            byte[] _arg03;
            byte[] _arg04;
            byte[] _arg2;
            byte[] _arg22;
            byte[] _arg3;
            byte[] _arg4;
            Bitmap _arg05;
            byte[] _arg06;
            int i = code;
            Parcel parcel = data;
            Parcel parcel2 = reply;
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result = installRomPackage(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        String _result2 = getOSVersion();
                        reply.writeNoException();
                        parcel2.writeString(_result2);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String _result3 = getDeviceId();
                        reply.writeNoException();
                        parcel2.writeString(_result3);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result4 = Lib_LogSwitch(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result4);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg0_length = data.readInt();
                        if (_arg0_length < 0) {
                            _arg0 = null;
                        } else {
                            _arg0 = new byte[_arg0_length];
                        }
                        int _result5 = Lib_GetRand(_arg0);
                        reply.writeNoException();
                        parcel2.writeInt(_result5);
                        parcel2.writeByteArray(_arg0);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result6 = Lib_Update_32550();
                        reply.writeNoException();
                        parcel2.writeInt(_result6);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg0_length2 = data.readInt();
                        if (_arg0_length2 < 0) {
                            _arg02 = null;
                        } else {
                            _arg02 = new byte[_arg0_length2];
                        }
                        int _result7 = Lib_GetVersion(_arg02);
                        reply.writeNoException();
                        parcel2.writeInt(_result7);
                        parcel2.writeByteArray(_arg02);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg0_length3 = data.readInt();
                        if (_arg0_length3 < 0) {
                            _arg03 = null;
                        } else {
                            _arg03 = new byte[_arg0_length3];
                        }
                        int _result8 = Lib_ReadSN(_arg03);
                        reply.writeNoException();
                        parcel2.writeInt(_result8);
                        parcel2.writeByteArray(_arg03);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result9 = Lib_WriteSN(data.createByteArray());
                        reply.writeNoException();
                        parcel2.writeInt(_result9);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg0_length4 = data.readInt();
                        if (_arg0_length4 < 0) {
                            _arg04 = null;
                        } else {
                            _arg04 = new byte[_arg0_length4];
                        }
                        int _result10 = Lib_ReadChipID(_arg04, data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result10);
                        parcel2.writeByteArray(_arg04);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        byte _arg07 = data.readByte();
                        byte _arg1 = data.readByte();
                        int _arg2_length = data.readInt();
                        if (_arg2_length < 0) {
                            _arg2 = null;
                        } else {
                            _arg2 = new byte[_arg2_length];
                        }
                        int _result11 = Lib_IccOpen(_arg07, _arg1, _arg2);
                        reply.writeNoException();
                        parcel2.writeInt(_result11);
                        parcel2.writeByteArray(_arg2);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result12 = Lib_IccClose(data.readByte());
                        reply.writeNoException();
                        parcel2.writeInt(_result12);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        byte _arg08 = data.readByte();
                        byte[] _arg12 = data.createByteArray();
                        int _arg2_length2 = data.readInt();
                        if (_arg2_length2 < 0) {
                            _arg22 = null;
                        } else {
                            _arg22 = new byte[_arg2_length2];
                        }
                        int _result13 = Lib_IccCommand(_arg08, _arg12, _arg22);
                        reply.writeNoException();
                        parcel2.writeInt(_result13);
                        parcel2.writeByteArray(_arg22);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result14 = Lib_IccCheck(data.readByte());
                        reply.writeNoException();
                        parcel2.writeInt(_result14);
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        byte _arg09 = data.readByte();
                        byte[] _arg13 = data.createByteArray();
                        int _arg23 = data.readInt();
                        int _arg3_length = data.readInt();
                        if (_arg3_length < 0) {
                            _arg3 = null;
                        } else {
                            _arg3 = new byte[_arg3_length];
                        }
                        int _arg4_length = data.readInt();
                        if (_arg4_length < 0) {
                            _arg4 = null;
                        } else {
                            _arg4 = new byte[_arg4_length];
                        }
                        byte[] _arg42 = _arg4;
                        int i2 = _arg4_length;
                        int _result15 = SC_ApduCmd(_arg09, _arg13, _arg23, _arg3, _arg42);
                        reply.writeNoException();
                        parcel2.writeInt(_result15);
                        parcel2.writeByteArray(_arg3);
                        parcel2.writeByteArray(_arg42);
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result16 = Lib_PrnInit();
                        reply.writeNoException();
                        parcel2.writeInt(_result16);
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result17 = Lib_PrnSetSpace(data.readByte(), data.readByte());
                        reply.writeNoException();
                        parcel2.writeInt(_result17);
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result18 = Lib_PrnSetFont(data.readByte(), data.readByte(), data.readByte());
                        reply.writeNoException();
                        parcel2.writeInt(_result18);
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result19 = Lib_PrnGetFont(data.createByteArray(), data.createByteArray(), data.createByteArray());
                        reply.writeNoException();
                        parcel2.writeInt(_result19);
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result20 = Lib_PrnStep(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result20);
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result21 = Lib_PrnSetVoltage(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result21);
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result22 = Lib_PrnIsCharge(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result22);
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result23 = Lib_PrnStr(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result23);
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg05 = (Bitmap) Bitmap.CREATOR.createFromParcel(parcel);
                        } else {
                            _arg05 = null;
                        }
                        int _result24 = Lib_PrnBmp(_arg05);
                        reply.writeNoException();
                        parcel2.writeInt(_result24);
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result25 = Lib_PrnBarcode(data.readString(), data.readInt(), data.readInt(), data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result25);
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result26 = Lib_PrintCutQrCode(data.readString(), data.readInt(), data.readInt(), data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result26);
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result27 = Lib_PrintCutQrCodeStr(data.readString(), data.readString(), data.readInt(), data.readInt(), data.readInt(), data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result27);
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result28 = Lib_PrnLogo(data.createByteArray());
                        reply.writeNoException();
                        parcel2.writeInt(_result28);
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result29 = Lib_SetLinPixelDis((char) data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result29);
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result30 = Lib_PrnStart();
                        reply.writeNoException();
                        parcel2.writeInt(_result30);
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result31 = Lib_PrnSetLeftIndent(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result31);
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result32 = Lib_PrnSetAlign(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result32);
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result33 = Lib_PrnSetCharSpace(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result33);
                        return true;
                    case 34:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result34 = Lib_PrnSetLineSpace(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result34);
                        return true;
                    case 35:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result35 = Lib_PrnSetLeftSpace(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result35);
                        return true;
                    case 36:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result36 = Lib_PrnSetGray(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result36);
                        return true;
                    case 37:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result37 = Lib_PrnSetSpeed(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result37);
                        return true;
                    case 38:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result38 = Lib_PrnCheckStatus();
                        reply.writeNoException();
                        parcel2.writeInt(_result38);
                        return true;
                    case 39:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result39 = Lib_PrnFeedPaper(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result39);
                        return true;
                    case 40:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result40 = Lib_PrnSetMode(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result40);
                        return true;
                    case 41:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result41 = Lib_PrnSetUnderline(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result41);
                        return true;
                    case 42:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result42 = Lib_PrnSetReverse(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result42);
                        return true;
                    case 43:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result43 = Lib_PrnSetBold(data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result43);
                        return true;
                    case 44: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result44 = enableAppInstallWhiteList();
                        reply.writeNoException();
                        parcel2.writeInt(_result44 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 45: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result45 = disableAppInstallWhiteList();
                        reply.writeNoException();
                        parcel2.writeInt(_result45 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 46: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result46 = addAppToInstallWhiteList(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result46 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 47: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result47 = delAppFromInstallWhiteList(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result47 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 48:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> _result48 = getAppInstallWhiteList();
                        reply.writeNoException();
                        parcel2.writeStringList(_result48);
                        return true;
                    case 49: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result49 = enableAppUninstallBlackList();
                        reply.writeNoException();
                        parcel2.writeInt(_result49 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 50: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result50 = disableAppUninstallBlackList();
                        reply.writeNoException();
                        parcel2.writeInt(_result50 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 51: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result51 = addAppToUninstallBlackList(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result51 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 52: // Corrected: boolean to int conversion
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean _result52 = delAppFromUninstallBlackList(data.readString());
                        reply.writeNoException();
                        parcel2.writeInt(_result52 ? 1 : 0); // Convert boolean to int
                        return true;
                    case 53:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> _result53 = getAppUninstallBlackList();
                        reply.writeNoException();
                        parcel2.writeStringList(_result53);
                        return true;
                    case 54:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg010 = data.readInt();
                        int _arg14 = data.readInt();
                        int _arg24 = data.readInt();
                        char _arg32 = (char) data.readInt();
                        char _arg43 = (char) data.readInt();
                        char c = _arg43;
                        int _result54 = fiscalOpen(_arg010, _arg14, _arg24, _arg32, _arg43);
                        reply.writeNoException();
                        parcel2.writeInt(_result54);
                        return true;
                    case 55:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result55 = fiscalClose();
                        reply.writeNoException();
                        parcel2.writeInt(_result55);
                        return true;
                    case 56:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _result56 = fiscalWrite(data.createByteArray());
                        reply.writeNoException();
                        parcel2.writeInt(_result56);
                        return true;
                    case 57:
                        parcel.enforceInterface(DESCRIPTOR);
                        int _arg0_length5 = data.readInt();
                        if (_arg0_length5 < 0) {
                            _arg06 = null;
                        } else {
                            _arg06 = new byte[_arg0_length5];
                        }
                        int _result57 = fiscalRead(_arg06, data.readInt(), data.readInt());
                        reply.writeNoException();
                        parcel2.writeInt(_result57);
                        parcel2.writeByteArray(_arg06);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements ICiontekPosService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public int installRomPackage(String romFilePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(romFilePath);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getOSVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getDeviceId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_LogSwitch(int LogSwitch) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(LogSwitch);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_GetRand(byte[] rnd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rnd == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(rnd.length);
                    }
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(rnd);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_Update_32550() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_GetVersion(byte[] buf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (buf == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(buf.length);
                    }
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(buf);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_ReadSN(byte[] SN) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (SN == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(SN.length);
                    }
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(SN);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_WriteSN(byte[] SN) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(SN);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_ReadChipID(byte[] buf, int len) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (buf == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(buf.length);
                    }
                    _data.writeInt(len);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(buf);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_IccOpen(byte slot, byte vccMode, byte[] atr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(slot);
                    _data.writeByte(vccMode);
                    if (atr == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(atr.length);
                    }
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(atr);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_IccClose(byte slot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(slot);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_IccCommand(byte slot, byte[] apduSend, byte[] apduResp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(slot);
                    _data.writeByteArray(apduSend);
                    if (apduResp == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(apduResp.length);
                    }
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(apduResp);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_IccCheck(byte slot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(slot);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int SC_ApduCmd(byte bslot, byte[] pbInApdu, int usInApduLen, byte[] pbOut, byte[] pbOutLen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(bslot);
                    _data.writeByteArray(pbInApdu);
                    _data.writeInt(usInApduLen);
                    if (pbOut == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(pbOut.length);
                    }
                    if (pbOutLen == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(pbOutLen.length);
                    }
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(pbOut);
                    _reply.readByteArray(pbOutLen);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnInit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetSpace(byte x, byte y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(x);
                    _data.writeByte(y);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetFont(byte AsciiFontHeight, byte ExtendFontHeight, byte Zoom) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(AsciiFontHeight);
                    _data.writeByte(ExtendFontHeight);
                    _data.writeByte(Zoom);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnGetFont(byte[] AsciiFontHeight, byte[] ExtendFontHeight, byte[] Zoom) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(AsciiFontHeight);
                    _data.writeByteArray(ExtendFontHeight);
                    _data.writeByteArray(Zoom);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnStep(int pixel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(pixel);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetVoltage(int voltage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(voltage);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnIsCharge(int ischarge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ischarge);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnStr(String str) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(str);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnBmp(Bitmap bitmap) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bitmap != null) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnBarcode(String contents, int desiredWidth, int desiredHeight, String barcodeType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contents);
                    _data.writeInt(desiredWidth);
                    _data.writeInt(desiredHeight);
                    _data.writeString(barcodeType);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrintCutQrCode(String contents, int desiredWidth, int desiredHeight, String barcodeType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contents);
                    _data.writeInt(desiredWidth);
                    _data.writeInt(desiredHeight);
                    _data.writeString(barcodeType);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrintCutQrCodeStr(String qrContent, String printTxt, int distance, int desiredWidth, int desiredHeight, String barcodeType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(qrContent);
                    _data.writeString(printTxt);
                    _data.writeInt(distance);
                    _data.writeInt(desiredWidth);
                    _data.writeInt(desiredHeight);
                    _data.writeString(barcodeType);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnLogo(byte[] logo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(logo);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_SetLinPixelDis(char LinDistance) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(LinDistance);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnStart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetLeftIndent(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetAlign(int X) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(X);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetCharSpace(int X) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(X);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetLineSpace(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetLeftSpace(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetGray(int nLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nLevel);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetSpeed(int iSpeed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(iSpeed);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnCheckStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnFeedPaper(int step) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(step);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetMode(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetUnderline(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetReverse(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int Lib_PrnSetBold(int x) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(x);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean enableAppInstallWhiteList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean disableAppInstallWhiteList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean addAppToInstallWhiteList(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean delAppFromInstallWhiteList(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public List<String> getAppInstallWhiteList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(48, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createStringArrayList();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean enableAppUninstallBlackList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(49, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean disableAppUninstallBlackList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(50, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean addAppToUninstallBlackList(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    this.mRemote.transact(51, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean delAppFromUninstallBlackList(String pkgName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkgName);
                    boolean _result = false;
                    this.mRemote.transact(52, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public List<String> getAppUninstallBlackList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(53, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createStringArrayList();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int fiscalOpen(int baudrate, int size, int stop, char parity, char cflow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(baudrate);
                    _data.writeInt(size);
                    _data.writeInt(stop);
                    _data.writeInt(parity);
                    _data.writeInt(cflow);
                    this.mRemote.transact(54, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int fiscalClose() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(55, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int fiscalWrite(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    this.mRemote.transact(56, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int fiscalRead(byte[] buffer, int bufLen, int timeout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (buffer == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(buffer.length);
                    }
                    _data.writeInt(bufLen);
                    _data.writeInt(timeout);
                    this.mRemote.transact(57, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readByteArray(buffer);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
