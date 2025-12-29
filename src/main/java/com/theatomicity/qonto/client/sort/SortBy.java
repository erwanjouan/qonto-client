package com.theatomicity.qonto.client.sort;

public enum SortBy {
    CREATED_AT_ASC("created_at", true),
    UPDATED_AT_ASC("updated_at", true),
    SETTLED_AT_ASC("settled_at", true),
    EMITTED_AT_ASC("emitted_at", true),
    CREATED_AT_DESC("created_at", false),
    UPDATED_AT_DESC("updated_at", false),
    SETTLED_AT_DESC("settled_at", false),
    EMITTED_AT_DESC("emitted_at", false);

    private final String property;
    private final Boolean asc;

    SortBy(final String property, final Boolean asc) {
        this.property = property;
        this.asc = asc;
    }

    public String getProperty() {
        return property;
    }

    public Boolean getAsc() {
        return asc;
    }

    public String getSorter() {
        return String.format("%s:%s", property, asc ? "asc" : "desc");
    }
}
