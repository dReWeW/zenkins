package io.kenxue.devops.sharedataobject.machine.enums;

public enum GroupEnum {

    BUILDER_MACHINE("efd2cf8deba8462f89cf2c4b672b2ca3");

    private String uuid;

    GroupEnum(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
