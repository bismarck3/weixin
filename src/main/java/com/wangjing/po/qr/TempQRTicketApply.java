package com.wangjing.po.qr;

public class TempQRTicketApply {

    private String action_name = "QR_SCENE";

    private int expire_seconds;

    private TempQRScene action_info;

    public String getAction_name() {
        return action_name;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public TempQRScene getAction_info() {
        return action_info;
    }

    public void setAction_info(TempQRScene action_info) {
        this.action_info = action_info;
    }
}
