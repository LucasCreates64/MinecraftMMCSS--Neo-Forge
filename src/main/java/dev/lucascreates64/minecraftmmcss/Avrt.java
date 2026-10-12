package dev.lucascreates64.minecraftmmcss;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

public interface Avrt extends Library {
    Avrt INSTANCE = Native.load("Avrt", Avrt.class);

    HANDLE AvSetMmThreadCharacteristicsW(WString TaskName, DWORDByReference TaskIndex);
}
