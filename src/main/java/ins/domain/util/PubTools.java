/****************************************************************************
 * DESC       �����ù�����
 * Author     : PICC�б����ո�����Ŀ��
 * CREATEDATE ��2009-09-19
 * MODIFYLIST ��Name       Date            Reason/Contents
 *          ------------------------------------------------------
 ****************************************************************************/

package ins.domain.util;

//import com.sinosoft.prpall.schema.model.PrpQitemKind;
//import com.sinosoft.prpall.schema.model.PrpQmain;
import com.sinosoft.sysframework.common.datatype.DateTime;
import com.sinosoft.utility.string.*;

import ins.framework.common.ServiceFactory;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * ͨ�ù�����
 */
public class PubTools {



    /**
     * @desc ��ȡ�������ֵĶ���ϵ�����¶��ڷ��ʣ�
     * @desc ��ҵ��ϵͳUICommon.js�еĶ��ڷ��ʼ����javaʵ��
     * @param String strRiskCode ����
     * @param Date startDate ʱ��ε���ʼ����
     * @param int intStartHour ʱ�����ʼСʱ��
     * @param Date endDate ʱ��ε���ֹ����
     * @param int intEndHour ʱ��ε���ֹСʱ��
     * @return Double dblShortRate����ϵ��
     */
    public static double getMonthShortRate(String strRiskCode, Date startDate, int intStartHour, Date endDate, int intEndHour)
     {
        ChgData chgData = new ChgData();
        double dblShortRate = 0d;
        int intMonth = 0;

        intMonth = getMonthMinus(startDate, intStartHour, endDate, intEndHour);
        if (startDate == endDate && intStartHour < intEndHour) {
            intMonth++;
        }
        dblShortRate = (double)intMonth / 12 * 100;
        return dblShortRate;
    }


    /**
     * @desc ������ʼ����ֹ���ڼ�����ݲ�
     * @param Date startDate ʱ��ε���ʼ����
     * @param int intStartHour ʱ�����ʼСʱ��
     * @param Date endDate ʱ��ε���ֹ����
     * @param int intEndHour ʱ��ε���ֹСʱ��
     * @return int intYear �����
     */
    public static int getYearMinus(Date startDate, int intStartHour, Date endDate, int intEndHour)
     {
        int intYear = 0;
        int intMonth = 0;
        intMonth = getMonthMinus(startDate, intStartHour, endDate, intEndHour);
        intYear = intMonth / 12;
        intMonth = intMonth % 12;
        if (intMonth != 0)
            intYear++;
        return intYear;
    }

    /**
     * @desc ������ʼ����ֹ���ڼ����·ݲ�
     * @desc ��ҵ��ϵͳUICommon.js�еĶ��ڷ��ʼ����javaʵ�֣��ű���û���ж�Сʱ����
     * @param Date startDate ʱ��ε���ʼ����
     * @param int intStartHour ʱ�����ʼСʱ��
     * @param Date endDate ʱ��ε���ֹ����
     * @param int intEndHour ʱ��ε���ֹСʱ��
     * @return int intMonth �·���
     */
    public static int getMonthMinus(Date startDate, int intStartHour, Date endDate, int intEndHour)
      {
        int intMonth = 0;
        intMonth = (endDate.get(Date.YEAR) - startDate.get(Date.YEAR)) * 12
                 + endDate.get(Date.MONTH) - startDate.get(Date.MONTH);
        if (endDate.get(Date.DATE) - startDate.get(Date.DATE) > 0 ||
            endDate.get(Date.DATE) == startDate.get(Date.DATE) &&
            intEndHour > intStartHour) {
            intMonth++;
        }
        return intMonth;
    }
    
    public static int getMonthMinus(java.util.Date startDate, int intStartHour,
    		java.util.Date endDate, int intEndHour, boolean roundUp) {
		if (roundUp) {
			return getMonthMinus(new Date(PubTools.formatDate(startDate)),
					intStartHour, new Date(PubTools.formatDate(endDate)),
					intEndHour);
		}
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		
		int intMonth = 0;

		if (intStartHour == 24) {
			startCalendar.add(Calendar.DATE, 1);
			startDate = startCalendar.getTime();
		}
		if (intEndHour == 24) {
			endCalendar.add(Calendar.DATE, 1);
			endDate = endCalendar.getTime();
			System.out.println(endCalendar.getTime());
		}
		intMonth = (endDate.getYear() - startDate.getYear()) * 12
				+ endDate.getMonth() - startDate.getMonth();
		int dayMinus = endDate.getDay() - startDate.getDay();
		if (dayMinus < 0) {
			intMonth--;
		} else if (dayMinus > 0) {
			intMonth++;
		}
		return intMonth;
	}

    
    public static int getNewMonthMinus(Date startDate, int intStartHour, Date endDate, int intEndHour)
    throws Exception {
        int intMonth = 0;
        intMonth = (endDate.get(Date.YEAR) - startDate.get(Date.YEAR)) * 12
                 + endDate.get(Date.MONTH) - startDate.get(Date.MONTH);
        return intMonth;
    }
    /**
     * @desc ������ʼ����ֹ���ڼ���������
     * @desc ��ҵ��ϵͳUICommon.js�еĶ��ڷ��ʼ����javaʵ�֣��ű���û���ж�Сʱ����
     * @param Date startDate ʱ��ε���ʼ����
     * @param int intStartHour ʱ�����ʼСʱ��
     * @param Date endDate ʱ��ε���ֹ����
     * @param int intEndHour ʱ��ε���ֹСʱ��
     * @return int intDay ����
     */
    public static int getDayMinus(Date startDate, int intStartHour, Date endDate, int intEndHour)
    throws Exception{
        int intDay = 0;
        java.util.Date startDateJava = new java.util.Date(
            startDate.get(Date.YEAR), startDate.get(Date.MONTH) - 1,
            startDate.get(Date.DATE), intStartHour, 0, 0);
        java.util.Date endDateJava = new java.util.Date(endDate.get(Date.YEAR),
            endDate.get(Date.MONTH) - 1, endDate.get(Date.DATE),
            intEndHour, 0, 0);
        intDay = (int)((endDateJava.getTime() - startDateJava.getTime()) / 86400000l);
        return intDay;
    }

