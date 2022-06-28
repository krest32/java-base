package com.krest.toImage;

import com.krest.toImage.utils.ToImageUtil;

public class Test {
    public static void main(String[] args) {
        String str= "<!DOCTYPE html>\n" +
                "\n" +
                "<head lang=\"en\">\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <title>Insert title here</title>\n" +
                "    <style>\n" +
                "        table.tftable,\n" +
                "        table.table-count {\n" +
                "            font-size: 12px;\n" +
                "            color: #333333;\n" +
                "            width: 98%;\n" +
                "            border-width: 1px;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        table.table-count th,\n" +
                "        table.table-count td {\n" +
                "            font-weight: 400;\n" +
                "            font-style: normal;\n" +
                "            font-size: 12px;\n" +
                "            border-width: 1px;\n" +
                "            border-style: solid;\n" +
                "            border-color: #e4e4e4;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        table.tftable th,\n" +
                "        table.tftable td {\n" +
                "            font-weight: 400;\n" +
                "            font-style: normal;\n" +
                "            font-size: 14px;\n" +
                "            border-width: 1px;\n" +
                "            padding: 8px;\n" +
                "            border-style: solid;\n" +
                "            border-color: #e4e4e4;\n" +
                "            text-align: left;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .tftable {\n" +
                "            margin-left: 1%;\n" +
                "            margin-bottom: 10px;\n" +
                "            margin-top: 10px;\n" +
                "        }\n" +
                "\n" +
                "        table.tftable tr:hover {\n" +
                "            background: #C3ECFF;\n" +
                "        }\n" +
                "\n" +
                "        .tftable>thead>tr:hover {\n" +
                "            background: white;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<html>\n" +
                "\n" +
                "<body>\n" +
                "    <h1 style=\"text-align:center\">Summary Report - Individual (Failed)</h1>\n" +
                "    <table border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"tftable\">\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">案件编号</th>\n" +
                "            <td>1234567890</td>\n" +
                "        </tr>\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">调用类型</th>\n" +
                "            <td>wq</td>\n" +
                "        </tr>\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">调用次数</th>\n" +
                "            <td>123456</td>\n" +
                "        </tr>\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">PBOC状态</th>\n" +
                "            <td>2</td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">PBOC描述</th>\n" +
                "            <td>个人征信报告数据文件（XML）不存在</td>\n" +
                "        </tr>\n" +
                "        <tr align=\"center\">\n" +
                "            <th style=\"width: 200px\">错误信息</th>\n" +
                "            <td>1234567</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table width=\"40%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td>&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <div align=\"center\"><strong>提示信息</strong></div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    <table width=\"40%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bordercolor=\"#A6C5E7\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td bordercolor=\"#FFFFFF\" bgcolor=\"#FFFFFF\">\n" +
                "                    <table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"scrolldiv1\">\n" +
                "                        <tbody>\n" +
                "                            <tr>\n" +
                "                                <td></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td>\n" +
                "                                    <div align=\"center\">\n" +
                "                                        <p class=\"style1\"><strong>&nbsp;&nbsp;&nbsp;<font color=\"#FF0000\">本地调取\n" +
                "                                                </font>中征码与企业名称不匹配！</strong></p>\n" +
                "                                    </div>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                    <table width=\"60%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#A6C5E7\">\n" +
                "                        <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"tdStyle1 style1\" width=\"40%\"><b>录入企业名称</b></td>\n" +
                "                                <td id=\"EnterpriseName\" class=\"tdStyle1 style1\"><b>上海华钇铄普精密机械制造有限公司</b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td class=\"tdStyle1 style1\"><b>中征码对应的企业名称</b></td>\n" +
                "                                <td id=\"PBOCenterpriseName\" class=\"tdStyle1 style1\"><b>长沙晴雨雷机电贸易有限公司</b></td>\n" +
                "                            </tr>\n" +
                "                        </tbody>\n" +
                "                    </table> &nbsp;<center> <input onclick=\"javascript:window.close();return false;\"\n" +
                "                            class=\"input-button\"\n" +
                "                            style=\"FONT-SIZE: 12px; FONT-VARIANT: normal; WIDTH: 45px; FONT-WEIGHT: normal; FONT-STYLE: normal; LINE-HEIGHT: normal\"\n" +
                "                            type=\"button\" value=\"关 闭\" /></center>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        ToImageUtil.toImage(str,"d:\\1.png");
    }
}
