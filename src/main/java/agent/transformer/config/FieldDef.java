package agent.transformer.config;


public class FieldDef {

    private String    name;
    private String    type;
    private int       modifier;

    public FieldDef() {
        super();
    }

    public FieldDef(String name, String type, int modifier) {
        super();
        this.name = name;
        this.type = type;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

}
