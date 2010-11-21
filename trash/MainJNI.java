package com.gennadz;


import com.sun.jna.*;
import com.sun.jna.win32.*;




public class MainJNI  {


    public static void main (String arg[]) {
        User32 lib = User32.INSTANCE;

        Pointer idw=lib.GetForegroundWindow(); //Это работает.
        int res = lib.GetWindowTextLengthA(idw);
        
        byte[] myString = new byte[res];

        lib.GetWindowTextA(idw,myString,res);

        String mySpecial = new String(myString).trim();
        System.out.println(mySpecial);
    }
    
public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32)Native.loadLibrary("User32", User32.class);
        public Pointer GetForegroundWindow();
        public int  GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);
        public int  GetWindowTextLengthA(Pointer hWnd);
    }

  
}


