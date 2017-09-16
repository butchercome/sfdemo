package com.cjw.demo.doc.address;

import com.cjw.demo.doc.address.entity.Result;
import com.cjw.demo.doc.address.entity.SmartAddressResp;

import java.io.IOException;

public interface SmartAddressMatchingService {
    /**
     * 得到解析后的地址数据
     * @param requestAddress
     * @return
     * @throws IOException
     */

    Result<SmartAddressResp> getAddressDividing(String requestAddress);
}
