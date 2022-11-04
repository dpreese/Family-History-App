package Result;

public class ClearResult {
    private String clearMessage;
    private String message;
    private boolean success;

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getClearMessage() {
        return clearMessage;
    }

    public void setClearMessage(String clearMessage) {
        this.clearMessage = clearMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
