package com.chaozusTracker.dto;

public class CloudinaryUploadResult {
    private String url;
    private String publicId;

    public CloudinaryUploadResult() {}

    public CloudinaryUploadResult(String secureUrl, String publicId) {
        this.url = secureUrl;
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
