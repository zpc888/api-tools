package com.prot.apitool.mock.model.def;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prot.apitool.mock.dto.UriParsedResult;
import com.prot.apitool.mock.dto.UriParser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.server.PathContainer;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

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

    public Pair<Boolean, PathPattern.PathMatchInfo> matchesRequestMethodAndPath(String httpMethod, PathContainer path) {
        PathPatternParser parser = PathPatternParser.defaultInstance;
        PathPattern pattern = parser.parse(urlPattern);
        if (httpMethod.equalsIgnoreCase(this.httpMethod) && pattern.matches(path)) {
            return Pair.of(Boolean.TRUE, pattern.matchAndExtract(path));
        }
        return Pair.of(Boolean.FALSE, null);
    }

    public UriParsedResult matchRequest(ServerRequest request) {
        return new UriParser(urlPattern).parseUri(request);
    }
}
