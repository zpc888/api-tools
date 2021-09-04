package com.prot.apitool.openapi.spec.model.v30;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.openapi.spec.model.anno.CommonMarkSyntax;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * it is used in query, header, path or cookie in (name, value) pair
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbstractParameterValueObject extends SpecExtension implements SpecModel {
    @CommonMarkSyntax
    private String description;
    @NotNull
    private Boolean required;               // true when in path, false for query, header and cookie
    private Boolean deprecated;
    private Boolean allowEmptyValue;        // likely to be removed in a later version

    /**
     * <b>Style</b> can be:
     * <ul>
     *     <li>matrix for types: primitive, array, object when it is path parameter. See RFC6570</li>
     *     <li>label for types: primitive, array, object when it is path parameter. See RFC6570</li>
     *     <li>form for types: primitive, array, object when it is query or cookie parameter. See RFC6570 and OpenAPI 2.0</li>
     *     <li>simple for types: array when it is path or header parameter. See RFC6570 and OpenAPI 2.0</li>
     *     <li>spaceDelimited for types: array when it is query parameter. See OpenAPI 2.0</li>
     *     <li>pipeDelimited for types: array when it is query parameter. See OpenAPI 2.0</li>
     *     <li>deepObject for types: object when it is query parameter. See OpenAPI 2.0</li>
     * </ul>
     */
    private String  style;                  // query - form, path - simple, header - simple, cookie - form
    private Boolean explode;                // default is true when style is form, otherwise default is false
    private Boolean allowReserved;

    // for simple scenarios, a schema and style can describe the structure and syntax of the parameter
    private EitherModelOrReferenceObject<SchemaObject> schema;
    private AnyObject example;
    // exclusive between example and examples field, and examples here override the example provided by the schema if provided.
    private Map<String, EitherModelOrReferenceObject<ExampleObject>> examples;

    // for more complex scenarios, the content property cand efine the media type and schema of the parameter
    // it MUST contain either a schema property, or a content property, but not both
    private Map<String, MediaTypeObject> content;       // MUST only contain one entry
}
