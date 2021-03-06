package com.krest.dom4j.utils;

public class JsonFormatUtil {
    public JsonFormatUtil() {
    }

    public static void printJson(String jsonStr) {
        System.out.println(formatJson(jsonStr));
    }

    public static String formatJson(String jsonStr) {
        if (jsonStr != null && !"".equals(jsonStr.trim())) {
            StringBuilder sb = new StringBuilder();
            char last;
            char current = 0;
            int indent = 0;
            boolean isInQuotationMarks = false;

            for(int i = 0; i < jsonStr.length(); ++i) {
                last = current;
                current = jsonStr.charAt(i);
                switch(current) {
                    case '"':
                        if (last != '\\') {
                            isInQuotationMarks = !isInQuotationMarks;
                        }

                        sb.append(current);
                        break;
                    case ',':
                        sb.append(current);
                        if (last != '\\' && !isInQuotationMarks) {
                            sb.append('\n');
                            addIndentBlank(sb, indent);
                        }
                        break;
                    case '[':
                    case '{':
                        sb.append(current);
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            ++indent;
                            addIndentBlank(sb, indent);
                        }
                        break;
                    case ']':
                    case '}':
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            --indent;
                            addIndentBlank(sb, indent);
                        }

                        sb.append(current);
                        break;
                    default:
                        sb.append(current);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        for(int i = 0; i < indent; ++i) {
            sb.append('\t');
        }

    }
}