    /**
     * @desc �Ƚ������Ƿ����
     * @param String strFirstDate �Ƚϵĵ�һ������ ��ʽΪyyyy-MM-DD����yyyy/MM/DD
     * @param String strSecondDate �Ƚϵĵڶ������� ��ʽΪyyyy-MM-DD����yyyy/MM/DD
     * @return int intReturn 1 ��First�����ڣ�second����0 ��ȡ�-1 ��First��С�ڣ�second��
     */
    public static int compareDate(String strFirstDate, String strSecondDate)
    throws Exception {
        java.util.Date firstDate = null;
        java.util.Date secondDate = null;
        int intReturn = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Str.replace(strFirstDate, "/", "-"); //����/���ķָ����滻Ϊ��-��
            Str.replace(strSecondDate, "/", "-");
            firstDate = format.parse(strFirstDate);
            secondDate = format.parse(strSecondDate);
            if (firstDate.after(secondDate)) {
                intReturn = 1;
            } else if (firstDate.before(secondDate)) {
                intReturn = -1;
            } else if (firstDate.equals(secondDate)) {
                intReturn = 0;
            }
            return intReturn;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * @desc �Ƚ������Ƿ����
     * @param String strFirstDate �Ƚϵĵ�һ������ ��ʽΪyyyy-MM-DD����yyyy/MM/DD
     * @param String strSecondDate �Ƚϵĵڶ������� ��ʽΪyyyy-MM-DD����yyyy/MM/DD
     * @return int intReturn 1 ��First�����ڣ�second����0 ��ȡ�-1 ��First��С�ڣ�second��
     */
    public static int compareTime(String strFirstDate, String strSecondDate)
    throws Exception {
        java.util.Date firstDate = null;
        java.util.Date secondDate = null;
        int intReturn = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Str.replace(strFirstDate, "/", "-"); //����/���ķָ����滻Ϊ��-��
            Str.replace(strSecondDate, "/", "-");
            firstDate = format.parse(strFirstDate);
            secondDate = format.parse(strSecondDate);
            if (firstDate.after(secondDate)) {
                intReturn = 1;
            } else if (firstDate.before(secondDate)) {
                intReturn = -1;
            } else if (firstDate.equals(secondDate)) {
                intReturn = 0;
            }
            return intReturn;
        } catch (Exception e) {
            throw e;
        }
    }
    /* modify by xiaojian 20050824 end */
/*
    /**
     * @desc ��þ�����������һ������ֱұ�Ķһ��� LIJIBIN 2004-02-24
     * @param iBaseCurrency
     *            ��׼�ұ�
     * @param iExchCurrency
     *            �һ��ұ�
     * @param iExchDate
     *            �һ����� ��׼�������ո�ʽ
     * @return �һ���
     * modify by xuning gpic 20081023 �������Ϊ��׼��
     */
   /* public static double getExchangeRate(DbPool dbpool, String iBaseCurrency,
            String iExchCurrency, String iExchDate) throws UserException,
            Exception {
        double dblExchangeRate = 0d;
        double dblExchangeRate1 = 0d;
        double dblExchangeRate2 = 0d;
        ChgDate chgDate = new ChgDate();
        iExchDate = chgDate.toFormat(iExchDate);
        iBaseCurrency = iBaseCurrency.trim();
        iExchCurrency = iExchCurrency.trim();
        if (iBaseCurrency.equals(iExchCurrency)) {
            dblExchangeRate = 1;
            return dblExchangeRate;
        }
        if (iExchDate == null || iExchDate.length() == 0) {
            return dblExchangeRate;
        } else {
            iExchDate = iExchDate.substring(0, 10);
        }
        //��ȡͬһ���׼�ұ�Ͷһ��ұ��ֱ�Ӷһ���
        dblExchangeRate = getStraightExchangeRate(dbpool, iBaseCurrency,
                iExchCurrency, iExchDate);
        if (dblExchangeRate != 0) {
            return dblExchangeRate;
        }

        //��ȡͬһ��һ��ұ�ͻ�׼�ұ��ֱ�Ӷһ���
        dblExchangeRate = getStraightExchangeRate(dbpool, iExchCurrency,
                iBaseCurrency, iExchDate);
        if (dblExchangeRate != 0) {
            dblExchangeRate = 1 / dblExchangeRate;
            return dblExchangeRate;
        }

        dblExchangeRate1 = getStraightExchangeRate(dbpool, iBaseCurrency,
                "CNY", iExchDate);
        if (dblExchangeRate1 != 0) {
            //���ڻ�׼�ұ��USD�Ķһ���
            dblExchangeRate2 = getStraightExchangeRate(dbpool, iExchCurrency,
                    "CNY", iExchDate);
            if (dblExchangeRate2 != 0) {
                //���ڶһ��ұ��USD�Ķһ���
                dblExchangeRate = dblExchangeRate1 / dblExchangeRate2;
                return dblExchangeRate;
            } else {
                dblExchangeRate2 = getStraightExchangeRate(dbpool, "CNY",
                        iExchCurrency, iExchDate);
                if (dblExchangeRate2 != 0) {
                    //����USD�Ͷһ��ұ�Ķһ���
                    dblExchangeRate = dblExchangeRate1 * dblExchangeRate2;
                    return dblExchangeRate;
                }
            }
        }

        dblExchangeRate1 = getStraightExchangeRate(dbpool, "CNY",
                iBaseCurrency, iExchDate);
        if (dblExchangeRate1 != 0) {
            //����USD�ͻ�׼�ұ�Ķһ���
            dblExchangeRate2 = getStraightExchangeRate(dbpool, iExchCurrency,
                    "CNY", iExchDate);
            if (dblExchangeRate2 != 0) {
                //���ڶһ��ұ��USD�Ķһ���
                dblExchangeRate = dblExchangeRate2 * dblExchangeRate1;
                return dblExchangeRate;
            } else {
                dblExchangeRate2 = getStraightExchangeRate(dbpool, "CNY",
                        iExchCurrency, iExchDate);
                if (dblExchangeRate2 != 0) {
                    //����USD�Ͷһ��ұ�Ķһ���
                    dblExchangeRate = dblExchangeRate2 / dblExchangeRate1;
                    return dblExchangeRate;
                }
            }
        }

        return dblExchangeRate;
    }

    private static double getStraightExchangeRate(DbPool dbpool,
            String iBaseCurrency, String iExchCurrency, String iExchDate)
            throws UserException, Exception {
        double dblExchangeRate = 0d;
        String strWherePart = "";
        String strExchDate = "";
        int intCount = 0;
        ChgDate chgDate = new ChgDate();
        iExchDate = chgDate.toFormat(iExchDate);
        DBPrpDexch dbPrpDexch = new DBPrpDexch();
        ChgData chgData = new ChgData();
        strWherePart = " BaseCurrency = '" + iBaseCurrency.trim() + "'"
                + " AND ExchCurrency = '" + iExchCurrency.trim() + "'"
                + " AND ExchDate <= '" + iExchDate.trim() + "'"
                + " AND ExchRate IS NOT NULL ";
        intCount = dbPrpDexch.getCount(dbpool, strWherePart);
        if (intCount == 0) {
            dblExchangeRate = 0;
            return dblExchangeRate;
        }

        strExchDate = dbPrpDexch.getMaxExchangeDate(dbpool, strWherePart);
        intCount = dbPrpDexch.getInfo(dbpool, strExchDate, iBaseCurrency,
                iExchCurrency);
        if (intCount != 0) {
            dblExchangeRate = 0;
            return dblExchangeRate;
        }
        dblExchangeRate = Double.parseDouble(chgData.chgStrZero(dbPrpDexch
                .getExchRate()));
        return dblExchangeRate;
    }

    public static void main(String[] args) {

    }

    /**
     * ��ȡ�ϸ��¶ȵ����·�
     *
     * @param iStrYearMonth
     * @return
     */
    public String GeneratePreYearMonth(String iStrYearMonth) {
        int intYear = Integer.parseInt(iStrYearMonth.substring(0, 4));
        int intMonth = Integer.parseInt(iStrYearMonth.substring(4, 6));
        String strMonth = "";
        intMonth = intMonth - 1;

        if (intMonth == 0) {
            intMonth = 12;
            intYear = intYear - 1;
        }
        strMonth = "" + intMonth;
        if (intMonth < 10)
            strMonth = "0" + intMonth;
        String strPreYearMonth = intYear + strMonth;
        return strPreYearMonth;
    }

    /* add by liuning begin 20040705 */
    /**
     * ������ʼ����ֹ���ڼ��������������Ϣ�ղ���
     *
     * @param iStartDate
     *            ��ʼ����
     * @param iStartHour
     *            ��ʼСʱ
     * @param iEndDate
     *            ��ֹ����
     * @param iEndHour
     *            ��ֹСʱ
     * @param iOption
     *            ����չ
     * @return ������
     */

