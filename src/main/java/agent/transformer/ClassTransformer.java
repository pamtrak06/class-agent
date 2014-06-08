package agent.transformer;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.logging.Logger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewConstructor;
import javassist.NotFoundException;
import agent.transformer.config.ClassDef;
import agent.transformer.config.TransformDef;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ClassTransformer implements ClassFileTransformer {

	private static final Logger	    LOGGER	= Logger.getLogger(ClassTransformer.class.getName());

	private static TransformDef	config	= null;

	static {
		try {
			config = TransformDef.getInstance().readConfig();
		} catch (JsonParseException e) {
			LOGGER.severe("Loading config file for transformer agent, cause: " + e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.severe("Loading config file for transformer agent, cause: " + e.getMessage());
		} catch (IOException e) {
			LOGGER.severe("Loading config file for transformer agent, cause: " + e.getMessage());
		}
	}

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] byteCode = classfileBuffer;

		List<ClassDef> classDefs = config.getClassDef();

		for (ClassDef classDef : classDefs) {

			String classNamePath = classDef.getClassForName().replace('.', '/');
			if (className.equals(classNamePath)) {

				try {
					ClassPool cp = ClassPool.getDefault();
					CtClass cc = cp.get(classDef.getClassForName());

					// inject default no arg constructor if not present
					if (classDef.isDefaultConstructor()) {
						if (!isDefaultConstructorPresent(cc)) {
							CtConstructor defaultConstructor = CtNewConstructor.defaultConstructor(cc);
							cc.addConstructor(defaultConstructor);
						}
					}

					byteCode = cc.toBytecode();
					cc.detach();
				} catch (Exception ex) {
					LOGGER.severe("Instrument classes from config file for transformer agent, cause: "
					        + ex.getMessage());
				}
			}
		}

		return byteCode;
	}

	private boolean isDefaultConstructorPresent(CtClass cc) throws NotFoundException {
		boolean defaultConstructorPresent = false;
		CtConstructor[] constructors = cc.getConstructors();
		for (CtConstructor ctConstructor : constructors) {
			if (ctConstructor.getParameterTypes().length == 0) {
				defaultConstructorPresent = true;
			}
		}
		return defaultConstructorPresent;
	}

}
