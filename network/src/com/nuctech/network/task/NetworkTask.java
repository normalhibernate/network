/*
 * @(#)CommunicationStatusTask.java 2019-4-1
 *
 * Copyright (c) 2010 by rayootech.com. All rights reserved.
 */
package com.nuctech.network.task;

import java.util.TimerTask;

import com.nuctech.network.service.NetworkTaskService;
import com.nuctech.network.spring.SpringContextUtil;

/**<p><br/>
 * @className CommunicationStatusTask.java<br/>
 * @packageName com.nuctech.network.task<br/>
 * @date 2019-4-1 上午10:49:37<br/>
 * </p>
 * 
 * @author Guo Yu Ting
 * @since 2019-4-1
 * @version v1.0.0
 */
public class NetworkTask extends TimerTask{

    private NetworkTaskService service;
    
    public NetworkTask() {
        if (service == null) {
            service = SpringContextUtil.getApplicationContext().getBean(NetworkTaskService.class);
        }
    }
    @Override
    public void run() {
        try {
            this.service.connectCheck();
        } finally {
            this.cancel();
        }
    }

}
