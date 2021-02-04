package fr.skyfighttv.cts.utils;

public enum Files {
    Config("config"),
    Kits("kits"),
    Lang("lang"),
    Temp("temp"),
    Spawn("spawn"),
    Sheep("sheep"),
    Zone("zone");

    private final String name;

    Files(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
