package com.adventureframework.client;

import com.adventureframework.common.ProxyCommon;
import com.adventureframework.core.ControllerManager;
import com.adventureframework.core.enums.ConfigType;
import com.adventureframework.core.gui.OverlayController;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon
{
    public void preInit(FMLPreInitializationEvent event)
    {
        OverlayController overlayController = new OverlayController();
        ControllerManager.registerControllerByName(ConfigType.OVERLAY, overlayController);
    }

    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(ControllerManager.getControllerByName(ConfigType.OVERLAY));
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}

