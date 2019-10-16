package com.education.sample.service.sendMessage;

public class ResponseClass {

    public String output;

    public Integer exitCode;

    public String message;

    public ResponseClass() {}

    public ResponseClass(String output, Integer exitCode, String message) {
        this.output = output;
        this.exitCode = exitCode;
        this.message = message;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
