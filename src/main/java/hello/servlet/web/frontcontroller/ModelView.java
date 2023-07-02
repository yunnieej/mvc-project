package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName;
    // model에 put해서 원하는 data 넣으면 그걸 꺼내서 jsp에 보낼 수 있도록..
    private Map <String, Object> model = new HashMap<>();

    public ModelView(String viewName){
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
