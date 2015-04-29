package com.adventureframework.core;

import java.util.Map;

public interface IConfigurable {

    public Map<String, AdventureConfigurable> writeConfig();

    public void loadConfig(Map<String, String> config);
}
