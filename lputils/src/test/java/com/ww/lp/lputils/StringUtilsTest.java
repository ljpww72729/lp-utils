package com.ww.lp.lputils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LinkedME06 on 23/08/2017.
 */
public class StringUtilsTest {
    @Test
    public void obtainBalanceString() throws Exception {
        String parseString = "您尾号8573卡6月10日23:10POS支出(转账)4,000元，余额213.84元。【工商银行】";
        Assert.assertEquals(StringUtils.obtainBalanceString(parseString), "￥213.84");
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainBalanceString(parseString), "");
        System.out.println(StringUtils.obtainBalanceString(parseString));
    }

    @Test
    public void obtainTransactionTypeString() throws Exception {
        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，10000.00元[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "在【京东支付】发生直付通/快捷支付扣款");
        parseString = "您账户3083于17年7月16日24时48分23秒消费人民币10000.00元[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "消费");
        parseString = "您账户3083于17年7月16日24时48分23秒消费10000.00元[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "消费");
        parseString = "您账户3083于17年7月16日24时48分23秒消费美元10000.00元[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "消费");
        parseString = "您尾号3081的招行信用卡在16日14:23经银联网络进行的网上交易，金额从人民币1.00撤销为0.00。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "");
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "入账款项");
        parseString = "您账户3083于08月08日09:04在【支付宝-理财】发生直付通/快捷支付扣款，人民币10000.00[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "在【支付宝-理财】发生直付通/快捷支付扣款");
        parseString = "您尾号3081的招行信用卡于今日发生的一笔当地币8.00元的网上支付交易未成功，请留意，如有需要可重新支付，谢谢！[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "");
        parseString = "您账户3083于07月20日他行实时转入人民币20000.00，付方姜召芳。5折来啦 cmbt.cn/a7 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "他行实时转入");
        parseString = "您账户3083于07月20日他行实时转入人民币20000.00元，付方姜召芳。5折来啦 cmbt.cn/a7 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "他行实时转入");
        parseString = "您账户3083于07月20日他行实时转入人民币20000.00，付方姜召芳。5折来啦 cmbt.cn/a7 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "他行实时转入");
        parseString = "您账户3083于07月20日他行实时转入20000.00元，付方姜召芳。5折来啦 cmbt.cn/a7 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "他行实时转入");
        parseString = "您尾号8573卡6月10日23:10POS支出(转账)4,000元，余额213.84元。【工商银行】";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "POS支出(转账)");
        parseString = "17年07月20日23:39您尾号225账户汇款金额20000.00元，余额10316.55元.客服95580/11185【中国邮政】";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "汇款");
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainTransactionTypeString(parseString), "入账款项");


        System.out.println(StringUtils.obtainTransactionTypeString(parseString));
    }

    @Test
    public void obtainMoneyString() throws Exception {
//        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，人民币10000.00[招商银行]";
//        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，10000.00元[招商银行]";
//        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款10000.00元[招商银行]";
        String parseString = "17年07月20日23:39您尾号225账户汇款金额20000.00元，余额10316.55元.客服95580/11185【中国邮政】";
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainMoneyString(parseString), "50000.00￥");
        System.out.println(StringUtils.obtainMoneyString(parseString));
    }

    @Test
    public void obtainAccountString() throws Exception {
        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，人民币10000.00[招商银行]";
        Assert.assertEquals(StringUtils.obtainAccountString(parseString), "3083");
        parseString = "您尾号3081的信用卡18日10:18网上交易美元1.00元。★快抽iPhone 7日！→ cmbt.cn/RMm 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainAccountString(parseString), "3081");
        parseString = "17年07月20日23:39您尾号225账户汇款金额20000.00元，余额10316.55元.客服95580/11185【中国邮政】";
        Assert.assertEquals(StringUtils.obtainAccountString(parseString), "225");
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainAccountString(parseString), "3083");

        System.out.println(StringUtils.obtainAccountString(parseString));
    }

    @Test
    public void obtainDateString() throws Exception {
//        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，10000.00元[招商银行]";
//        String parseString = "您账户3083于07月20日他行实时转入人民币20000.00，付方姜召芳。5折来啦 cmbt.cn/a7 。[招商银行]";
//        String parseString = "您账户3083于17年7月16日24时48分23秒在【京东支付】发生直付通/快捷支付扣款，人民币10000.00[招商银行]";
//        String parseString = "您尾号3081的信用卡18日10:18网上交易美元1.00元。★快抽iPhone 7日！→ cmbt.cn/RMm 。[招商银行]";
//        String parseString = "一网通绑定银行卡，验证码688614，请妥善保管，切勿泄露。[招商银行]";
//        String parseString = "您尾号8573卡6月10日23:10POS支出(转账)4,000元，余额213.84元。【工商银行】";
        String parseString = "17年07月20日23:39您尾号225账户汇款金额20000.00元，余额10316.55元.客服95580/11185【中国邮政】";
        parseString = "您账户3083于08月11日入账款项，人民币50000.00。代发理财 cmbt.cn/Nqn 。[招商银行]";
        Assert.assertEquals(StringUtils.obtainDateString(parseString), "08月11日");
        System.out.println(StringUtils.obtainDateString(parseString));
    }

}