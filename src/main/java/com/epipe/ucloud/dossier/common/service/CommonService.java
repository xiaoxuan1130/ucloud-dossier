package com.epipe.ucloud.dossier.common.service;

import com.epipe.ucloud.dossier.common.NoSuchDataException;
import com.epipe.ucloud.dossier.enums.MiddleDateEnum;
import com.epipe.ucloud.dossier.modules.coderule.entity.CodeRule;
import com.epipe.ucloud.dossier.modules.coderule.service.CodeRuleService;
import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.sys.utils.DictUtils;
import com.jeeplus.modules.sys.utils.UserUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 业务逻辑通用 Service
 * @author gongtao
 * @date 2019-04-22 9:12
 **/
public class CommonService {


    /**
     * 获取编码编号
     * @author gongtao
     * @param code 编码
     * @return java.lang.String 编码编号
     **/

    public static String getCodeNumber(String code){
        CodeRuleService ruleService = SpringContextHolder.getBean(CodeRuleService.class);
        String companyId=UserUtils.getUser().getCompany().getId();
        CodeRule codeRule = ruleService.getByCode(code,companyId);
        if (codeRule == null){
            throw new NoSuchDataException("找不到 " + code + " 对应的编码规则！");
        }
        StringBuilder sb = new StringBuilder();
        //获取当前日期
        LocalDateTime now = LocalDateTime.now();
        sb.append(codeRule.getPrefix());
        boolean existsMiddleDate = true;
        try {
            //设置了中间日期
            MiddleDateEnum middleDate = MiddleDateEnum.of(codeRule.getMiddleDate());
            switch (middleDate){
                //两位年份
                case TWO_YEAR:
                    sb.append(now.getYear() % 1000);
                    break;
                //两位年份+两位月份
                case TWO_YEAR_TWO_MONTH:
                    sb.append(now.getYear() % 1000)
                            .append(String.format("%02d",now.getMonthValue()));
                    break;
                //两位年份+两位月份+两位日份
                case TWO_YEAR_TWO_MONTH_TWO_DAY:
                    sb.append(now.getYear() % 1000)
                            .append(String.format("%02d",now.getMonthValue()))
                            .append(String.format("%02d",now.getDayOfMonth()));
                    break;
                //四位年份
                case FOUR_YEAR:
                    sb.append(now.getYear());
                    break;
                //四位年份+两位月份
                case FOUR_YEAR_TWO_MONTH:
                    sb.append(now.getYear())
                            .append(String.format("%02d",now.getMonthValue()));
                    break;
                //四位年份+两位月份+两位日份
                case FOUR_YEAR_TWO_MONTH_TWO_DAY:
                    sb.append(now.getYear())
                            .append(String.format("%02d",now.getMonthValue()))
                            .append(String.format("%02d",now.getDayOfMonth()));
                    break;
                default:
                    break;
            }
        }catch (NoSuchDataException e){
            existsMiddleDate = false;
        }finally {
            //拼接流水号
            int serialNo;
            if (existsMiddleDate && isNextYearOrMonthOrDay(codeRule.getUpdateDate(), now)){
                //新的时间，流水号重置
                serialNo = ruleService.getAndResetSerialNo(code) ;
            }else {
                //流水号+1
                serialNo = ruleService.getAndSetNextSerialNo(code);
            }
            sb.append(StringUtils.leftPad(serialNo + "", codeRule.getLastSerialBit(), "0"));
        }
        return sb.toString();
    }

    /**
     * 判断是否为下一年/下一月/下一天
     * @author gongtao
     * @param date 参数时间
     * @param now 当前时间
     * @return true/false
     */
    private static boolean isNextYearOrMonthOrDay(Date date, LocalDateTime now){
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        if (zonedDateTime.getYear() == now.getYear() - 1){
            return true;
        }
        if (zonedDateTime.getMonthValue() == now.getMonthValue() - 1){
            return true;
        }
        return zonedDateTime.getDayOfMonth() == now.getDayOfMonth() - 1;
    }


    /**
     * 编码规则设置编码详情字段
     * @param codeRule 数据规则
     */
    public static void setRuleDetail(CodeRule codeRule){
        StringBuilder sb = new StringBuilder();
        sb.append(codeRule.getPrefix());
        String middleDate = codeRule.getMiddleDate();
        if (StringUtils.isNotBlank(middleDate)){
            sb.append("+").append(DictUtils.getDictLabel(middleDate, "middle_date","-"));
        }
        sb.append("+").append(codeRule.getLastSerialBit()).append("位流水");
        codeRule.setDetail(sb.toString());
    }

}
