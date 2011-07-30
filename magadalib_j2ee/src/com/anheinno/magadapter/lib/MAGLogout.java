package com.anheinno.magadapter.lib;

import com.anheinno.magadapter.lib.MAGPushClient;

public class MAGLogout implements IMAGHandler
{

    public boolean process(MAGRequest req)
    {
        if(MAGPushClient.unregisterPush(req)) {
            req.resultOK();
            return true;
        }else {
            req.error("Fail to unregister device!");
            return false;
        }
    }

    public String getAction()
    {
        return "LOGOUT";
    }

}
