package com.zhaochuninhefei.webpmsbtvt.dto;

/**
 * @author zhaochun
 */
@SuppressWarnings({"LombokGetterMayBeUsed", "LombokSetterMayBeUsed", "unused"})
public class Asset {
    private Integer id;

    private String name;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
