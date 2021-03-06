package com.adventureframework.core;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.HashMap;
import java.util.Map;

public abstract class AdventureController {

    public void init()
    {

    }

    protected void readControllerData(Configuration config, String propertyName, IConfigurable loadable)
    {
        Map<String,String> propertiesToLoad = new HashMap<>();
        String configPropertyName = propertyName.toLowerCase();
        ConfigCategory existingCategory;

        if(config.hasCategory(configPropertyName))
        {
            existingCategory = config.getCategory(configPropertyName);

            for(Map.Entry<String, Property> entry : existingCategory.entrySet())
            {
                propertiesToLoad.put(entry.getKey(), entry.getValue().getString());
            }

            loadable.loadConfig(propertiesToLoad);
        }
    }

    protected void writeControllerData(Configuration config, String propertyName, IConfigurable loadable)
    {
        Map<String,AdventureConfigurable> attributeProperties = loadable.writeConfig();
        String configPropertyName = propertyName.toLowerCase();

        for(Map.Entry<String,AdventureConfigurable> property : attributeProperties.entrySet())
        {
            config.get(configPropertyName, property.getKey(), property.getValue().getConfigValue(), property.getValue().getDescription());
        }
    }
}
