package agent.transformer;

import java.io.IOException;
import java.lang.reflect.Modifier;

import org.junit.Test;

import agent.transformer.config.ClassDef;
import agent.transformer.config.FieldDef;
import agent.transformer.config.TransformDef;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestTransformResource {

	@Test
	public void testTransformResource() {
		try {
			TransformDef readValue = TransformDef.getInstance().readConfig();
			System.out.println(readValue.toString());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMock() {
		ClassDef classDef = new ClassDef();
		classDef.setClassForName("test.Center2");
		classDef.setDefaultConstructor(true);
		FieldDef e = new FieldDef("name", String.class.getName(), Modifier.PRIVATE);
		FieldDef e2 = new FieldDef("adress", String.class.getName(), Modifier.PRIVATE);
		classDef.getFieldDefs().add(e);
		classDef.getFieldDefs().add(e2);
		TransformDef.getInstance().getClassDef().add(classDef);
		try {
			TransformDef.getInstance().writeConfig(TransformDef.getInstance());
		} catch (JsonGenerationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
