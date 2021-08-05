package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Entity
@Table(name = "MOCK_API")
public class MockApi extends AbstractApiResponse implements ISharedApiResponseRef {
    @Column(name = "URL_PATTERN", nullable = false)
    private String urlPattern;
    @Column(name = "HTTP_METHOD", nullable = false, length = 8)
    private String httpMethod;
    @OneToOne(targetEntity = SharedApiResponse.class)
    @JoinColumn(name = "SHARED_RES_ID", referencedColumnName = "ID")
    private SharedApiResponse sharedApiResponse;
    @OneToMany(targetEntity = MockApiBranch.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "API_ID", referencedColumnName = "ID")       // nullable = true
    private List<MockApiBranch> conditionalBranches;
}
