package com.example.demo;

import com.example.demo.DAO.ResultMatrix;
import com.example.demo.Utils.Calculate;
import com.example.demo.Utils.Questionnaire;
import com.example.demo.Utils.VoiceAndPrint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class OralMathApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	void testCalculate(){
		Calculate t = new Calculate();
		String s = "1+2*3-10/5";
		double d=t.calculate(s);
		if(d==4) {
			System.out.println(d+" is true");
		}
	}

	@Test
	void Questionaire() {
		System.out.println(Questionnaire.Addition(20));
		System.out.println(Questionnaire.Subtraction(20));
		System.out.println(Questionnaire.AdditionAndSubtraction(20));
	}


	@Test
	void testResultMatrix() throws InterruptedException {
		ResultMatrix resultMatrix = new ResultMatrix();
		resultMatrix.setEquation("2+3");
		Thread.sleep(1000);
		resultMatrix.setResult(4);
		System.out.println("Corrective: "
				+resultMatrix.getCorrect()+
				", cut off time:" +
				resultMatrix.getCutOffTime());
	}

	@Test
	void questionaireList(){
		ArrayList resultMatrixList=new ArrayList();
		for(int i=0;i<10;i++) {
			ResultMatrix resultMatrix=new ResultMatrix();
			resultMatrix.setEquation(Questionnaire.Addition(20));
			resultMatrixList.add(resultMatrix);
		}
		for(int i=0;i<resultMatrixList.size();i++) {
			System.out.println(((ResultMatrix)resultMatrixList.get(i)).getEquation());
		}
	}
	@Test
	void voiceReport() {
		VoiceAndPrint.voiceOnly("测试");
	}
}
