//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.psqa.framework;

public class ConfigData {
    private String fieldName;
    private String value;
    private int beginIndex;
    private int endIndex;
    private String header;
    private String footer;

    public ConfigData(String fieldName, int beginIndex, int endIndex, String value) {
        this.fieldName = fieldName;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.value = value;
    }

    public String getHeader() { return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public int getBeginIndex() {
        return this.beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
