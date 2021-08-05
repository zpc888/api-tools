package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractApiResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "STATUS_CODE", nullable = false, columnDefinition = "smallint default 200")
    private int statusCode = 200;
    @Column(name = "STATUS_DESC", length = 64)
    private String statusDescription;
    @Column(name = "HEADERS", columnDefinition = "text")
    @Convert(converter = ObjectNodeJPAConverter.class)
    private ObjectNode headers;
    @Column(name = "CONTENT_TYPE", length = 64, nullable = false, columnDefinition = "varchar(64) default 'application/json'")
    private String contentType = "application/json";
    @Column(name = "CONTENT_TEMPLATE", columnDefinition = "longtext")
    private String contentTemplate;
}
