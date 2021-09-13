package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CallbackObject extends PatternedFieldsModelWithExtension<List<PathItemObject>> implements SpecModel {
    // key is {expression}
    // value is Path Item Object
    // A Path Item Object used to define a callback request and expected responses.
}
