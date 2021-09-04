package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
// TODO: schema itself should be in its own project
public class SchemaObject extends LinkedHashMap<String, Object> implements SpecModel {
}
