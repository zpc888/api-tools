package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AnyOrExpressionObjectSimpleTest {
    private WrapObject container;

    @BeforeEach
    void setup() {
        container = new WrapObject();
        container.setOperationId("changeAccountNumber");
        container.setParameters(new HashMap<String, AnyOrExpressionObject>());
        container.getParameters().put("userId", AnyOrExpressionObject.expression("$request.path.id"));
        container.getParameters().put("streetNumber", AnyOrExpressionObject.any(88));
        container.getParameters().put("zipcode", AnyOrExpressionObject.any("M3M 8N8"));
        Map<String, Object> yourInfo = new HashMap<>();
        yourInfo.put("firstName", "George");
        yourInfo.put("lastName", "Zhou");
        yourInfo.put("age", 18);
        container.getParameters().put("yourInfo", AnyOrExpressionObject.any(yourInfo));
        container.getParameters().put("nullable", null);
    }

    @Test
    void sedes() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(container);
        System.out.println(json);
        WrapObject container2 = om.readValue(json, WrapObject.class);
        assertEquals(container.getOperationId(), container2.getOperationId());
        assertEquals(container.getParameters().size(), container2.getParameters().size());
        assertEquals(container.getParameters().get("userId"), container2.getParameters().get("userId"));
        assertEquals(container.getParameters().get("yourInfo"), container2.getParameters().get("yourInfo"));
        assertEquals(container.getParameters().get("nullable"), container2.getParameters().get("nullable"));
        assertEquals(container.getParameters().get("streetNumber"), container2.getParameters().get("streetNumber"));
        assertEquals(container.getParameters().get("zipcode"), container2.getParameters().get("zipcode"));
        String json2 = om.writeValueAsString(container2);
        System.out.println(json2);
        assertEquals(json, json2);
    }

    static class WrapObject {
        private String operationId;
        private Map<String, AnyOrExpressionObject> parameters;

        public String getOperationId() {
            return operationId;
        }

        public void setOperationId(String operationId) {
            this.operationId = operationId;
        }

        public Map<String, AnyOrExpressionObject> getParameters() {
            return parameters;
        }

        public void setParameters(Map<String, AnyOrExpressionObject> parameters) {
            this.parameters = parameters;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WrapObject that = (WrapObject) o;
            return operationId.equals(that.operationId) && parameters.equals(that.parameters);
        }

        @Override
        public int hashCode() {
            return Objects.hash(operationId, parameters);
        }
    }

}
