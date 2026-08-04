package dev.girlboss.minecraftmmcss;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import dev.girlboss.minecraftmmcss.util.LoggerInterface;
import net.fabricmc.api.ClientModInitializer;

public class MinecraftMMCSS implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (!System.getProperty("os.name").contains("Windows")) {
            return;
        }

        LoggerInterface logger = LoggerInterface.getLogger();

        try {
            WinNT.HANDLE handle = Avrt.INSTANCE.AvSetMmThreadCharacteristicsW(new WString("Games"), new WinDef.DWORDByReference(new WinDef.DWORD(0)));
            if (handle == null) {
                logger.error("Failed to set thread characteristics");
                return;
            }

            logger.info("Thread characteristics set successfully");
        } catch (Throwable throwable) {
            logger.error("Failed to set thread characteristics: ", throwable);
        }
    }
}
