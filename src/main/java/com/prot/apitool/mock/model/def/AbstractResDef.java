package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractResDef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "STATUS_CODE", nullable = false)
    private int statusCode = 200;
    @Column(name = "STATUS_DESC", length = 64)
    private String statusDescription;
    @Column(name = "HEADERS")
    @Convert(converter = ObjectNodeJPAConverter.class)
    private ObjectNode headers;
    @Column(name = "CONTENT_TYPE", length = 64, nullable = false)
    private String contentType = "application/json";
    @Column(name = "CONTENT_TEMPLATE")
    private String contentTemplate;
}
