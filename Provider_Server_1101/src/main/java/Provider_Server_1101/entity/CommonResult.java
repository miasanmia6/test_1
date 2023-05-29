package Provider_Server_1101.entity;

import java.io.Serializable;

public class CommonResult<T> extends User implements Serializable {
    private Integer code;

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private String message;

    private T result;
}