    public static int getDayMinus(Date iStartDate, int iStartHour,
            Date iEndDate, int iEndHour, int iOption) throws Exception {
        int intDay = 0;
        int intRest = 0; //��¼���������Ϣ�յ�����
        ChgDate chgDate = new ChgDate();
        String strYear = "0";
        String strMonth = "0";
        String strDate = "0";
        int intMonth = 0;
        int intDate = 0;
        int intWeek = 0;

        try {

            String strStartDate = iStartDate.getString(Date.YEAR + Date.MONTH
                    + Date.DATE);
            strStartDate = chgDate.toFormat(strStartDate);
            String strEndDate = iEndDate.getString(Date.YEAR + Date.MONTH
                    + Date.DATE);
            strEndDate = chgDate.toFormat(strEndDate);
            if (compareDate(strStartDate, strEndDate) >= 0) //�������ں�¼������Ϊͬһ��,ͬʱ�ų��������ڱ�¼����������쳣
            {
                //return intDay;
                return 1; //jsp����0ʱ��24ʱ������1��
            } else {

                java.util.Date startDate = new java.util.Date(iStartDate
                        .get(Date.YEAR), iStartDate.get(Date.MONTH) - 1,
                        iStartDate.get(Date.DATE), iStartHour, 0, 0);
                java.util.Date endDate = new java.util.Date(iEndDate
                        .get(Date.YEAR), iEndDate.get(Date.MONTH) - 1, iEndDate
                        .get(Date.DATE), iEndHour, 0, 0);
                intDay = (int) ((endDate.getTime() - startDate.getTime()) / 86400000l);
                intRest = 0;
                while (compareDate(strStartDate, strEndDate) <= 0) {
                    //�ۼ��ж�����Ϣ��
                    strStartDate = chgDate.toFormat(strStartDate); //��ʽ����yyyy-MM-dd����ʽ
                    strYear = strStartDate.substring(0, 4);
                    strMonth = strStartDate.substring(5, 7);
                    strDate = strStartDate.substring(8);
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.set(Integer.parseInt(strYear), Integer
                            .parseInt(strMonth) - 1, Integer.parseInt(strDate)); //1�·ݶ�Ӧ����ֵ0�������ն�Ӧ����ֵ1
                    intMonth = cal.get(cal.MONTH);
                    intDate = cal.get(cal.DATE);
                    intWeek = cal.get(cal.DAY_OF_WEEK);
                    if (intWeek == 1
                            || intWeek == 7
                            || ((intMonth == 4 || intMonth == 9)
                                    && intDate >= 1 && intDate <= 7)
                            || (intMonth == 0 && intDate == 1)) {
                        intRest++;
                    }
                    //ȡ��һ��
                    com.sinosoft.utility.string.Date currentDay = new com.sinosoft.utility.string.Date(
                            strStartDate);
                    currentDay
                            .set(
                                    com.sinosoft.utility.string.Date.DATE,
                                    currentDay
                                            .get(com.sinosoft.utility.string.Date.DATE) + 1);
                    strStartDate = currentDay.getString(Date.YEAR + Date.MONTH
                            + Date.DATE);
                }
                intDay -= intRest; //��Ϣ�ղ���
                if (intDay < 0) {
                    intDay = 0;
                }
                return intDay;

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /* add by liuning end 20040705 */

    //�õ���n����
    public static String getNextMonthFullDate(String strDate, int intCount) {
        String strReturn;
        int intYear = 0;
        int intMonth = 0;
        int intDate = 0;
        int month = 0;
        Date tempDate = new Date(strDate);
        month = tempDate.get(Date.MONTH) + intCount;
        intYear = month / 12;
        intMonth = month % 12;
        intYear = intYear + tempDate.get(Date.YEAR);
        intDate = tempDate.get(Date.DATE);
        tempDate = new Date(intYear, intMonth, intDate);
        tempDate.set(Date.DATE, tempDate.get(Date.DATE) - 1);
        strReturn = tempDate.getString(Date.YEAR + Date.MONTH + Date.DATE);
        strReturn = Str.replace(strReturn, "/", "-");
        return strReturn;
    }
    
    //�õ���n����
    public static String getLastMonthFullDate(String strDate, int intCount) {
        String strReturn;
        int intYear = 0;
        int intMonth = 0;
        int intDate = 0;
        int month = 0;
        Date tempDate = new Date(strDate);
        month = tempDate.get(Date.MONTH) + intCount;
        intYear = month / 12;
        intMonth = month % 12;
        intYear = intYear + tempDate.get(Date.YEAR);
        intDate = tempDate.get(Date.DATE);
        tempDate = new Date(intYear, intMonth, intDate);
        tempDate.set(Date.DATE, tempDate.get(Date.DATE) + 1);
        strReturn = tempDate.getString(Date.YEAR + Date.MONTH + Date.DATE);
        strReturn = Str.replace(strReturn, "/", "-");
        return strReturn;
    }

    /**
     * ������������֮���������
     *
     * @param iStartDate
     *            ��ʼ����
     * @param iStartHour
     *            ��ʼСʱ
     * @param iEndDate
     *            ��ֹ����
     * @param iEndHour
     *            ��ֹСʱ
     * @return ���ڷ���
     */
 /*  public static int getDiffDay(Date iStartDate, int iStartHour,
            Date iEndDate, int iEndHour) {
        java.util.Date startDate = new java.util.Date(
                iStartDate.get(Date.YEAR), iStartDate.get(Date.MONTH) - 1,
                iStartDate.get(Date.DATE), 0, 0, 0);
        java.util.Date endDate = new java.util.Date(iEndDate.get(Date.YEAR),
                iEndDate.get(Date.MONTH) - 1, iEndDate.get(Date.DATE), 0, 0, 0);
        int intDiffDay = (int) ((endDate.getTime() - startDate.getTime()) / 86400000l) + 1;

        if (iEndHour <= iStartHour) {
            if (iStartHour == 24 && iEndHour == 0)
                intDiffDay = intDiffDay - 2;
            else
                intDiffDay = intDiffDay - 1;
        }
        return intDiffDay;
    }

    /**
     * ������������
     *
     * @param strDeductCond
     *            ��������
     * @return ������������
     */
 /*   public static String getDeductCondCName(String strDeductCond) {
        String strDeductCondCName = "";
        String[] arrStrDeductCondCName = new String[10];
        boolean isDeductCond = false;

        arrStrDeductCondCName[0] = "���������¹�";
        arrStrDeductCondCName[1] = "�޷��ҵ�������";
        arrStrDeductCondCName[2] = "Υ����ȫװ�ع涨";
        arrStrDeductCondCName[3] = "��Υ����ȫװ�ع涨���±����¹ʷ���";
        arrStrDeductCondCName[4] = "��Լ����ʻ��Աʹ�ñ��ճ������������¹ʵ�";
        arrStrDeductCondCName[5] = "ͬһ����������ѷ���������";
        arrStrDeductCondCName[6] = "ȱ����ص�֤����(������)";
        arrStrDeductCondCName[7] = "ȫ��������";
        arrStrDeductCondCName[8] = "������������";
        arrStrDeductCondCName[9] = "����Э�̴���,����Э�������˿���";

        if (strDeductCond.length() == 10) {
            for (int i = 0; i < strDeductCond.length(); i++) {
                if (!strDeductCond.substring(i, i + 1).equals("0")) {
                    isDeductCond = true;
                    strDeductCondCName += arrStrDeductCondCName[i];
                    if (i == 5) {
                        strDeductCondCName += "("
                                + strDeductCond.substring(i, i + 1) + "��)";
                    }
                    if (i == 6) {
                        strDeductCondCName += "("
                                + strDeductCond.substring(i, i + 1) + "��)";
                    }
                    strDeductCondCName += ";";
                }
            }
            if (!isDeductCond) {
                strDeductCondCName = "��";
            }
        } else {
            strDeductCondCName = "�����������󣡣�";
        }
        return strDeductCondCName;
    }

    /**
     * ��õ���������ǰ���·� @author zhangshengli
     * @param strDate
     * @param StatisWay
     * @param intCount
     * @return
     */
    public String getPreYearMonth(String strDate, String StatisWay, int intCount) {
        String strReturn = "";
        int intYear = 0;
        int intMonth = 0;
        int intDate = 0;
        int inSemester = 0;
        int intThreeMonth = 0;
        int month = 0;
        int year = 0;
        Date tempDate = new Date(strDate);
        intMonth = tempDate.get(Date.MONTH);
        month = intMonth - intCount * Integer.parseInt(StatisWay);
        if (month <= 0) {
            year = (-month) / 12 + 1;
            intYear = tempDate.get(Date.YEAR) - 1 * year;
            intMonth = month + 12 * year;
        } else {
            intYear = tempDate.get(Date.YEAR);
            intMonth = month;
        }
        //��
        if (StatisWay.equals("1")) {
            if (intMonth < 10)
                strReturn = Integer.toString(intYear) + "0"
                        + Integer.toString(intMonth);
            else
                strReturn = Integer.toString(intYear)
                        + Integer.toString(intMonth);
        }
        //��
        if (StatisWay.equals("3")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3) {
                intThreeMonth = 1;
            } else if (intMonth == 4 || intMonth == 5 || intMonth == 6) {
                intThreeMonth = 2;
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9) {
                intThreeMonth = 3;
            } else if (intMonth == 10 || intMonth == 11 || intMonth == 12) {
                intThreeMonth = 4;
            }
            strReturn = Integer.toString(intYear) + "Q"
                    + Integer.toString(intThreeMonth);
        }
        //����
        if (StatisWay.equals("6")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3
                    || intMonth == 4 || intMonth == 5 || intMonth == 6) {
                strReturn = Integer.toString(intYear) + "H1";
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9
                    || intMonth == 10 || intMonth == 11 || intMonth == 12) {
                strReturn = Integer.toString(intYear) + "H2";
            }
        }
        //��
        if (StatisWay.equals("12")) {
            strReturn = Integer.toString(intYear);
        }
        return strReturn;
    }

    /**
     * ��õ���������ǰ���·ݵ��³� @author zhangshengli
     * @param strDate
     * @param StatisWay
     * @param intCount
     * @return
     */
    public String getMonthStart(String strDate, String StatisWay, int intCount) {
        String strReturn = "";
        int intYear = 0;
        int intMonth = 0;
        int intDate = 0;
        int inSemester = 0;
        int intThreeMonth = 0;
        int month = 0;
        int year = 0;
        Date tempDate = new Date(strDate);
        intMonth = tempDate.get(Date.MONTH);
        month = intMonth - intCount * Integer.parseInt(StatisWay);
        if (month <= 0) {
            year = (-month) / 12 + 1;
            intYear = tempDate.get(Date.YEAR) - 1 * year;
            intMonth = month + 12 * year;
        } else {
            intYear = tempDate.get(Date.YEAR);
            intMonth = month;
        }
        //��
        if (StatisWay.equals("1")) {
            if (intMonth < 10)
                strReturn = Integer.toString(intYear) + "-" + "0"
                        + Integer.toString(intMonth) + "-01";
            else
                strReturn = Integer.toString(intYear) + "-"
                        + Integer.toString(intMonth) + "-01";
        }
        //��
        if (StatisWay.equals("3")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3) {
                strReturn = Integer.toString(intYear) + "-01-01";
            } else if (intMonth == 4 || intMonth == 5 || intMonth == 6) {
                strReturn = Integer.toString(intYear) + "-04-01";
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9) {
                strReturn = Integer.toString(intYear) + "-07-01";
            } else if (intMonth == 10 || intMonth == 11 || intMonth == 12) {
                strReturn = Integer.toString(intYear) + "-10-01";
            }
        }
        //����
        if (StatisWay.equals("6")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3
                    || intMonth == 4 || intMonth == 5 || intMonth == 6) {
                strReturn = Integer.toString(intYear) + "-01-01";
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9
                    || intMonth == 10 || intMonth == 11 || intMonth == 12) {
                strReturn = Integer.toString(intYear) + "-07-01";
            }
        }
        //��
        if (StatisWay.equals("12")) {
            strReturn = Integer.toString(intYear) + "-01-01";
        }

