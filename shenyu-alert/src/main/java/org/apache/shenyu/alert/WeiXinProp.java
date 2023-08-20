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

package org.apache.shenyu.alert;

/**
 * WeiXin Prop.
 */
public class WeiXinProp {

    /**
     * The Rob key.
     */
    private String robKey;

    /**
     * The Content.
     */
    private String content;


    public String getRobKey() {
        return robKey;
    }

    public void setRobKey(String robKey) {
        this.robKey = robKey;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    /**
     * Get web hook url.
     * @return web hook url
     */
    public String getWebHookUrl() {
        // The web hook url is like this: https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx-xxx-xxx-xxx
        return String.format("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s", robKey);
    }
}
