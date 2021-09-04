package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prot.apitool.openapi.spec.model.v30.sedes.AnyOrExpressionObjectDeserializer;
import com.prot.apitool.openapi.spec.model.v30.sedes.AnyOrExpressionObjectSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonSerialize(using = AnyOrExpressionObjectSerializer.class)
@JsonDeserialize(using = AnyOrExpressionObjectDeserializer.class)
@Data
public class AnyOrExpressionObject implements SpecModel {
    private AnyObject any;
    private String    expression;

    public static AnyOrExpressionObject any(@NotNull Object value) {
        AnyOrExpressionObject ret = new AnyOrExpressionObject();
        if (value instanceof AnyObject) {
            ret.any = (AnyObject) value;
        } else {
            ret.any = new AnyObject(value);
        }
        return ret;
    }

    public static AnyOrExpressionObject expression(@NotNull String expression) {
        if (!isExpression(expression)) {
            throw new IllegalArgumentException(String.format("expecting an expression, but '%s' is not", expression));
        }
        AnyOrExpressionObject ret = new AnyOrExpressionObject();
        ret.expression = expression;
        return ret;
    }

    public void setAny(AnyObject any) {
        this.any = any;
        this.expression = null;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        this.any = null;
    }

    @JsonIgnore
    public boolean isExpression() {
        return expression != null;
    }

    public static boolean isExpression(Object value) {
        return value instanceof String && ((String)value).trim().startsWith("$");
    }
}
