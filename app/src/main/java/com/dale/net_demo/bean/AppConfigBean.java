package com.dale.net_demo.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public class AppConfigBean {

    public DataBean data;
    public String msg;
    public int status;
    public double time_consumed;
    public String userLoginRecord;

    @Override
    public String toString() {
        return "AppConfigBean{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                ", time_consumed=" + time_consumed +
                ", userLoginRecord='" + userLoginRecord + '\'' +
                '}';
    }

    public static class DataBean implements Serializable {
        public ConfigBean config;

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public String getCache_key() {
            return cache_key;
        }

        public void setCache_key(String cache_key) {
            this.cache_key = cache_key;
        }

        public String cache_key;

        @Override
        public String toString() {
            return "DataBean{" +
                    "config=" + config +
                    '}';
        }

        public static class ConfigBean implements Serializable {
            public int bet_future_num;
            public float proxy_default_rate;
            public String client_service_url;
            public String play_instruction_url;
            public String proxy_info;
            public String proxy_info_url;
            private String can_register;
            private String api_address;
            private String customerQQ;
            private String red_packets_url;
            private String bet_api_address;

            @Override
            public String toString() {
                return "ConfigBean{" +
                        "bet_future_num=" + bet_future_num +
                        ", proxy_default_rate=" + proxy_default_rate +
                        ", client_service_url='" + client_service_url + '\'' +
                        ", play_instruction_url='" + play_instruction_url + '\'' +
                        ", proxy_info='" + proxy_info + '\'' +
                        ", proxy_info_url='" + proxy_info_url + '\'' +
                        ", can_register='" + can_register + '\'' +
                        ", api_address='" + api_address + '\'' +
                        ", customerQQ='" + customerQQ + '\'' +
                        ", bank_charge_type=" + bank_charge_type +
                        ", proxy_return_rates=" + proxy_return_rates +
                        ", trial_account_disables=" + trial_account_disables +
                        ", trial_account_info=" + trial_account_info +
                        ", lhc_shengxiao=" + lhc_shengxiao +
                        ", register_config=" + register_config +
                        ", bet_api_address=" + bet_api_address +
                        '}';
            }

            public String getCustomerQQ() {
                return customerQQ;
            }


            public String getRed_packets_url() {
                return red_packets_url;
            }

            public void setRed_packets_url(String red_packets_url) {
                this.red_packets_url = red_packets_url;
            }


            public void setCustomerQQ(String customerQQ) {
                this.customerQQ = customerQQ;
            }


            public void setApi_address(String api_address) {
                this.api_address = api_address;
            }

            public String getBet_api_address() {
                return bet_api_address;
            }



            public String getApi_address() {
                return api_address;
            }

            public int getBet_future_num() {
                return bet_future_num;
            }

            public void setBet_future_num(int bet_future_num) {
                this.bet_future_num = bet_future_num;
            }

            public float getProxy_default_rate() {
                return proxy_default_rate;
            }

            public void setProxy_default_rate(float proxy_default_rate) {
                this.proxy_default_rate = proxy_default_rate;
            }

            public String getClient_service_url() {
                return client_service_url;
            }

            public void setClient_service_url(String client_service_url) {
                this.client_service_url = client_service_url;
            }

            public String getPlay_instruction_url() {
                return play_instruction_url;
            }

            public void setPlay_instruction_url(String play_instruction_url) {
                this.play_instruction_url = play_instruction_url;
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

            public String getCan_register() {
                return can_register;
            }

            public void setCan_register(String can_register) {
                this.can_register = can_register;
            }

            public List<String> getBank_charge_type() {
                return bank_charge_type;
            }

            public void setBank_charge_type(List<String> bank_charge_type) {
                this.bank_charge_type = bank_charge_type;
            }

            public List<List<Double>> getProxy_return_rates() {
                return proxy_return_rates;
            }

            public void setProxy_return_rates(List<List<Double>> proxy_return_rates) {
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

            public Map<String, String> getLhc_shengxiao() {
                return lhc_shengxiao;
            }

            public void setLhc_shengxiao(Map<String, String> lhc_shengxiao) {
                this.lhc_shengxiao = lhc_shengxiao;
            }

            public List<String> bank_charge_type;
            public List<List<Double>> proxy_return_rates;
            public List<TrialAccountDisablesBean> trial_account_disables;
            public List<String> trial_account_info;

            public Map<String, String> lhc_shengxiao;

            private RegisterConfigBean register_config;

            public RegisterConfigBean getRegister_config() {
                return register_config;
            }

            public void setRegister_config(RegisterConfigBean register_config) {
                this.register_config = register_config;
            }

            public static class RegisterConfigBean implements Serializable {
                /**
                 * show_phone : 1
                 * show_email : 1
                 * show_QQ : 1
                 */

                private String show_phone;
                private String show_email;
                private String show_QQ;

                @Override
                public String toString() {
                    return "RegisterConfigBean{" +
                            "show_phone='" + show_phone + '\'' +
                            ", show_email='" + show_email + '\'' +
                            ", show_QQ='" + show_QQ + '\'' +
                            '}';
                }

                public String getShow_phone() {
                    return show_phone;
                }

                public void setShow_phone(String show_phone) {
                    this.show_phone = show_phone;
                }

                public String getShow_email() {
                    return show_email;
                }

                public void setShow_email(String show_email) {
                    this.show_email = show_email;
                }

                public String getShow_QQ() {
                    return show_QQ;
                }

                public void setShow_QQ(String show_QQ) {
                    this.show_QQ = show_QQ;
                }


            }


            public static class TrialAccountDisablesBean implements Serializable {
                public String hint;
                public String type;

                @Override
                public String toString() {
                    return "TrialAccountDisablesBean{" +
                            "hint='" + hint + '\'' +
                            ", type='" + type + '\'' +
                            '}';
                }
            }



        }
    }
}
