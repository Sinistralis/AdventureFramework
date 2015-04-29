package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureConfigurable;
import com.adventureframework.core.IConfigurable;
import com.adventureframework.core.enums.AttributeCategory;

import java.util.HashMap;
import java.util.Map;


public class Attribute implements IConfigurable {

    private String name = "Attribute";
    private String description = "Need Description";
    private Boolean isEnabled = true;
    private Integer attributeWeight = 0;
    private AttributeCategory category = AttributeCategory.PRIMARY;

    private int baseValue = 0;
    private int flatModifier = 0;
    private float multiplicativeModifier = 1;

    private int currentTotalValue = 0;

    private int calculateTotalValue()
    {
        int result = (int) Math.floor((baseValue + flatModifier) * (multiplicativeModifier));
        return result >= 0 ? result : 0;
    }

    public static Attribute cloneStructure(Attribute toClone)
    {
        Attribute clone = new Attribute();
        clone.setName(toClone.name).setDescription(toClone.description).setCategory(toClone.category).setAttributeWeight(toClone.attributeWeight).setEnabled(toClone.isEnabled);
        return clone;
    }

    public String getName() {
        return name;
    }

    public Attribute setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Attribute setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Attribute setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public int getAttributeWeight() {
        return attributeWeight;
    }

    public Attribute setAttributeWeight(int attributeWeight) {
        this.attributeWeight = attributeWeight;
        return this;
    }

    public AttributeCategory getCategory() {
        return category;
    }

    public Attribute setCategory(AttributeCategory category) {
        this.category = category;
        return this;
    }

    public void alterBaseValue(float amount) {
        baseValue += amount;
        currentTotalValue = calculateTotalValue();
    }

    public void alterFlatModifier(int amount) {
        flatModifier += amount;
        currentTotalValue = calculateTotalValue();
    }

    public void alterMultiplicativeModifier(float amount) {
        multiplicativeModifier += amount;
        currentTotalValue = calculateTotalValue();
    }

    public double getCurrentTotalValue() {
        return currentTotalValue;
    }

    public Map<String, AdventureConfigurable> writeConfig()
    {
        Map<String, AdventureConfigurable> config = new HashMap<>();

        config.put("Description",new AdventureConfigurable().setConfigValue(description).setDescription("Describes what the attribute does. Primarily used in tooltips."));
        config.put("Enabled",new AdventureConfigurable().setConfigValue(isEnabled).setDescription("Determines if this attribute should be loaded. Requires a server restart to function. Removing this will have unexpected consequences on gear and is highly recommended to not change once you have the initial server configurations setup. The system will try to just delete the property from gear, but issues may occur."));
        config.put("Weight",new AdventureConfigurable().setConfigValue(attributeWeight).setDescription("The weight per point of this attribute when assigning properties to gear. Higher weight means this attribute appears on gear with lower values, or less combinations."));
        config.put("Category",new AdventureConfigurable().setConfigValue(category).setDescription("The category this attribute is calculated against when determining which properties to apply on gear."));

        return config;
    }

    public void loadConfig(Map<String, String> attributeConfig)
    {
        description = attributeConfig.get("Description");
        isEnabled = Boolean.valueOf(attributeConfig.get("Enabled"));
        attributeWeight = Integer.parseInt(attributeConfig.get("Weight"));
        category = AttributeCategory.valueOf(attributeConfig.get("Category").toUpperCase());
    }
}