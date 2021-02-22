package com.example.demo.DAO;

import com.example.demo.util.Calculate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ResultMatrix {

    @Setter private String equation;
    @Getter private int result;
    @Getter @Setter private int correct;
    @Getter @Setter private double cutOffTime;

    @Getter @Setter private long timeMillis;

    public ResultMatrix(){setTimeMillis(System.currentTimeMillis());
    }

    public void setResult(int result){
        this.result=result;
        setCutOffTime(System.currentTimeMillis()-timeMillis);
        Calculate calculate = new Calculate();
        if(calculate.calculate(equation)==result) {
            setCorrect(1);
        } else {
            setCorrect(0);
        }
    }

    public String getEquation(){
        setTimeMillis(System.currentTimeMillis());
        return(this.equation);
    }
}
