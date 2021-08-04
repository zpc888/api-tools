package com.prot.apitool.mock.model.def;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MOCK_SHARED_RES")
public class SharedResDef extends AbstractResDef {
}
