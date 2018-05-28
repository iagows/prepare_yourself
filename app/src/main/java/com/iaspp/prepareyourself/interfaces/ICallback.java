package com.iaspp.prepareyourself.interfaces;

import com.iaspp.prepareyourself.dto.AbstractResponseDTO;

public interface ICallback {
    public void onSucess(AbstractResponseDTO dto);
    public void onFail(String error);

}
