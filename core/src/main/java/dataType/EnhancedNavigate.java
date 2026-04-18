package main.java.dataType;

public class EnhancedNavigate extends Statement{

    private static final long serialVersionUID = 1L;

    // action 合法表示仅包括: back、forward、refresh
    public EnhancedNavigate(String action) throws Exception {
        super();
        this.appiumAction = AppiumAction.Navigate;
        if (action.equals("back") || action.equals("forward") || action.equals("refresh")) {
            this.value = action;
        } else {
            throw new Exception("[ERR]\tNavigate Statement malformed");
        }
    }

    public EnhancedNavigate(int line, String action) throws Exception {
        super(line);
        this.appiumAction = AppiumAction.Navigate;
        if (action.equals("back") || action.equals("forward") || action.equals("refresh")) {
            this.value = action;
        } else {
            throw new Exception("[ERR]\tNavigate Statement malformed");
        }
    }

    @Override
    public String toString() {
        return "driver.navigate()." + getValue()+ "()";
    }
}
