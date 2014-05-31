package agent.transformer;

import java.lang.instrument.Instrumentation;

public class ClassAgent {

	public static void premain(String args, Instrumentation inst)
			throws Exception {
		// registers the transformer
		inst.addTransformer(new ClassTransformer());
	}
}
