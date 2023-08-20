/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.alert.strategy;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.shenyu.alert.WeiXinProp;
import org.apache.shenyu.common.utils.GsonUtils;
import org.apache.shenyu.spi.Join;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Alert Strategy for WeiXin. <p>Reference documentation <a href="https://developer.work.weixin.qq.com/document/path/91770">Swarm Robot Configuration Instructions</a> </p>
 */
@Join
public class WeiXinStrategy implements AlertStrategy {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient()
            .newBuilder().connectTimeout(50L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .build();

    @Override
    public void execute(String handle) throws Exception {

        WeiXinProp weiXinProp = GsonUtils.getInstance().fromJson(handle, WeiXinProp.class);

        Map<String, String> content = new HashMap<>();
        content.put("content", weiXinProp.getContent());

        Map<String, Object> body = new HashMap<>();
        body.put("msgtype", "markdown");
        body.put("markdown", content);

        Response response = OK_HTTP_CLIENT.newCall(new Request.Builder()
                .url(weiXinProp.getWebHookUrl())
                .post(RequestBody.create(GsonUtils.getInstance().toJson(body), MediaType.parse("application/json")))
                .build()).execute();

    }

}
