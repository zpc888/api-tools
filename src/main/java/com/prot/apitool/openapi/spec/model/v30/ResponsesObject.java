package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponsesObject extends PatternedFieldsModelWithExtension<EitherModelOrReferenceObject<ResponseObject>> implements SpecModel {
    // key is HTTP Status Code 200, 2XX (represents [200-299])
    // value is Either Response Object or Reference Object

    // default is fixed field
    public EitherModelOrReferenceObject<ResponseObject> getDefault() {
        return get("default");
    }

    public void setDefault(EitherModelOrReferenceObject<ResponseObject> defaultVal) {
        put("default", defaultVal);
    }
}
