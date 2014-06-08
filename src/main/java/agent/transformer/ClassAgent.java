package agent.transformer;

import java.lang.instrument.Instrumentation;

public final class ClassAgent {

	private ClassAgent() {
	}

	public static void premain(String args, Instrumentation inst) {
		// registers the transformer
		inst.addTransformer(new ClassTransformer());
	}
}