        return strReturn;
    }

    /**
     * ��õ���������ǰ����ĩ @author zhangshengli
     * @param strDate
     * @param StatisWay
     * @param intCount
     * @return
     */
    public String getMonthEnd(String strDate, String StatisWay, int intCount) {
        String strReturn = "";
        int intYear = 0;
        int intMonth = 0;
        int intDate = 0;
        int inSemester = 0;
        int intThreeMonth = 0;
        int month = 0;
        int year = 0;
        Date tempDate = new Date(strDate);
        intMonth = tempDate.get(Date.MONTH);
        intDate = tempDate.get(Date.DATE);
        month = intMonth - intCount * Integer.parseInt(StatisWay);
        if (month <= 0) {
            year = (-month) / 12 + 1;
            intYear = tempDate.get(Date.YEAR) - 1 * year;
            intMonth = month + 12 * year;
        } else {
            intYear = tempDate.get(Date.YEAR);
            intMonth = month;
        }
        //if (intDate>=29)
        //{
        if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7
                || intMonth == 8 || intMonth == 10 || intMonth == 12)
            intDate = 31;
        else if (intMonth == 4 || intMonth == 6 || intMonth == 9
                || intMonth == 11)
            intDate = 30;
        else
            intDate = 28;
        //}

        //��
        if (StatisWay.equals("1")) {
            if (intMonth < 10)
                strReturn = Integer.toString(intYear) + "-" + "0"
                        + Integer.toString(intMonth) + "-"
                        + Integer.toString(intDate);
            else
                strReturn = Integer.toString(intYear) + "-"
                        + Integer.toString(intMonth) + "-"
                        + Integer.toString(intDate);
        }
        //��
        if (StatisWay.equals("3")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3) {
                strReturn = Integer.toString(intYear) + "-03-31";
            } else if (intMonth == 4 || intMonth == 5 || intMonth == 6) {
                strReturn = Integer.toString(intYear) + "-06-30";
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9) {
                strReturn = Integer.toString(intYear) + "-09-30";
            } else if (intMonth == 10 || intMonth == 11 || intMonth == 12) {
                strReturn = Integer.toString(intYear) + "-12-31";
            }
        }
        //����
        if (StatisWay.equals("6")) {
            if (intMonth == 1 || intMonth == 2 || intMonth == 3
                    || intMonth == 4 || intMonth == 5 || intMonth == 6) {
                strReturn = Integer.toString(intYear) + "-06-30";
            } else if (intMonth == 7 || intMonth == 8 || intMonth == 9
                    || intMonth == 10 || intMonth == 11 || intMonth == 12) {
                strReturn = Integer.toString(intYear) + "-12-31";
            }
        }
        //��
        if (StatisWay.equals("12")) {
            strReturn = Integer.toString(intYear) + "-12-31";
        }

        return strReturn;
    }

    /**
     * �������YearMonth֮����·ݲ� @author Administrator
     * @param startYearMonth
     * @param endYearMonth
     * @param StatisWay
     * @return
     */
    public int getYearMonthMinus(String startYearMonth, String endYearMonth,
            String StatisWay) {
        int month = 0;
        int intStartYear = 0;
        int intStartMonth = 0;
        int intEndYear = 0;
        int intEndMonth = 0;
        if (StatisWay.equals("1")) {
            intStartYear = Integer.parseInt(startYearMonth.substring(0, 4));
            intStartMonth = Integer.parseInt(startYearMonth.substring(4, 6));
            intEndYear = Integer.parseInt(endYearMonth.substring(0, 4));
            intEndMonth = Integer.parseInt(endYearMonth.substring(4, 6));

            month = intEndMonth - intStartMonth + 12
                    * (intEndYear - intStartYear);
        }
        if (StatisWay.equals("3")) {
            intStartYear = Integer.parseInt(startYearMonth.substring(0, 4));
            intStartMonth = Integer.parseInt(startYearMonth.substring(5, 6));
            intEndYear = Integer.parseInt(endYearMonth.substring(0, 4));
            intEndMonth = Integer.parseInt(endYearMonth.substring(5, 6));
            month = intEndMonth - intStartMonth + 4
                    * (intEndYear - intStartYear);
        }
        if (StatisWay.equals("6")) {
            intStartYear = Integer.parseInt(startYearMonth.substring(0, 4));
            intEndYear = Integer.parseInt(endYearMonth.substring(0, 4));
            if (startYearMonth.substring(5, 6).equals(
                    endYearMonth.substring(5, 6)))
                month = 2 * (intEndYear - intStartYear);
            else
                month = 1 + 2 * (intEndYear - intStartYear);
        }
        if (StatisWay.equals("12")) {
            intStartYear = Integer.parseInt(startYearMonth.substring(0, 4));
            intEndYear = Integer.parseInt(endYearMonth.substring(0, 4));
            month = intEndYear - intStartYear;
        }
        return month;
    }

    /**
     * ������ʼ����ֹ���ڼ���δ��������׼����Ҫ�õ����·ݲ����һ���²��㣬������>=28ʱ�·ݼ�1��
     *
     * @param iStartDate   ��ʼ����
     * @param iStartHour ��ʼСʱ
     * @param iEndDate ��ֹ����
     * @param iEndHour ��ֹСʱ
     * @return �·ݲ�
     */
    public int getMonthMinusForNoDuty(Date iStartDate, int iStartHour,
            Date iEndDate, int iEndHour) throws Exception {
        int intMonth = 0;
        intMonth = (iEndDate.get(Date.YEAR) - iStartDate.get(Date.YEAR)) * 12
                + iEndDate.get(Date.MONTH) - iStartDate.get(Date.MONTH);
        if (iEndDate.get(Date.DATE) - iStartDate.get(Date.DATE) >= 28
                || iEndDate.get(Date.DATE) == iStartDate.get(Date.DATE)
                && iEndHour > iStartHour)
            intMonth++;
        return intMonth;
    }

    /**
     * @desc �������ı������ޱ���
     * @param String strRiskCode
     * @param double dblPremiumOld
     * @param double dblShortRateOld
     * @param double dblShortRate
     * @return double dblPremiumNew
     * @author luyang
     */
    public double calculateByPeriod(String strRiskCode, double dblPremiumOld, double dblShortRateOld, double dblShortRateNew)
    throws Exception {
        double dblPremiumNew = 0;
        dblPremiumNew = dblPremiumOld * (dblShortRateNew / dblShortRateOld);
        return dblPremiumNew;
    }

    /**
     * �������ı������޶��ڷ���
     * @param newStartDate Date
     * @param newStartHour int
     * @param newEndDate Date
     * @param newEndHour int
     * @param oldStartDate Date
     * @param oldStartHour int
     * @param oldEndDate Date
     * @param oldEndHour int
     * @param shortFlag String ����ʱѡ��Ķ��ڷ���
     * @param oldShortRate double
     * @param riskCode String
     * @throws Exception
     * @return double
     * @author luyang
     */
  /*  public double calPeriodShortRate(Date newStartDate, int newStartHour,
                                     Date newEndDate, int newEndHour,
                                     Date oldStartDate, int oldStartHour,
                                     Date oldEndDate, int oldEndHour,
                                     String shortFlag, double oldShortRate,
                                     String riskCode)
    throws Exception {
        double shortRate = 0d; //���صĶ���ϵ��
        double shortRateOld2 = 0d; //ԭ�������ޡ��¶��ڷ��ʷ�ʽ����Ķ���ϵ��
        double shortRateNew = 0d; //�±������ޡ��¶��ڷ��ʷ�ʽ����Ķ���ϵ��

        shortRateNew = this.getShortRate(riskCode,
                                         newStartDate, newStartHour,
                                         newEndDate, newEndHour,
                                         shortFlag);
        shortRateOld2 = this.getShortRate(riskCode,
                                          oldStartDate, oldStartHour,
                                          oldEndDate, oldEndHour,
                                          shortFlag);
        //����ϵ��=ԭ����ϵ��
        //+�±������ޡ��¶��ڷ��ʷ�ʽ����Ķ���ϵ��
        //-ԭ�������ޡ��¶��ڷ��ʷ�ʽ����Ķ���ϵ��
        shortRate = oldShortRate + shortRateNew - shortRateOld2;
        return shortRate;
    }*/
    /**
	 * ������Ŀ��ר�� ��ȡ�����·ݲ�
	 * ������Ŀ��  ����  
	 * �����·ݲ�     
	 * @param startDate
     * @param startHour
     * @param endDate
     * @param endHour
     * @return 
	 */
   public static int insuredMonth(String startDate, String startHour,
         String endDate, String endHour) {
//       java.util.Date start = new java.util.Date(startDate);
//       java.util.Date end = new java.util.Date(endDate);
//       SimpleDateFormat simpleStart = new SimpleDateFormat("YYYY-MM-DD");
//       String strStrat = simpleStart.format(start);
//       String strEnd  =  simpleStart.format(end);
	   //
	   //
	  // 
	  // 
       String StartArray[] = startDate.split("-");
       String EndArray[] = endDate.split("-");
       //String startTime[] = startHour.split(":");
       //String endTime[] = endHour.split(":");
       
       int startYear  = 0;
       int startMonth = 0;
       int startDay   = 0;
       int startH     = 0;
       int startM     = 0;
       int startS     = 0;
       int endYear  = 0;
       int endMonth = 0;
       int endDay   = 0;
       int endH     = 0;
       int endM     = 0;
       int endS     = 0;
       
       
       
       startYear = Integer.parseInt(StartArray[0]);
       startMonth = Integer.parseInt(StartArray[1]);
       startDay = Integer.parseInt(StartArray[2]);
       startH = Integer.parseInt(startHour);
       //startM = Integer.parseInt(startTime[1]);
       //startS = Integer.parseInt(startTime[2]);

       endYear = Integer.parseInt(EndArray[0]);
       endMonth = Integer.parseInt(EndArray[1]);
       endDay = Integer.parseInt(EndArray[2]);
       endH = Integer.parseInt(endHour);
       //endM = Integer.parseInt(endTime[1]);
       //endS = Integer.parseInt(endTime[2]);
       
       int month = 0;
       int lv_Year= endYear-startYear;
       int lv_Month=endMonth-startMonth;
       int lv_Day=endDay-startDay;
       //int lv_Hour=endH-startH;
       month= lv_Month+lv_Year*12;
       
       if(startH==0)//0ʱ��
       {
        if(lv_Day>=0)
        {
        	month++; 
        }
       }
       else  //12ʱ��
       {
    	   if(lv_Day>0)
    	   {
    		   month++; 
    	   }
       }
    
       
       return month;
   
        }

    /**
     * @deprecated ���ݴ������޻�ȡ��׼����
     * @param strStartDate
     * @param strEndDate
     * @return double
     * @throws Exception
     */
    public double getLoanRate(String strStartDate,String strEndDate) throws Exception
    {
      double[] yearrates = new double[]{1.00,   //1��
              1.98,   //2��
              2.93,   //3��
              3.86,
              4.76,
              5.65,
              6.51,
              7.35,
              8.17,
              8.97,   //10��
              9.75,
              10.51,
              11.26,
              11.98,
              12.69,
              13.38,
              14.06,
              14.71,
              15.35,
              15.98,  //20��
              16.59,
              17.18,
              17.77,
              18.33,
              18.88,
              19.42,
              19.95,
              20.46,
              20.96,
              21.45};
      Date startdate = new Date(strStartDate);
      startdate.setDateDelimiter("-");
      Date enddate   = new Date(strEndDate);
      enddate.setDateDelimiter("-");
      int startMonth= startdate.get(Date.MONTH);
      int endMonth  = enddate.get(Date.MONTH);
      int startYear = startdate.get(Date.YEAR);
      int endYear   = enddate.get(Date.YEAR);
      int intLoanYear  = endYear - startYear;
      int intLoanMonth = this.getMonthMinus(startdate,0,enddate,0);
      int intModMonth  = intLoanMonth % 12;
      if (intLoanYear > 31 || intLoanYear == 30 && endMonth >startMonth)
      {
          //�������޲��ܳ���30��
          return 0;
      }
      if (intModMonth == 0)
      {
        if (intLoanYear == 0)
            intLoanYear = 1;
        return yearrates[intLoanYear-1];
      }
      else
      {
        double dblModMonth = Double.parseDouble(String.valueOf(intModMonth))/12;
        if (endMonth >= startMonth)
        {
            if (intLoanYear == 0)
              return yearrates[0] * intModMonth /12;
            else
            {
              return yearrates[intLoanYear-1] * (1- dblModMonth) + yearrates[intLoanYear] * dblModMonth;
            }
        }
        else
        {
            if (intLoanYear == 0 || intLoanYear == 1)
              return yearrates[0] * dblModMonth;
            else
              return yearrates[intLoanYear-2] * (1- dblModMonth) + yearrates[intLoanYear-1] * dblModMonth;
        }
      }
    }

    /**
     * @deprecated �����ձ��Ӧ��ͬ����������ȡ���ڷ���ϵ��
     * @param oldYears
     * @param newYears
     * @param kindCode
     * @return shortRate
     * @throws Exception
     * @author luyang
     * @since �÷��������淶��ֻ�ǿ������ȽϽ�û�����ɶ�Ӧ�ı�ṹ������ʱ����
     */
 /*  public double getYearShortRate(int oldYears,int newYears ,
            String kindCode)throws Exception
    {
        double shortRate = 0;
        DbPool dbpool = new DbPool();
        //�����ݿ⣬��ʼ����
        dbpool.open(SysConfig.getProperty("DDCCDATASOURCE"));
        try{
            String sql = "SELECT ShortRate FROM PrpDYearShortRate WHERE "
                + " KindCode='"+ kindCode +"'"
                + " AND OriginalPeriod='"+ oldYears +"'"
                + " AND ActualPeriod='"+ newYears +"'";
            ResultSet rs = dbpool.query(sql);
            if(rs.next()){
                shortRate = rs.getDouble("ShortRate");
            }
        }
        catch(Exception exception){
            throw exception;
        }
        finally{
            dbpool.close();
        }
        return shortRate;
    }
    
    //added by LanNing begin 20070625 ��itemkind���ѯ���ݣ������ձ�������
 /*   public static BLPrpCitemKind getItemKindGroup(String policyNo,String flag) throws Exception{
    	DbPool dbpool = new DbPool();
    	try{
    		String strDataSource = SysConfig.getProperty("DDCCDATASOURCE");
        	BLPrpCitemKind blPrpCitemKind = new BLPrpCitemKind();
        	//�����ݿ⣬��ʼ����
            dbpool.open(strDataSource);
            String strFlag = "";
            if(flag.trim().toLowerCase().equals("main")){//����
            	strFlag = "1";
            }
            if(flag.trim().toLowerCase().equals("sub")){//������
            	strFlag = "2";
            }
            String strSQL = "Select kindname,kindcode,currency, Sum(amount) amount,Sum(premium) premium From prpcitemkind " +
            				"Where policyno = ? And substr(flag,2,1) = ? " +
            				"Group By kindname,kindcode,currency";
            dbpool.prepareStatement(strSQL);
            dbpool.setString(1,policyNo);
            dbpool.setString(2,strFlag);
            ResultSet resultSet = dbpool.executePreparedQuery();
            while(resultSet.next()){
                PrpCitemKindSchema prpCitemKindSubSchema = new PrpCitemKindSchema();
                prpCitemKindSubSchema.setKindName(resultSet.getString("kindName"));
                prpCitemKindSubSchema.setCurrency(resultSet.getString("currency"));
                prpCitemKindSubSchema.setAmount(resultSet.getString("amount"));
                prpCitemKindSubSchema.setPremium(resultSet.getString("premium"));
                blPrpCitemKind.setArr(prpCitemKindSubSchema);
            }
            dbpool.close();
            return blPrpCitemKind;
    	}catch(Exception e){
    		e.printStackTrace();
    		dbpool.close();
            throw e;
    	}finally{
            dbpool.close();
        }
    }
    //added by LanNing end 20070625 ��itemkind���ѯ���ݣ������ձ�������
    
    //added by Rowland begin 20070628 ����ԤԼ���պţ���prpcfee��ȡС�����Ľ��������֧���ұ����ֻ���
    public BLPrpCfee queryFeeByPoliRiskCurrency1(String strSQL) throws Exception{ 
    	DbPool dbpool = new DbPool();
    	try{
    		String strDataSource = SysConfig.getProperty("DDCCDATASOURCE");
    		BLPrpCfee blPrpCfee = new BLPrpCfee();
        	//�����ݿ⣬��ʼ����
            dbpool.open(strDataSource);            
            ResultSet resultSet = dbpool.query(strSQL);
            while(resultSet.next()){
    			PrpCfeeSchema prpCfeeSchema = new PrpCfeeSchema();
    			prpCfeeSchema.setPolicyNo(resultSet.getString("policyNo"));
    			prpCfeeSchema.setRiskCode(resultSet.getString("riskCode"));
    			prpCfeeSchema.setCurrency1(resultSet.getString("currency1"));
    			prpCfeeSchema.setAmount1(resultSet.getString("amount1"));
    			prpCfeeSchema.setPremium1(resultSet.getString("premium1"));
    			blPrpCfee.setArr(prpCfeeSchema);
            }
            dbpool.close();
            return blPrpCfee;
    	}catch(Exception e){
    		e.printStackTrace();
    		dbpool.close();
            throw e;
    	}finally{
            dbpool.close();
        }
    }
    public BLPrpCfee queryFeeByRiskCurrency1(String strSQL) throws Exception{ 
    	DbPool dbpool = new DbPool();
    	try{
    		String strDataSource = SysConfig.getProperty("DDCCDATASOURCE");
    		BLPrpCfee blPrpCfee = new BLPrpCfee();
        	//�����ݿ⣬��ʼ����
            dbpool.open(strDataSource);            
            ResultSet resultSet = dbpool.query(strSQL);
            while(resultSet.next()){
    			PrpCfeeSchema prpCfeeSchema = new PrpCfeeSchema();
    			prpCfeeSchema.setRiskCode(resultSet.getString("riskCode"));
    			prpCfeeSchema.setCurrency1(resultSet.getString("currency1"));
    			prpCfeeSchema.setAmount1(resultSet.getString("amount1"));
    			prpCfeeSchema.setPremium1(resultSet.getString("premium1"));
    			prpCfeeSchema.setExchangeRate1(resultSet.getString("exchangerate1"));
    			blPrpCfee.setArr(prpCfeeSchema);
            }
            dbpool.close();
            return blPrpCfee;
    	}catch(Exception e){
    		e.printStackTrace();
    		dbpool.close();
            throw e;
    	}finally{
            dbpool.close();
        }
    }
    //added by Rowland end 20070628 ����ԤԼ���պţ���prpcfee��ȡС�����Ľ��������֧���ұ����ֻ���
    //added by taoyan begin 20070925
    public BLPrpCfee querySumAmountAndPremium(String strSQL) throws Exception{ 
    	DbPool dbpool = new DbPool();
    	try{
    		String strDataSource = SysConfig.getProperty("DDCCDATASOURCE");
    		BLPrpCfee blPrpCfee = new BLPrpCfee();
        	//�����ݿ⣬��ʼ����
            dbpool.open(strDataSource);            
            ResultSet resultSet = dbpool.query(strSQL);
            while(resultSet.next()){
    			PrpCfeeSchema prpCfeeSchema = new PrpCfeeSchema();
    			prpCfeeSchema.setAmount1(resultSet.getString("amount1"));
    			prpCfeeSchema.setPremium1(resultSet.getString("premium1"));
    			blPrpCfee.setArr(prpCfeeSchema);
            }
            dbpool.close();
            return blPrpCfee;
    	}catch(Exception e){
    		e.printStackTrace();
    		dbpool.close();
            throw e;
    	}finally{
            dbpool.close();
        }
    }
    //add by zhangruifeng 20080525 reason:�����������س�������¼ʱ��Ʊ�Ǽ�
    public void echoVisaForInvoice(String proposalNo) throws Exception
    {
		BLPrpInvoicePrintInfo blPrpInvoicePrintInfo = new BLPrpInvoicePrintInfo();
		BLPrpInvoicePrintInfo blPrpInvoicePrintInfoCI = new BLPrpInvoicePrintInfo();
		BLPrpJpayRefRec blPrpJpayRefRec = new BLPrpJpayRefRec();
		BLPrpJplanFee blPrpJplanFee = new BLPrpJplanFee ();
		PrpJplanFeeSchema  prpJplanFeeSchema = new PrpJplanFeeSchema();
		PrpJpayRefRecSchema prpJpayRefRecSchema  = new PrpJpayRefRecSchema();
		BLPrpJinvoiceLoan blPrpJinvoiceLoan = new BLPrpJinvoiceLoan(); 
		PrpJinvoiceLoanSchema prpJinvoiceLoanSchema = new PrpJinvoiceLoanSchema(); 
		BLVsCodeSet blVsCodeSet = new BLVsCodeSet();
		BLPrpCmain blPrpCmain = new BLPrpCmain();
		BLPrpCmainSub blPrpCmainSub = new BLPrpCmainSub();
		Visa  visa  = new Visa ();
		String strPreVisaCode = "";
		String strPreVisaSerialNo = "";
		String strVisaName="";
		String	strPrpJplanFee ="";
		String policyNo = "";
		String policyNoCI = "";
		String strWhere ="";
		String strUserCode = "";
		String strComCode="";
		strWhere = "proposalno = '"+proposalNo+"'";
		blPrpCmain.query(strWhere);
		if(blPrpCmain.getSize()>0){
			policyNo=blPrpCmain.getArr(0).getPolicyNo();
			strUserCode = blPrpCmain.getArr(0).getHandler1Code();//������¼ʱ���չ���ҵ��Ա���ж��Ƿ����
			strComCode = blPrpCmain.getArr(0).getComCode();
		}
    	blPrpInvoicePrintInfo.getData(policyNo);
		if(blPrpInvoicePrintInfo.getSize()>0){
		String 	iWherePart = "visaCode = '"+blPrpInvoicePrintInfo.getArr(0).getVisaCode()+"'";
		blVsCodeSet.query(iWherePart);
		if(blVsCodeSet.getSize()>0){
			strVisaName = blVsCodeSet.getArr(0).getVisaName();
		}
		
		/*��blPrpInvoicePrintInfo���д�������ʱ֤�����������س����������س���Ʊ��¼ʱ�ո���ϵͳ�Ĵ����ֵ���Ʊ�ͺϴ�Ʊ
		  PrpInvoicePrintInfo��flag 0.���� 1.�ϴ�
		*/
        //����֤��ˮ�ſ���ʱ���ɽ����ո��ѵô���
