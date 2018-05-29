package com.iaspp.prepareyourself.config;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.interfaces.IDTO;

import java.util.List;

public class TMDbConfig implements IDTO{
    private TMDbImagesConfig images;

    public TMDbImagesConfig getImages() {
        return images;
    }

    public void setImages(TMDbImagesConfig images) {
        this.images = images;
    }
}
