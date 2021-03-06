package com.adventureframework.stats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * A simple class that handles events pertaining to the Stats module.
 */
public class StatsInterceptor
{
    @SubscribeEvent
    public void interceptHurtEvent(LivingHurtEvent event)
    {
        if(!(event.entityLiving instanceof EntityPlayer))
            return;

        EntityPlayer player = (EntityPlayer) event.entityLiving;
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {

    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase derp = event.entityLiving;
    }
}