/*		
		try {
			
		if(visa.checkUsedReady(strUserCode,blPrpInvoicePrintInfo.getArr(0).getVisaCode(),blPrpInvoicePrintInfo.getArr(0).getVisaSerialNo()))
		  {
			
			String strQueryType = "1";
			if("0".equals(blPrpInvoicePrintInfo.getArr(0).getFlag())){//���ǵ���Ʊʱ
				strPrpJplanFee = " CertiType = 'P'  AND CertiNo = '"+policyNo+"'";
			}else if ("1".equals(blPrpInvoicePrintInfo.getArr(0).getFlag())){
				blPrpCmainSub.getData(policyNo);
				if(blPrpCmainSub.getSize()>0){//���Ǻϴ�Ʊʱ
					policyNoCI = blPrpCmainSub.getArr(0).getMainPolicyNo();
					strPrpJplanFee = " CertiType = 'P'  AND CertiNo in( '"+policyNo+"','"+policyNoCI+"')";
				}
			}
			
			blPrpJplanFee.query(strPrpJplanFee);
			
			blPrpJinvoiceLoan.query(strPrpJplanFee);
			  String[] arrCertiType = new String[blPrpJplanFee.getSize()];
			  String[] arrCertiNo = new String[blPrpJplanFee.getSize()];
			  String[] arrSerialNo = new String[blPrpJplanFee.getSize()];
			  String[] arrPayRefReason = new String[blPrpJplanFee.getSize()];
			  String[] arrPlanFee = new String[blPrpJplanFee.getSize()];
			  String[] arrCurrency2 = new String[blPrpJplanFee.getSize()];
			  String[] arrPayrefFee = new String[blPrpJplanFee.getSize()];
			  String[] arrRealPayRefFee = new String[blPrpJplanFee.getSize()];
			  String[] arrAppliName = new String[blPrpJplanFee.getSize()];
			  String[] arrPayRefTimes = new String[1];
			  for (int j = 0; j < blPrpJplanFee.getSize(); j++) {
			      prpJplanFeeSchema  = blPrpJplanFee.getArr(j);
						arrCertiType[j] = prpJplanFeeSchema.getCertiType();
						
						arrCertiNo[j]= prpJplanFeeSchema.getCertiNo();
						arrSerialNo[j] = "1";
						arrPayRefReason[j]= prpJplanFeeSchema.getPayRefReason();
						arrPlanFee[j]= prpJplanFeeSchema.getPlanFee();
						arrCurrency2[j]= prpJplanFeeSchema.getCurrency1();
						arrPayrefFee[j]= prpJplanFeeSchema.getPlanFee();
						arrRealPayRefFee[j]= prpJplanFeeSchema.getPlanFee();
						arrAppliName[j]= prpJplanFeeSchema.getAppliName();				  
			      prpJpayRefRecSchema.setOperateDate(blPrpInvoicePrintInfo.getArr(0).getOperateDate());
			      //modify by huabaoguo 20081201 ����OperatorCode �� PrinterCode
			      prpJpayRefRecSchema.setOperatorCode(blPrpInvoicePrintInfo.getArr(0).getOperatorCode());
			      prpJpayRefRecSchema.setPrinterCode(strUserCode);
			      prpJpayRefRecSchema.setOperateUnit(strComCode);
			      prpJpayRefRecSchema.setCurrency2(prpJplanFeeSchema.getCurrency1());
			      prpJpayRefRecSchema.setVisaCode(blPrpInvoicePrintInfo.getArr(0).getVisaCode());
			      prpJpayRefRecSchema.setVisaName(strVisaName);
			      prpJpayRefRecSchema.setVisaSerialNo(blPrpInvoicePrintInfo.getArr(0).getVisaSerialNo());
			      prpJpayRefRecSchema.setPrintDate(blPrpInvoicePrintInfo.getArr(0).getOperateDate());
			      prpJpayRefRecSchema.setVisaHandler(strUserCode);
			      prpJpayRefRecSchema.setPayRefName("");  //�������س��޷�Ʊ̧ͷ
			      prpJpayRefRecSchema.setPayRefFee(prpJplanFeeSchema.getPlanFee());
			      prpJpayRefRecSchema.setRemark("");	 
			      } 
			 blPrpJpayRefRec.preInvoicePrpall(arrCertiType, arrCertiNo,
                        arrSerialNo, arrPayRefReason, arrPlanFee, arrPayrefFee,
                        arrCurrency2, arrRealPayRefFee, prpJpayRefRecSchema,
                        blPrpInvoicePrintInfo.getArr(0).getOperatorCode(),strQueryType,"",arrCertiType.length,strPreVisaCode,strPreVisaSerialNo);
			 if("0".equals(blPrpInvoicePrintInfo.getArr(0).getFlag())){//���ǵ���Ʊʱ
				 blPrpCmainSub.getData(policyNo);
					if(blPrpCmainSub.getSize()>0){//���ǵ���Ʊ��������
						policyNoCI = blPrpCmainSub.getArr(0).getMainPolicyNo();
						blPrpInvoicePrintInfoCI.getData(policyNoCI);
						strPrpJplanFee = " CertiType = 'P'  AND CertiNo = '"+policyNoCI+"'";
						
						blPrpJplanFee.query(strPrpJplanFee);
						
						blPrpJinvoiceLoan.query(strPrpJplanFee);
						  arrCertiType = new String[blPrpJplanFee.getSize()];
						  arrCertiNo = new String[blPrpJplanFee.getSize()];
						  arrSerialNo = new String[blPrpJplanFee.getSize()];
						  arrPayRefReason = new String[blPrpJplanFee.getSize()];
						  arrPlanFee = new String[blPrpJplanFee.getSize()];
						  arrCurrency2 = new String[blPrpJplanFee.getSize()];
						  arrPayrefFee = new String[blPrpJplanFee.getSize()];
						  arrRealPayRefFee = new String[blPrpJplanFee.getSize()];
						  arrAppliName = new String[blPrpJplanFee.getSize()];
						  arrPayRefTimes = new String[1];
						  for (int j = 0; j < blPrpJplanFee.getSize(); j++) {
						      prpJplanFeeSchema  = blPrpJplanFee.getArr(j);
									arrCertiType[j] = prpJplanFeeSchema.getCertiType();
									
									arrCertiNo[j]= prpJplanFeeSchema.getCertiNo();
									arrSerialNo[j] = "1";
									arrPayRefReason[j]= prpJplanFeeSchema.getPayRefReason();
									arrPlanFee[j]= prpJplanFeeSchema.getPlanFee();
									arrCurrency2[j]= prpJplanFeeSchema.getCurrency1();
									arrPayrefFee[j]= prpJplanFeeSchema.getPlanFee();
									arrRealPayRefFee[j]= prpJplanFeeSchema.getPlanFee();
									arrAppliName[j]= prpJplanFeeSchema.getAppliName();				  
						      prpJpayRefRecSchema.setOperateDate(blPrpInvoicePrintInfoCI.getArr(0).getOperateDate());
						      //modify by huabaoguo 20081201 ����OperatorCode �� PrinterCode
						      prpJpayRefRecSchema.setOperatorCode(blPrpInvoicePrintInfoCI.getArr(0).getOperatorCode());
						      prpJpayRefRecSchema.setPrinterCode(strUserCode);
						      prpJpayRefRecSchema.setOperateUnit(strComCode);
						      prpJpayRefRecSchema.setCurrency2(prpJplanFeeSchema.getCurrency1());
						      prpJpayRefRecSchema.setVisaCode(blPrpInvoicePrintInfoCI.getArr(0).getVisaCode());
						      prpJpayRefRecSchema.setVisaName(strVisaName);
						      prpJpayRefRecSchema.setVisaSerialNo(blPrpInvoicePrintInfoCI.getArr(0).getVisaSerialNo());
						      prpJpayRefRecSchema.setPrintDate(blPrpInvoicePrintInfoCI.getArr(0).getOperateDate());
						      prpJpayRefRecSchema.setVisaHandler(strUserCode);
						      prpJpayRefRecSchema.setPayRefName("");  //�������س��޷�Ʊ̧ͷ
						      prpJpayRefRecSchema.setPayRefFee(prpJplanFeeSchema.getPlanFee());
						      prpJpayRefRecSchema.setRemark("");	 
						      } 
						 blPrpJpayRefRec.preInvoicePrpall(arrCertiType, arrCertiNo,
			                        arrSerialNo, arrPayRefReason, arrPlanFee, arrPayrefFee,
			                        arrCurrency2, arrRealPayRefFee, prpJpayRefRecSchema,
			                        blPrpInvoicePrintInfoCI.getArr(0).getOperatorCode(),strQueryType,"",arrCertiType.length,strPreVisaCode,strPreVisaSerialNo);
					}
			 }		 
		  }
		}
		catch(Exception e){
    		e.printStackTrace();
            throw e;
    	}
		
		}
    }
    public void echoVisaForInvoiceEndorse(String endorseNo,String visaCode,String visaserialNo) throws Exception
    {
		BLPrpInvoicePrintInfo blPrpInvoicePrintInfo = new BLPrpInvoicePrintInfo();
		BLPrpJpayRefRec blPrpJpayRefRec = new BLPrpJpayRefRec();
		BLPrpJplanFee blPrpJplanFee = new BLPrpJplanFee ();
		PrpJplanFeeSchema  prpJplanFeeSchema = new PrpJplanFeeSchema();
		PrpJpayRefRecSchema prpJpayRefRecSchema  = new PrpJpayRefRecSchema();
		BLPrpJinvoiceLoan blPrpJinvoiceLoan = new BLPrpJinvoiceLoan(); 
		PrpJinvoiceLoanSchema prpJinvoiceLoanSchema = new PrpJinvoiceLoanSchema(); 
		BLVsCodeSet blVsCodeSet = new BLVsCodeSet();
		BLPrpPmain blPrpPmain = new BLPrpPmain();
		BLPrpCmainSub blPrpCmainSub = new BLPrpCmainSub();
		Visa  visa  = new Visa ();
		String strPreVisaCode = "";
		String strPreVisaSerialNo = "";
		String strVisaName="";
		String	strPrpJplanFee ="";
		String policyNo = "";
		String policyNoCI = "";
		String strWhere ="";
		String strUserCode = "";
		String strComCode="";
		strWhere = "endorseNo = '"+endorseNo+"'";
		blPrpPmain.query(strWhere);
		if(blPrpPmain.getSize()>0){
			strUserCode = blPrpPmain.getArr(0).getHandler1Code();//������¼ʱ���չ���ҵ��Ա���ж��Ƿ����
			strComCode = blPrpPmain.getArr(0).getComCode();
		}
		try {
    	blPrpInvoicePrintInfo.getData(endorseNo);
		if(blPrpInvoicePrintInfo.getSize()>0){
		String 	iWherePart = "visaCode = '"+blPrpInvoicePrintInfo.getArr(0).getVisaCode()+"'";
		blVsCodeSet.query(iWherePart);
		if(blVsCodeSet.getSize()>0){
			strVisaName = blVsCodeSet.getArr(0).getVisaName();
		}
		
		/*��blPrpInvoicePrintInfo���д�������ʱ֤�����������س����������س���Ʊ��¼ʱ�ո���ϵͳ�Ĵ����ֵ���Ʊ�ͺϴ�Ʊ
		  PrpInvoicePrintInfo��flag 0.���� 1.�ϴ�
		*/
        //����֤��ˮ�ſ���ʱ���ɽ����ո��ѵô���
