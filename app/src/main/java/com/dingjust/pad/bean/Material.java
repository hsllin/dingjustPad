package com.dingjust.pad.bean;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class Material {
    private String imageUrl;
    private String code;
    private String materialName;

    public Material(String imageUrl, String code, String materialName) {
        this.imageUrl = imageUrl;
        this.code = code;
        this.materialName = materialName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
