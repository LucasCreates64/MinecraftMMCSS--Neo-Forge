package dev.lucascreates64.minecraftmmcss;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import dev.lucascreates64.minecraftmmcss.util.LoggerInterface;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


public class MinecraftMMCSS{
    public MinecraftMMCSS(IEventBus modEventBus){
        modEventBus.addListener(this::setThread);
    }

    private void setThread(final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
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
        });
    }
}