/*		
		if(visa.checkUsedReady(strUserCode,blPrpInvoicePrintInfo.getArr(0).getVisaCode(),blPrpInvoicePrintInfo.getArr(0).getVisaSerialNo()))
		  {
			String strQueryType = "1";
			if("0".equals(blPrpInvoicePrintInfo.getArr(0).getFlag())){//���ǵ���Ʊʱ
				strPrpJplanFee = " CertiType = 'E'  AND CertiNo = '"+endorseNo+"'";
			}
			blPrpJplanFee.query(strPrpJplanFee);
			blPrpJinvoiceLoan.query(strPrpJplanFee);
			  String[] arrCertiType = new String[blPrpJplanFee.getSize()];
			  String[] arrCertiNo = new String[blPrpJplanFee.getSize()];
			  String[] arrSerialNo = new String[blPrpJplanFee.getSize()];
			  String[] arrPayRefReason = new String[blPrpJplanFee.getSize()];
			  String[] arrPlanFee = new String[blPrpJplanFee.getSize()];
			  String[] arrCurrency2 = new String[blPrpJplanFee.getSize()];
			  String[] arrPayrefFee = new String[blPrpJplanFee.getSize()];
			  String[] arrRealPayRefFee = new String[blPrpJplanFee.getSize()];
			  String[] arrAppliName = new String[blPrpJplanFee.getSize()];
			  String[] arrPayRefTimes = new String[1];
			  
			  for (int j = 0; j < blPrpJplanFee.getSize(); j++) {
			      prpJplanFeeSchema  = blPrpJplanFee.getArr(j);
						arrCertiType[j] = prpJplanFeeSchema.getCertiType();
						
						arrCertiNo[j]= prpJplanFeeSchema.getCertiNo();
						arrSerialNo[j] = "1";
						arrPayRefReason[j]= prpJplanFeeSchema.getPayRefReason();
						arrPlanFee[j]= prpJplanFeeSchema.getPlanFee();
						arrCurrency2[j]= prpJplanFeeSchema.getCurrency1();
						arrPayrefFee[j]= prpJplanFeeSchema.getPlanFee();
						arrRealPayRefFee[j]= prpJplanFeeSchema.getPlanFee();
						arrAppliName[j]= prpJplanFeeSchema.getAppliName();				  
			      prpJpayRefRecSchema.setOperateDate(blPrpInvoicePrintInfo.getArr(0).getOperateDate());
			    //modify by huabaoguo 20081201 ����OperatorCode �� PrinterCode
			      prpJpayRefRecSchema.setOperatorCode(blPrpInvoicePrintInfo.getArr(0).getOperatorCode());
			      prpJpayRefRecSchema.setPrinterCode(strUserCode);
			      prpJpayRefRecSchema.setOperateUnit(strComCode);
			      prpJpayRefRecSchema.setCurrency2(prpJplanFeeSchema.getCurrency1());
			      prpJpayRefRecSchema.setVisaCode(blPrpInvoicePrintInfo.getArr(0).getVisaCode());
			      prpJpayRefRecSchema.setVisaName(strVisaName);
			      prpJpayRefRecSchema.setVisaSerialNo(blPrpInvoicePrintInfo.getArr(0).getVisaSerialNo());
			      prpJpayRefRecSchema.setPrintDate(blPrpInvoicePrintInfo.getArr(0).getOperateDate());
			      prpJpayRefRecSchema.setVisaHandler(strUserCode);
			      prpJpayRefRecSchema.setPayRefName("");  //�������س��޷�Ʊ̧ͷ
			      prpJpayRefRecSchema.setPayRefFee(prpJplanFeeSchema.getPlanFee());
			      prpJpayRefRecSchema.setRemark("");
			      blPrpJpayRefRec.preInvoicePrpall(arrCertiType, arrCertiNo,
	                        arrSerialNo, arrPayRefReason, arrPlanFee, arrPayrefFee,
	                        arrCurrency2, arrRealPayRefFee, prpJpayRefRecSchema,
	                        blPrpInvoicePrintInfo.getArr(0).getOperatorCode(),strQueryType,"",arrCertiType.length,strPreVisaCode,strPreVisaSerialNo);	 
			      } 
		  }
		}
		  if(!"".equals(visaCode)&&visaCode!=null&&!"".equals(visaserialNo)&&visaserialNo!=null){
			if(visa.checkUsedReady(strUserCode,visaCode,visaserialNo))//�����Ĵ�ӡ��ˮ�ŵ�����
			{
				visa.useTrans(strUserCode,visaCode,visaserialNo,endorseNo);
			}
		   }
		 
		}
		catch(Exception e){
    		e.printStackTrace();
            throw e;
    	}
		
		
    }
	   //added by gengxiaobo begin 20080708 �ж�05007�Ƿ��Զ��˱�
	   public String checkAutoProposalToPolicy(String strProposalNo,String strComCode)throws Exception
	   {
	   	BLPrpTcarshipTax blPrpTcarshipTax = new BLPrpTcarshipTax();
	   	BLPrpTmain blPrpTmain = new BLPrpTmain();
	   	BLPrpTengage blPrpTengage = new BLPrpTengage();
	   	String strTaxRelifFlag = "";
	   	String strRtnTemp = "12";
	   	blPrpTmain.getData(strProposalNo);
   	    blPrpTengage.getData(strProposalNo);
   	    DateTime strStartDate = new DateTime(blPrpTmain.getArr(0).getStartDate());	    	 
   	    DateTime strEndDate = new DateTime(blPrpTmain.getArr(0).getEndDate());	
   	    String strEndDateForYear ;
   	    int intStartHour = 0;
   	    int intEndHour = 24;
   	    intStartHour = Integer.parseInt(blPrpTmain.getArr(0).getStartHour());
    	intEndHour = Integer.parseInt(blPrpTmain.getArr(0).getEndHour());
	   	blPrpTcarshipTax.getData(strProposalNo);
	     if(blPrpTcarshipTax.getSize()>0)
	     {
	     	strTaxRelifFlag = blPrpTcarshipTax.getArr(0).getTaxRelifFlag().trim();
	     }
	     //������������ǿ��ʱ�ֹ��˱����������ڵ�����ǿ��ʱ����
	     if(SysConfig.getProperty("PERMITCOMCODE_05007").indexOf(strComCode.substring(0,2))>=0)
	     {
	    	 return strRtnTemp = "11";
	     }
	     //���Ϲرս�����Ӫ������(41080000),��ǿ�ա�����˰��־��Ϊ����˰������ı����Զ��˱����ܣ���Ϊ���˹��˱���ʽ����
	     if(!"1N".equals(strTaxRelifFlag.trim())&&"41080000".equals(strComCode.trim()))
	     {
	    	 return strRtnTemp = "11";
	     }
	     if("1409".equals(strComCode.substring(0,4)))
	     {
	    	 return strRtnTemp = "11";
	     }
	     // add by zhangfan ȡ�����ڵ���Ͷ����ǿ���Զ��˱� 20080911 begin
	     if("4403".equals(strComCode.substring(0,4)))
	     {
	    	 return strRtnTemp = "11";
	     }
        //	add by zhangfan ȡ�����ڵ���Ͷ����ǿ���Զ��˱� 20080911 end
	     //���ϸ���ʵ��ҵ����Ҫ����05007�Ƿ��Զ��˱�
	     if("53".equals(strComCode.trim().substring(0,2)))
	     {
	    	 
	    	 if(DateTime.intervalYear(strStartDate, intStartHour, strEndDate, intEndHour)<1
	    			 ||blPrpTengage.getSize()>0
	    			 ||Double.parseDouble(blPrpTmain.getArr(0).getDiscount())>0.2)
	    	 {
	    		 return strRtnTemp = "11";
	    	 }
	     }	
	     //add by zhangruifeng 20081118 reason:�����ı���ֹ���뱣���������ľ�ȷ����������1�꣬ת�˹��˱�
	     if("11".equals(strComCode.trim().substring(0,2)))
	     {
	    	 strEndDateForYear=this.getNextMonthFullDate(blPrpTmain.getArr(0).getStartDate(),12);
	    	 
	        // * @return int intReturn 1 ��First�����ڣ�second����0 ��ȡ�-1 ��First��С�ڣ�second��
	    	 if(!(this.compareDate( strEndDateForYear,blPrpTmain.getArr(0).getEndDate())==0)){
	    		 return strRtnTemp = "11";   
	    	 }
	     }
         //add by panyafeng begin 20081224
	     if("11".equals(strComCode.trim().substring(0,2)))
	     {
	    	 BLPrpTitemCar blPrpTitemCar = new BLPrpTitemCar();
	    	 blPrpTitemCar.getData(strProposalNo);
	    	 String strOtherFlag8 = blPrpTitemCar.getArr(0).getOtherNature().substring(7, 8);
	    	 if("1".equals(strOtherFlag8) || "3M".equals(strTaxRelifFlag.trim()) || "6Q".equals(strTaxRelifFlag.trim())){
	    		 return strRtnTemp = "11";
	    	 }
	     }
	     //add by panyafeng end 20081224
	     return strRtnTemp;
	   }  
	 	//added by gengxiaobo end 20080708 �ж�05007�Ƿ��Զ��˱�      */
    
	/**
	 * ȥ��List��Ϊ�յĶ���
	 */
	@SuppressWarnings("unchecked")
	public static List takeOffNullObject(List list) {
		List newList = new ArrayList();
		for (Object object : list) {
			if (object != null) {
				newList.add(object);
			}
		}
		return newList;
	}
	
	/**
	 * ȥ��List��Ϊ�յĶ���
	 * �㶫ȥ�ն��󷽷� ��������list
	 */
	@SuppressWarnings("unchecked")
	public static void takeOffNullObjectGD(List list) {
		if(list == null || list.size()==0){
			return;
		}
		for(int i = list.size()-1;i>=0;i--){
			if(list.get(i)==null){
				list.remove(i);
			}
		}
	}
	
	/**
	 * <p>
	 * java.util.Date���͵�ʱ��ת��ΪSTRING
	 * </p>
	 * 
	 * @param prpCmain
	 * @return list
	 * @throws Exception
	 * @throws Exception
	 */
    public static String formatDate(java.util.Date date) {
	/*strreturn = ""
		+ (date.getYear() + 1900)
		+ ((date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1))
			: ("" + (date.getMonth() + 1))) + date.getDate();*/
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	return df.format(date);
    }

    /**
     * ��ʽ�����ڵ���ʾ��ʽ
     * 
     * @param date
     *                ����ʾ�����ڶ���
     * @param pattern
     *                ��ʽ��������pattern����yyyy-MM-dd yyyy/MM/dd yyyy��MM��d�յ�
     * @return ���ص��Ǹ�ʽ�����������ʾ�ַ���
     */
    public static String formatDate(java.util.Date date, String pattern) {
	// ��������FORMAT��ʵ��
	SimpleDateFormat df = new SimpleDateFormat(pattern);
	return df.format(date);
    }

    /**
     * String ת Date
     * 
     * @param dateString8���ַ�������
     * @return YYYY-MM-DD��ʽ������
     */
    public static java.util.Date stringToDate(String dateString) {
	if (dateString == null || dateString.equals("")) {
	    return new java.util.Date();
	}
	if (dateString.length() < 8) {
	    throw new IllegalArgumentException(dateString
		    + "�����ڸ�ʽ���ԣ�����Ϊ����8λ�Ĵ����ֵ��ַ���");
	}
	DateTime result =  new DateTime(dateString, DateTime.YEAR_TO_DAY);
	return result;
    }
	
    
    public static String getMapValue(Map<String,String> map,String key){
    	String result = "";
    	key = key.trim();
    	if(map == null || map.isEmpty() || StringUtils.isEmpty(key)){
    		return "";
    	}

    	Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			if(it.next().equals(key)){
				result = map.get(key);
				break;
			}
		}
    	return result;
    }
//    public static PrpQitemKind searchQ(PrpQmain prpQmain,Integer itemKindNo)
//     {
//	PrpQitemKind prpQitemKind = new PrpQitemKind();
//	for (int i = 0; i < prpQmain.getPrpQitemKinds().size(); i++) {
//	    if (prpQmain.getPrpQitemKinds().get(i).getId().getItemKindN()
//		    .intValue() == itemKindNo.intValue()) {
//		prpQitemKind = prpQmain.getPrpQitemKinds().get(i);
//		break;
//	    }
//	}
//	return prpQitemKind;
//    }
}
