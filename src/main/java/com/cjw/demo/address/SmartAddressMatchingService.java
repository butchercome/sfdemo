package com.cjw.demo.address;

import com.cjw.demo.address.entity.Result;
import com.cjw.demo.address.entity.SmartAddressResp;

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
