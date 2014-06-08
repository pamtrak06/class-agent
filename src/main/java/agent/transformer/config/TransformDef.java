package agent.transformer.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.io.Resources;
import com.google.gson.Gson;

public final class TransformDef {
    private static final String            EXT_JSON    = ".json";

    private static final ObjectMapper    MAPPER        = new ObjectMapper();

    static {
        // take account only public fields
        MAPPER.setVisibilityChecker(MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.ANY)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.ANY));
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.enable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        MAPPER.enable(MapperFeature.AUTO_DETECT_CREATORS);
    }

    private List<ClassDef>                classDef    = new ArrayList<ClassDef>();

    private static TransformDef            instance    = new TransformDef();

    private TransformDef() {
        super();
    }

    public static TransformDef getInstance() {
        return instance;
    }

    public List<ClassDef> getClassDef() {
        return classDef;
    }

    public void setClassDef(List<ClassDef> classDef) {
        this.classDef = classDef;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void writeConfig(TransformDef transformDef) throws JsonGenerationException, JsonMappingException,
            IOException {
        MAPPER.writeValue(new File(TransformDef.class.getSimpleName() + EXT_JSON), TransformDef.getInstance());
    }

    public TransformDef readConfig() throws JsonParseException, JsonMappingException, IOException {
        URL resource = Resources.getResource(TransformDef.class.getSimpleName() + EXT_JSON);
        return MAPPER.readValue(resource, TransformDef.class);
    }
}
