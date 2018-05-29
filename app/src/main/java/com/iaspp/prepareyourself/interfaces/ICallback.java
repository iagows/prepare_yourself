package com.iaspp.prepareyourself.interfaces;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.movie.AbstractResponseDTO;

public interface ICallback {
    public interface OnConfigLoaded {
        /**
         * When the configuration is loaded
         * @param t
         */
        void onConfig(TMDbConfig t);
    }

    public interface OnRequest {
        void onSucess(IDTO dto);
        void onFail(String msg);
    }

}
