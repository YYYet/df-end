package com.ruoyi.project.system.domain;

public enum BillStatus {
    // 枚举常量定义，包含中文描述和对应的字母标识
    PENDING("待提交", "(FDOCUMENTSTATUS = 'Z' or FDOCUMENTSTATUS = 'A' or FDOCUMENTSTATUS = 'D')"),

    REVIEWING("待审核", "FDOCUMENTSTATUS = 'B'"),
    REVIEWED("已审核", "FDOCUMENTSTATUS = 'C'"),
    SAVED("保存", "(FDOCUMENTSTATUS = 'Z' or FDOCUMENTSTATUS = 'A' or FDOCUMENTSTATUS = 'D' or FDOCUMENTSTATUS = 'B')"),
    AUDITED("已提交", "FDOCUMENTSTATUS = 'C'"),

//    REREVIEW("重新审核", "D"),
    ALL("全部", "");

    // 成员变量，存储中文描述和字母标识
    private final String description;
    private final String identifier;

    // 构造方法，初始化中文描述和字母标识
    BillStatus(String description, String identifier) {
        this.description = description;
        this.identifier = identifier;
    }

    // 获取中文描述
    public String getDescription() {
        return description;
    }

    // 获取字母标识
    public String getIdentifier() {
        return identifier;
    }

    // 根据中文描述获取枚举常量
    public static BillStatus fromDescription(String description) {
        for (BillStatus status : values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        return BillStatus.ALL;
    }

    // 根据字母标识获取枚举常量
    public static BillStatus fromIdentifier(String identifier) {
        for (BillStatus status : values()) {
            if (status.getIdentifier().equals(identifier)) {
                return status;
            }
        }
        return BillStatus.ALL;
    }

    // 覆盖toString方法，返回中文描述和字母标识
    @Override
    public String toString() {
        return "BillStatus{" +
                "description='" + description + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
