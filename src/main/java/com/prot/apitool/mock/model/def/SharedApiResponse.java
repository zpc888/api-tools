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
@Table(name = "MOCK_SHARED_RES")
public class SharedApiResponse extends AbstractApiResponse {
}
