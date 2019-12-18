package com.dale.net_demo.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * create by Dale
 * create on 2019/7/23
 * description:
 */
public class MyData implements Serializable{

    /**
     * cache_key : 0fbf909f93adda886bef1f1d53c719e4
     * config : {"api_address":"https://fxingedu.com/ZZCP","app_hide_buttons":{"1":"投注按钮"},"bank_charge_type":["网银转账","ATM自动柜员机","ATM现金入款","银行柜台转账","手机银行转账","其他"],"bet_api_address":"https://fxingedu.com","bet_future_num":100,"can_register":"1","channel_address":[{"api_address":"soarginterface222.com","circuitInfo":"默认线路1","id":1},{"api_address":"daqin78.com","circuitInfo":"daqin线路","id":2},{"api_address":"soargby2.com","circuitInfo":"默认线路3","id":3}],"client_service_url":"https://ored.masarn.com/chat/chatClient/chatbox.jsp?companyID=365027836&configID=3184&jid=9805587816&s=1","customerQQ":"244488618","function_switch":{"displayChatRoom":0,"displayGame":1,"displayVipSystem":1},"lhc_shengxiao":{"兔":"09,21,33,45","牛":"11,23,35,47","狗":"02,14,26,38","猪":"01,13,25,37,49","猴":"04,16,28,40","羊":"05,17,29,41","虎":"10,22,34,46","蛇":"07,19,31,43","马":"06,18,30,42","鸡":"03,15,27,39","鼠":"12,24,36,48","龙":"08,20,32,44"},"lhc_wuxing":{"1":"05,06,19,20,27,28,35,36,49","2":"01,02,09,10,17,18,31,32,39,40,47,48","3":"07,08,15,16,23,24,37,38,45,46","4":"03,04,11,12,25,26,33,34,41,42","5":"13,14,21,22,29,30,43,44"},"play_instruction_url":"https://fxingedu.com/ZZCP/play_instruction?type=","proxy_default_rate":0.98,"proxy_info":"彩票代理是为了促进彩票平台的销售而采取的一种市场推广的方式，成为彩票代理可以赚取彩票网站提供的丰厚佣金！彩票125代理平台支持最多5极代理机制。","proxy_info_url":"https://fxingedu.com/ZZCP/proxy_instruction","proxy_return_rates":[[1960,98],[1920,96],[1910,95.5],[1900,95],[1890,94.5],[1880,94],[1870,93.5],[1860,93],[1850,92.5],[1840,92],[1830,91.5],[1820,91],[1810,90.5],[1800,90],[20,1],[0,98]],"red_packets_url":"1","register_config":{"show_QQ":"0","show_email":"0","show_phone":"0"},"trial_account_disables":[{"hint":"试玩账号不能进行充值操作！","type":"账户充值"},{"hint":"试玩账号不能进行提现操作！","type":"提取现金"}],"trial_account_info":["1、每个试玩账号初始金额2000元","2、每个IP每天最多可以创建3个试玩账号","3、每个账号从注册开始有效期为7天，超过有效期系统自动回收","4、试玩账号不允许充值、提现操作"],"weixin_appid":"wxd4c41a1eabdc9752"}
     */

    private String cache_key;
    private ConfigBean config;

    public String getCache_key() {
        return cache_key;
    }

