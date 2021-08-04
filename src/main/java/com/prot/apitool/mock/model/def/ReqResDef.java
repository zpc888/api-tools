package com.prot.apitool.mock.model.def;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "MOCK_REQ_RES")
public class ReqResDef extends AbstractResDef implements ISharedResDefRef {
    @Column(name = "URL_PATTERN", nullable = false)
    private String urlPattern;
    @Column(name = "HTTP_METHOD", nullable = false)
    private String httpMethod;
    @OneToOne(targetEntity = SharedResDef.class)
    @Column(name = "SHARED_RES_DEF_ID")
    private SharedResDef sharedResDef;
    @OneToMany(targetEntity = ConditionalBranchDef.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "REQ_RES_DEF_ID", referencedColumnName = "ID")
    private List<ConditionalBranchDef> conditionalBranchDefs;
}
