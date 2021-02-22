package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoiceAndPrint {

    static Logger logger=LoggerFactory.getLogger(VoiceAndPrint.class);

    public static void voice(String s) {
        String s1,s2;
        var processBuilder = new ProcessBuilder();
        s1=s.replaceAll("-","减");
        s1=s1.replaceAll("\\*","乘");
        s1=s1.replaceAll("/","除");

        processBuilder.command("say","-v","Ting-Ting",s1);
        try {
            processBuilder.start();
        }catch(Exception ex){
            logger.warn("ERROR: "+ex);
        }
        s2=s.replaceAll("/","÷");
        s2=s2.replaceAll("\\*","x");
        System.out.print(s2);
    }

    public static void voiceOnly(String s){
        var processBuilder = new ProcessBuilder();
        processBuilder.command("say","-v","Ting-Ting",s);
        try {
            processBuilder.start();
        }catch(Exception ex){
            logger.warn("ERROR: "+ex);
        }
    }
}
