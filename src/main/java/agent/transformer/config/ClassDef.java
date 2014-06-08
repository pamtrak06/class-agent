package agent.transformer.config;

import java.util.ArrayList;
import java.util.List;

public class ClassDef {
    private String            classForName;
    private boolean            defaultConstructor;
    private List<MethodDef>    methodDefs    = new ArrayList<MethodDef>();
    private List<FieldDef>    fieldDefs    = new ArrayList<FieldDef>();

    public ClassDef() {
        super();
    }

    public ClassDef(String classForName, boolean defaultConstructor) {
        super();
        this.classForName = classForName;
        this.defaultConstructor = defaultConstructor;
    }

    public String getClassForName() {
        return classForName;
    }

    public void setClassForName(String classForName) {
        this.classForName = classForName;
    }

    public List<MethodDef> getMethodDefs() {
        return methodDefs;
    }

    public void setMethodDefs(List<MethodDef> methodDefs) {
        this.methodDefs = methodDefs;
    }

    public List<FieldDef> getFieldDefs() {
        return fieldDefs;
    }

    public void setFieldDefs(List<FieldDef> fieldDefs) {
        this.fieldDefs = fieldDefs;
    }

    public boolean isDefaultConstructor() {
        return defaultConstructor;
    }

    public void setDefaultConstructor(boolean defaultConstructor) {
        this.defaultConstructor = defaultConstructor;
    }

}
