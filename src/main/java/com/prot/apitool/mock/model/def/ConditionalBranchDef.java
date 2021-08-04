package com.prot.apitool.mock.model.def;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "MOCK_COND_BRANCH")
public class ConditionalBranchDef extends AbstractResDef implements ISharedResDefRef {
    @Column(name = "REQ_RES_DEF_ID", nullable = false)
    private Long ownerId;
    @Column(name = "ORDER", nullable = false)
    private int order;
    @Column(name = "CONDITION_EXPR", nullable = false)
    private String branchCondition;
    @OneToOne(targetEntity = SharedResDef.class)
    @Column(name = "SHARED_RES_DEF_ID")
    private SharedResDef sharedResDef;
}
