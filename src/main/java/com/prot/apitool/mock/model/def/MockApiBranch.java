package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@JsonInclude(JsonInclude.Include.NON_NULL)

@Entity
@Table(name = "MOCK_API_BRANCH")
public class MockApiBranch extends AbstractApiResponse implements ISharedApiResponseRef {
    // technical ownerId can be removed here since it is one-to-many unidirectional
    @Column(name = "API_ID")
    private Long ownerId;
    @Column(name = "EVAL_ORDER", nullable = false, columnDefinition = "smallint default 50")
    private int order = 50;
    @Column(name = "CONDITION_EXPR", nullable = false, length = 768)
    private String conditionExpr;
    @OneToOne(targetEntity = SharedApiResponse.class)
    @JoinColumn (name = "SHARED_RES_ID", referencedColumnName = "ID")
    private SharedApiResponse sharedApiResponse;
}
