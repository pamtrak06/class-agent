package agent.transformer.config;

import java.lang.reflect.Modifier;

public class MethodDef {
	private String	 name;
	private String	 type;
	private Modifier	modifier;
	private String	 code;

	public MethodDef() {
		super();
	}

	public MethodDef(String name, String type, Modifier modifier, String code) {
		super();
		this.name = name;
		this.type = type;
		this.modifier = modifier;
		this.code = code;
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

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
