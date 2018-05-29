package com.iaspp.prepareyourself.config;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.interfaces.IDTO;

import java.util.List;

public class TMDbConfig implements IDTO{
    private List<TMDbImagesConfig> images;

    public List<TMDbImagesConfig> getImages() {
        return images;
    }

    public void setImages(List<TMDbImagesConfig> images) {
        this.images = images;
    }
}
