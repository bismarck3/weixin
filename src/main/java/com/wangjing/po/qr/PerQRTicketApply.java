package com.wangjing.po.qr;

public class PerQRTicketApply {

    private String action_name = "QR_LIMIT_STR_SCENE";

    private PerQRScene action_info;

    public String getAction_name() {
        return action_name;
    }

    public PerQRScene getAction_info() {
        return action_info;
    }

    public void setAction_info(PerQRScene action_info) {
        this.action_info = action_info;
    }
}
