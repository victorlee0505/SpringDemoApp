package com.example.demo.Calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);
    
    private double leftOperand;
    private double rightOperand;
    private String operator;

    public double getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(double leftOperand) {
        this.leftOperand = leftOperand;
    }

    public double getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(double rightOperand) {
        this.rightOperand = rightOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double calculateResult() {

        LOG.debug("Operator: [{}]", getOperator());
        LOG.debug("leftOperand: [{}]", getLeftOperand());
        LOG.debug("rightOperand: [{}]", getRightOperand());

        double result = 0;
        switch(this.operator) {
            case "+":
                result = this.leftOperand + this.rightOperand;
                break;
            case "-":
                result = this.leftOperand - this.rightOperand;
                break;
            case "*":
                result = this.leftOperand * this.rightOperand;
                break;
            case "/":
                result = this.leftOperand / this.rightOperand;
                break;
            case "^":
                result = Math.pow(this.leftOperand,this.rightOperand);
                break;
            default:
                LOG.error("Invalid operator [{}]", getOperator());
                result = 0;
        }

        return result;
    }


    public double getLeftNumber(String leftOperand) {
		double leftNumber;
		try {
			leftNumber = Double.parseDouble(leftOperand);
			LOG.info("Left Operand [{}]", leftNumber);
		} catch (NumberFormatException ex) {
			leftNumber = 0.0;
			LOG.error("Left Operand Exception [{}]", ex);
		}
		return leftNumber;

	}

	public double getrightNumber(String rightOperand) {
		double rightNumber;
		try {
			rightNumber = Double.parseDouble(rightOperand);
			LOG.info("right Operand [{}]", rightNumber);
		} catch (NumberFormatException ex) {
			rightNumber = 0.0;
			LOG.error("right Operand Exception [{}]", ex);
		}
		return rightNumber;
	}
}