    public void setCache_key(String cache_key) {
        this.cache_key = cache_key;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public static class ConfigBean {
        /**
         * api_address : https://fxingedu.com/ZZCP
         * app_hide_buttons : {"1":"投注按钮"}
         * bank_charge_type : ["网银转账","ATM自动柜员机","ATM现金入款","银行柜台转账","手机银行转账","其他"]
         * bet_api_address : https://fxingedu.com
         * bet_future_num : 100
         * can_register : 1
         * channel_address : [{"api_address":"soarginterface222.com","circuitInfo":"默认线路1","id":1},{"api_address":"daqin78.com","circuitInfo":"daqin线路","id":2},{"api_address":"soargby2.com","circuitInfo":"默认线路3","id":3}]
         * client_service_url : https://ored.masarn.com/chat/chatClient/chatbox.jsp?companyID=365027836&configID=3184&jid=9805587816&s=1
         * customerQQ : 244488618
         * function_switch : {"displayChatRoom":0,"displayGame":1,"displayVipSystem":1}
         * lhc_shengxiao : {"兔":"09,21,33,45","牛":"11,23,35,47","狗":"02,14,26,38","猪":"01,13,25,37,49","猴":"04,16,28,40","羊":"05,17,29,41","虎":"10,22,34,46","蛇":"07,19,31,43","马":"06,18,30,42","鸡":"03,15,27,39","鼠":"12,24,36,48","龙":"08,20,32,44"}
         * lhc_wuxing : {"1":"05,06,19,20,27,28,35,36,49","2":"01,02,09,10,17,18,31,32,39,40,47,48","3":"07,08,15,16,23,24,37,38,45,46","4":"03,04,11,12,25,26,33,34,41,42","5":"13,14,21,22,29,30,43,44"}
         * play_instruction_url : https://fxingedu.com/ZZCP/play_instruction?type=
         * proxy_default_rate : 0.98
         * proxy_info : 彩票代理是为了促进彩票平台的销售而采取的一种市场推广的方式，成为彩票代理可以赚取彩票网站提供的丰厚佣金！彩票125代理平台支持最多5极代理机制。
         * proxy_info_url : https://fxingedu.com/ZZCP/proxy_instruction
         * proxy_return_rates : [[1960,98],[1920,96],[1910,95.5],[1900,95],[1890,94.5],[1880,94],[1870,93.5],[1860,93],[1850,92.5],[1840,92],[1830,91.5],[1820,91],[1810,90.5],[1800,90],[20,1],[0,98]]
         * red_packets_url : 1
         * register_config : {"show_QQ":"0","show_email":"0","show_phone":"0"}
         * trial_account_disables : [{"hint":"试玩账号不能进行充值操作！","type":"账户充值"},{"hint":"试玩账号不能进行提现操作！","type":"提取现金"}]
         * trial_account_info : ["1、每个试玩账号初始金额2000元","2、每个IP每天最多可以创建3个试玩账号","3、每个账号从注册开始有效期为7天，超过有效期系统自动回收","4、试玩账号不允许充值、提现操作"]
         * weixin_appid : wxd4c41a1eabdc9752
         */

        private String api_address;
        private AppHideButtonsBean app_hide_buttons;
        private String bet_api_address;
        private int bet_future_num;
        private String can_register;
        private String client_service_url;
        private String customerQQ;
        private FunctionSwitchBean function_switch;
        private LhcShengxiaoBean lhc_shengxiao;
        private LhcWuxingBean lhc_wuxing;
        private String play_instruction_url;
        private double proxy_default_rate;
        private String proxy_info;
        private String proxy_info_url;
        private String red_packets_url;
        private RegisterConfigBean register_config;
        private String weixin_appid;
        private List<String> bank_charge_type;
        private List<ChannelAddressBean> channel_address;
        private List<List<Integer>> proxy_return_rates;
        private List<TrialAccountDisablesBean> trial_account_disables;
        private List<String> trial_account_info;

        public String getApi_address() {
            return api_address;
        }

        public void setApi_address(String api_address) {
            this.api_address = api_address;
        }

        public AppHideButtonsBean getApp_hide_buttons() {
            return app_hide_buttons;
        }

        public void setApp_hide_buttons(AppHideButtonsBean app_hide_buttons) {
            this.app_hide_buttons = app_hide_buttons;
        }

        public String getBet_api_address() {
            return bet_api_address;
        }

        public void setBet_api_address(String bet_api_address) {
            this.bet_api_address = bet_api_address;
        }

        public int getBet_future_num() {
            return bet_future_num;
        }

        public void setBet_future_num(int bet_future_num) {
            this.bet_future_num = bet_future_num;
        }

        public String getCan_register() {
            return can_register;
        }

        public void setCan_register(String can_register) {
            this.can_register = can_register;
        }

        public String getClient_service_url() {
            return client_service_url;
        }

        public void setClient_service_url(String client_service_url) {
            this.client_service_url = client_service_url;
        }

        public String getCustomerQQ() {
            return customerQQ;
        }

        public void setCustomerQQ(String customerQQ) {
            this.customerQQ = customerQQ;
        }

        public FunctionSwitchBean getFunction_switch() {
            return function_switch;
        }

        public void setFunction_switch(FunctionSwitchBean function_switch) {
            this.function_switch = function_switch;
        }

        public LhcShengxiaoBean getLhc_shengxiao() {
            return lhc_shengxiao;
        }

        public void setLhc_shengxiao(LhcShengxiaoBean lhc_shengxiao) {
            this.lhc_shengxiao = lhc_shengxiao;
        }

        public LhcWuxingBean getLhc_wuxing() {
            return lhc_wuxing;
        }

        public void setLhc_wuxing(LhcWuxingBean lhc_wuxing) {
            this.lhc_wuxing = lhc_wuxing;
        }

        public String getPlay_instruction_url() {
            return play_instruction_url;
        }

        public void setPlay_instruction_url(String play_instruction_url) {
            this.play_instruction_url = play_instruction_url;
        }

        public double getProxy_default_rate() {
            return proxy_default_rate;
        }

        public void setProxy_default_rate(double proxy_default_rate) {
            this.proxy_default_rate = proxy_default_rate;
        }

        public String getProxy_info() {
            return proxy_info;
        }

        public void setProxy_info(String proxy_info) {
            this.proxy_info = proxy_info;
        }

        public String getProxy_info_url() {
            return proxy_info_url;
        }

        public void setProxy_info_url(String proxy_info_url) {
            this.proxy_info_url = proxy_info_url;
        }

        public String getRed_packets_url() {
            return red_packets_url;
        }

        public void setRed_packets_url(String red_packets_url) {
            this.red_packets_url = red_packets_url;
        }

        public RegisterConfigBean getRegister_config() {
            return register_config;
        }

        public void setRegister_config(RegisterConfigBean register_config) {
            this.register_config = register_config;
        }

        public String getWeixin_appid() {
            return weixin_appid;
        }

        public void setWeixin_appid(String weixin_appid) {
            this.weixin_appid = weixin_appid;
        }

        public List<String> getBank_charge_type() {
            return bank_charge_type;
        }

        public void setBank_charge_type(List<String> bank_charge_type) {
            this.bank_charge_type = bank_charge_type;
        }

        public List<ChannelAddressBean> getChannel_address() {
            return channel_address;
        }

        public void setChannel_address(List<ChannelAddressBean> channel_address) {
            this.channel_address = channel_address;
        }

        public List<List<Integer>> getProxy_return_rates() {
            return proxy_return_rates;
        }

        public void setProxy_return_rates(List<List<Integer>> proxy_return_rates) {
            this.proxy_return_rates = proxy_return_rates;
        }

        public List<TrialAccountDisablesBean> getTrial_account_disables() {
            return trial_account_disables;
        }

        public void setTrial_account_disables(List<TrialAccountDisablesBean> trial_account_disables) {
            this.trial_account_disables = trial_account_disables;
        }

        public List<String> getTrial_account_info() {
            return trial_account_info;
        }

        public void setTrial_account_info(List<String> trial_account_info) {
            this.trial_account_info = trial_account_info;
        }

        public static class AppHideButtonsBean {
            /**
             * 1 : 投注按钮
             */

            @SerializedName("1")
            private String _$1;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }
        }

        public static class FunctionSwitchBean {
            /**
             * displayChatRoom : 0
             * displayGame : 1
             * displayVipSystem : 1
             */

            private int displayChatRoom;
            private int displayGame;
            private int displayVipSystem;

            public int getDisplayChatRoom() {
                return displayChatRoom;
            }

            public void setDisplayChatRoom(int displayChatRoom) {
                this.displayChatRoom = displayChatRoom;
            }

            public int getDisplayGame() {
                return displayGame;
            }

            public void setDisplayGame(int displayGame) {
                this.displayGame = displayGame;
            }

            public int getDisplayVipSystem() {
                return displayVipSystem;
            }

            public void setDisplayVipSystem(int displayVipSystem) {
                this.displayVipSystem = displayVipSystem;
            }
        }

        public static class LhcShengxiaoBean {
            /**
             * 兔 : 09,21,33,45
             * 牛 : 11,23,35,47
             * 狗 : 02,14,26,38
             * 猪 : 01,13,25,37,49
             * 猴 : 04,16,28,40
             * 羊 : 05,17,29,41
             * 虎 : 10,22,34,46
             * 蛇 : 07,19,31,43
             * 马 : 06,18,30,42
             * 鸡 : 03,15,27,39
             * 鼠 : 12,24,36,48
             * 龙 : 08,20,32,44
             */

            private String 兔;
            private String 牛;
            private String 狗;
            private String 猪;
            private String 猴;
            private String 羊;
            private String 虎;
            private String 蛇;
            private String 马;
            private String 鸡;
            private String 鼠;
            private String 龙;

            public String get兔() {
                return 兔;
            }

            public void set兔(String 兔) {
                this.兔 = 兔;
            }

            public String get牛() {
                return 牛;
            }

            public void set牛(String 牛) {
                this.牛 = 牛;
            }

            public String get狗() {
                return 狗;
            }

            public void set狗(String 狗) {
                this.狗 = 狗;
            }

            public String get猪() {
                return 猪;
            }

            public void set猪(String 猪) {
                this.猪 = 猪;
            }

            public String get猴() {
                return 猴;
            }

            public void set猴(String 猴) {
                this.猴 = 猴;
            }

            public String get羊() {
                return 羊;
            }

            public void set羊(String 羊) {
                this.羊 = 羊;
            }

            public String get虎() {
                return 虎;
            }

            public void set虎(String 虎) {
                this.虎 = 虎;
            }

            public String get蛇() {
                return 蛇;
            }

            public void set蛇(String 蛇) {
                this.蛇 = 蛇;
            }

            public String get马() {
                return 马;
            }

            public void set马(String 马) {
                this.马 = 马;
            }

            public String get鸡() {
                return 鸡;
            }

            public void set鸡(String 鸡) {
                this.鸡 = 鸡;
            }

            public String get鼠() {
                return 鼠;
            }

            public void set鼠(String 鼠) {
                this.鼠 = 鼠;
            }

            public String get龙() {
                return 龙;
            }

            public void set龙(String 龙) {
                this.龙 = 龙;
            }
        }

        public static class LhcWuxingBean {
            /**
             * 1 : 05,06,19,20,27,28,35,36,49
             * 2 : 01,02,09,10,17,18,31,32,39,40,47,48
             * 3 : 07,08,15,16,23,24,37,38,45,46
             * 4 : 03,04,11,12,25,26,33,34,41,42
             * 5 : 13,14,21,22,29,30,43,44
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;
            @SerializedName("4")
            private String _$4;
            @SerializedName("5")
            private String _$5;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }

            public String get_$4() {
                return _$4;
            }

            public void set_$4(String _$4) {
                this._$4 = _$4;
            }

            public String get_$5() {
                return _$5;
            }

            public void set_$5(String _$5) {
                this._$5 = _$5;
            }
        }

        public static class RegisterConfigBean {
            /**
             * show_QQ : 0
             * show_email : 0
             * show_phone : 0
             */

            private String show_QQ;
            private String show_email;
            private String show_phone;

            public String getShow_QQ() {
                return show_QQ;
            }

            public void setShow_QQ(String show_QQ) {
                this.show_QQ = show_QQ;
            }

            public String getShow_email() {
                return show_email;
            }

            public void setShow_email(String show_email) {
                this.show_email = show_email;
            }

            public String getShow_phone() {
                return show_phone;
            }

            public void setShow_phone(String show_phone) {
                this.show_phone = show_phone;
            }
        }

        public static class ChannelAddressBean {
            /**
             * api_address : soarginterface222.com
             * circuitInfo : 默认线路1
             * id : 1
             */

            private String api_address;
            private String circuitInfo;
            private int id;

            public String getApi_address() {
                return api_address;
            }

            public void setApi_address(String api_address) {
                this.api_address = api_address;
            }

            public String getCircuitInfo() {
                return circuitInfo;
            }

            public void setCircuitInfo(String circuitInfo) {
                this.circuitInfo = circuitInfo;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class TrialAccountDisablesBean {
            /**
             * hint : 试玩账号不能进行充值操作！
             * type : 账户充值
             */

            private String hint;
            private String type;

            public String getHint() {
                return hint;
            }

            public void setHint(String hint) {
                this.hint = hint;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

