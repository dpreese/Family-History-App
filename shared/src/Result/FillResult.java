package Result;

public class FillResult {
    private boolean success;
    private String message; // is this neccessary? I could just update this variable/edit it accordingly

    public FillResult(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
