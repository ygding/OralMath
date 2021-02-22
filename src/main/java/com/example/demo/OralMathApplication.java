package com.example.demo;

import com.example.demo.DAO.ResultMatrix;
import com.example.demo.util.Questionnaire;
import com.example.demo.util.VoiceAndPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author thomas_ding
 */

@SpringBootApplication
public class OralMathApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OralMathApplication.class, args);
	}
	@Override
	public void run(String[] args) {
		int type = 1;
		int totalNumber = 10;
		int range = 20;
		double Interval = 15000;
		double maxInterval = Interval;

		List resultMatrixList=new ArrayList();

		int result = 99999;

		int correctSum = 0;
		double cutOfTimeSum = 0;
		String choice="";

		// Preparations
		Logger logger = LoggerFactory.getLogger(getClass());
		Scanner scanner=new Scanner(System.in);
		VoiceAndPrint.voice("请选择计算范围：");
		try {
			range = Integer.valueOf(scanner.nextLine());
		} catch(Exception ex){
			logger.warn("SCAN: "+ex.toString());
		}
		VoiceAndPrint.voice("请选择出题种类");
		System.out.print("  (1. "+range+" 以内加法；2. "+range+" 以内减法；3. "
				+range+" 以内乘法；4."+range+" 以内整除法 5."
				+range+" 以内加减混合运算；6."+range+" 以内四则混合计算）：");
		try {
			type = Integer.valueOf(scanner.nextLine());
		} catch(Exception ex){
			logger.warn("SCAN: "+ex.toString());
		}
		VoiceAndPrint.voice("\n请选择总题数：");
		try {
			totalNumber = Integer.valueOf(scanner.nextLine());
		} catch(Exception ex){
			logger.warn("SCAN: "+ex.toString());
		}
		VoiceAndPrint.voice("\n请修改超时时间");
		System.out.print(" (默认15秒)：");
		try {
			maxInterval = Double.valueOf(scanner.nextLine())*1000;
			Interval = maxInterval;
		} catch(Exception ex){
			logger.warn("SCAN: "+ex.toString());
		}

		//Generate Questions
		for (int i=0;i<totalNumber;i++){
			ResultMatrix resultMatrix=new ResultMatrix();
			switch(type) {
				case 2:
					resultMatrix.setEquation(Questionnaire.Subtraction(range));
					choice="以内的减法";
					break;
				case 3:
					resultMatrix.setEquation(Questionnaire.Multiplication(range));
					choice="以内的乘法";
					break;
				case 4:
					resultMatrix.setEquation(Questionnaire.Division(range));
					choice="以内的除法";
					break;
				case 5:
					resultMatrix.setEquation(Questionnaire.AdditionAndSubtraction(range));
					choice="以内的加减混合运算";
					break;
				case 6:
					resultMatrix.setEquation(Questionnaire.arithmeticMix(range));
					choice="以内的四则混合运算";
					break;
				default:
					resultMatrix.setEquation(Questionnaire.Addition(range));
					choice="以内的加法";
			}
			resultMatrixList.add(resultMatrix);
		}
		try {
			VoiceAndPrint.voiceOnly("您选择了"+range+choice+"，准备开始");
			Thread.sleep(5000);
		} catch(Exception ex) {
			logger.warn("Sleep: "+ex);
		}

		//Game Start
		for (int i=0;i<resultMatrixList.size();i++) {
			if(i>=3){
				for(int j=i-3;j<i;j++){
					if(((ResultMatrix)resultMatrixList.get(j)).getCorrect()==0){
						Interval=maxInterval+Interval;
					} else{
						Interval=Interval+((ResultMatrix)resultMatrixList.get(j)).getCutOffTime();
					}
				}
				Interval=Interval/3*1.2;
				logger.info("Adjust maxInterval = "+Interval);
			}

			VoiceAndPrint.voice("\n"+((ResultMatrix)resultMatrixList.get(i)).getEquation()+" = ");

			try {
				result=scanner2(Interval);
			}catch(Exception ex){
				logger.warn("SCAN2: "+ex.toString());
			}
			((ResultMatrix)resultMatrixList.get(i)).setResult(result);
			logger.info("Correct ? "+String.valueOf(((ResultMatrix)resultMatrixList.get(i)).getCorrect()));
			logger.info("TimeConsumed :"+String.valueOf(((ResultMatrix)resultMatrixList.get(i)).getCutOffTime()/1000)+"s");
		}

		//Report results
		for (int i=0;i<resultMatrixList.size();i++) {
			correctSum+=((ResultMatrix)resultMatrixList.get(i)).getCorrect();
			cutOfTimeSum+=((ResultMatrix)resultMatrixList.get(i)).getCutOffTime();
		}
		VoiceAndPrint.voice("本次总共做题："+resultMatrixList.size()+
				"题。总共做对题数: "+correctSum+"题，得分："+Math.round(Double.valueOf(correctSum)/resultMatrixList.size()*100)+
				"分。总共用时: "+Math.round(cutOfTimeSum/1000)+"秒，平均每题用时："+
				Math.round(cutOfTimeSum/resultMatrixList.size()/1000)+"秒。你真棒！");
		scanner.close();
	}

	private static int scanner2(double waitTime) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < waitTime
				&& !in.ready()) {
		}
		if (in.ready()) {
			return (Integer.valueOf(in.readLine()));
		} else {
			return(99999);
		}
	}
}
