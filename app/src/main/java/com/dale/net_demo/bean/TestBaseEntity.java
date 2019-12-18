package com.dale.net_demo.bean;

import android.text.TextUtils;

import com.dale.net.bean.NetEntity;

public class TestBaseEntity extends NetEntity {
    /**
     * data : {"cache_key":"29a479f54062851777792aadd10fef3d","config":{"api_address":"https://hnjszn.com/CP57","app_hide_buttons":{"1":"投注按钮"},"bank_charge_type":["网银转账","ATM自动柜员机","ATM现金入款","银行柜台转账","手机银行转账","其他"],"bet_api_address":"https://hnjszn.com","bet_future_num":100,"can_register":"1","channel_address":[{"api_address":"hnjszn.com","circuitInfo":"极速网络","id":1},{"api_address":"soargby999.com","circuitInfo":"999线路","id":2},{"api_address":"soargby2.com","circuitInfo":"by2线路","id":3},{"api_address":"soarginterfacehctcp.com","circuitInfo":"默认线路1","id":4}],"charge_reward":18,"client_service_url":"https://a16.chatsets.com/chat/chatClient/chatbox.jsp?companyID=995971&configID=2463&jid=8333070671&s=1","customerQQ":"客服QQ：170999999","function_switch":{"displayChatRoom":0,"displayGame":1,"displayVipSystem":1},"lhc_shengxiao":{"兔":"09,21,33,45","牛":"11,23,35,47","狗":"02,14,26,38","猪":"01,13,25,37,49","猴":"04,16,28,40","羊":"05,17,29,41","虎":"10,22,34,46","蛇":"07,19,31,43","马":"06,18,30,42","鸡":"03,15,27,39","鼠":"12,24,36,48","龙":"08,20,32,44"},"lhc_wuxing":{"1":"05,06,19,20,27,28,35,36,49","2":"01,02,09,10,17,18,31,32,39,40,47,48","3":"07,08,15,16,23,24,37,38,45,46","4":"03,04,11,12,25,26,33,34,41,42","5":"13,14,21,22,29,30,43,44"},"play_instruction_url":"https://hnjszn.com/CP57/play_instruction?type=","proxy_default_rate":0.98,"proxy_info":"彩票代理是为了促进彩票平台的销售而采取的一种市场推广的方式，成为彩票代理可以赚取彩票网站提供的丰厚佣金！彩票125代理平台支持最多5极代理机制。","proxy_info_url":"https://hnjszn.com/CP57/proxy_instruction","proxy_return_rates":[[1960,98],[1918,95.9],[1910,95.5],[1900,95],[1890,94.5],[1880,94],[1870,93.5],[1860,93],[1850,92.5],[1840,92],[1830,91.5],[1820,91],[1810,90.5],[1800,90],[20,1],[0,98]],"red_packets_url":"1","register_config":{"show_QQ":"0","show_email":"0","show_phone":"0"},"trial_account_disables":[{"hint":"试玩账号不能进行充值操作！","type":"账户充值"},{"hint":"试玩账号不能进行提现操作！","type":"提取现金"}],"trial_account_info":["1、每个试玩账号初始金额2000元","2、每个IP每天最多可以创建3个试玩账号","3、每个账号从注册开始有效期为7天，超过有效期系统自动回收","4、试玩账号不允许充值、提现操作"],"weixin_appid":"wxd4c41a1eabdc9752"}}
     * msg :
     * status : 1
     * time_consumed : 8784
     */

    private String data;
    private String msg;
    private int status;

    @Override
    public boolean isOk() {
        return status == 1;
    }

    @Override
    public String getResult() {
        return data;
    }

    @Override
    public String getErrMsg() {
        return TextUtils.isEmpty(msg) ? "请求错误" : msg;
    }

}
