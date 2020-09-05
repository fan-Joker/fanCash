package me.fanjoker.cash.others;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NFormat {

    private static final String[] formats2 = {"-", "-", " mil", " milhões", " bilhões", " trilhões", " quadrilhões",
            " quintilhões", " sextilhões", " septilhões", " octilhões", " nonilhões", " decilhões", " undecilhões",
            " duodecilhões", " tredecilhões", " quatrodecilhões", " quindecilhões"};
    public static final String[] format1 = {"-", "-", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "OC", "N", "D", "UN", "DD", "TD", "QD", "QQD"};

    public static String format1000(Object valor)
    {
        String formated = "";
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));
        formated += decimalFormat.format(valor);
        return formated;
    }
    public static String format1k(Object value){
        String val = new DecimalFormat("#,###").format(value).replace(".", ",");
        Integer ii = val.indexOf(","), i = val.split(",").length;
        if(ii == -1)return val;
        return (val.substring(0, ii+2)+format1[i]).replace(",0", "");
    }
    public static String format1mil(Object value){
        String val = new DecimalFormat("#,###").format(value).replace(".", ",");
        Integer ii = val.indexOf(","), i = val.split(",").length;
        if(ii == -1)return val;
        return (val.substring(0, ii+2)+formats2[i]).replace(",0", "");
    }
}
